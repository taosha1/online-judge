package com.tc.service.impl;

import com.alibaba.fastjson.JSON;
import com.tc.dao.ProblemDao;
import com.tc.domain.Problem;
import com.tc.domain.User;
import com.tc.service.ProblemService;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemDao problemDao;

    @Override
    public Result findProblemById(String problemId) {
        Problem problem = problemDao.findProblemById(Integer.parseInt(problemId));
        if (problem != null) {
            return new Result(true, JSON.toJSON(problem).toString());
        } else {
            return new Result(false, "没有该题号的题目");
        }
    }

    @Override
    public Result addProblem(Problem problem) {
        int addNum = problemDao.addProblem(problem);
        if (addNum == 0) {
            return new Result(false, "添加题目失败");
        } else {
            return new Result(true, "添加题目成功");
        }
    }

    @Override
    public Result findProblemsByPage(int page, int problemsNum) {
        List<Problem> problemList = null;
        int startNum = (page - 1) * problemsNum;
        problemList = problemDao.findProblemLimitOrderByProNum(startNum, problemsNum);
        if (problemList != null) {
            return new Result(true, JSON.toJSON(problemList).toString());
        } else {
            return new Result(false, "没有更多的用户了");
        }
    }

    @Override
    public Result deleteProblemById(int id) {
        int deleteRow = 0;
        deleteRow = problemDao.deleteProblemById(id);
        if (deleteRow == 0) {
            return new Result(false, "删除题目失败");
        } else {
            return new Result(true, "删除题目成功");
        }
    }

    @Override
    public Result findProblemCount() {
        int countNum = 0;
        countNum = problemDao.findCount();
        if (countNum != 0) {
            return new Result(true, "" + countNum);
        } else {
            return new Result(false, "problem数据库为空");
        }
    }
}
