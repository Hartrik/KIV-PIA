<#include "layout/layout.ftl">

<#assign page_title="Create User"/>

<#macro page_body>
<#assign form_user_details_action="/service/a/create-user"/>
<#include "part/form-user-details.ftl">
<p>Press Submit to create a new user. All of the fields are mandatory.</p>
</#macro>

<@display_page/>