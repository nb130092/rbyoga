package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author R&B
 * @create 2020/3/26 16:54:15
 * 产品表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer p_id;
    private String  p_name;
    private String p_image;
    private Integer p_price;
    private String p_status;
    private Integer store_id;

    //关联场馆
    private User store;

}
