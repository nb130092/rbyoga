package com.woniu.service.impl;

import com.woniu.dao.ProductDao;
import com.woniu.pojo.Product;
import com.woniu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/26 19:09:28
 */

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductDao pd;

    @Override
    public List<Product> findAll() {
        return pd.findAll();
    }

    @Override
    public Product findOne(Integer pid) {
        return pd.findOne(pid);
    }

    @Override
    public void save(Product product) {
        pd.save(product);
    }

    @Override
    public void delete(Integer pid) {
        pd.delete(pid);
    }

    @Override
    public void update(Product product) {
        pd.update(product);
    }
}
