<#macro user user>
    <div>
        <input type="text" class="form-control" id="credit_offers_id" name="credit_offers_id"
               value="<#if user?has_content>${user.creditOffer.id}</#if>" hidden>
        <input type="text" class="form-control" id="user_id" name="user_id"
               value="<#if user?has_content>${user.id}</#if>" hidden>
        <input type="text" class="form-control" id="bank_id" name="bank_id"
               value="<#if user?has_content>${user.bankId}</#if>" hidden>
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
</#macro>