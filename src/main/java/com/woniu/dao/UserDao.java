package com.woniu.dao;

import com.woniu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
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
    //findAllCoach:查询所有教练
    List<User> findAllCoach(RowBounds rowBounds);
    //getCountByCoach:获取教练总数
    Integer getCountByCoach();
    //findAllCoach:查询所有场馆
    List<User> findAllVenue(RowBounds rowBounds);
    //getCountByCoach:获取场馆总数
    Integer getCountByVenue();


}
