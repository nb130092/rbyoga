package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/26 16:51:52
 *关系表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relation {

    private Integer r_id;
    private String r_relation;
    private Integer main_id;
    private Integer guest_id;
    //用户集合
    private List<User> users;

    public Relation(Integer r_id, String r_relation, Integer main_id, Integer guest_id) {
        this.r_id = r_id;
        this.r_relation = r_relation;
        this.main_id = main_id;
        this.guest_id = guest_id;
    }
}
