package com.woniu.service.impl;

import com.woniu.dao.NoticeDao;
import com.woniu.pojo.Notice;
import com.woniu.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.prefs.NodeChangeListener;
@Service
@Transactional
public class NoticeServiceImpl implements INoticeService {
    @Autowired
    public NoticeDao noticeDao;
    @Override
    public List<Notice> findAll() {
        return noticeDao.findAll();
    }

    @Override
    public Notice findOne(Integer u_id) {
        return noticeDao.findOne(u_id);
    }

    @Override
    public void delete(Integer u_id) {
        noticeDao.delete(u_id);
    }

    @Override
    public void save(Notice notice) {
        noticeDao.save(notice);
    }

    @Override
    public void update(Notice notice) {
        noticeDao.update(notice);
    }

    //根据场馆的id在通知表中查出所有通知我的
    @Override
    public   List<Notice> findNoticeByArrive_id(Integer arrive_id){
        return noticeDao.findNoticeByArrive_id(arrive_id);
    }
    //根据场馆的id在通知表中查出所有我通知的
    @Override
    public   List<Notice> findNoticeByInit_id(Integer init_id){
        return noticeDao.findNoticeByInit_id(init_id);
    }
}
