package com.woniu.dao;

import com.woniu.pojo.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoticeDao {
    List<Notice> findAll();
    Notice findOne(Integer u_id);
    void delete(Integer u_id);
    void save(Notice notice);
    void update(Notice notice);
}
