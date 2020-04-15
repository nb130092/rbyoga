package com.woniu.controller;

import com.woniu.pojo.ResultVO;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author lxy
 * @date 2020/3/28-12:21
 */
@RestController
public class LoginController {
    @Autowired
    IUserService userService;

    @ResponseBody
    @PostMapping("login")
    public ResultVO login(@RequestBody User user, HttpSession session){
        User loginUser=null;

        try{
            loginUser=userService.login(user);




            if (loginUser!=null){
                session.setAttribute("loginUser", loginUser);
                System.out.println("loginUser = " + loginUser);

                if (loginUser.getU_role().equals("场馆")){   //如果code是201,则跳转到场馆管理
                    return new ResultVO(201, "场馆登录成功");
                }
                if (loginUser.getU_role().equals("学员")){   //如果code是202,则跳转到学员管理
                    return new ResultVO(202, "学员登录成功");
                }
                if (loginUser.getU_role().equals("教练")){   //如果code是203,则跳转到教练管理
                    return new ResultVO(203, "教练登录成功");
                }
            }
            return new ResultVO(500, "登录失败");
        }catch(Exception e){
            return new ResultVO(500, "登录失败");
        }

    }

    @PostMapping("register")
    public ResultVO register(@RequestBody User user){
        try{
            System.out.println(user);
            userService.register(user);

            return new ResultVO(200, "注册成功");
        }catch(Exception e){
            return new ResultVO(500, "注册失败");
        }
    }

    @ResponseBody
    @PostMapping("check")
    public ResultVO check(@RequestBody User user){
        try{
            User user1=userService.check(user);
            if(user1!=null){
                return new ResultVO(200, "用户名已存在",user1);
            }
            return new ResultVO(200, "用户名不存在");
        }catch(Exception e){
            return new ResultVO(500, "查询失败");
        }
    }

    @PostMapping("updPwd")
    @ResponseBody
    public ResultVO updPwd(@RequestBody  User user){
        try{
            Integer i=userService.updPwd(user);
            if(i!=0){
                return new ResultVO(200, "密码修改成功！！");
            }
            return new ResultVO(500, "密码修改失败！！");
        }catch(Exception e){
            return new ResultVO(500, "密码修改失败！！");
        }
    }

}
