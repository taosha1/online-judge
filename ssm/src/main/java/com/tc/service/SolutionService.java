package com.tc.service;

import com.tc.domain.Problem;
import com.tc.domain.Solution;
import com.tc.utils.Result;

public interface SolutionService {
    Result commitByPost(Solution solution, String username, Problem problem, String codeRootPath, String questionRootPath);



}
