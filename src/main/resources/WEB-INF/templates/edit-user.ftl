<#include "layout/layout.ftl">

<#assign page_title="Contact Information"/>

<#macro page_body>
<#assign form_user_details_action="/ib/a/edit-user"/>
<#include "part/form-user-details.ftl">
<p>Press Submit to save. All of the fields are mandatory.</p>
</#macro>

<@display_page/>