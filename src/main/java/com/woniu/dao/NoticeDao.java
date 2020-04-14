package com.woniu.dao;

import com.woniu.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface NoticeDao {
    List<Notice> findAll();
    Notice findOne(Integer u_id);
    void delete(Integer u_id);
    void save(Notice notice);
    void update(Notice notice);
    //根据场馆的id在通知表中查出所有通知我的
    List<Notice> findNoticeByArrive_id(Integer arrive_id);
    //我通知的
    List<Notice> findNoticeByInit_id(Integer init_id);
}
