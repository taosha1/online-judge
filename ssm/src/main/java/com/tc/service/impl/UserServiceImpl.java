package com.tc.service.impl;

import com.alibaba.fastjson.JSON;
import com.tc.dao.UserDao;
import com.tc.domain.User;
import com.tc.service.UserService;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        User byName = userDao.findByName(username);
        if (byName == null) {
            return null;
        } else {
            return byName;
        }
    }

    @Override
    public Result findById(int id) {

        User user = userDao.findById(id);
        if (user == null) {
            return new Result(false, "there is no this user");
        } else {
            if (user.getIcon() == null || user.getIcon().equals("")) {
                user.setPassword(" ");
                user.setIcon("dist/static/user_icon/no_user.jpg");
                user.setPassword("");
            }
            return new Result(true, JSON.toJSONString(user));
        }
    }

    @Override
    public Result registerUser(User user) {
//        userDao.findById();
        User byName = userDao.findByName(user.getUsername());
        if (byName == null) {
            userDao.addUser(user);
            return new Result(true, "注册成功");
        } else {
            return new Result(false, "该用户名已经被创建");
        }
    }

    @Override
    public Result updateUser(User user) {

        int updateUser = userDao.updateUser(user);
        if (updateUser!=0){
            return new Result(true,""+updateUser);
        }else {
            return new Result(false,"更新失败，没有该用户名的对象");
        }
    }

    @Override
    public Result changePassword(String studentId, String old_password, String password) {
        User byStuId = userDao.findByStuId(studentId);
        if (byStuId==null){
            return new Result(false,"用户无记录，修改失败");
        }
        if (!byStuId.getPassword().equals(old_password)){
            return new Result(false,"原密码错误，修改失败");
        }else {
            byStuId.setPassword(password);
            userDao.updateUser(byStuId);
            return new Result(true,"密码修改成功");
        }
    }

    @Override
    public Result findRankByPage(int num) {
        List<User> lists = userDao.findRankLimitOrderByPassNum(num);
        if (lists==null){
            return new Result(false,"无用户信息");
        }else {
            for (User user:lists){
                if (user.getDiscription()==null || user.getDiscription().equals("")){
                    user.setDiscription("还没有留言，快去写写吧");
                }
            }
            return new Result(true,JSON.toJSONString(lists));
        }
    }

    @Override
    public Result findUserCount() {
        int countNum = userDao.findCount();
        if (countNum != 0) {
            return new Result(true, "" + countNum);
        } else {
            return new Result(false, "user数据库为空");
        }
    }

    @Override
    public Result searchRankByPage(int page, int rankNum) {
        List<User> userList = null;
        int startNum = (page - 1) * rankNum;
        userList = userDao.findRankLimitOrderByPassNum2(startNum,rankNum);
        if (userList != null) {
            return new Result(true, JSON.toJSON(userList).toString());
        } else {
            return new Result(false, "没有更多的用户了");
        }
    }
}
