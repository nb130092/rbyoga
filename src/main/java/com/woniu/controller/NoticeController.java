package com.woniu.controller;

import com.sun.javafx.text.TextRun;
import com.woniu.pojo.Notice;
import com.woniu.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {
    @Autowired
    public INoticeService iNoticeService;
    @RequestMapping("findAll")
    public List<Notice> findAll(){
        return iNoticeService.findAll();
    }
    @RequestMapping("/findOne")
    public Notice findOne(Integer u_id){
        return iNoticeService.findOne(u_id);
    }
    @RequestMapping("/save")
    public void save(@RequestBody Notice notice){
        iNoticeService.save(notice);
    }
    @RequestMapping("/delete")
    public void delete(Integer u_id){
        iNoticeService.delete(u_id);
    }
    @RequestMapping("/update")
    public void update(@RequestBody Notice notice){
        iNoticeService.update(notice);
    }
}
