package com.tc.controller;

import com.alibaba.fastjson.JSON;
import com.tc.domain.Problem;
import com.tc.service.ProblemService;
import com.tc.utils.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/searchProblem", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String searchProblem(HttpServletRequest request) {
        String problemId = request.getParameter("problemId");
        if (problemId.isEmpty()) {
            return JSON.toJSON(new Result(false, "输入题目号为空")).toString();
        } else {
            Result result = problemService.findProblemById(problemId);
            return JSON.toJSON(result).toString();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String add(HttpServletRequest request,
                      @RequestParam("title") String title,
                      @RequestParam("discription") String discription,
                      @RequestParam("inputData") String inputData,
                      @RequestParam("outputData") String outputData,
                      @RequestParam("example") String example,
                      @RequestParam("testData") String testData) throws Exception {
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setDiscription(discription);
        problem.setInputData(inputData);
        problem.setOutputData(outputData);
        problem.setExample(example);
        problem.setTestData(testData);
        problem.setCommit(0);
        problem.setPass(0);
        Result result = problemService.addProblem(problem);
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/getProblems", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getProblems(HttpServletRequest request) throws Exception {
        int page = Integer.parseInt(request.getParameter("pageNum"));
        int problemsNum = Integer.parseInt(request.getParameter("problemsNum"));
        Result result = problemService.findProblemsByPage(page, problemsNum);
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/showDetailedProblem", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String showDetailedProblem(HttpServletRequest request) throws Exception {
        String problemId = request.getParameter("problemId");
        Result result = problemService.findProblemById(problemId);
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String delete(HttpServletRequest request) throws Exception {
        String problemId = request.getParameter("problemId");
        int id = Integer.parseInt(problemId);
        Result result = problemService.deleteProblemById(id);
        return JSON.toJSON(result).toString();
    }

    @RequestMapping(value = "/getCount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getCount() {
        Result result = problemService.findProblemCount();
        return JSON.toJSON(result).toString();
    }
}
