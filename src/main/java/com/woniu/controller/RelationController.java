package com.woniu.controller;



import com.woniu.pojo.Relation;
import com.woniu.pojo.ResultVO;
import com.woniu.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author R&B
 * @create 2020/3/28 20:13:38
 */

@RestController
@RequestMapping("relations")
public class RelationController {

    @Autowired
    IRelationService relationService;

    @GetMapping
    public ResultVO findAll(){
        ResultVO resultVO= null;
        List<Relation> relationsList = relationService.findAll();
        if (relationsList!=null && relationsList.size()!=0){
            resultVO = new ResultVO(200,"查看全部关系成功",relationsList);
        }else{
            resultVO = new ResultVO(500,"查看全部关系失败");
        }
        return resultVO;
    }

    @GetMapping("{rid}")
    public ResultVO findOne(@PathVariable Integer rid){
        ResultVO resultVO= null;
        try{
            Relation relation= relationService.findOne(rid);
            resultVO = new ResultVO(200,"查看单独关系成功",relation);
        }catch (Exception e){
            resultVO = new ResultVO(500,"查看单独关系失败");
        }
        return resultVO;
    }


    @PostMapping("save")
    public ResultVO save(@RequestBody Relation relation){
        ResultVO resultVO= null;
        try{
            relationService.save(relation);
            resultVO = new ResultVO(200,"增加关系成功",relation);
        }catch (Exception e){
            resultVO = new ResultVO(500,"增加关系失败",relation);
        }
        return resultVO;
    }


    @PutMapping
    public ResultVO update(@RequestBody Relation relation){
        ResultVO resultVO= null;
        try{
            relationService.update(relation);
            resultVO = new ResultVO(200,"修改关系成功",relation);
        }catch (Exception e){
            resultVO = new ResultVO(500,"修改关系失败",relation);
        }
        return resultVO;
    }


    @DeleteMapping("{rid}")
    public ResultVO delete(@PathVariable Integer rid){
        ResultVO resultVO= null;
        try{
            relationService.delete(rid);
            resultVO = new ResultVO(200,"删除关系成功",rid);
        }catch (Exception e){
            resultVO = new ResultVO(500,"删除关系失败",rid);
        }
        return resultVO;
    }



}
