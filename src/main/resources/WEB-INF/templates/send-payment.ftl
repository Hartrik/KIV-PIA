<#include "layout/layout.ftl">

<#assign page_title="Send Payment"/>

<#macro page_body>

<div class="row">
  <div class="col-md-8">
    <dl class="container">
      <dt class="col-md-2">Account Number</dt>
      <dd class="col-md-10">${account.accountNumberFull}</dd>

      <dt class="col-md-2">Card Number</dt>
      <dd class="col-md-10">${account.cardNumber}</dd>

      <dt class="col-md-2">Balance</dt>
      <dd class="col-md-10">${account.balance?string["###,###,##0.00"]} ${account.currency}</dd>
    </dl>
  </div>
  <div class="col-md-4">
    <div class="container send-button-container">
      <a href="/ib/account/${account.id}" class="btn btn-primary btn-sm">Back to Account</a>
    </div>
  </div>
</div>

<#assign form_transaction_action="/ib/account/${account.id}/send/action"/>
<#include "part/form-transaction.ftl">
</#macro>

<@display_page/>