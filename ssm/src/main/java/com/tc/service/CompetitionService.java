package com.tc.service;

import com.tc.domain.Competition;
import com.tc.utils.Result;

public interface CompetitionService {
    Result get_resent_competition(int i);

//    Result addCompetiton(Competition competition);

    Result findCompetitionCount();

    Result findCompetitionsByPage(String page, String competitionsNum);

    Result findCompetitionByGet(String competitionId);

    Result findCompetition_Problems(String competitionId);
}
