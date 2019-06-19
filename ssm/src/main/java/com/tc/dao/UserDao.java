package com.tc.dao;

import com.tc.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from user_db where id = #{id}")
    User findById(int id);

    @Select("select * from user_db where username = #{name}")
    User findByName(String name);

    @Insert("insert into user_db(studentId,username,password) values (#{studentId},#{username},#{password})")
    void addUser(User user);

    @Update("update user_db set rights=#{rights},passNum=#{passNum},studentId=#{studentId} ,username=#{username},password=#{password}, icon=#{icon},major=#{major},grade=#{grade}, QQ=#{QQ},email=#{email}, sex=#{sex},discription=#{discription} where username=#{username}")
    int updateUser(User user);

    @Select("select * from user_db where studentId=#{studentId}")
    User findByStuId(String studentId);

    @Select("select * from user_db order by passNum desc limit 0,#{num}")
    List<User> findRankLimitOrderByPassNum(int num);

    @Select("select count(1) from user_db ")
    int findCount();

    @Select("select * from user_db order by passNum desc limit #{arg0},#{arg1}")
    List<User> findRankLimitOrderByPassNum2(int startNum, int rankNum);
}
