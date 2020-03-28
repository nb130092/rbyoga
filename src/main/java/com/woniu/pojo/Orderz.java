package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author R&B
 * @create 2020/3/26 16:56:09
 * 订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderz implements Serializable {

    private Integer o_id;  //订单id
    private Date o_date;    //订单日期
    private Integer o_price;     //订单价格
    private String o_isPay;     //支付状态
    private Integer student_id;   //购买者id(外
    private Integer product_id;    //产品id(外)

}
