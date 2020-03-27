package com.woniu.controller;

import com.woniu.pojo.ResultVO;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/24 21:11:26
 */


@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("findAll")
    public ResultVO showAllUser(){
        List<User> userList=null;
        try{
            userList = userService.findAll();
            if (userList!=null&&userList.size()>0){
                return new ResultVO(200, "查询成功", userList);
            }
            return null;
        }catch(Exception e){
            return new ResultVO(500, "查询失败", userList);
        }
    }

    @GetMapping("findOne/{u_id}")
    public ResultVO showUser(@PathVariable Integer u_id){
        User user=null;
        try{
            user = userService.findOne(u_id);
            if (user!=null){
                return new ResultVO(200, "查询成功", user);
            }
            return null;
        }catch(Exception e){
            return new ResultVO(500, "查询失败", user);
        }
    }


    @PostMapping("saveUser")
    public ResultVO saveUser(@RequestBody  User user){
        try{
            userService.save(user);
            return new ResultVO(200, "添加成功", user);
        }catch(Exception e){
            return new ResultVO(500, "添加失败", user);
        }

    }
    @PutMapping("updateUser")
    public ResultVO updateUser(@RequestBody User user){
        try{
            userService.update(user);
            return new ResultVO(200, "修改成功", user);
        }catch(Exception e){
            return new ResultVO(500, "修改失败", user);
        }
    }

    @DeleteMapping("deleteUser/{u_id}")
    public ResultVO deleteUser(@PathVariable Integer u_id){
        try{
            User user  = new User();
            userService.delete(u_id);
            return new ResultVO(200, "删除成功", u_id);
        }catch(Exception e){
            return new ResultVO(500, "删除失败", u_id);
        }
    }

}
