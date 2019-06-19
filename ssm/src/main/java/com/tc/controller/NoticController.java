package com.tc.controller;

import com.alibaba.fastjson.JSON;
import com.tc.domain.BBS;
import com.tc.domain.User;
import com.tc.domain.User_BBS;
import com.tc.service.BbsService;
import com.tc.service.UserService;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/notic")
public class NoticController {

    @Autowired
    private BbsService bbsService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get_resent_notic", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String get_resent_notic() {
        Result result = bbsService.get_resent_notic(5);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/showDetailedNotic", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String showDetailedNotic(@RequestParam("bbsId") String bbsId) {
        Result result = bbsService.findBBSById(bbsId);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String add(@RequestParam("Lz_name") String Lz_name,
                      @RequestParam("discription") String discription,
                      @RequestParam("startDate") String startDate,
                      @RequestParam("title") String title) throws Exception {
        User user = userService.findByUsername(Lz_name);
        BBS bbs = new BBS();
        bbs.setLz(user);
        bbs.setDiscription(discription);
        bbs.setStartDate(startDate);
        bbs.setTitle(title);
        Result result = bbsService.addByPost(bbs);
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/reply", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String reply(@RequestParam("username") String username,
                        @RequestParam("bbsId") String bbsId,
                        @RequestParam("msg") String msg,
                        @RequestParam("postDate") String postDate) throws Exception {
        User user = userService.findByUsername(username);
        Result bbsResult = bbsService.findBBSById(bbsId);
        BBS bbs = JSON.parseObject(bbsResult.getMessage(), BBS.class);
        User_BBS user_bbs = new User_BBS();
        user_bbs.setUser(user);
        user_bbs.setBbs(bbs);
        user_bbs.setMsg(msg);
        user_bbs.setPostDate(postDate);
        Result result = bbsService.addReply(user_bbs);
        return JSON.toJSON(result).toString();
    }


    @RequestMapping(value = "/getReplys", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String getReplys(@RequestParam("bbsId") String bbsId) {
        int id = Integer.parseInt(bbsId);
        Result result = bbsService.findReplyByBBSId(id);
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getCount(HttpServletRequest request) {
        Result result = bbsService.findBBSCount();
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/getNotics", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getNotics(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("pageNum"));
        int bbsNum = Integer.parseInt(request.getParameter("bbsNum"));
        Result result = bbsService.findBBSByPage(page, bbsNum);
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String delete(@RequestParam("bbsId")String bbsId) throws Exception {
        int id = Integer.parseInt(bbsId);
        Result result = bbsService.RemoveBBSById(id);
        return JSON.toJSON(result).toString();
    }
}
