package com.tc.dao;

import com.tc.domain.Solution;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SolutionDao{

    @Insert("insert into solution_db(code,languageType,problem_id,state) values " +
            "(#{code},#{languageType},#{problem.id},#{state})")
    public int addSolution(Solution solution) ;

    @Select("select * from solution_db limit #{startNum},#{solutionsNum}")
    @Results(id = "solution",value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "code", property = "code"),
            @Result(column = "languageType", property = "languageType"),
            @Result(column = "problem_id", property = "problem" ,
                    many = @Many(select = "com.ein.Dao.ProblemDao.searchProblemById")),
            @Result(column = "state", property = "state"),
    })
    public List<Solution> searchSolutionsLimit(HashMap<String, Integer> pageLimit) ;

    @Select("select * from solution_db where id = #{id}")
    @ResultMap(value = "solution")
    public Solution searchSolutionById(int id) ;

    @Select("select * from solution_db where code = #{code}")
    @ResultMap(value = "solution")
    public Solution searchSolutionByCode(String code) ;
    
    @Update("update solution_db set code=#{code},languageType=#{languageType},problem_id =#{problem_id},state=#{state} where id = #{id}")
    public int updataSolutionProvider(Solution solution) ;

    @Select("select count(1) from solution_db")
    public int searchCount() ;

}
