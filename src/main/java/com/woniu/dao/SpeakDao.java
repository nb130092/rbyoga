package com.woniu.dao;

import com.woniu.pojo.Speak;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangchendong
 * @create 2020/03/2020/3/26:21:14:40
 */
@Mapper
@Repository
public interface SpeakDao {
    List<Speak> findAll();
    Speak findOne(Integer sid);
    void save(Speak speak);
    void delete(Integer sid);
    void update(Speak speak);
}
