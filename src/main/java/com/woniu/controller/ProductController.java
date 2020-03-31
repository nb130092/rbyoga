package com.woniu.controller;

import com.woniu.pojo.Product;
import com.woniu.pojo.ResultVO;
import com.woniu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author R&B
 * @create 2020/3/26 19:15:07
 */

@RestController
@RequestMapping("products")
public class ProductController {

        @Autowired
        IProductService ps;

        @GetMapping
        public ResultVO findAll(){
            ResultVO resultVO= null;
                List<Product> productsList = ps.findAll();
                if (productsList!=null && productsList.size()!=0){
                    resultVO = new ResultVO(200,"查看全部商品成功",productsList);
                }else{
                    resultVO = new ResultVO(500,"查看全部商品失败");
                }
            return resultVO;
        }

        @GetMapping("{pid}")
        public ResultVO findOne(@PathVariable Integer pid){
            ResultVO resultVO= null;
            try{
                Product product= ps.findOne(pid);
                resultVO = new ResultVO(200,"查看单独商品成功",product);
            }catch (Exception e){
                resultVO = new ResultVO(500,"查看单独商品失败");
            }
            return resultVO;
         }


         @PostMapping
         public ResultVO save(@RequestBody Product product){
            ResultVO resultVO= null;
            try{
                ps.save(product);
                resultVO = new ResultVO(200,"增加商品成功",product);
            }catch (Exception e){
                resultVO = new ResultVO(500,"增加商品失败",product);
            }
            return resultVO;
         }


         @PutMapping
         public ResultVO update(@RequestBody Product product){
                ResultVO resultVO= null;
                try{
                    ps.update(product);
                    resultVO = new ResultVO(200,"修改商品成功",product);
                }catch (Exception e){
                    resultVO = new ResultVO(500,"修改商品失败",product);
                }
                return resultVO;
         }


         @DeleteMapping("{pid}")
         public ResultVO delete(@PathVariable Integer pid){
                ResultVO resultVO= null;
                try{
                    ps.delete(pid);
                    resultVO = new ResultVO(200,"删除商品成功",pid);
                }catch (Exception e){
                    resultVO = new ResultVO(500,"删除商品失败",pid);
                }
                return resultVO;
        }


        // 梁瑞：新增商品（带图片）
        @PostMapping("saveWithImage")
        public ResultVO saveWithImg(@RequestBody MultipartFile file, Product product,HttpServletRequest req){
            ResultVO resultVO = null;
            try {
                String name = file.getOriginalFilename();   //获得文件名
                String suffix = name.substring(name.lastIndexOf("."));  //获得文件的后缀
                String realPath = req.getServletContext().getRealPath("/upload");   //声明文件保存的位置
                System.out.println(product);
                System.out.println(realPath);
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
                // 把图片名存给商品对象
                product.setP_image(fileName);
                ps.save(product);

                resultVO = new ResultVO(200,"上传商品成功");

            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                resultVO = new ResultVO(200,"上传商品失败");
            }
            return resultVO;
        }





}
