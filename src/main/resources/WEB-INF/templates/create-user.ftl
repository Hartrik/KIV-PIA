<#include "layout/layout.ftl">

<#assign page_title="Create User Account"/>

<#macro page_body>
<#assign form_user_details_action="/service/a/user-create"/>
<#include "part/form-user-details.ftl">
</#macro>

<@display_page/>