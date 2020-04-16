package com.woniu.service.impl;

import com.woniu.dao.UserDao;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/24 21:10:13
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(Integer u_id) {
        userDao.delete(u_id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findOne(Integer u_id) {

        return userDao.findOne(u_id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public User check(User user) {
        return userDao.check(user);
    }

    @Override
    public Integer updPwd(User user) {
        return userDao.updPwd(user);
    }

    @Override
    public Integer countAll(PageBean<User> pageBean) {
        return userDao.countAll(pageBean);
    }

    @Override
    public List<User> findByPage(PageBean<User> pageBean) {
        return userDao.findByPage(pageBean);
    }
    @Override
    public List<User> findAllCoach (PageBean pageBean){
        return userDao.findAllCoach(new RowBounds(pageBean.getOffset(), pageBean.getPageSize()));
    }

    @Override
    public Integer getCountByCoach () {
        return userDao.getCountByCoach();
    }

    @Override
    public List<User> findAllVenue (PageBean pageBean){
        return userDao.findAllVenue(new RowBounds(pageBean.getOffset(), pageBean.getPageSize()));
    }

    @Override
    public Integer getCountByVenue () {
        return userDao.getCountByVenue();
    }

    //lr: 查询所有学员
    @Override
    public List<User> findAllStudents () {
        return userDao.findAllStudents();
    }

    //lr: 通过id查询某个用户以及他的全部动态
    public User showUserAllSpeaks (Integer u_id){
        User user = userDao.showUserAllSpeaks(u_id);
        return user;

    }

    // lr: 查询所有教练
    public List<User> findAllCoaches(){
        return userDao.findAllCoaches();
    }

    @Override
    public List<User> findStudents(PageBean<User> pageBean) {
        return userDao.findStudents(pageBean);
    }

    @Override
    public List<User> findStudentsByStore(User store) {
        return userDao.findStudentsByStore(store);
    }

}
