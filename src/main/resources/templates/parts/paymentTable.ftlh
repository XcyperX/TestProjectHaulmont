<#macro payment payments>
    <table class="table table-bordered mb-0">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Дата платежа</th>
            <th scope="col">Сумма платежа</th>
            <th scope="col">Тело кредита</th>
            <th scope="col">Проценты</th>
        </tr>
        </thead>
        <tbody>
        <#list payments as payment>
            <tr>
                <td>${payment.paymentDate}</td>
                <td>${payment.paymentSum}</td>
                <td>${payment.bodyCreditSum}</td>
                <td>${payment.percentSum}</td>
            </tr>
        </#list>
    </table>
    <dl class="row">
        <dt class="col-auto">Итоговая сумма:</dt>
        <dd class="col-auto">
            <#local sum = 0>
            <#list payments as payment>
                <#local sum += (payment.bodyCreditSum?float + payment.percentSum?float)>
            </#list>
            ${sum} руб.
        </dd>
    </dl>
    <dl class="row">
        <dt class="col-auto">Переплата:</dt>
        <dd class="col-auto">
            <#local sum_p = 0>
            <#list payments as payment>
                <#local sum_p += payment.percentSum?float>
            </#list>
            ${sum_p} руб.
        </dd>
    </dl>
</#macro>