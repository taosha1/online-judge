package com.tc.controller;

import com.alibaba.fastjson.JSON;
import com.tc.domain.User;
import com.tc.service.UserService;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        System.out.println("login check");
        Object user_id = request.getSession().getAttribute("user_id");
//        System.out.println(user_id.toString());
        if (user_id == null && user_id != request.getSession().getId()) {
            return JSON.toJSONString(new Result(false, "check login fail"));
        } else {
            User user = null;
            String username = "";
            for (Cookie c : request.getCookies()) {
                System.out.println(c.getName() + " " + c.getValue());
                if (c.getName().equals("username")) {
                    username = c.getValue();
                    System.out.println(" String username = c.getValue();" + username);
                }
            }
            user = userService.findByUsername(username);
            if (user.getIcon() == null || user.getIcon().equals("")) {
                user.setIcon("dist/static/user_icon/no_user.jpg");
//                user.setPassword("");
            }
            return JSON.toJSONString(new Result(true, JSON.toJSONString(user)));
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(username + "  " + password);
        User user = userService.findByUsername(username);
        if (user == null) {
            return JSON.toJSONString(new Result(false, "用户名输入错误！"));
        } else if (!password.equals(user.getPassword())) {
            return JSON.toJSONString(new Result(false, "密码输入错误！"));
        } else {
            if (user.getIcon() == null || user.getIcon().equals("")) {
                user.setIcon("dist/static/user_icon/no_user.jpg");
//                user.setPassword("");
            }
            String seesion_id = request.getSession().getId();
            request.getSession().setAttribute("user_id", seesion_id);
            Cookie cookie = new Cookie("JSESSIONID", seesion_id);
            Cookie cookie2 = new Cookie("username", user.getUsername());
            cookie.setMaxAge(1000 * 60);
            cookie.setPath("/");
            cookie2.setPath("/");
            response.addCookie(cookie);
            response.addCookie(cookie2);
            return JSON.toJSONString(new Result(true, JSON.toJSONString(user)));
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getSession().getId();
        Object user_id = request.getSession().getAttribute("user_id");
        if (user_id != null && id.equals(user_id)) {
            request.getSession().removeAttribute("user_id");
            Cookie cookie = new Cookie("JSESSIONID", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return JSON.toJSONString(new Result(true, "logout success"));
        } else {
            return JSON.toJSONString(new Result(false, "logout fail"));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("studentId") String studentId) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setStudentId(studentId);
        Result result = userService.registerUser(user);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/showDetailedUser", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String showDetailedUser(@RequestParam("id") int id) {
        Result byId = userService.findById(id);
        return JSON.toJSONString(byId);
    }

    @RequestMapping(value = "/changeMsg", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String changeMeg(User user) {
//        System.out.println("runing123 "+user);
        Result result = userService.updateUser(user);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/confirmPassword", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String confirmPassword(@RequestParam("studentId") String studentId, @RequestParam("old_password") String old_password, @RequestParam("password") String password) {
        Result result = userService.changePassword(studentId, old_password, password);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/get_resent_rank", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getRankUser(HttpServletRequest request) {
        Result result = userService.findRankByPage(5);
        return JSON.toJSON(result).toString();
    }

}
