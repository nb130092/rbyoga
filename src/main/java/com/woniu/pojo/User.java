package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author R&B
 * @create 2020/3/24 21:05:51
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

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

    //某人的动态集合
    private List<Speak> speakList;

    //用户的关注集合
    private List<Relation> relationList;
}
