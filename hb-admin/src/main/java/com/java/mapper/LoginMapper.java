package com.java.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    @Select("select * from admin_users where username = #{username} and pwd = #{psw}")
    int selectUser(String username,String psw);
}
