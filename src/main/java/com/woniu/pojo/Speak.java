package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author R&B
 * @create 2020/3/26 16:47:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Speak implements Serializable {

    private Integer s_id;
    private String s_content;
    private Date s_time;
    private Integer user_id;
}
