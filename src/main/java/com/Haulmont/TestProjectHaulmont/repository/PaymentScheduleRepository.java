package com.Haulmont.TestProjectHaulmont.repository;

import com.Haulmont.TestProjectHaulmont.model.Bank;
import com.Haulmont.TestProjectHaulmont.model.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, String> {
}
