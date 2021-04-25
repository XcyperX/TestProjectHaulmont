package com.Haulmont.TestProjectHaulmont.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "credits_offer")
@Data
public class CreditOffer implements Serializable {
    @Id
    private String id;

    @Column(nullable = false)
    private Float sumCredit;

    @ManyToOne
    @JoinColumn(name = "credit_id", referencedColumnName = "id")
    private Credit credit;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentSchedule> paymentSchedules = new ArrayList<>();

    public CreditOffer() {
    }

    public CreditOffer(String id) {
        this.id = id;
    }
}
