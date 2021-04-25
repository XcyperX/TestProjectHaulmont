package com.Haulmont.TestProjectHaulmont.service.impl;

import com.Haulmont.TestProjectHaulmont.DTO.BankDTO;
import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.mapper.AllMapper;
import com.Haulmont.TestProjectHaulmont.model.Bank;
import com.Haulmont.TestProjectHaulmont.model.User;
import com.Haulmont.TestProjectHaulmont.repository.BankRepository;
import com.Haulmont.TestProjectHaulmont.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final AllMapper allMapper;
    private final BankRepository bankRepository;

    @Override
    public BankDTO getById(String id) {
        if (bankRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого банка!");
        }
        return allMapper.map(bankRepository.findById(id).get(), BankDTO.class);
    }

    @Override
    public BankDTO save(BankDTO bankDTO) {
        if (bankDTO != null) {
            bankDTO.setId(UUID.randomUUID().toString());
            Bank bank = bankRepository.save(allMapper.map(bankDTO, Bank.class));
            return allMapper.map(bank, BankDTO.class);
        }
        return null;
    }

    @Override
    public BankDTO update(BankDTO bankDTO) {
        if (bankRepository.findById(bankDTO.getId()).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого банка!");
        }
        Bank bank = bankRepository.save(allMapper.map(bankDTO, Bank.class));
        return allMapper.map(bank, BankDTO.class);
    }

    @Override
    public void delete(String id) {
        if (bankRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого банка!");
        }
        bankRepository.deleteById(id);
    }

    @Override
    public List<BankDTO> findAll() {
        return allMapper.mapAsList(bankRepository.findAll(), BankDTO.class);
    }
}
