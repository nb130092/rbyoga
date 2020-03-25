package com.woniu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author R&B
 * @create 2020/3/24 21:11:26
 */

@Controller
public class UserController {


    @ResponseBody
    @RequestMapping("users")
    public String showAllUsers(){


    }

}
