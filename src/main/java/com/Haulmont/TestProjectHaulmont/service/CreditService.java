package com.Haulmont.TestProjectHaulmont.service;


import com.Haulmont.TestProjectHaulmont.DTO.CreditDTO;
import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.base.CRUDService;
import com.Haulmont.TestProjectHaulmont.model.Credit;

import java.util.List;

public interface CreditService extends CRUDService<CreditDTO, String> {
    List<CreditDTO> findAll();
    List<CreditDTO> findAllByBankId(String id);
}
