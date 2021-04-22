package com.example.reportsharingplatform.controller;

import com.example.reportsharingplatform.model.Account;
import com.example.reportsharingplatform.service.AccountService;
import com.example.reportsharingplatform.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping
    @ResponseBody
    public Account currentUser(Principal principal) {
        return userDetailsService.findAccountByEmail(principal.getName());
    }

    @GetMapping("list")
    public List<Account> list() {
        return accountService.getList();
    }

    @GetMapping("{id}")
    public Account getAccount(@PathVariable("id") String id) {
        return accountService.getAccount(id);
    }

    @GetMapping("list/role/{authorization}")
    public List<Account> getListByRole(@PathVariable("authorization") String authorization) {
        return accountService.getListByRole(authorization);
    }

    @PostMapping
    public Account save(@RequestBody Account a1) {
        return accountService.save(a1);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        accountService.delete(id);
    }
}
