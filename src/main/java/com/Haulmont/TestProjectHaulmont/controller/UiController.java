package com.Haulmont.TestProjectHaulmont.controller;

import com.Haulmont.TestProjectHaulmont.service.BankService;
import com.Haulmont.TestProjectHaulmont.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UiController {
    private final BankService bankService;
    private final UserService userService;

    @GetMapping("/banks")
    public String listBanks(Model model) {
        model.addAttribute("banks", bankService.findAll());
        return "bankPage";
    }

    @GetMapping("/")
    public String createCreditOffer(Model model) {
        model.addAttribute("banks", bankService.findAll());
        return "mainPage";
    }

    @GetMapping("/bank/{id}/users")
    public String createCreditOffer(@PathVariable("id") String id, Model model) {
        model.addAttribute("users", userService.findAllByBankId(id));
        return "listUser";
    }
}
