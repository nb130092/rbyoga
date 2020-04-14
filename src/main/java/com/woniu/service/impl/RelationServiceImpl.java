package com.woniu.service.impl;

import com.woniu.dao.RelationDao;
import com.woniu.pojo.Relation;
import com.woniu.pojo.User;
import com.woniu.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author R&B
 * @create 2020/3/28 20:08:15
 */

@Service
@Transactional
public class RelationServiceImpl implements IRelationService {

    @Autowired
    RelationDao relationDao;

    @Override
    public List<Relation> findAll() {
        return relationDao.findAll();
    }

    @Override
    public Relation findOne(Integer rid) {
        return relationDao.findOne(rid);
    }

    @Override
    public void save(Relation relation) {

        relationDao.save(relation);

    }

    @Override
    public void delete(Integer rid) {
        relationDao.delete(rid);
    }

    @Override
    public void update(Relation relation) {

        relationDao.update(relation);

    }


    // 查找某个人的所有关注的人的id
    @Override
    public  List<Integer> findAllFollows(Integer myId){
        return  relationDao.findAllFollows(myId);
    }

    @Override
    public void cancelFollow(Map<String, Integer> map) {
        relationDao.cancelFollow(map);
    }

    @Override
    public List<Relation> myFollow(Integer u_id) {
        return relationDao.myFllow(u_id);
        // 查找某个人的所有关注的人的id

    }


    // 查找某个人的所有关注的人
    @Override
    public List<User> findAllFollowUsers(User user) {
        return relationDao.findAllFollowUsers(user);
    }
    //根据场馆的id在关系表中查出与场馆相关人员信息
    @Override
    public List<Relation> findStorePerByMain_id(Integer main_id) {
        return relationDao.findStorePerByMain_id(main_id);
    }

    @Override
    public void deleteRelationByMain_idAndGuest_id(Integer main_id, Integer guest_id) {
        relationDao.deleteRelationByMain_idAndGuest_id(main_id,guest_id);
    }


}