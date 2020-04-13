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

    // 查找某个人的所有关注的人的id
    List<Integer> findAllFollows(Integer myId);

    //根据场馆的id在关系表中查出与场馆相关人员信息
    List<Relation>  findStorePerByMain_id(Integer main_id);
}
