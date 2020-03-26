package com.woniu.service;

import com.woniu.pojo.User;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/24 21:06:36
 */
public interface IUserService {

    void save(User user);
    void delete(Integer u_id);
    void update(User user);
    User findOne(Integer u_id);
    List<User> findAll();
}
