package com.tc.dao;

import com.tc.domain.Competition;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionDao {

    @Results(id = "competition",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "sponsor_studentId",property = "sponsor",one = @One(select = "com.tc.dao.UserDao.findByStuId",fetchType = FetchType.EAGER)),
            @Result(column = "title",property = "title"),
            @Result(column = "startDate",property = "startDate"),
            @Result(column = "isOpen",property = "isOpen"),
            @Result(column = "endDate",property = "endDate"),
            @Result(column = "languageType",property = "languageType"),
            @Result(column = "discription",property = "discription")
    })
    @Select("select * from competition_db where id = #{id}")
    Competition findCompetitionById(int id);

    @Select("select * from competition_db order by id desc limit #{i}")
    @ResultMap("competition")
    List<Competition> findCompetitionByTopNum(int i);

    @Select("select count(1) from competition_db")
    int findCount();

    @Select("select * from competition_db limit #{arg0},#{arg1}")
    @ResultMap("competition")
    List<Competition> findCompetitionsByPage(int page, int competitionsNum);


}
