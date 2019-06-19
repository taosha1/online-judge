package com.tc.dao;

import com.tc.domain.BBS;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BbsDao {

    @Select("select * from bbs_db order by startDate desc limit 0,#{searchNum}")
    List<BBS> findBBSByTopNum(int searchNum);

    @Results(id = "bbs",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(column ="Lz_studentId",property = "Lz",one = @One(select = "com.tc.dao.UserDao.findByStuId",fetchType = FetchType.EAGER)),
            @Result(column = "discription",property = "discription"),
            @Result(column = "startDate",property = "startDate"),
            @Result(column = "title",property = "title")
    })
    @Select("select * from bbs_db where id = #{i}")
    BBS findBBSById(int i);

    @Insert("insert into bbs_db(id,Lz_studentId,discription,startDate,title) values(#{id},#{Lz.studentId},#{discription},#{startDate},#{title})")
    int addBBS(BBS bbs);

    @Select("select * from bbs_db order by id desc limit #{arg0},#{arg1}")
    @ResultMap("bbs")
    List<BBS> findBBSLimit(int startNum, int bbsNum);

    @Select("select count(1) from bbs_db")
    int findCount();

    @Delete("delete from bbs_db where id = #{id}")
    int deleteById(int id);
}
