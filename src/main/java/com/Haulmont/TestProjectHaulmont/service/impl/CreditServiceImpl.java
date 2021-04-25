package com.Haulmont.TestProjectHaulmont.service.impl;

import com.Haulmont.TestProjectHaulmont.DTO.BankDTO;
import com.Haulmont.TestProjectHaulmont.DTO.CreditDTO;
import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.mapper.AllMapper;
import com.Haulmont.TestProjectHaulmont.model.Credit;
import com.Haulmont.TestProjectHaulmont.model.User;
import com.Haulmont.TestProjectHaulmont.repository.CreditRepository;
import com.Haulmont.TestProjectHaulmont.service.BankService;
import com.Haulmont.TestProjectHaulmont.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final AllMapper allMapper;
    private final CreditRepository creditRepository;

    @Override
    public CreditDTO getById(String id) {
        if (creditRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого кредита!");
        }
        return allMapper.map(creditRepository.findById(id).get(), CreditDTO.class);
    }

    @Override
    public CreditDTO save(CreditDTO creditDTO) {
        if (creditDTO != null) {
            creditDTO.setId(UUID.randomUUID().toString());
            Credit credit = creditRepository.save(allMapper.map(creditDTO, Credit.class));
            return allMapper.map(credit, CreditDTO.class);
        }
        return null;
    }

    @Override
    public CreditDTO update(CreditDTO creditDTO) {
        if (creditRepository.findById(creditDTO.getId()).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого кредита!");
        }
        Credit credit = creditRepository.save(allMapper.map(creditDTO, Credit.class));
        return allMapper.map(credit, CreditDTO.class);
    }

    @Override
    public void delete(String id) {
        if (creditRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого кредита!");
        }
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> findAll() {
        return allMapper.mapAsList(creditRepository.findAll(), CreditDTO.class);
    }

    @Override
    public List<CreditDTO> findAllByBankId(String id) {
        return allMapper.mapAsList(creditRepository.findAllByBankId(id), CreditDTO.class);
    }
}
