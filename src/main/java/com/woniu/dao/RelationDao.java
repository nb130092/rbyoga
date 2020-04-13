package com.woniu.dao;


import com.woniu.pojo.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    //根据场馆的id在关系表中查出与场馆相关人员信息
    List<Relation>  findStorePerByMain_id(Integer main_id);

    //取消关注
    void cancelFollow(Map<String, Integer> map);
    //我的关注
    List<Relation> myFllow(Integer u_id);
    // 查找某个人的所有关注的人的id
    List<Integer>  findAllFollows(Integer myId);
    //根据场馆id和教练id 删除教练和场馆的关系。也就是解雇
    void deleteRelationByMain_idAndGuest_id(Integer main_id,Integer guest_id);
}
