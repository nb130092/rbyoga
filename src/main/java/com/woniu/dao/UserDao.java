package com.woniu.dao;

import com.woniu.pojo.PageBean;
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
    //findAllStudent:查询所有学员
    List<User> findAllStudents();
    //findAllCoach:查询所有教练
    List<User> findAllCoach(RowBounds rowBounds);
    //getCountByCoach:获取教练总数
    Integer getCountByCoach();
    //findAllVenue:查询所有场馆
    List<User> findAllVenue(RowBounds rowBounds);
    //getCountByVenue:获取场馆总数
    Integer getCountByVenue();

    //lxy:登录
    User login(User user);
    //lxy:注册
    void register(User user);
    //lxy:验证用户名是否存在
    User check(User user);
    //lxy:忘记密码，根据用户名和安全码修改密码
    Integer updPwd(User user);


    Integer countAll(PageBean<User> pageBean);

    List<User> findByPage(PageBean<User> pageBean);

    //通过id查询某个用户以及他的全部动态
    User showUserAllSpeaks(Integer u_id);

    List<User> findStudents(PageBean<User> pageBean);
    // lr: 查询所有教练
    List<User> findAllCoaches();

    // lr: 查找某个场馆的全部学员
    List<User> findStudentsByStore(User store);
}
