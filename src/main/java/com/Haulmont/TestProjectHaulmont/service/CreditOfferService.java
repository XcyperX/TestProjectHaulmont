package com.Haulmont.TestProjectHaulmont.service;


import com.Haulmont.TestProjectHaulmont.DTO.BankDTO;
import com.Haulmont.TestProjectHaulmont.DTO.CreditOfferDTO;
import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.base.CRUDService;

import java.util.List;

public interface CreditOfferService extends CRUDService<CreditOfferDTO, String> {
    List<CreditOfferDTO> findAll();
    CreditOfferDTO getPreview(Float sumCreditUrl, Long amountMonth, String creditId);
}
