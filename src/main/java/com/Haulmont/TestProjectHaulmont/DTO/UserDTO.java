package com.Haulmont.TestProjectHaulmont.DTO;

import com.Haulmont.TestProjectHaulmont.model.CreditOffer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    @JsonProperty("user_id")
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    @JsonProperty("number_passport")
    private String numberPassport;

    @JsonProperty("credit_offers")
    private CreditOfferDTO creditOffer;

    @JsonProperty("bank_id")
    private String bankId;
}
