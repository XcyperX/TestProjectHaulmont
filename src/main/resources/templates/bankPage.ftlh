<#import "parts/common.ftlh" as c>
<#import "parts/createAndUpdateCredit.ftlh" as createCredit>

<@c.page>
    <div class="container mb-5">
        <button type="button" class="btn btn-primary btn-block mb-4" data-toggle="modal"
                data-target="#create_bank">
            Добавить банк
        </button>
        <!-- Modal -->
        <div class="modal fade" id="create_bank" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="name_bank" class="form-label">Название банка</label>
                            <input type="text" class="form-control" id="name_bank" name="name_bank">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="submitCreateNewBank()">Создать банк
                        </button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <#list banks as bank>
            <div class="card mb-2">
                <div class="card-body">
                    <h3>${bank.name}</h3>
                    <div class="mb-2">
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#bank_${bank.id}">
                            Добавить кредит
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="bank_${bank.id}" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <@createCredit.createAndUpdateCredit bank=bank credit=""/>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary"
                                                onclick="submitCreateNewCredit(this)">
                                            Создать кредит
                                        </button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                            Закрыть
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a href="/bank/${bank.id}/users" type="button" class="btn btn-primary">Список
                            клиентов
                        </a>
                        <button type="button" class="btn btn-primary pl-3" onclick="deleteBankById(this)">
                            Удалить банк
                        </button>
                    </div>
                    <hr>
                    <div class="row row-cols-1 row-cols-md-3 g-4 mb-3">
                        <#list bank.credits as credit>
                            <div class="col mb-3">
                                <div class="card">
                                    <div class="card-body pl-3 pr-3">
                                        <dl class="row">
                                            <dt class="col-auto">Лимит по кредиту:</dt>
                                            <dd class="col-auto">${credit.loanLimit} руб.</dd>
                                        </dl>
                                        <hr>
                                        <dl class="row">
                                            <dt class="col-auto">Процентная ставка:</dt>
                                            <dd class="col-auto">${credit.interesRate}%</dd>
                                        </dl>
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#update_credit_${credit.id}">
                                            Изменить кредит
                                        </button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="update_credit_${credit.id}" tabindex="-1"
                                             role="dialog"
                                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-body">
                                                        <@createCredit.createAndUpdateCredit bank=bank credit=credit/>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-primary"
                                                                onclick="submitCreateNewCredit(this)">Изменить
                                                            кредит
                                                        </button>
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                            Закрыть
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-primary"
                                                onclick="deleteCreditById(this)">Удалить
                                            кредит
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</@c.page>