package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author R&B
 * @create 2020/3/26 16:58:41
 * 关注表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    private Integer n_id;
    private String n_content;
    private String n_isRead;
    private String n_isYes;
    private Integer init_id;
    private Integer arrive_id;
}
