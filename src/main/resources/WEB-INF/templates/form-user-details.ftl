<form class="form-horizontal" action="${form_user_details_action}" method="POST">
  <fieldset>

    <div class="form-group">
      <label class="col-md-4 control-label" for="firstName">First name</label>
      <div class="col-md-4">
        <input id="firstName" name="firstName" type="text" placeholder=""
               class="form-control input-md" required="">
      </div>
    </div>

    <div class="form-group">
      <label class="col-md-4 control-label" for="lastName">Last name</label>
      <div class="col-md-4">
        <input id="lastName" name="lastName" type="text" placeholder=""
               class="form-control input-md" required="">
      </div>
    </div>

    <div class="form-group">
      <label class="col-md-4 control-label" for="personalNumber">Personal number</label>
      <div class="col-md-4">
        <input id="personalNumber" name="personalNumber" type="text" placeholder=""
               class="form-control input-md" required="">
      </div>
    </div>

    <div class="form-group">
      <label class="col-md-4 control-label" for="email">E-mail</label>
      <div class="col-md-4">
        <input id="email" name="email" type="email" required="" placeholder=""
               class="form-control input-md">
      </div>
    </div>

    <div class="form-group">
      <label class="col-md-4 control-label" for="apply"></label>
      <div class="col-md-4">
        <button id="apply" class="btn btn-primary">Submit</button>
      </div>
    </div>

  </fieldset>
</form>