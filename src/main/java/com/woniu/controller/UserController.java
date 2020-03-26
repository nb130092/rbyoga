package com.woniu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author R&B
 * @create 2020/3/24 21:11:26
 */


@Controller
@RequestMapping("users")
public class UserController {


    @ResponseBody
    @GetMapping
    public String showAllUsers(){

        return "啊哈哈哈";

    }

}
