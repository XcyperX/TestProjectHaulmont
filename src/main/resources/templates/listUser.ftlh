<#import "parts/common.ftlh" as c>
<#import "parts/user.ftlh" as us>
<#import "parts/paymentTable.ftlh" as pt>

<@c.page>
    <table class="table table-bordered mb-0">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Имя</th>
            <th scope="col">Email</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Номер паспорта</th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.numberPassport}</td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            data-target="#update_user_${user.id}">
                        Изменить
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="update_user_${user.id}" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <@us.user user=user/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" onclick="submitUpdateUser(this)">
                                        Обновить
                                    </button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            data-target="#payment_info_${user.id}">
                        Подробности
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="payment_info_${user.id}" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <@pt.payment payments=user.creditOffer.paymentSchedules/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" onclick="deleteUserById(this)">Удалить
                    </button>
                </td>
            </tr>
        </#list>
    </table>
</@c.page>