package com.Haulmont.TestProjectHaulmont.DTO;

import com.Haulmont.TestProjectHaulmont.model.Credit;
import com.Haulmont.TestProjectHaulmont.model.PaymentSchedule;
import com.Haulmont.TestProjectHaulmont.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreditOfferDTO implements Serializable {
    @JsonProperty("credit_offer_id")
    private String id;

    @NotNull
    @JsonProperty("sum_credit")
    private Float sumCredit;

    private CreditDTO credit;

    @JsonProperty("amount_month")
    private Long amountMonth;

    @NotNull
    private UserDTO user;

    @JsonProperty("payment_schedules")
    private List<PaymentScheduleDTO> paymentSchedules = new ArrayList<>();
}
