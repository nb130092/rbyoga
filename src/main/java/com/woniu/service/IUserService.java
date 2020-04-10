package com.woniu.service;

import com.woniu.pojo.PageBean;
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
    //lxy:登录
    User login(User user);
    //lxy:注册
    void register(User user);
    //lxy:验证用户名是否存在
    User check(User user);

    Integer updPwd(User user);
    //findAllCoach:查询所有教练
    List<User> findAllCoach(PageBean pageBean);
    //getCountByCoach:获取教练总数
    Integer getCountByCoach();
    //findAllVenue:查询所有场馆
    List<User> findAllVenue(PageBean pageBean);
    //getCountVenue:获取场馆总数
    Integer getCountByVenue();

    //findAllStudent:查询所有学员
    List<User> findAllStudents();

    //通过id查询某个用户以及他的全部动态
    User showUserAllSpeaks(Integer u_id);
}
