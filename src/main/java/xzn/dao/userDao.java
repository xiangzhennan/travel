package xzn.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xzn.domain.User;

import java.util.List;

@Repository
public interface userDao {
    @Insert("insert into tab_user (username,password,email) values(#{username},#{password},#{email})")
    int registUser(User user);
    @Select("select * from tab_user where username=#{username} and password = #{password}")
    User findUserByPassword(User user);
}
