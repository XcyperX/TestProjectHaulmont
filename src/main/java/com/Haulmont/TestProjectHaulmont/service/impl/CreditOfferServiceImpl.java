package com.Haulmont.TestProjectHaulmont.service.impl;

import com.Haulmont.TestProjectHaulmont.DTO.CreditDTO;
import com.Haulmont.TestProjectHaulmont.DTO.CreditOfferDTO;
import com.Haulmont.TestProjectHaulmont.DTO.PaymentScheduleDTO;
import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.mapper.AllMapper;
import com.Haulmont.TestProjectHaulmont.model.Credit;
import com.Haulmont.TestProjectHaulmont.model.CreditOffer;
import com.Haulmont.TestProjectHaulmont.model.PaymentSchedule;
import com.Haulmont.TestProjectHaulmont.model.User;
import com.Haulmont.TestProjectHaulmont.repository.CreditOfferRepository;
import com.Haulmont.TestProjectHaulmont.repository.CreditRepository;
import com.Haulmont.TestProjectHaulmont.service.CreditOfferService;
import com.Haulmont.TestProjectHaulmont.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditOfferServiceImpl implements CreditOfferService {
    private final AllMapper allMapper;
    private final CreditOfferRepository creditOfferRepository;
    private final CreditRepository creditRepository;

    @Override
    public CreditOfferDTO getById(String id) {
        if (creditOfferRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого кредитного предложения!");
        }
        return allMapper.map(creditOfferRepository.findById(id).get(), CreditOfferDTO.class);
    }

    @Override
    public CreditOfferDTO save(CreditOfferDTO creditOfferDTO) {
        if (creditOfferDTO != null) {
            if (creditOfferDTO.getSumCredit() > creditOfferDTO.getCredit().getLoanLimit()) {
                throw new RuntimeException("Ошибка, кредит превышает лимит!!");
            } else {
                creditOfferDTO.setId(UUID.randomUUID().toString());
                CreditOffer creditOffer = creditOfferRepository.save(allMapper.map(creditOfferDTO, CreditOffer.class));
                return allMapper.map(creditOffer, CreditOfferDTO.class);
            }
        }
        return null;
    }

    @Override
    public CreditOfferDTO update(CreditOfferDTO creditOfferDTO) {
        if (creditOfferRepository.findById(creditOfferDTO.getId()).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого кредитного предложения!");
        }
        CreditOffer creditOffer = creditOfferRepository.save(allMapper.map(creditOfferDTO, CreditOffer.class));
        return allMapper.map(creditOffer, CreditOfferDTO.class);
    }

    @Override
    public void delete(String id) {
        if (creditOfferRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого кредитного предложения!");
        }
        creditOfferRepository.deleteById(id);
    }

    @Override
    public List<CreditOfferDTO> findAll() {
        return allMapper.mapAsList(creditOfferRepository.findAll(), CreditOfferDTO.class);
    }

    @Override
    public CreditOfferDTO getPreview(Float sumCreditUrl, Long amountMonth, String creditId) {
        List<PaymentScheduleDTO> paymentSchedules = new ArrayList<>();
        Credit credit = creditRepository.findById(creditId).get();
        CreditOfferDTO creditOfferDTO = new CreditOfferDTO();
        Float sumCredit = sumCreditUrl;
        Float monthlyPayment = sumCreditUrl/amountMonth;
        Float perMonth = (credit.getInteresRate()/100)/12;
        for (int i = 0; i < amountMonth; i++) {
            PaymentScheduleDTO paymentSchedule = new PaymentScheduleDTO();
            paymentSchedule.setPercentSum(sumCredit*perMonth);
            paymentSchedule.setBodyCreditSum(monthlyPayment);
            paymentSchedule.setPaymentSum(monthlyPayment+(sumCredit*perMonth));
            paymentSchedule.setPaymentDate(LocalDate.now().plusMonths(i));
            sumCredit -= monthlyPayment;
            paymentSchedules.add(paymentSchedule);
        }
        creditOfferDTO.setPaymentSchedules(paymentSchedules);
        return creditOfferDTO;
    }
}
