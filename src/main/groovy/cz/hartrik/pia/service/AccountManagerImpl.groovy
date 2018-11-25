package cz.hartrik.pia.service

import cz.hartrik.pia.ObjectNotFoundException
import cz.hartrik.pia.WrongInputException
import cz.hartrik.pia.dao.AccountDao
import cz.hartrik.pia.dao.TransactionDao
import cz.hartrik.pia.dto.Account
import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.dto.Transaction
import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service

import javax.transaction.Transactional
import java.time.ZonedDateTime

/**
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
@Transactional
@Service
class AccountManagerImpl implements AccountManager {

    @Autowired
    AccountDao accountDao

    @Autowired
    TransactionDao transactionDao

    @Override
    AccountManager.AuthorizedAccountManager authorize(User user) {
        new AuthorizedAccountManagerImpl(user)
    }

    class AuthorizedAccountManagerImpl implements AccountManager.AuthorizedAccountManager {

        private final User currentUser

        AuthorizedAccountManagerImpl(User currentUser) {
            this.currentUser = currentUser
        }

        @Override
        Account createAccount(Currency currency, User user) {
            if (user.role == User.ROLE_ADMIN) {
                throw new AccessDeniedException("Admin cannot have an account")
            }
            if (currentUser.role != User.ROLE_ADMIN && user.id != currentUser.id) {
                throw new AccessDeniedException("Cannot create account for other user")
            }

            final def dummyNumber = '0'

            def account = new Account(owner: user, currency: currency, balance: BigDecimal.ZERO,
                    accountNumber: dummyNumber, cardNumber: dummyNumber)

            account = accountDao.save(account)

            account.accountNumber = String.format('6432%06d', account.id)
            account.cardNumber = String.format('1282560512%06d', account.id)

            return accountDao.save(account)
        }

        @Override
        Account retrieveAccount(int id) {
            def account = accountDao.findById(id)
                    .orElseThrow { new ObjectNotFoundException('Account not found') }

            if (account.owner.id != currentUser.id) {
                throw new AccessDeniedException("Cannot show other user's account")
            }

            return account
        }

        @Override
        Transaction performTransaction(Account sender, Account receiver, BigDecimal amount,
                                       ZonedDateTime date, String description) {

            def transaction = new Transaction(
                    date: date,
                    description: description,
                    sender: sender,
                    senderAccountNumber: sender.getAccountNumberFull(),
                    amountSent: amount,
                    receiver: receiver,
                    receiverAccountNumber: receiver.getAccountNumberFull(),
                    amountReceived: amount // TODO: conversion
            )

            validate(transaction)
            return perform(transaction, sender, receiver)
        }

        @Override
        Transaction performTransaction(String sender, Account receiver, BigDecimal amount,
                                       ZonedDateTime date, String description) {

            def transaction = new Transaction(
                    date: date,
                    description: description,
                    sender: null,
                    senderAccountNumber: sender,
                    amountSent: amount,
                    receiver: receiver,
                    receiverAccountNumber: receiver.getAccountNumberFull(),
                    amountReceived: amount
            )

            validate(transaction)
            return perform(transaction, null, receiver)
        }

        @Override
        Transaction performTransaction(Account sender, String receiver, BigDecimal amount,
                                       ZonedDateTime date, String description) {

            def transaction = new Transaction(
                    date: date,
                    description: description,
                    sender: sender,
                    senderAccountNumber: sender.getAccountNumberFull(),
                    amountSent: amount,
                    receiver: null,
                    receiverAccountNumber: receiver,
                    amountReceived: amount
            )

            validate(transaction)
            return perform(transaction, sender, null)
        }

        private void validate(Transaction transaction) {
            if (transaction.sender == null && transaction.receiver == null)
                throw new WrongInputException('Both sender and receiver cannot be null')

            if (transaction.sender == transaction.receiver)
                throw new WrongInputException('Sender and receiver cannot be one person')

            if (transaction.amountReceived == 0 || transaction.amountSent == 0)
                throw new WrongInputException('Amount cannot be zero')

            if (transaction.amountReceived < 0 || transaction.amountSent < 0)
                throw new WrongInputException('Amount cannot be negative')

            if (transaction.sender && transaction.sender.balance < transaction.amountSent)
                throw new WrongInputException('Sender does not have enough money')
        }

        private Transaction perform(Transaction transaction, Account sender, Account receiver) {
            // save transaction
            transaction = transactionDao.save(transaction)

            // update accounts balance
            if (sender) {
                sender.balance -= transaction.amountSent
                accountDao.save(sender)
            }
            if (receiver) {
                receiver.balance += transaction.amountReceived
                accountDao.save(receiver)
            }

            return transaction
        }

    }

}
