package com.woniu.controller;

import com.woniu.pojo.ResultVO;
import com.woniu.pojo.Speak;
import com.woniu.service.ISpeakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangchendong
 * @create 2020/03/2020/3/26:21:28:22
 */
@RestController
@RequestMapping("speaks")
public class SpeakController {
    @Autowired
    ISpeakService speakService;
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

    //lr：展示所有动态及发起人信息
    @GetMapping("showAllspeakWithUser")
    public ResultVO showAllspeakWithUser(){
        try {
            List<Speak> speakList = speakService.showAllspeakWithUser();
            return new ResultVO(200, "修改数据成功", speakList);
        } catch (Exception e) {
            return new ResultVO(500, "修改数据失败");
        }
    }

}
