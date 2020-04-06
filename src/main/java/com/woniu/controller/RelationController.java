package com.woniu.controller;

import com.woniu.pojo.Relation;
import com.woniu.pojo.ResultVO;
import com.woniu.pojo.User;
import com.woniu.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author R&B
 * @create 2020/3/26 19:15:07
 */

@RestController
@RequestMapping("relation")
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


        //lxy:增加 点击关注增加
        @PostMapping("follow")
        @ResponseBody
        public ResultVO follow(@RequestBody User user, HttpSession session){
            try{

                User loginUser = (User) session.getAttribute("loginUser");
                String loginUserRole = loginUser.getU_role();
                String userRole = user.getU_role();
                //主客关系
                String rel=loginUserRole+"To"+userRole;
                Relation relation = new Relation(null, rel, loginUser.getU_id(), user.getU_id());
                relationService.save(relation);
                return new ResultVO(200, "已关注");
            }catch(Exception e){
                return  new ResultVO(500, "关注失败" );

            }
        }


    //lxy:删除 点击取消关注
    @DeleteMapping("cancelfollow")
    @ResponseBody
    public ResultVO cancleFollow(@RequestBody User user, HttpSession session){
        try{

            User loginUser = (User) session.getAttribute("loginUser");
            Map<String,Integer> map = new HashMap<>();
            map.put("main_id",loginUser.getU_id());
            map.put("guest_id", user.getU_id());
            relationService.cancelFollow(map);
            return new ResultVO(200, "取消关注");
        }catch(Exception e){
            return  new ResultVO(500, "取消关注失败" );

        }
    }

    //lxy:增加 我的关注
    @GetMapping("myFollow")
    @ResponseBody
    public ResultVO myFollow(HttpSession session){
        try{

            //User loginUser = (User) session.getAttribute("loginUser");

            // Integer u_id = loginUser.getU_id();
            Integer u_id1=2;
            Relation relation = relationService.myFollow(u_id1);
            return new ResultVO(200, "获取成功",relation);
        }catch(Exception e){
            return  new ResultVO(500, "获取失败" );

        }
    }




}
