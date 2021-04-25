<#import "parts/common.ftlh" as c>
<#import "parts/creditOffer.ftlh" as user>

<@c.page>
    <div class="container mb-5">
        <@user.user user="" banks=banks/>
        <button type="button" class="btn btn-primary btn-block mb-2" data-toggle="modal" onclick="previewPayment(this)"
                data-target="#preview">
            Рассчитать
        </button>
        <!-- Modal -->
        <div class="modal fade" id="preview" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-body">
                        <table class="table table-bordered mb-0">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col">Дата платежа</th>
                                <th scope="col">Сумма платежа</th>
                                <th scope="col">Тело кредита</th>
                                <th scope="col">Проценты</th>
                            </tr>
                            </thead>
                            <tbody id="preview_payment">
                            <tr>

                            </tr>
                        </table>
                        <dl class="row">
                            <dt class="col-auto">Итоговая сумма:</dt>
                            <dd class="col-auto" id="sum">
                            </dd>
                        </dl>
                        <dl class="row">
                            <dt class="col-auto">Переплата:</dt>
                            <dd class="col-auto" id="overpayment">
                            </dd>
                        </dl>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <button type="button" class="btn btn-primary btn-block" onclick="submitCreateNewCreditOffer(this)">Оформить
        </button>
    </div>
</@c.page>