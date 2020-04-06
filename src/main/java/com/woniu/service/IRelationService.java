package com.woniu.service;


import com.woniu.pojo.Relation;

import java.util.List;
import java.util.Map;

/**
 * @author R&B
 * @create 2020/3/26 17:57:34
 */
public interface IRelationService {

    List<Relation> findAll();
    Relation findOne(Integer rid);
    void save(Relation relation);
    void delete(Integer rid);
    void update(Relation relation);

    void cancelFollow(Map<String, Integer> map);

    Relation myFollow(Integer u_id);
}
