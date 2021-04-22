package com.example.reportsharingplatform.repository;

import com.example.reportsharingplatform.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findAccountByEmail(String email);

    Account findAccountById(String id);

    List<Account> findAccountsByRoleAuthorization(String authorization);
}
