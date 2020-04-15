package com.woniu.controller;

import com.woniu.pojo.PageBean;
import com.woniu.pojo.Relation;
import com.woniu.pojo.ResultVO;
import com.woniu.pojo.User;
import com.woniu.service.IRelationService;
import com.woniu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author R&B
 * @create 2020/3/24 21:11:26
 */

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    IRelationService relationService;

    @GetMapping
    public ResultVO showAllUser() {
        List<User> userList = null;
        try {
            userList = userService.findAll();
            if (userList != null && userList.size() > 0) {
                return new ResultVO(200, "查询成功", userList);
            }
            return null;
        } catch (Exception e) {
            return new ResultVO(500, "查询失败", userList);
        }
    }

    @GetMapping("{u_id}")
    public ResultVO showUser(@PathVariable Integer u_id) {
        User user = null;
        try {
            user = userService.findOne(u_id);
            if (user != null) {
                return new ResultVO(200, "查询成功", user);
            }
            return null;
        } catch (Exception e) {
            return new ResultVO(500, "查询失败", user);
        }
    }

    @PostMapping("saveUser")
    public ResultVO saveUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResultVO(200, "添加成功", user);
        } catch (Exception e) {
            return new ResultVO(500, "添加失败", user);
        }

    }

    @PutMapping
    public ResultVO updateUser(@RequestBody User user) {
        try {
            userService.update(user);
            return new ResultVO(200, "修改成功", user);
        } catch (Exception e) {
            return new ResultVO(500, "修改失败", user);
        }
    }

    @DeleteMapping("{u_id}")
    public ResultVO deleteUser(@PathVariable Integer u_id) {
        try {
            System.out.println("访问到了");
            userService.delete(u_id);
            return new ResultVO(200, "删除成功", u_id);
        } catch (Exception e) {
            return new ResultVO(500, "删除失败", u_id);
        }
    }

    //lxy完善登录用户个人信息
    @PostMapping("improve")
    @ResponseBody
    public ResultVO improveUser(@RequestBody User user,HttpSession session) {
        try {
            User loginUser = (User) session.getAttribute("loginUser");
            user.setU_id(loginUser.getU_id());
            userService.update(user);
            return new ResultVO(200, "修改成功", user);
        } catch (Exception e) {
            return new ResultVO(500, "修改失败", user);
        }

    }

    //lxy:查询 主页信息展示
    //lxy:查询 分页查询所有学员

    @PostMapping("findStudents")
    @ResponseBody
    public ResultVO findStudents(@RequestBody PageBean<User> pageBean,HttpSession session) {
        Map<String, Object> map = null;
        try {
            User loginUser = (User) session.getAttribute("loginUser");
            System.out.println(loginUser.getU_id());
            Integer allRow = userService.countAllStudents(pageBean);
            Integer allPage = allRow % pageBean.getPageSize() == 0 ? allRow / pageBean.getPageSize() : allRow / pageBean.getPageSize() + 1;
            pageBean.setAllPage(allPage);
            if (pageBean.getNowPage() == null) {
                pageBean.setNowPage(1);
            }
            pageBean.setAllRow(allRow);
            List<User> list = userService.findStudents(pageBean);
            List<User> allFollowUsers = relationService.findAllFollowUsers(loginUser);
            pageBean.setList(list);
            map = new HashMap<>();
            map.put("pageBean", pageBean);
            map.put("followUsers", allFollowUsers);
            return new ResultVO(200, "查询成功", map);
        } catch (Exception e) {
            return new ResultVO(500, "查询失败", map);
        }
    }

    @ResponseBody
    @PostMapping("findByPage")
    public ResultVO findByPage(@RequestBody PageBean<User> pageBean,HttpSession session) {
        Map<String, Object> map = null;
        try {
            User loginUser = (User) session.getAttribute("loginUser");
            System.out.println(loginUser.getU_id());
            Integer allRow = userService.countAll(pageBean);
            Integer allPage = allRow % pageBean.getPageSize() == 0 ? allRow / pageBean.getPageSize() : allRow / pageBean.getPageSize() + 1;
            pageBean.setAllPage(allPage);
            if (pageBean.getNowPage() == null) {
                pageBean.setNowPage(1);
            }
            pageBean.setAllRow(allRow);
            List<User> list = userService.findByPage(pageBean);
            pageBean.setList(list);
            List<User> allFollowUsers = relationService.findAllFollowUsers(loginUser);
            map = new HashMap<>();
            map.put("pageBean", pageBean);
            map.put("allUsers", allFollowUsers);
            return new ResultVO(200, "查询成功", map);
        } catch (Exception e) {
            return new ResultVO(500, "查询失败", map);
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

    //分页查询所有教练
    @GetMapping("findAllCoach")
    public ResultVO showAllCoach(PageBean pageBean) {
        List<User> userList = null;
        try {
            userList = userService.findAllCoach(pageBean);
            pageBean.setList(userList);
            Integer countByCoach = userService.getCountByCoach();
            pageBean.setAllRow(countByCoach);
            if (userList != null && userList.size() > 0) {
                return new ResultVO(200, "查询所有教练成功", pageBean);
            }
            return null;
        } catch (Exception e) {
            return new ResultVO(500, "查询所有教练失败", pageBean);
        }
    }

     // 查询所有场馆
    @GetMapping("findAllVenue")
    public ResultVO showAllVenue(PageBean pageBean) {
        List<User> userList = null;
        try {
            userList = userService.findAllVenue(pageBean);
            pageBean.setList(userList);
            Integer countByVenue = userService.getCountByVenue();
            pageBean.setAllRow(countByVenue);
            if (userList != null && userList.size() > 0) {
                return new ResultVO(200, "查询所有场馆成功", pageBean);
            }
            return null;
        } catch (Exception e) {
            return new ResultVO(500, "查询所有场馆失败", pageBean);
        }

    }

    // 查询所有学员以及我关注的所有用户ID
    @GetMapping("findAllStudents")
    public ResultVO findAllStudents(HttpSession session) {
        List<User> studentList=null;
        try{
            User loginUser =  (User)session.getAttribute("loginUser");
            studentList = userService.findAllStudents();  // 获取所有学员
            List<Integer> followIdList = relationService.findAllFollows(loginUser.getU_id()); //获取我关注的所有用户ID

            Map<String,Object> theMap = new HashMap<>();

            theMap.put("studentList",studentList);
            theMap.put("followIdList",followIdList);

            if (studentList!=null&&studentList.size()>0){
                return new ResultVO(200, "查询学员成功", theMap);
            }
            return null;
        }catch(Exception e){
            return new ResultVO(500, "查询学员失败");
        }
    }


    // 查询所有教练以及我关注的所有用户ID
    @GetMapping("findAllCoachs")
    public ResultVO findAllStudents(HttpSession session,PageBean pageBean) {
        List<User> coachList=null;
        try{
            User loginUser =  (User)session.getAttribute("loginUser");
            coachList = userService.findAllCoaches(); // 获取所有教练
            List<Integer> followIdList = relationService.findAllFollows(loginUser.getU_id()); //获取我关注的所有用户ID
            Map<String,Object> theMap = new HashMap<>();
            theMap.put("coachList",coachList);
            theMap.put("followIdList",followIdList);

            if (coachList!=null&&coachList.size()>0){
                return new ResultVO(200, "查询教练成功", theMap);
            }
            return null;
        }catch(Exception e){
            return new ResultVO(500, "查询教练成功");
        }
    }


    //lr：关注某人
    @GetMapping(value = "followWho")
    public ResultVO FollowWho(Integer guest_id, HttpSession session) {

        try {
            User loginUser= (User)session.getAttribute("loginUser");
            Integer main_id = loginUser.getU_id();
            Relation relation = new Relation(null, "关注To被关注", main_id, guest_id);

            relationService.save(relation);
            return new ResultVO(200, "关注成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(200, "关注失败");
        }

    }

    //lr: 通过id查询某个用户以及他的全部动态
    @GetMapping(value = "showUserAllSpeaks")
    public ResultVO showUserAllSpeaks(Integer user_id) {
        try {
            User user = userService.showUserAllSpeaks(user_id);
            return new ResultVO(200, "查询成功", user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(200, "查询失败");
        }
    }



    // lr: 展示所有我关注的人
    @GetMapping( "findAllFollowUsers")
    public ResultVO findAllFollowUsers(HttpSession session){
        ResultVO resultVO = null;
        try {
            User loginUser =  (User)session.getAttribute("loginUser");
            List<User> myfollowUsersList= relationService.findAllFollowUsers(loginUser);
            resultVO = new ResultVO(200,"查询我的关注成功",myfollowUsersList);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200,"查询我的关注失败");
        }
        return resultVO;

    }



    // lr: 取关某人
    @GetMapping("endFollow/{guest_id}")
    public ResultVO endFollow(@PathVariable Integer guest_id,HttpSession session){
        ResultVO resultVO = null;
        try {
            System.out.println(guest_id);
            User loginUser =  (User)session.getAttribute("loginUser");
            Integer mainId = loginUser.getU_id();
            Relation relation = new Relation();
            relation.setMain_id(mainId);
            relation.setGuest_id(guest_id);
            relation.setR_relation("关注To被关注");
            List<Relation> relationList = relationService.findAll();

            for (Relation r : relationList) {

                    if (r.getMain_id()==mainId && r.getGuest_id()==guest_id && r.getR_relation().equals("关注To被关注")){
                        System.out.println(r);
                        relationService.delete(r.getR_id());
                        resultVO = new ResultVO(200,"取关成功");
                        break;
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200,"取关失败");
        }

        return resultVO;

    }

    // lr: 展示我的信息
    @GetMapping("showMySelf")
    public ResultVO showMyself(HttpSession session){
        ResultVO resultVO = null;
        try {
            User loginUser =  (User)session.getAttribute("loginUser");
            User user = userService.findOne(loginUser.getU_id());
            resultVO = new ResultVO(200,"我的信息查找成功",user);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200,"我的信息查找失败");
        }
        return  resultVO;
    }


    // lr：修改我的资料(不含头像)
    @PostMapping("updateMyself")
    public ResultVO updateMyself(User user, HttpSession session) {
        ResultVO resultVO = null;
        try {
                User loginUser = (User)session.getAttribute("loginUser");
                user.setU_id(loginUser.getU_id());

                userService.update(user);

                resultVO = new ResultVO(200, "个人信息修改成功");

        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200, "个人信息修改失败");
        }
        return resultVO;
    }

    // lr: 用户修改头像
    @PostMapping("changMyHead")
    public ResultVO changMyHead(@RequestBody MultipartFile file,HttpServletRequest req){
            ResultVO resultVO = null;
            try {
                    User loginUser = (User)req.getSession().getAttribute("loginUser");
                    String name = file.getOriginalFilename();   //获得文件名
                    String suffix = name.substring(name.lastIndexOf("."));  //获得文件的后缀
                    String realPath = req.getServletContext().getRealPath("/upload");   //声明文件保存的位置
                    File dir = new File(realPath);
                    if(!dir.exists()) {
                        dir.mkdirs();
                    }
                    //声明一个新的文件名（不重复）
                    String fileName = UUID.randomUUID()+suffix;
                    //声明一个新的文件
                    File target = new File(dir,fileName);
                    //将上传的临时文件写入指定位置
                    file.transferTo(target);
                    loginUser.setU_head(fileName);
                    userService.update(loginUser);
                    resultVO = new ResultVO(200, "个人头像修改成功");
            } catch (Exception e) {
                e.printStackTrace();
                resultVO = new ResultVO(200, "个人头像修改失败");
            }
            return resultVO;
    }


    // lr: 修改个人密码
    @PostMapping("changeMyPassword/{safecode}/{password}/{newPassword}")
    public ResultVO changeMyPassword(@PathVariable String safecode,@PathVariable String password,@PathVariable String newPassword,HttpSession session){
        ResultVO resultVO = null;
        try {
            User loginUser = (User)session.getAttribute("loginUser");
            if (safecode.equals(loginUser.getU_safecode()) && password.equals(loginUser.getU_password())){
                loginUser.setU_password(newPassword);
                userService.update(loginUser);
                resultVO = new ResultVO(200, "密码修改成功");
            }else{
                resultVO = new ResultVO(200, "安全码或旧密码填写错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200, "密码修改失败");
        }
        return resultVO;


    }


    // 展示个人余额
    @GetMapping("showMyMoney")
    public ResultVO showMyMoney(HttpSession session){
        ResultVO resultVO = null;
        try {
            User loginUser = (User)session.getAttribute("loginUser");
            Integer myMoney = userService.findOne(loginUser.getU_id()).getU_money();
            resultVO = new ResultVO(200, "余额查询成功",myMoney);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200, "余额查询失败");
        }
        return resultVO;



    }


    // lr: 提现
    @GetMapping("OutMoney/{safeCode}/{howMoney}")
    public ResultVO OutMonet(@PathVariable String safeCode,@PathVariable Integer howMoney,HttpSession session){
        ResultVO resultVO = null;
        try {
            User loginUser = (User)session.getAttribute("loginUser");
            Integer myMoney = userService.findOne(loginUser.getU_id()).getU_money();
            if (myMoney>=howMoney){
                if (safeCode.equals(loginUser.getU_safecode())) {
                    loginUser.setU_money(myMoney - howMoney);
                    userService.update(loginUser);
                    resultVO = new ResultVO(200, "提现成功");
                }else{
                    resultVO = new ResultVO(200, "安全码错误");
                }
            }else{
                resultVO = new ResultVO(200, "余额不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200, "提现失败");
        }
        return resultVO;

    }

    // lr: 展示场馆的所有学员
    @GetMapping( "findStudentsByStore")
    public ResultVO findStudentsByStore(HttpSession session){
        ResultVO resultVO = null;
        try {
            User loginUser =  (User)session.getAttribute("loginUser");
            List<User> myStudentsList= userService.findStudentsByStore(loginUser);
            resultVO = new ResultVO(200,"查询我的学员成功",myStudentsList);
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200,"查询我的学员失败");
        }
        return resultVO;

    }
























}
