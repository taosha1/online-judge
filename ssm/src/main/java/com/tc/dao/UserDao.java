package com.tc.dao;

import com.tc.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from user_db where id = #{id}")
    User findById(int id);

    @Select("select * from user_db where username = #{name}")
    User findByName(String name);

    @Insert("insert into user_db(studentId,username,password) values (#{studentId},#{username},#{password})")
    void addUser(User user);
}
