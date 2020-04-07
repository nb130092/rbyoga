package com.woniu.controller;

import com.woniu.pojo.*;
import com.woniu.service.INoticeService;
import com.woniu.service.IProductService;
import com.woniu.service.IRelationService;
import com.woniu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Bobo
 * @Date 2020/4/1 0001 19:41
 */
//场馆业务
@Controller
@RequestMapping("store")
public class StoreController {
    @Autowired
    IUserService userService;

    @Autowired
    IProductService productService;

    @Autowired
    INoticeService noticeService;

    @Autowired
    IRelationService relationService;


    //信息完善、根据id查找到信息
    @ResponseBody
    @RequestMapping("showStore")
    public ResultVO showStore(Integer u_id){
        ResultVO resultVO = null;
        try {
            User user  = userService.findOne(u_id);
            resultVO =  new  ResultVO(200,"场馆信息已经找到",user);
        }catch (Exception e){
            resultVO =  new  ResultVO(500,"场馆信息没有找到");
        }
        return resultVO;
    }
    //信息完善--修改信息
    @ResponseBody
    @RequestMapping("updStore")
    public ResultVO updStore(User user){
        ResultVO resultVO = null;
        try{
            userService.update(user);
            resultVO=  new  ResultVO(200,"场馆信息修改完成");
        }catch (Exception e){
            resultVO=  new  ResultVO(500,"场馆信息修改失败");
        }
       return resultVO;
    }

    //场馆产品
    @ResponseBody
    @RequestMapping("showProduct")
    public ResultVO showProduct(Integer store_id){
        //根据场馆的id在产品表中查出所有的产品
        ResultVO resultVO = null;
        try{
            List<Product> productList = productService.findProductByStore_id(store_id);
            resultVO =  new  ResultVO(200,"场馆产品已经找到",productList);
        }catch (Exception e){
            resultVO =  new  ResultVO(500,"场馆产品没有找到");
        }
        return resultVO;
    }

    //新增场馆产品
    @ResponseBody
    @RequestMapping("addProduct")
    public ResultVO addProduct(Product product){
        //根据场馆的id在产品表中查出所有的产品
        ResultVO resultVO = null;
        try{
            //将场馆id传过来，完成场馆新增
            productService.save(product);
            resultVO =  new  ResultVO(200,"场馆产品已经新增");
        }catch (Exception e){
            resultVO =  new  ResultVO(500,"场馆产品没有新增");
        }
        return resultVO;
    }

    //删除场馆产品（一个）
    @ResponseBody
    @RequestMapping("delProduct")
    public ResultVO delProduct(Integer pid){
        ResultVO resultVO = null;
        try{
            productService.delete(pid);
            resultVO =  new  ResultVO(200,"场馆产品已经删除");
        }catch (Exception e){
            resultVO =  new  ResultVO(500,"场馆产品没有删除");
        }
        return resultVO;
    }


    //场馆通知
    @ResponseBody
    @RequestMapping("showNotice")
    public ResultVO showNotice(Integer init_id){
        //根据场馆的id在通知表中查出所有通知
        ResultVO resultVO = null;
        try{
            List<Notice> noticeList = noticeService.findNoticeByArrive_id(init_id);
            resultVO =  new  ResultVO(200,"场馆通知已经找到",noticeList);
        }catch (Exception e ){
            resultVO =  new  ResultVO(200,"场馆通知没有找到");
        }
        return resultVO;
    }

    //场馆回复通知（y/n）
    @ResponseBody
    @RequestMapping("replyNotice")
    public ResultVO showNotice(Notice notice){
        ResultVO resultVO = null;
        try{
            //通知人接收人互换
            int newArrive_id = notice.getInit_id();
            int newInit_id = notice.getArrive_id();
            notice.setArrive_id(newArrive_id);
            notice.setInit_id(newInit_id);
            noticeService.save(notice);
            resultVO =  new  ResultVO(200,"场馆通知回复成功",notice);
        }catch (Exception e ){
            resultVO =  new  ResultVO(200,"场馆通知回复失败");
        }
        return resultVO;
    }

    //场馆签约教练
    @ResponseBody
    @RequestMapping("showStoreYogaCoach")
    public ResultVO showStoreYogaCoach(Integer main_id){
        //根据场馆的id在关系表中查出与场馆相关人员信息
        List<User> StoreYogaCoach = new ArrayList<User>();
        ResultVO resultVO = null;
        //判断其角色为教员或者学员
        try{
            List<Relation> relationList   = relationService.findStorePerByMain_id(main_id);
            for (Relation relation : relationList) {
                //判断如果用户的角色为“”c（教练），则将其加到StoreYogaCoach中去
                User user = userService.findOne(relation.getGuest_id());
                if(user.getU_role().equals("C")){
                    StoreYogaCoach.add(user);
                }
                resultVO = new ResultVO(200,"场馆签约教练已经找到",StoreYogaCoach);
            }
        }catch (Exception e){
            resultVO = new ResultVO(200,"场馆签约教练没有找到");
        }
        return  resultVO ;
    }

//    //向场馆签约教练发送通知
//    @ResponseBody
//    @RequestMapping("replyStoreYogaCoach")
//    public ResultVO replyStoreYogaCoach() {
//        //传入的值为教练的id和场馆的id,还有
//        return  null;
//    }

    //场馆学员
    @ResponseBody
    @RequestMapping("showStoreStu")
    public ResultVO showStoreStu(Integer main_id){
        //根据场馆的id在关系表中查出与场馆相关人员信息
        List<User> StoreStu = new ArrayList<User>();
        ResultVO resultVO = null;
        try{
            List<Relation> relationList   = relationService.findStorePerByMain_id(main_id);
            for (Relation relation : relationList) {
                //判断如果用户的角色为“”b（xueyuan），则将其加到StoreYogaCoach中去
                User user = userService.findOne(relation.getGuest_id());
                if(user.getU_role().equals("B")){
                    StoreStu.add(user);
                }
                resultVO = new ResultVO(200,"场馆学员已经找到",StoreStu);
            }
        }catch (Exception e ){
            resultVO = new ResultVO(200,"场馆学员没有找到");
        }

        return  resultVO ;
    }
}
