package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author R&B
 * @create 2020/3/24 21:05:51
 */


@Data
@AllArgsConstructor

public class User implements Serializable {
    public User(){

    }
    private Integer u_id;
    /**昵称*/
    private String  u_pickname;
    private String u_username;
    private String u_password;
    private String u_safecode;
    /**头像*/
    private String u_head;
    /**个性签名*/
    private String u_selfword;
    private String u_phone;
    private Integer u_money;
    private String u_role;
    private String u_card;

}
