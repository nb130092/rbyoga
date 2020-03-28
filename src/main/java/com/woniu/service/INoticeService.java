package com.woniu.service;

import com.woniu.pojo.Notice;

import java.util.List;

public interface INoticeService {
    List<Notice> findAll();
    Notice findOne(Integer u_id);
    void delete(Integer u_id);
    void save(Notice notice);
    void update(Notice notice);
}
