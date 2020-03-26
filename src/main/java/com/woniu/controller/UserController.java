package com.woniu.controller;

import com.woniu.pojo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author R&B
 * @create 2020/3/24 21:11:26
 */


@RestController
@RequestMapping("users")
public class UserController {




    @GetMapping
    public ResultVO showAllUsers(){

        return null;

    }


}
