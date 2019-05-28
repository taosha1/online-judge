package com.tc.dao;

import com.tc.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountDao {

    @Select("select * from account2")
    public List<Account> findAll();

    @Insert("insert into account2 (name,money) values(#{name},#{money})")
    public void saveAccount(Account account);
}
