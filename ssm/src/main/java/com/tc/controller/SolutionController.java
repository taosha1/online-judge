package com.tc.controller;

import com.alibaba.fastjson.JSON;
import com.tc.domain.Problem;
import com.tc.domain.Solution;
import com.tc.service.ProblemService;
import com.tc.service.SolutionService;
import com.tc.utils.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/solution")
public class SolutionController {
    @Autowired
    private SolutionService solutionService;
    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/commit",method= RequestMethod.POST,produces="text/html;charset=UTF-8")
    public String commit(HttpServletRequest request,
                      @RequestParam("code")String code,
                      @RequestParam("languageType")String languageType,
                      @RequestParam("problemId")String problemId,
                         @RequestParam("username")String username)  {
        String codeRootPath = request.getSession().getServletContext().getRealPath("code")+"/";
        String questionRootPath = request.getSession().getServletContext().getRealPath("question")+"/";
        System.out.println(codeRootPath+"codeRootPath"+questionRootPath);
        Result problemResult = problemService.findProblemById(problemId);
        Problem problem = JSON.parseObject(problemResult.getMessage(),Problem.class);
        Solution solution = new Solution();
        solution.setCode(code);
        solution.setLanguageType(languageType);
        solution.setProblem(problem);
        Result result = solutionService.commitByPost(solution,username,problem,codeRootPath,questionRootPath);
        return JSON.toJSON(result).toString();
    }

//    @RequestMapping(value = "/getUser_Solutions",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
//    public String getUser_Solutions(HttpServletRequest request){
//        int page = Integer.parseInt(request.getParameter("page"));
//        int solutionsNum = Integer.parseInt(request.getParameter("solutionsNum"));
//        Result result = solutionService.searchUser_SolutionsByPage(page,solutionsNum);
//        return JSON.toJSON(result).toString();
//    }
//
//    @RequestMapping(value = "/showDetailedUser_Solution",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
//    public String showDetailedUser_Solution(HttpServletRequest request){
//        String solutionId = request.getParameter("solutionId");
//        String userId = request.getParameter("userId");
//        Result result = solutionService.findUser_SolutionByGet(solutionId,userId);
//        return JSON.toJSON(result).toString();
//    }
//
//    @RequestMapping(value = "/getCount",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
//    public String getCount(HttpServletRequest request){
//        Result result = solutionService.findSolutionCount();
//        return JSON.toJSON(result).toString();
//    }
//
//    @RequestMapping(value = "/showSolutionByProblemAndLanguageType",method= RequestMethod.GET,produces="text/html;charset=UTF-8")
//    public String showSolutionByProblemAndLanguageType(HttpServletRequest request){
//        String problemId = request.getParameter("problemId");
//        String username = request.getParameter("username");
//        String languageType = request.getParameter("languageType");
//        Result result = solutionService.findSolutionByProblem(problemId,username,languageType);
//        return JSON.toJSON(result).toString();
//    }

}
