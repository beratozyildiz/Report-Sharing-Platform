package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.model.Account;
import com.example.reportsharingplatform.model.Role;
import com.example.reportsharingplatform.repository.AccountRepository;
import com.example.reportsharingplatform.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    public void saveAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        Role userRole = roleRepository.findRoleByAuthorization("EVALUATOR");
        account.setRole(new HashSet<>(Arrays.asList(userRole)));
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String fullMail= email + "@dummytech.com";
        Account account = accountRepository.findAccountByEmail(fullMail);
        if (account != null) {
            List<GrantedAuthority> authorities = getUserAuthority(account.getRole());
            return buildUserForAuthentication(account, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getAuthorization()));
        });
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Account account, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), authorities);
    }
}
