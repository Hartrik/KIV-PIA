<#include "layout/layout.ftl">

<#assign page_title="User Management"/>

<#macro page_body>
<#include "part/dialog-confirm-delete.ftl">

<h2>Customers</h2>
<div class="table-responsive">
  <table class="table table-striped">
    <tr><th>#</th><th>Name</th><th>E-mail</th><th>Accounts</th><th></th></tr>
    <#list users as u>
    <tr>
      <td class="col-content">${u.id}</td>
      <td class="col-content">${u.firstName} ${u.lastName}</td>
      <td class="col-content"><a href="mailto:${u.email}">${u.email}</td>
      <td class="col-content">
        <#list u.accounts as account>
        ${account.accountNumberFull}<br>
        <#else>
        --
        </#list>
      </td>
      <td class="col-action">
        <a href="#" class="btn btn-danger btn-xs"
           data-href="/service/a/remove-user?id=${u.id}"
           data-toggle="modal" data-target="#dialog-confirm-delete">Remove</a>
      </td>
    </tr>
    </#list>
  </table>
</div>

<h2>Administrators</h2>
<div class="table-responsive">
  <table class="table table-striped">
    <tr><th>#</th><th>Name</th><th>E-mail</th></tr>
    <#list admins as u>
    <tr>
      <td class="col-content">${u.id}</td>
      <td class="col-content">${u.firstName} ${u.lastName}</td>
      <td class="col-content"><a href="mailto:${u.email}">${u.email}</td>
    </tr>
    </#list>
  </table>
</div>
</#macro>

<@display_page/>