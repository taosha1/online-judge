package com.tc.service.impl;

import com.alibaba.fastjson.JSON;
import com.tc.dao.CompetitionDao;
import com.tc.dao.Competition_ProblemDao;
import com.tc.domain.Competition;
import com.tc.domain.Competition_Problem;
import com.tc.service.CompetitionService;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;
    @Autowired
    private Competition_ProblemDao competition_problemDao;

    @Override
    public Result get_resent_competition(int i) {
        List<Competition> competitions = null;
        competitions = competitionDao.findCompetitionByTopNum(i);
        if (competitions != null) {
            return new Result(true, JSON.toJSON(competitions).toString());
        } else {
            return new Result(false, "没有比赛！");
        }
    }

//    @Override
//    public Result addCompetiton(Competition competition) {
//        return null;
//    }

    @Override
    public Result findCompetitionCount() {
        int row = competitionDao.findCount();
        if (row == 0) {
            return new Result(false, "competition数据库为空");
        } else {
            return new Result(true, row + "");
        }
    }

    @Override
    public Result findCompetitionsByPage(String page, String competitionsNum) {
        int startNum = (Integer.parseInt(page) - 1) * Integer.parseInt(competitionsNum);
        List<Competition> competitionsByPage = competitionDao.findCompetitionsByPage(startNum, Integer.parseInt(competitionsNum));
        if (competitionsByPage == null) {
            return new Result(false, "没有更多的比赛了");
        } else {
            return new Result(true, JSON.toJSON(competitionsByPage).toString());
        }
    }

    @Override
    public Result findCompetitionByGet(String competitionId) {
        Competition competitionById = competitionDao.findCompetitionById(Integer.parseInt(competitionId));
        if (competitionById==null){
            return new Result(false,"没有该比赛！");
        }else {
            return new Result(true, JSON.toJSON(competitionById).toString());
        }
    }

    @Override
    public Result findCompetition_Problems(String competitionId) {
        List<Competition_Problem> competition_problems = null;
        competition_problems = competition_problemDao.findCompetition_ProblemsByCompetitionId(Integer.parseInt(competitionId));
        if (competition_problems!=null){
            return new Result(true, JSON.toJSON(competition_problems).toString());
        }else {
            return new Result(false,"没有该比赛题目！");
        }
    }
}
