package com.woniu.service;

import com.woniu.pojo.Notice;
import com.woniu.pojo.User;

import java.util.List;

public interface INoticeService {
    List<Notice> findAll();
    Notice findOne(Integer u_id);
    void delete(Integer u_id);
    void save(Notice notice);
    void update(Notice notice);
    //根据场馆的id在通知表中查出所有通知我的
    List<Notice> findNoticeByArrive_id(Integer arrive_id);
    //我通知的
    List<Notice> findNoticeByInit_id(Integer init_id);

    //根据用户获取所有我发送的通知 by:lr
    List<Notice> findAllNoticeByInit(User user);
    //根据用户获取所有我接受的通知 by:lr
    List<Notice> findAllNoticeByArrive(User user);

}
