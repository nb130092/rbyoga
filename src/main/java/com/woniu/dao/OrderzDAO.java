package com.woniu.dao;

import com.woniu.pojo.Orderz;
import com.woniu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Bobo
 * @Date 2020/3/26  20:09
 */

@Mapper
@Repository
public interface OrderzDAO {
    void saveOrderz(Orderz orderz);
    void deleteOrderz(Integer o_id);
    void updateOrderz(Orderz orderz);
    List<Orderz> findAllOrderz();
    Orderz findOneOrderz(Integer o_id);

    // 查找某个场馆的全部订单
    List<Orderz> findOrderzByStore(User store);
}
