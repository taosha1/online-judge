package com.tc.service.impl;

import com.tc.dao.UserDao;
import com.tc.domain.User;
import com.tc.service.UserService;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        User byName = userDao.findByName(username);
        if (byName==null){
            return null;
        }else {
            return byName;
        }
    }

    @Override
    public Result registerUser(User user) {
//        userDao.findById();
        User byName = userDao.findByName(user.getUsername());
        if (byName == null){
            userDao.addUser(user);
            return new Result(true,"注册成功");
        }else {
            return new Result(false,"该用户名已经被创建");
        }
    }
}
