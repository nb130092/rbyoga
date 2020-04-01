package com.woniu.dao;

import com.woniu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/24 21:06:11
 */
@Mapper
@Repository
public interface UserDao {


    void save(User user);
    void delete(Integer u_id);
    void update(User user);
    User findOne(Integer u_id);
    List<User> findAll();

    //lxy:登录
    User login(User user);
    //lxy:注册
    void register(User user);
    //lxy:验证用户名是否存在
    User check(User user);
    //lxy:忘记密码，根据用户名和安全码修改密码
    Integer updPwd(User user);
}
