package com.woniu.service.impl;

import com.woniu.dao.UserDao;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;
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
}
