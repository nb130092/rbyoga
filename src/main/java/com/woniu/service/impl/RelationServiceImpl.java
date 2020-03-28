package com.woniu.service.impl;

import com.woniu.dao.RelationDao;
import com.woniu.pojo.Relation;
import com.woniu.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhaojunjie
 * @create 2020/03/28  12:19:16
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
}
