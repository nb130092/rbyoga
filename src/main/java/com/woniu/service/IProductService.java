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


}
