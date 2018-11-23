<form class="form-horizontal" action="/login" method="POST">
  <div class="form-group">
    <label class="control-label col-sm-3" for="login-id">Name</label>
    <div class="col-sm-9">
      <input type="text" id="username" name="username" required=""
             class="form-control" placeholder="Enter login" maxlength="30">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-3" for="pass">Pass</label>
    <div class="col-sm-9">
      <input type="password" id="password" name="password" required=""
             class="form-control" placeholder="Enter password" maxlength="30">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <button type="submit" class="btn btn-default">Send</button>
    </div>
  </div>
</form>