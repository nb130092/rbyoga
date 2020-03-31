package com.woniu.dao;

import com.woniu.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/26 17:49:51
 */

@Mapper
@Repository
public interface ProductDao {

    List<Product> findAll();
    Product findOne(Integer pid);
    void save(Product product);
    void delete(Integer pid);
    void update(Product product);



}
