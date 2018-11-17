<#macro page_body></#macro>

<#macro display_page>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${page_title}</title>

    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/main.css" />

    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- GitHub ribbon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-fork-ribbon-css/0.2.1/gh-fork-ribbon.min.css" />
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-fork-ribbon-css/0.2.1/gh-fork-ribbon.ie.min.css" />
    <![endif]-->
    <style>
      .github-fork-ribbon:before {
        background-color: #090;
      }
    </style>

  </head>

  <body>
    <a class="github-fork-ribbon" href="https://github.com/Hartrik/KIV-PIA" title="Fork me on GitHub">Fork me on GitHub</a>

    <#include "layout-nav.ftl">

    <!-- page content -->
    <div class="container article">

      <div class="row">
        <div class="col-md-8">
          <article>
            <#if error??>
            <div class="alert alert-danger error-output">${error}</div>
            </#if>

            <h1 class="page-header">${page_title}</h1>
            <@page_body/>
          </article>
        </div>

        <div class="col-md-4 sidebar">
          <#if !user??>
          <div class="well">
            <h4>Log In</h4>
            <#include "../part/form-login.ftl">
          </div>
          <#elseif user.role == 'ADMIN'>
          <div class="well">
            <h4>Administrator <small style="white-space: nowrap">(${user.firstName} ${user.lastName})</small></h4>
            <div class="list-group">
              <a href="/service/user-management" class="list-group-item">User Management</a>
              <a href="/service/create-user" class="list-group-item">Create User</a>
              <a href="/user/logout" class="list-group-item list-group-item-warning">Log Out</a>
            </div>
          </div>
          <#elseif user.role == 'CUSTOMER'>
          <div class="well">
            <h4>Logged <small style="white-space: nowrap">(${user.firstName} ${user.lastName})</small></h4>
            <div class="list-group">
              <a href="/ib/account" class="list-group-item">Account Details</a>
              <a href="/user/logout" class="list-group-item list-group-item-warning">Log Out</a>
            </div>
          </div>
          </#if>
        </div>
      </div>
    </div>

    <#include "layout-footer.ftl">

  </body>
</html>
</#macro>