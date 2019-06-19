package com.tc.controller;

import com.alibaba.fastjson.JSON;
import com.tc.domain.Competition;
import com.tc.domain.User;
import com.tc.service.CompetitionService;
import com.tc.service.ProblemService;
import com.tc.service.UserService;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/competition")
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/getCompetition_Problems",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String getCompetition_Problems(@RequestParam("competitionId")String competitionId){
        Result result = competitionService.findCompetition_Problems(competitionId);
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/getCompetitions",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String getCompetitions(@RequestParam("page")String page,@RequestParam("competitionsNum")String competitionsNum ){
        Result result = competitionService.findCompetitionsByPage(page,competitionsNum);
        return JSON.toJSONString(result);
    }


    @RequestMapping(value = "/showDetailedCompetition",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String showDetailedCompetition(@RequestParam("competitionId")String competitionId){
        Result result = competitionService.findCompetitionByGet(competitionId);
        return JSON.toJSON(result).toString();
    }

//    @RequestMapping(value = "/switchCompetition",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
//    public String switchCompetition(HttpServletRequest request){
//        boolean isOpen =Boolean.parseBoolean(request.getParameter("isOpen"));
//        String competitionId = request.getParameter("competitionId");
//        int id = Integer.parseInt(competitionId);
//        Competition competition = new Competition();
//        competition.setId(id);
//        competition.setOpen(isOpen);
//        Result result = competitionService.changeCompetitionMsgByPost(competition);
//        return JSON.toJSON(result).toString();
//    }

    @RequestMapping(value = "/get_resent_competition",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String get_resent_competition(HttpServletRequest request){
        int searchNum = 5;
        Result result = competitionService.get_resent_competition(5);
        return JSON.toJSON(result).toString();
    }

    @ResponseBody
    @RequestMapping(value = "/getCount",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
    public String getCount(HttpServletRequest request){
        Result result = competitionService.findCompetitionCount();
        return JSON.toJSON(result).toString();
    }

}
