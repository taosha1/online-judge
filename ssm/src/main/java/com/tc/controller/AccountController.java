package com.tc.controller;

import com.tc.domain.Account;
import com.tc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/find")
    public String findAll(Model model) {
        List<Account> all = accountService.findAll();
        System.out.println("controller ");
        model.addAttribute("list", all);
        return "list";
    }

    @RequestMapping("/save")
    public String save(Account account){
        accountService.saveAccount(account);
        return "save";
    }
}
