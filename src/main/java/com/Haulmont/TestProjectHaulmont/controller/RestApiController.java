package com.Haulmont.TestProjectHaulmont.controller;

import com.Haulmont.TestProjectHaulmont.DTO.BankDTO;
import com.Haulmont.TestProjectHaulmont.DTO.CreditDTO;
import com.Haulmont.TestProjectHaulmont.DTO.CreditOfferDTO;
import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.service.BankService;
import com.Haulmont.TestProjectHaulmont.service.CreditOfferService;
import com.Haulmont.TestProjectHaulmont.service.CreditService;
import com.Haulmont.TestProjectHaulmont.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestApiController {
    private final UserService userService;
    private final BankService bankService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;

//    Запросы пользователей
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
//        userDTO.setId(id);
        return userService.update(userDTO);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
    }

    //Запросы банков
    @GetMapping("/banks")
    public ResponseEntity<?> getBanks() {
        return ResponseEntity.ok(bankService.findAll());
    }

    @GetMapping("/banks/{id}")
    public ResponseEntity<?> getBanksById(@PathVariable("id") String id) {
        return ResponseEntity.ok(bankService.getById(id));
    }

    @PostMapping("/banks")
    public BankDTO createBank(@RequestBody @Valid BankDTO bankDTO) {
        return bankService.save(bankDTO);
    }

    @PutMapping("/banks/{id}")
    public BankDTO updateBank(@PathVariable("id") String id, @RequestBody BankDTO bankDTO) {
        bankDTO.setId(id);
        return bankService.update(bankDTO);
    }

    @DeleteMapping("/banks/{id}")
    public void deleteBank(@PathVariable("id") String id) {
        bankService.delete(id);
    }

//    Запросы кредитов
    @GetMapping("/credits")
    public ResponseEntity<?> getCredits() {
    return ResponseEntity.ok(creditService.findAll());
}

    @GetMapping("/credits/{id}")
    public ResponseEntity<?> getCreditsById(@PathVariable("id") String id) {
        return ResponseEntity.ok(creditService.getById(id));
    }

    @GetMapping("/credits/bank/{id}")
    public ResponseEntity<?> getCreditsByBankId(@PathVariable("id") String id) {
        return ResponseEntity.ok(creditService.findAllByBankId(id));
    }

    @PostMapping("/credits")
    public CreditDTO createCredit(@RequestBody @Valid CreditDTO creditDTO) {
        return creditService.save(creditDTO);
    }

    @PutMapping("/credits/{id}")
    public CreditDTO updateCredit(@PathVariable("id") String id, @RequestBody CreditDTO creditDTO) {
        creditDTO.setId(id);
        return creditService.update(creditDTO);
    }

    @DeleteMapping("/credits/{id}")
    public void deleteCredit(@PathVariable("id") String id) {
        creditService.delete(id);
    }

//    Запросы кредитных предложений
    @GetMapping("/credits/offers")
    public ResponseEntity<?> getCreditOffers() {
        return ResponseEntity.ok(creditOfferService.findAll());
    }

    @GetMapping("/credits/offers/{id}")
    public ResponseEntity<?> getCreditOffers(@PathVariable("id") String id) {
        return ResponseEntity.ok(creditOfferService.getById(id));
    }

    @GetMapping("/credits/offers/preview")
    public ResponseEntity<?> getPreview(@RequestParam(name = "sum_credit") Float sumCredit,
                                        @RequestParam(name = "month") Long amountMonth,
                                        @RequestParam(name = "credit_id") String creditId) {
        return ResponseEntity.ok(creditOfferService.getPreview(sumCredit, amountMonth, creditId));
    }

    @PostMapping("/credits/offers")
    public CreditOfferDTO createCreditOffer(@RequestBody @Valid CreditOfferDTO creditOfferDTO) {
        return creditOfferService.save(creditOfferDTO);
    }

    @PutMapping("/credits/offers/{id}")
    public CreditOfferDTO updateCreditOffer(@PathVariable("id") String id, @RequestBody CreditOfferDTO creditOfferDTO) {
        creditOfferDTO.setId(id);
        return creditOfferService.update(creditOfferDTO);
    }

    @DeleteMapping("/credits/offers/{id}")
    public void deleteCreditOffer(@PathVariable("id") String id) {
        creditOfferService.delete(id);
    }
}
