package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.model.Account;
import com.example.reportsharingplatform.model.Log;
import com.example.reportsharingplatform.repository.AccountRepository;
import com.example.reportsharingplatform.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private LogService logService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account save(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        Log log = logService.tempLog(account);
        if (accountRepository.findAccountById(account.getId()) != null)
            log.setType(Log.Type.accountUpdate);
        else
            log.setType(Log.Type.accountCreate);
        logRepository.save(log);
        return accountRepository.save(account);
    }

    public List<Account> getList() {
        return accountRepository.findAll();
    }

    public Account getAccount(String accountId) {
        Account account= accountRepository.findAccountById(accountId);
        Log log = logService.tempLog(account);
                log.setType(Log.Type.accountRead);
        logRepository.save(log);
        return account;
    }

    public List<Account> getListByRole(String authorization) {
        return accountRepository.findAccountsByRoleAuthorization(authorization);

    }

    public void delete(String accountId) {
        Account account = accountRepository.findAccountById(accountId);
        Log log = logService.tempLog(account);
        log.setType(Log.Type.accountDelete);
        logRepository.save(log);
        accountRepository.delete(account);
    }


}
