package com.woniu.service;

import com.woniu.pojo.Speak;

import java.util.List;

/**
 * @author zhangchendong
 * @create 2020/03/2020/3/26:21:22:39
 */
public interface ISpeakService {
    List<Speak> findAll();
    Speak findOne(Integer sid);
    void save(Speak speak);
    void delete(Integer sid);
    void update(Speak speak);

    //查询所有的动态，包含发起人信息
    List<Speak> showAllspeakWithUser();
}
