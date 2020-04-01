package com.woniu.service;


import com.woniu.pojo.Relation;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/28 20:07:09
 */
public interface IRelationService {

    List<Relation> findAll();
    Relation findOne(Integer rid);
    void save(Relation relation);
    void delete(Integer rid);
    void update(Relation relation);

}