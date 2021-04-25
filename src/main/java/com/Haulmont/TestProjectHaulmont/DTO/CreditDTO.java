package com.Haulmont.TestProjectHaulmont.DTO;

import com.Haulmont.TestProjectHaulmont.model.Bank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreditDTO implements Serializable {
    @JsonProperty("credit_id")
    private String id;

    @NotNull
    @JsonProperty("loan_limit")
    private Float loanLimit;

    @NotNull
    @JsonProperty("interes_rate")
    private Float interesRate;

    @JsonProperty("bank_id")
    private String bankId;
}
