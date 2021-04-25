package com.Haulmont.TestProjectHaulmont.repository;

import com.Haulmont.TestProjectHaulmont.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByBankId(String id);
}
