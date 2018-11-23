<#include "layout/layout.ftl">

<#assign page_title="Accounts Overview"/>

<#macro page_body>

<#if accounts?has_content>
<h2>Accounts</h2>
<div class="table-responsive">
  <table class="table table-striped">
    <thead>
    <tr>
      <th scope="col">Account Number</th>
      <th scope="col">Card Number</th>
      <th scope="col">Balance</th>
      <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <#list accounts as account>
    <tr>
      <td>${account.accountNumberFull}</td>
      <td>${account.cardNumber}</td>
      <td>${account.balance} ${account.currency}</td>
      <td>
        <a href="/ib/account/${account.id}" class="btn btn-primary btn-xs">Show</a>
      </td>
    </tr>
    </#list>
    </tbody>
  </table>
</div>
</#if>

<h2>Create Account</h2>
<#assign form_create_account_action="/ib/a/create-account"/>
<#include "part/form-create-account.ftl">

</#macro>

<@display_page/>