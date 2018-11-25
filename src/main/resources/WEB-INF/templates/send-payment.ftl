<#include "layout/layout.ftl">

<#assign page_title="Account"/>

<#macro page_body>

<div class="panel panel-default">
  <div class="panel-heading">Account Summary</div>
  <div class="panel-body">
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
          <a href="/ib/account/${account.id}" class="btn btn-danger btn-sm">Cancel</a>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="panel panel-default">
  <div class="panel-heading">Send Payment</div>
  <div class="panel-body">
    <#assign form_transaction_action="/ib/account/${account.id}/send/action"/>
    <#include "part/form-transaction.ftl">
  </div>
</div>
</#macro>

<@display_page/>