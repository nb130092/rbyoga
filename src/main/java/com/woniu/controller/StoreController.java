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

    //查看产品详细信息，获取到一个产品
    @ResponseBody
    @RequestMapping("findOneProduct")
    public ResultVO findOneProduct(Integer pid){
        ResultVO resultVO = null;
        try{
             Product product= productService.findOne(pid);
            resultVO =  new  ResultVO(200,"场馆产品已经查到",product);
        }catch (Exception e){
            resultVO =  new  ResultVO(500,"场馆产品没有查到");
        }
        return resultVO;
    }


    //修改场馆产品
    @ResponseBody
    @RequestMapping("updProduct")
    public ResultVO updProduct(Product product){
        ResultVO resultVO = null;
        try{
            productService.update(product);
            resultVO =  new  ResultVO(200,"场馆产品已经修改");
        }catch (Exception e){
            resultVO =  new  ResultVO(500,"场馆产品没有修改");
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
                if(user.getU_role().equals("教练")){
                    StoreYogaCoach.add(user);
                }
                resultVO = new ResultVO(200,"场馆签约教练已经找到",StoreYogaCoach);
            }
        }catch (Exception e){
            resultVO = new ResultVO(200,"场馆签约教练没有找到");
        }
        return  resultVO ;
    }


//    展示场馆签约的所有的教练的信息，教练头像旁边有发送通知按钮，点击可发送通知。
//     //向场馆签约教练发送通知
    @ResponseBody
    @RequestMapping("replyStoreYogaCoach")
    public ResultVO replyStoreYogaCoach(Integer u_id,Integer u_id2,String n_content) {
        //传入的值为 教练id和   场馆id    通知的信息。
        ResultVO resultVO = null;
        try {
            Notice notice = new Notice();
            notice.setInit_id(u_id2);
            notice.setArrive_id(u_id);
            notice.setN_content(n_content);
            notice.setN_isRead("N");
            notice.setN_isYes("N");
            noticeService.save(notice);
            resultVO = new ResultVO(200,"发送通知成功",notice);
        }catch (Exception e){
            resultVO = new ResultVO(500,"发送通知失败");
        }
        return  resultVO;
       }


    //    发送通知按钮旁边解除雇佣按钮，点击后会给该教练通知消息，并解除场馆于教练的关联关系。
    //解雇教练
    @ResponseBody
    @RequestMapping("dismissStoreYogaCoach")
    public ResultVO dismissStoreYogaCoach(Integer u_id,Integer u_id2) {
        //传入的值为                          教练id       场馆id
        ResultVO resultVO = null;
        try {
            //创建解雇教练的通知
            Notice notice = new Notice();
            notice.setN_isRead("N");
            notice.setN_isYes("N");
            notice.setInit_id(u_id2);
            notice.setArrive_id(u_id);
            notice.setN_content("解雇！");
            noticeService.save(notice);
            //在通知表中 根据教练id(main_id(外))和场馆id(Guest_id(外)) 查出对应的信息  再删除场馆和教练的关系
            //Relation relation = relationService.findRelationByMain_idAndGuest_id(u_id2,u_id);
            //relationService.delete(relation);
            resultVO = new ResultVO(200,"发送解雇通知成功",notice);
        }catch (Exception e){
            resultVO = new ResultVO(500,"发送解雇通知失败");
        }
        return  resultVO;
    }
    //为该教练分配学员。         教练和学员的关系如何？    一對多
    //点击分配学员的时候，根据场馆id在关系表中，main_id为场馆id 的时候，查出所有的Guest_id，
    //再根据 Guest_id 在user表中查出所有u_role =“” “”'“学员' 的列
    //展示某场馆下的所有学员    还有选中的教练
    @ResponseBody
    @RequestMapping("showStoreAllStu")
    public ResultVO showStoreAllStu(){

        return  null;
    }
    //  教练To学员
    //  将所有的学员展示出
    //

    // 查找  签约场馆To学员
    @ResponseBody
    @RequestMapping("storeYogaCoachAllotStu")
    public ResultVO storeYogaCoachAllotStu() {
        //传入的值为                          教练id       场馆id
        ResultVO resultVO = null;

        return  resultVO;
    }

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
                if(user.getU_role().equals("学员")){
                    StoreStu.add(user);
                }
                resultVO = new ResultVO(200,"场馆学员已经找到",StoreStu);
            }
        }catch (Exception e ){
            resultVO = new ResultVO(200,"场馆学员没有找到");
        }
        return  resultVO ;
    }
    //旁边还有发送通知按钮，点击后，可像该学员发送通知！
    @ResponseBody
    @RequestMapping("replyStu")
    public ResultVO replyStu(Integer u_id,Integer u_id2,String n_content) {
        //传入的值为                学员id和        场馆id        通知的信息。
        ResultVO resultVO = null;
        try {
            Notice notice = new Notice();
            notice.setInit_id(u_id2);
            notice.setArrive_id(u_id);
            notice.setN_isRead("N");
            notice.setN_isYes("N");
            notice.setN_content(n_content);
            noticeService.save(notice);
            resultVO = new ResultVO(200,"发送通知成功",notice);
        }catch (Exception e){
            resultVO = new ResultVO(500,"发送通知失败");
        }
        return  resultVO;
    }
}
