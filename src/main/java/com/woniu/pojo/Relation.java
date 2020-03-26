package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
