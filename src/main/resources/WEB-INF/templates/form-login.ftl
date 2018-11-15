<form class="form-horizontal" action="/user/login" method="POST">
  <div class="form-group">
    <label class="control-label col-sm-3" for="login-id">Name</label>
    <div class="col-sm-9">
      <input type="text" id="login-id" name="login" required=""
             class="form-control" placeholder="Enter login" maxlength="30">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-3" for="pass">Password</label>
    <div class="col-sm-9">
      <input type="password" id="pass" name="pass" required=""
             class="form-control" placeholder="Enter password" maxlength="30">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <button type="submit" class="btn btn-default">Send</button>
      <a href="/user/register" style="margin-left: 10px">Register</a>
    </div>
  </div>
</form>