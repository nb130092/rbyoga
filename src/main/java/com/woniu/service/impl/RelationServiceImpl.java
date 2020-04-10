package com.woniu.service.impl;

import com.woniu.dao.RelationDao;
import com.woniu.pojo.Relation;
import com.woniu.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public  List<Integer> findAllFollows(Integer myId){
        return  relationDao.findAllFollows(myId);

    }
}
