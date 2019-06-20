package com.tc.dao;

import com.tc.domain.Problem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemDao {

    @Select("select * from problem_db where id = #{problemId}")
    Problem findProblemById(int problemId);

    @Insert("insert into problem_db(title,discription,inputData,outPutData,example,commit,pass,testData) values " +
            "(#{title},#{discription},#{inputData},#{outPutData},#{example},#{commit},#{pass},#{testData})")
    int addProblem(Problem problem);

    @Select("select * from problem_db order by id desc limit #{arg0},#{arg1}")
    List<Problem> findProblemLimitOrderByProNum(int startNum, int problemsNum);

    @Delete("delete from  problem_db where id = #{id}")
    int deleteProblemById(int id);

    @Select("select count(1) from problem_db")
    int findCount();

    @Update("update problem_db set title=#{title}, discription=#{discription} ,inputData=#{inputData},outputData =#{outputData }," +
            "example =#{example }" )
    int updataProblem(Problem problem);
}
