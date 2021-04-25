submitCreateNewBank = () => {
    bank.name = document.getElementById("name_bank").value;
    createNewBank(bank);
}

submitCreateNewCredit = (elem) => {
    let data = elem.parentNode.parentNode;
    credit.bank_id = data.querySelector("#bank_id").value;
    credit.loan_limit = data.querySelector("#loan_limit").value;
    credit.interes_rate = data.querySelector("#interes_rate").value;
    if (data.querySelector("#credit_id").value !== "") {
        credit.credit_id = data.querySelector("#credit_id").value;
        updateCreditById(credit.credit_id, credit)
    } else {
        createNewCredit(credit);
    }
}

submitCreateNewCreditOffer = (elem) => {
    let data = elem.parentNode;
    user.name = data.querySelector("#name").value;
    user.email = data.querySelector("#email").value;
    user.phone = data.querySelector("#phone").value;
    user.number_passport = data.querySelector("#number_passport").value;

    getCreditsById(data.querySelector("#credits_id").value).then(credits => {
        creditOffer.credit = JSON.parse(JSON.stringify(credits));

        creditOffer.user = JSON.parse(JSON.stringify(user));
        creditOffer.sum_credit = data.querySelector("#sum_credit").value;
        creditOffer.amount_month = data.querySelector("#amount_month").value;

        createNewCreditOffer(creditOffer);
    });

}

submitUpdateUser = (elem) => {
    let data = elem.parentNode.parentNode;
    let legacyCreditOffer;
    // user.user_id = data.querySelector("#user_id").value;
    user.name = data.querySelector("#name").value;
    user.email = data.querySelector("#email").value;
    user.phone = data.querySelector("#phone").value;
    user.number_passport = data.querySelector("#number_passport").value;
    user.bank_id = data.querySelector("#bank_id").value;
    getCreditOfferById(data.querySelector("#credit_offers_id").value).then(creditOffer => {
        console.log(creditOffer)
        legacyCreditOffer = JSON.parse(JSON.stringify(creditOffer));
        legacyCreditOffer.user = JSON.parse(JSON.stringify(user));
        legacyCreditOffer.amount_month = creditOffer.payment_schedules.length;
        console.log(legacyCreditOffer);
        deleteUserById(elem);
        createNewCreditOffer(legacyCreditOffer);
    });
}

const user = {
    user_id: null,
    name: null,
    email:null,
    phone: null,
    number_passport: null,
    bank_id: null,
    credit_offers: null
}

const creditOffer = {
    credit_offer_id: null,
    sum_credit: null,
    amount_month: null,
    user: {},
    credit: {}
}

const credit = {
    loan_limit: null,
    interes_rate: null,
    bank_id: null,
    credit_id: null
}

const bank = {
    name: null
}

sendRequest = (method, url, body) => {
    const headers = {
        'Content-Type': 'application/json'
    }
    if (body !== null) {
        return fetch(url, {
            method: method,
            body: JSON.stringify(body),
            headers: headers
        });
    } else {
        return fetch(url, {
            method: method,
            headers: headers
        });
    }
}


updateUser = (user_id, user) => {
    sendRequest('PUT', '/api/users/' + user_id, user).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            alert("Что-то пошло не так! )");
            console.log(response);
        }
    });
}

deleteUserById = (elem) => {
    let data = elem.parentNode.parentNode;
    sendRequest('DELETE', '/api/users/' + data.querySelector("#user_id").value).then(response => {
        if (response.ok) {
            document.location.reload(true);
        } else {
            console.log(response);
        }
    });
}


createNewBank = (bank) => {
    sendRequest('POST', '/api/banks', bank).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            alert("Что-то пошло не так! )");
            console.log(response);
        }
    });
}

deleteBankById = (elem) => {
    let data = elem.parentNode;
    sendRequest('DELETE', '/api/banks/' + data.querySelector("#bank_id").value).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            console.log(response);
        }
    });
}


getCreditsByBankId = (bankId) => {
    return sendRequest('GET', '/api/credits/bank/' + bankId).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            console.log(response);
        }
    });
}

getCreditsById = (creditId) => {
    return sendRequest('GET', '/api/credits/' + creditId).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            console.log(response);
        }
    });
}

createNewCredit = (credit) => {
    sendRequest('POST', '/api/credits', credit).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            alert("Что-то пошло не так! )");
            console.log(response);
        }
    });
}

updateCreditById = (credit_id, credit) => {
    sendRequest('PUT', '/api/credits/' + credit_id, credit).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            console.log(response);
        }
    });
}

deleteCreditById = (elem) => {
    let data = elem.parentNode.parentNode;
    sendRequest('DELETE', '/api/credits/' + data.querySelector("#credit_id").value).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            console.log(response);
        }
    });
}

getCreditOfferById = (creditOfferId) => {
    return sendRequest('GET', '/api/credits/offers/' + creditOfferId).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            console.log(response);
        }
    });
}

getCreditOfferPreview = (sumCredit, amountMonth, creditId) => {
    return sendRequest('GET', '/api/credits/offers/preview?sum_credit=' + sumCredit +
        '&month=' + amountMonth +
        '&credit_id=' + creditId).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            console.log(response);
        }
    });
}

createNewCreditOffer = (creditOffer) => {
    sendRequest('POST', '/api/credits/offers', creditOffer).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            alert("Что-то пошло не так! )");
            console.log(response);
        }
    });
}

updateNewCreditOffer = (creditOffer_id, creditOffer) => {
    sendRequest('PUT', '/api/credits/offers/' + creditOffer_id, creditOffer).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            alert("Что-то пошло не так! )");
            console.log(response);
        }
    });
}

deleteCreditOfferById = (elem) => {
    let data = elem.parentNode.parentNode;
    sendRequest('DELETE', '/api/credits/offers/' + data.querySelector("#credit_id").value).then(response => {
        if (response.ok) {
            console.log(response);
            document.location.reload(true);
        } else {
            console.log(response);
        }
    });
}


$(document).ready(function(){
    $('#banks_id').change(function(){
        getCreditsByBankId(this.value).then(credits => {
            appendCredits(credits);
        });
    });
});

$(document).ready(function(){
    $('#credits_id').change(function(){
        getCreditsById(this.value).then(credits => {
            document.getElementById("loan_limit").innerHTML = credits.loan_limit + " руб.";
            document.getElementById("interes_rate").innerHTML = credits.interes_rate + "%";
        });
    });
});

function appendCredits(credits) {
    let select = document.getElementById('credits_id');
    select.innerHTML = "";
    let optionNone = `<option value="null"></option>`;
    select.appendChild(document.createRange().createContextualFragment(optionNone));
    for (let i in credits) {
        let option = document.createElement('option');
        option.value = credits[i].credit_id;
        option.innerHTML = "Лимит: " + credits[i].loan_limit + " руб. | " + "Процентная ставка: " + credits[i].interes_rate + "%";
        select.appendChild(option);
    }
}

previewPayment = (elem) => {
    let data = elem.parentNode;
    let sum = 0;
    let overpayment = 0;

    let table = document.getElementById("preview_payment");
    while (table.hasChildNodes()) {
        table.removeChild(table.lastChild);
    }
    getCreditOfferPreview(data.querySelector("#sum_credit").value,
        data.querySelector("#amount_month").value, data.querySelector("#credits_id").value).then(preview => {
        for (let i in preview.payment_schedules) {
            let row = table.insertRow(-1)
            row.insertCell(-1).innerHTML = preview.payment_schedules[i].payment_date;
            row.insertCell(-1).innerHTML = preview.payment_schedules[i].payment_sum;
            row.insertCell(-1).innerHTML = preview.payment_schedules[i].body_credit_sum;
            row.insertCell(-1).innerHTML = preview.payment_schedules[i].percent_sum;
            sum += (Number(preview.payment_schedules[i].body_credit_sum) + Number(preview.payment_schedules[i].percent_sum));
            overpayment += Number(preview.payment_schedules[i].percent_sum);
        }
        document.getElementById("sum").innerHTML = sum + " руб.";
        document.getElementById("overpayment").innerHTML = overpayment + " руб.";
    });


}