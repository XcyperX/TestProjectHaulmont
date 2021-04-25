package com.Haulmont.TestProjectHaulmont.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PaymentScheduleDTO implements Serializable {
    @JsonProperty("payment_schedules_id")
    private String id;

    @JsonProperty("payment_date")
    private LocalDate paymentDate;

    @JsonProperty("payment_sum")
    private Float paymentSum;

    @JsonProperty("body_credit_sum")
    private Float bodyCreditSum;

    @JsonProperty("percent_sum")
    private Float percentSum;

    @JsonProperty("credit_offer_id")
    private String creditOfferId;
}
