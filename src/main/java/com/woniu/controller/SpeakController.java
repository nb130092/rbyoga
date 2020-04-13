package com.woniu.controller;

import com.woniu.pojo.ResultVO;
import com.woniu.pojo.Speak;
import com.woniu.pojo.User;
import com.woniu.service.IRelationService;
import com.woniu.service.ISpeakService;
import com.woniu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangchendong
 * @create 2020/03/2020/3/26:21:28:22
 */
@RestController
@RequestMapping("speaks")
public class SpeakController {
    @Autowired
    ISpeakService speakService;
    @Autowired
    IUserService userService;
    @Autowired
    IRelationService relationService;


    @GetMapping
    public ResultVO findAll(){
        List<Speak> list = speakService.findAll();
        ResultVO resultVO = null;
        if(list!=null && list.size()>0){
            resultVO = new ResultVO(200,"查询所有动态成功",list);
        }else {
            resultVO = new ResultVO(500,"查询所有动态失败");
        }
        return resultVO;
    }
    @GetMapping("{sid}")
    public ResultVO findOne(@PathVariable Integer sid){
        Speak speak = speakService.findOne(sid);
        if (speak != null) {
            return new ResultVO(200, "查询speak成功", speak);
        }else {
            return new ResultVO(500, "查询speak失败");
        }
    }

    @PostMapping("save")
    public ResultVO save(@RequestBody Speak speak) {
        try {
            speakService.save(speak);
            return new ResultVO(200, "增加数据成功", speak);
        } catch (Exception e) {
            return new ResultVO(500, "增加数据失败");
        }
    }

    @DeleteMapping("{sid}")
    public ResultVO delete(@PathVariable Integer sid){
        try {
            speakService.delete(sid);
            return new ResultVO(200,"删除数据成功",sid);
        } catch (Exception e) {
            return new ResultVO(500, "删除数据失败");
        }
    }

    @PutMapping
    public ResultVO update(@RequestBody Speak speak){
        try {
            speakService.update(speak);
            return new ResultVO(200, "修改数据成功", speak);
        } catch (Exception e) {
            return new ResultVO(500, "修改数据失败");
        }
    }

    //lr：展示所有动态及发起人信息以及我关注的所有用户ID
    @GetMapping("showAllspeakWithUser")
    public ResultVO showAllspeakWithUser(HttpSession session){
        try {
            Map<String,Object> theMap = new HashMap<>();

            User loginUser =  (User)session.getAttribute("loginUser");
            List<Speak> speakList = speakService.showAllspeakWithUser();
            List<Integer> followIdList = relationService.findAllFollows(loginUser.getU_id()); //获取我关注的所有用户ID
            theMap.put("speakList",speakList);
            theMap.put("followIdList",followIdList);
            return new ResultVO(200, "修改数据成功", theMap);
        } catch (Exception e) {
            return new ResultVO(500, "修改数据失败");
        }
    }


    // lr: 发表动态
    @PostMapping("toSpeak")
    public ResultVO toSpeak(Speak speak,HttpSession session){

        try {
            User loginUser =  (User)session.getAttribute("loginUser");
            speak.setUser_id(loginUser.getU_id());
            speak.setS_time(new Date());
            speakService.save(speak);
            return new ResultVO(200, "动态发表成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(500, "动态发表失败");
        }

    }










}
