package com.woniu.service.impl;

import com.woniu.dao.OrderzDao;
import com.woniu.pojo.Orderz;
import com.woniu.service.IOrderzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Bobo
 * @Date 2020/3/26 0026 17:57
 */
@Service
@Transactional
public class OrderzServiceImpl  implements IOrderzService {


    @Autowired
    private OrderzDao orderzDao;

    @Override
    public void saveOrderz(Orderz orderz) {
        orderzDao.saveOrderz(orderz);
    }

    @Override
    public void deleteOrderz(Integer o_id) {
        orderzDao.deleteOrderz(o_id);
    }

    @Override
    public void updateOrderz(Orderz orderz) {
        orderzDao.updateOrderz(orderz);
    }

    @Override
    public List<Orderz> findAllOrderz() {
        return orderzDao.findAllOrderz();
    }

    @Override
    public Orderz findOneOrderz(Integer o_id) {
        return orderzDao.findOneOrderz(o_id);
    }


}

