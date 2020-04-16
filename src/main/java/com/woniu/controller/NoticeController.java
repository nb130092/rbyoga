package com.woniu.controller;

import com.sun.javafx.text.TextRun;
import com.woniu.pojo.Notice;
import com.woniu.pojo.ResultVO;
import com.woniu.pojo.User;
import com.woniu.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    public INoticeService iNoticeService;

    @GetMapping
    public ResultVO findAll(){
        ResultVO resultVO = null;
        List<Notice> noticeList = iNoticeService.findAll();
        if(noticeList != null && noticeList.size() != 0){
            resultVO = new ResultVO(200,"查看全部通知成功",noticeList);
        } else {
            resultVO  = new ResultVO(500,"查看全部通知失败");
        }
        return resultVO;
    }
    @GetMapping("{n_id}")
    public ResultVO findOne(@PathVariable Integer n_id){
        ResultVO resultVO = null;
        try {
            Notice notice = iNoticeService.findOne(n_id);
            resultVO = new ResultVO(200,"产看单独通知成功",notice);
        } catch (Exception e){
            resultVO = new ResultVO(500,"产看单独通知失败");
        }
        return resultVO;

    }
    @PostMapping
    public ResultVO save(@RequestBody Notice notice){
        ResultVO resultVO = null;
        try {
            iNoticeService.save(notice);
            resultVO = new ResultVO(200,"添加通知成功",notice);
        } catch (Exception e){
            resultVO = new ResultVO(500,"添加通知失败");
        }
        return resultVO;
    }
    @DeleteMapping("{n_id}")
    public ResultVO delete(@PathVariable Integer n_id){
        ResultVO resultVO = null;
        try {
            iNoticeService.delete(n_id);
            resultVO = new ResultVO(200,"删除通知成功",n_id);
        } catch (Exception e){
            resultVO = new ResultVO(500,"删除通知失败");
        }
        return resultVO;
    }
    @PutMapping
    public ResultVO update(@RequestBody Notice notice){
        ResultVO resultVO = null;
        try {
            iNoticeService.update(notice);
            resultVO = new ResultVO(200,"修改通知成功",notice);
        } catch (Exception e){
            resultVO = new ResultVO(500,"修改通知失败");
        }
        return resultVO;
    }


    // 给学员发送通知
    @GetMapping("toNoticeStudent")
    public ResultVO toNoticeStudent(Notice notice,HttpSession session){
        ResultVO resultVO = null;
        try {
            User loginUser = (User)session.getAttribute("loginUser");
            notice.setN_isRead("N");
            notice.setInit_id(loginUser.getU_id());
            iNoticeService.save(notice);
            resultVO = new ResultVO(200, "发送通知成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = new ResultVO(200, "发送通知失败");
        }
        return resultVO;

    }
}
