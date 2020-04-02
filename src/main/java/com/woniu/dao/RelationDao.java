package com.woniu.dao;


import com.woniu.pojo.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author R&B
 */

@Mapper
@Repository
public interface RelationDao {
    List<Relation> findAll();
    Relation findOne(Integer rid);
    void save(Relation relation);
    void delete(Integer rid);
    void update(Relation relation);
}
