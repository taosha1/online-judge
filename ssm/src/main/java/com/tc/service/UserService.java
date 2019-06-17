package com.tc.service;

import com.tc.domain.User;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    User findByUsername(String username);

    Result registerUser(User user);
}
