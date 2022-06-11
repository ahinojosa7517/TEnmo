package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountRepository;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@RestController
@RequestMapping("/account")
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountRepository accountRepo;
    private JdbcUserDao userDao;

    public AccountController(AccountRepository accountRepo, JdbcUserDao userDao) {
        this.accountRepo = accountRepo;
        this.userDao = userDao;
    }

    public AccountController() {
    }


    @GetMapping
    public Account getAccount(Principal principal) {
        int id = userDao.findIdByUsername(principal.getName());
        Optional<Account> opt = accountRepo.findById(id);
        return opt.orElse(new Account());
    }
}