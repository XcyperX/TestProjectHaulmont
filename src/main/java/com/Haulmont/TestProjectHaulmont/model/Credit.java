package com.Haulmont.TestProjectHaulmont.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "credits")
@Data
public class Credit implements Serializable {
    @Id
    private String id;

    @Column(nullable = false)
    private Float loanLimit;

    @Column(nullable = false)
    private Float interesRate;

    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;

    @OneToMany(mappedBy = "credit", orphanRemoval = true)
    private List<CreditOffer> creditOfferList = new ArrayList<>();

    public Credit() {
    }

    public Credit(String id) {
        this.id = id;
    }
}
