package com.woniu.service;

import com.woniu.pojo.Product;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/26 17:57:34
 */
public interface IProductService {

    List<Product> findAll();
    Product findOne(Integer pid);
    void save(Product product);
    void delete(Integer pid);
    void update(Product product);

    // 获取全部产品以及关联场馆
    List<Product> findAllWithStore();

    //根据场馆的id在产品表中查出所有的产品
    List<Product> findProductByStore_id(Integer store_id);

}
