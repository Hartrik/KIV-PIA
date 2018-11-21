<#include "layout/layout.ftl">

<#assign page_title="Accounts Overview"/>

<#macro page_body>

<h2>Accounts</h2>
<#if accounts?has_content>
<table class="table">
  <thead>
  <tr>
    <th scope="col">Account Number</th>
    <th scope="col">Currency</th>
    <th scope="col">Balance</th>
  </tr>
  </thead>
  <tbody>
  <#list accounts as account>
  <tr>
    <td>${account.accountNumber}</td>
    <td>${account.currency}</td>
    <td>${account.balance}</td>
  </tr>
  </#list>
  </tbody>
</table>
<#else>
<div class="alert alert-primary" role="alert">
  No accounts.
</div>
</#if>

<h2>Create Account</h2>
<#assign form_create_account_action="/ib/a/create-account"/>
<#include "part/form-create-account.ftl">

</#macro>

<@display_page/>