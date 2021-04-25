package com.Haulmont.TestProjectHaulmont.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "payment_schedule")
@Data
public class PaymentSchedule implements Serializable {
    @Id
    private String id;

    private LocalDate paymentDate;

    private Float paymentSum;

    private Float bodyCreditSum;

    private Float percentSum;

    @ManyToOne
    @JoinColumn(name = "credit_offer_id", referencedColumnName = "id")
    private CreditOffer creditOffer;

    public PaymentSchedule() {
    }

    public PaymentSchedule(String id) {
        this.id = id;
    }
}
