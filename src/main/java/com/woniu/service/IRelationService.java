package com.woniu.service;


import com.woniu.pojo.Relation;
import com.woniu.pojo.User;

import java.util.List;
import java.util.Map;

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


    void cancelFollow(Map<String, Integer> map);

    List<Relation> myFollow(Integer u_id);
    // 查找某个人的所有关注的人的id
    List<Integer> findAllFollows(Integer myId);

    //根据场馆的id在关系表中查出与场馆相关人员信息
    List<Relation>  findStorePerByMain_id(Integer main_id);


    //根据场馆id和教练id 删除教练和场馆的关系。也就是解雇
    void deleteRelationByMain_idAndGuest_id(Integer main_id,Integer guest_id);

    // 查找某个人的所有关注的人
    List<User>  findAllFollowUsers(User user);

}
