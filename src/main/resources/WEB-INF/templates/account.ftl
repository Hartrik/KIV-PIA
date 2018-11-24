<#include "layout/layout.ftl">

<#assign page_title="Account"/>

<#macro page_body>

<dl class="row">
  <dt class="col-sm-3">Account Number</dt>
  <dd class="col-sm-9">${account.accountNumberFull}</dd>

  <dt class="col-sm-3">Card Number</dt>
  <dd class="col-sm-9">${account.cardNumber}</dd>

  <dt class="col-sm-3">Balance</dt>
  <dd class="col-sm-9">${account.balance} ${account.currency}</dd>
</dl>

<div class="table-responsive">
  <table class="table table-striped">
    <thead>
    <tr>
      <th scope="col">Date</th>
      <th scope="col">Amount</th>
      <th scope="col">Account</th>
      <th scope="col">Note</th>
      <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
      <#list transactions as transaction>
      <tr>
        <td>${transaction.dateAsIso8601}</td>
        <td>
          <#if transaction.senderAccountNumber == account.accountNumberFull>
          <span class="amount-dec">${transaction.amountSent}</span>
          <#else>
          <span class="amount-inc">${transaction.amountReceived}</span>
          </#if>
        </td>
        <td>
          <#if transaction.senderAccountNumber == account.accountNumberFull>
          ${transaction.receiverAccountNumber}
          <#else>
          ${transaction.senderAccountNumber}
          </#if>
        </td>
        <td>${transaction.description!''}</td>
        <td></td>
      </tr>
      </#list>
    </tbody>
  </table>
</div>

</#macro>

<@display_page/>