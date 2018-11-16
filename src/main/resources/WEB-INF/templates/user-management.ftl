<#include "layout.ftl">

<#assign page_title="User Management"/>

<#macro page_body>
<h2>Customers</h2>
<div class="table-responsive">
  <table class="table table-bordered table-condensed table-striped table-hover">
    <tr><th>Id</th><th>Name</th><th>E-mail</th><th>Actions</th></tr>
    <#list users as u>
    <tr>
      <td class="col-content">${u.id}</td>
      <td class="col-content">${u.firstName} ${u.secondName}</td>
      <td class="col-content"><a href="mailto:${u.email}">${u.email}</td>
      <td class="col-action">
        <a href="/service/user-management?id=${u.id}"
           class="btn btn-default btn-xs">Show</a>
        <a href="#" class="btn btn-danger btn-xs"
           data-href="/service/a/remove-user?id=${u.id}"
           data-toggle="modal" data-target="#confirm-delete">Remove</a>
      </td>
    </tr>
    </#list>
  </table>
</div>

<h2>Administrators</h2>
<div class="table-responsive">
  <table class="table table-bordered table-condensed table-striped table-hover">
    <tr><th>Id</th><th>Name</th><th>E-mail</th></tr>
    <#list admins as u>
    <tr>
      <td class="col-content">${u.id}</td>
      <td class="col-content">${u.firstName} ${u.secondName}</td>
      <td class="col-content"><a href="mailto:${u.email}">${u.email}</td>
    </tr>
    </#list>
  </table>
</div>
</#macro>

<@display_page/>