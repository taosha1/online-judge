package com.tc.dao;

import com.tc.domain.User_BBS;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_BBSDao {
    @Insert("insert into user_bbs_db(user_id,BBS_id,msg,postDate) values(#{user.id},#{bbs.id},#{msg},#{postDate})")
    int addUser_BBS(User_BBS user_bbs);

    @Select("select * from user_bbs_db where id = #{id}")
    @Results(id = "user_bbs",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_id",property = "user",many = @Many(select = "com.tc.dao.UserDao.findById",fetchType = FetchType.LAZY)),
            @Result(column = "BBS_id",property = "bbs",many = @Many(select = "com.tc.dao.BbsDao.findBBSById",fetchType = FetchType.LAZY)),
            @Result(column = "msg",property = "msg"),
            @Result(column = "postDate",property = "postDate")
    })
    List<User_BBS> findUser_BBSByBBSId(int id);
}
