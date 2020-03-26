package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author R&B
 * @create 2020/3/26 16:56:09
 * 订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderz {

    private Integer o_id;
    private Date o_date;
    private String o_isPay;
    private String u_password;
    private Integer student_id;
    private Integer product_id;
}
