package com.Haulmont.TestProjectHaulmont.repository;

import com.Haulmont.TestProjectHaulmont.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, String> {
    List<Credit> findAllByBankId(String id);
}
