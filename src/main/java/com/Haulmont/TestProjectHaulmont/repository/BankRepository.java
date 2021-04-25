package com.Haulmont.TestProjectHaulmont.repository;

import com.Haulmont.TestProjectHaulmont.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, String> {
}
