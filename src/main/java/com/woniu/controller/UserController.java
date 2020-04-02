package com.woniu.controller;

import com.woniu.dao.UserDao;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.ResultVO;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author R&B
 * @create 2020/3/24 21:11:26
 */


@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping
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

    @GetMapping("{u_id}")
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

    @PutMapping
    public ResultVO updateUser(@RequestBody User user){
        try{
            userService.update(user);
            return new ResultVO(200, "修改成功", user);
        }catch(Exception e){
            return new ResultVO(500, "修改失败", user);
        }
    }

    @DeleteMapping("{u_id}")
    public ResultVO deleteUser(@PathVariable Integer u_id){
        try{
            System.out.println("访问到了");
            userService.delete(u_id);
            return new ResultVO(200, "删除成功", u_id);
        }catch(Exception e){
            return new ResultVO(500, "删除失败", u_id);
        }
    }


    // 梁瑞：新增用户+图片
    @PostMapping("saveUserWithImage")
    public ResultVO saveUserWithImage(@RequestBody MultipartFile file, User user, HttpServletRequest req) {
        ResultVO resultVO = null;
        try {
            String name = file.getOriginalFilename();   //获得文件名
            String suffix = name.substring(name.lastIndexOf("."));  //获得文件的后缀
            String realPath = req.getServletContext().getRealPath("/upload");   //声明文件保存的位置

            System.out.println(realPath);
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //声明一个新的文件名（不重复）
            String fileName = UUID.randomUUID() + suffix;
            //声明一个新的文件
            File target = new File(dir, fileName);
            //将上传的临时文件写入指定位置
            file.transferTo(target);
            // 把图片名存给用户对象
            user.setU_head(fileName);
            userService.save(user);

            resultVO = new ResultVO(200, "用户添加成功");

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            resultVO = new ResultVO(200, "用户添加失败");
        }
        return resultVO;
    }


      @GetMapping("findAllCoach")
        public ResultVO showAllCoach(PageBean pageBean){
            List<User> userList=null;
            try{
                userList = userService.findAllCoach(pageBean);
                pageBean.setList(userList);
                Integer countByCoach = userService.getCountByCoach();
                pageBean.setAllRow(countByCoach);
                if (userList!=null&&userList.size()>0){
                    return new ResultVO(200, "查询所有教练成功",pageBean);
                }
                return null;
            }catch(Exception e){
                return new ResultVO(500, "查询所有教练失败", pageBean);
            }
         }

    @GetMapping("findAllVenue")
    public ResultVO showAllVenue(PageBean pageBean){
        List<User> userList=null;
        try{
            userList = userService.findAllVenue(pageBean);
            pageBean.setList(userList);
            Integer countByVenue = userService.getCountByVenue();
            pageBean.setAllRow(countByVenue);
            if (userList!=null&&userList.size()>0){
                return new ResultVO(200, "查询所有场馆成功", pageBean);
            }
            return null;
        }catch(Exception e){
            return new ResultVO(500, "查询所有场馆失败", pageBean);
        }

    }


}
