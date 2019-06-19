package com.tc.dao;

import com.tc.domain.Competition_Problem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Competition_ProblemDao {

    @Select("select * from competition_problem_db where competition_id = #{id}")
    @Results(id = "competition_problem",value = {
            @Result(column = "id", property = "id",id = true),
            @Result(column = "problem_id", property = "problem",
                    many = @Many(select = "com.tc.dao.ProblemDao.findProblemById")),
            @Result(column = "competition_id", property = "competition",
                    one = @One(select = "com.tc.dao.CompetitionDao.findCompetitionById",fetchType = FetchType.EAGER ))
    })
    List<Competition_Problem> findCompetition_ProblemsByCompetitionId(int id);

}
