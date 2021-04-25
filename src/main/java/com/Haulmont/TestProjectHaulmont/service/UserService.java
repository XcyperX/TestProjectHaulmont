package com.Haulmont.TestProjectHaulmont.service;



import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.base.CRUDService;
import com.Haulmont.TestProjectHaulmont.model.User;

import java.util.List;

public interface UserService extends CRUDService<UserDTO, String> {
    List<UserDTO> findAll();
    List<UserDTO> findAllByBankId(String id);
}
