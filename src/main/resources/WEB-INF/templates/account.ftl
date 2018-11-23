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

</#macro>

<@display_page/>