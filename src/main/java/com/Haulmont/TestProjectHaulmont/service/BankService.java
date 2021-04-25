package com.Haulmont.TestProjectHaulmont.service;


import com.Haulmont.TestProjectHaulmont.DTO.BankDTO;
import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.base.CRUDService;

import java.util.List;

public interface BankService extends CRUDService<BankDTO, String> {
    List<BankDTO> findAll();
}
