package com.woniu.service;

import com.woniu.pojo.Orderz;
import com.woniu.pojo.User;

import java.util.List;

/**
 * @Author Bobo
 * @Date 2020/3/26 0026 17:58
 */

public interface IOrderzService {
    void saveOrderz(Orderz orderz);
    void deleteOrderz(Integer o_id);
    void updateOrderz(Orderz orderz);
    List<Orderz> findAllOrderz();
    Orderz findOneOrderz(Integer o_id);
    // 查找某个场馆的全部订单
    List<Orderz> findOrderzByStore(User store);
}
