<form class="form-vertical" action="${form_transaction_action}" method="POST" data-toggle="validator">
  <fieldset>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-row col-md-12">
      <div class="form-group col-md-4">
        <label for="amount">Amount</label>
        <input id="amount" name="amount" class="form-control"
               type="number" required
               value="<#if default??>${default.amount!''}</#if>">
      </div>
      <div class="form-group col-md-3">
        <label for="currency">Currency</label>
        <select class="form-control" id="currency" name="currency">
        <#list currencies as currency>
          <option value="${currency}">${currency}</option>
        </#list>
        </select>
      </div>
    </div>

    <div class="form-row col-md-12">
      <div class="form-group col-md-4">
        <label for="accountNumber">Account Number</label>
        <input id="accountNumber" name="accountNumber" type="text" class="form-control"
               pattern="^([0-9]{10}|[0-9]{16})$" maxlength="16" required
               value="<#if default??>${default.accountNumber!''}</#if>">
      </div>
      <div class="form-group col-md-3">
        <label for="bankCode">Bank code</label>
        <input id="bankCode" name="bankCode" type="text" class="form-control"
               pattern="^([0-9]{4}$" maxlength="4" required
               value="<#if default??>${default.accountCode!''}</#if>">
      </div>
    </div>

    <div class="form-row col-md-12">
      <div class="form-group col-md-4">
        <label for="date">Due Date</label>
        <input id="date" name="date" type="text" class="form-control"
               pattern="^20[0-9]{2}-[01][0-9]-[0123][0-9]$" maxlength="10" required
               value="<#if default??>${default.date!''}</#if>">
      </div>
    </div>

    <div class="form-row col-md-12">
      <div class="form-group col-md-12">
        <label for="description">Note (optional)</label>
        <input id="description" name="description" type="text" class="form-control"
               maxlength="200"
               value="<#if default??>${default.description!''}</#if>">
      </div>
    </div>

    <input type="hidden" id="turingTestQuestionId" name="turingTestQuestionId" value="${turing_test.id}">

    <div class="form-row col-md-12">
      <div class="form-group col-md-12">
        <label for="turingTestAnswer">Anti-Robot Test: ${turing_test.question}</label>
        <input id="turingTestAnswer" name="turingTestAnswer" type="text" class="form-control"
               required>
      </div>
    </div>

    <div class="form-row col-md-12">
      <div class="form-group col-md-12 text-center">
        <button id="apply" class="btn btn-primary">Submit</button>
      </div>
    </div>

  </fieldset>
</form>