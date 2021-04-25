<#macro user user banks>
    <div class="row">
        <div class="col-sm">
            <div class="card mb-3">
                <div class="card-body">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">ФИО</label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="Введите свое имя"
                               value="<#if user?has_content>${user.name}</#if>">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Почта</label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="Введите свою почту"
                               value="<#if user?has_content>${user.email}</#if>">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Телефон</label>
                        <input type="text" class="form-control" id="phone" name="phone"
                               placeholder="Введите свой номер телефона"
                               value="<#if user?has_content>${user.phone}</#if>">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Номер паспорта</label>
                        <input type="text" class="form-control" id="number_passport" name="number_passport"
                               placeholder="Введите свой номер паспорта"
                               value="<#if user?has_content>${user.numberPassport}</#if>">
                    </div>
                </div>
            </div>
            <div class="card mb-3">
                <div class="card-body">
                    <div class="form-row">
                        <div class="form-group col">
                            <label>Выберите банк</label>
                            <select id="banks_id" class="form-control">
                                <option value="null">Выберите банк</option>
                                <#list banks as bank>
                                    <#if user?has_content>
                                        <#if bank.id == user.bankId>
                                            <option value="${bank.id}" selected>${bank.name}</option>
                                        <#else>
                                            <option value="${bank.id}">${bank.name}</option>
                                        </#if>
                                    <#else>
                                        <option value="${bank.id}">${bank.name}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col">
                            <label>Выберите кредит</label>
                            <select id="credits_id" class="form-control">
                                <option value="null">Выберите кредит</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card mb-2">
                <div class="card-body">
                    <dl class="row">
                        <dt class="col-auto">Лимит по кредиту:</dt>
                        <dd class="col-auto" id="loan_limit">0 руб.</dd>
                        <dt class="col-auto">Процентная ставка:</dt>
                        <dd class="col-auto" id="interes_rate">0%</dd>
                    </dl>
                    <input type="text" class="form-control" id="sum_credit" name="sum_credit"
                           placeholder="Введите необходимую сумму"
                           value="<#if user?has_content>${user.numberPassport}</#if>">
                    <hr>
                    <input type="text" class="form-control" id="amount_month" name="amount_month"
                           placeholder="Введите количество месяцев"
                           value="<#if user?has_content>${user.numberPassport}</#if>">
                </div>
            </div>
        </div>
    </div>
</#macro>