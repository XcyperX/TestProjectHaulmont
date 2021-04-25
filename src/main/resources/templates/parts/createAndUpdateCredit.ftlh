<#macro createAndUpdateCredit bank credit>
    <div class="container">
        <div class="mb-3">
            <label for="loan_limit" class="form-label">Лимит по кредиту</label>
            <input type="text" class="form-control" id="loan_limit" name="loan_limit"
                   value="<#if credit?has_content>${credit.loanLimit?c}</#if>">
        </div>
        <div class="mb-3">
            <label for="interes_rate" class="form-label">Процентная ставка</label>
            <input type="number" class="form-control" id="interes_rate" name="interes_rate"
                   value="<#if credit?has_content>${credit.interesRate?replace(",", ".")}</#if>">
        </div>
        <input type="text" class="form-control" id="bank_id" name="bank_id" value="${bank.id}" hidden>
        <input type="text" class="form-control" id="credit_id" name="credit_id"
               value="<#if credit?has_content>${credit.id}</#if>" hidden>
    </div>
</#macro>