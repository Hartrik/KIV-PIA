<#include "layout/layout.ftl">

<#assign page_title="Settings"/>

<#macro page_body>
<div class="panel panel-default">
  <div class="panel-heading">Edit User Details</div>
  <div class="panel-body">
    <#assign form_user_details_action="/ib/a/edit-user"/>
    <#include "part/form-user-details.ftl">
    <p>Press Submit to save. All of the fields are mandatory.</p>
  </div>
</div>
</#macro>

<@display_page/>