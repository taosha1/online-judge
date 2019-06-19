package com.tc.service;

import com.tc.domain.Problem;
import com.tc.utils.Result;

public interface ProblemService {
    Result findProblemById(String problemId);

    Result addProblem(Problem problem);

    Result findProblemsByPage(int page, int problemsNum);

    Result deleteProblemById(int id);

    Result findProblemCount();
}
