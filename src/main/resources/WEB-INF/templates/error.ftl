<#include "template.ftl">

<#assign page_title="JAVA Bank - error"/>

<#macro page_body>
  <div class="alert alert-danger error-output">${message}</div>
</#macro>

<@display_page/>