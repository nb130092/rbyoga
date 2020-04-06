package com.woniu.dao;


import com.woniu.pojo.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author R&B
 * @create 2020/3/26 17:49:51
 */

@Mapper
@Repository
public interface RelationDao {

    List<Relation> findAll();
    Relation findOne(Integer rid);
    void save(Relation relation);
    void delete(Integer rid);
    void update(Relation relation);

    void cancelFollow(Map<String, Integer> map);

    Relation myFllow(Integer u_id);
}
