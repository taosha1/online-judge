package com.tc.service;

import com.tc.domain.User;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    User findByUsername(String username);

    Result findById(int id);

    Result registerUser(User user);

    Result updateUser(User user);

    Result changePassword(String studentId, String old_password, String password);

    Result findRankByPage(int num);
}
