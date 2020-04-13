package com.woniu.service.impl;

import com.woniu.dao.SpeakDao;
import com.woniu.pojo.Speak;
import com.woniu.service.ISpeakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangchendong
 * @create 2020/03/2020/3/26:21:24:57
 */
@Service
@Transactional
public class SpeakServiceImpl implements ISpeakService {
    @Autowired
    SpeakDao speakDao;
    @Override
    public List<Speak> findAll() {
        return speakDao.findAll();
    }

    @Override
    public Speak findOne(Integer sid) {
        return speakDao.findOne(sid);
    }

    @Override
    public void save(Speak speak) {
        speakDao.save(speak);
    }

    @Override
    public void delete(Integer sid) {
        speakDao.delete(sid);
    }

    @Override
    public void update(Speak speak) {
        speakDao.update(speak);
    }

    @Override
    public List<Speak> showAllspeakWithUser() {
        return speakDao.showAllspeakWithUser();
    }
}
