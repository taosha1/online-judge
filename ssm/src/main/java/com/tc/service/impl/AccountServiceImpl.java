package com.tc.service.impl;

import com.tc.dao.AccountDao;
import com.tc.domain.Account;
import com.tc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        List<Account> all = accountDao.findAll();
        System.out.println("service impl done");
        return all;
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
        System.out.println("service saveAccount");
    }
}
