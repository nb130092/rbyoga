package com.woniu.service;

import com.woniu.pojo.Notice;

import java.util.List;

public interface INoticeService {
    List<Notice> findAll();
    Notice findOne(Integer u_id);
    void delete(Integer u_id);
    void save(Notice notice);
    void update(Notice notice);
    //根据场馆的id在通知表中查出所有通知
    List<Notice> findNoticeByArrive_id(Integer init_id);
}
