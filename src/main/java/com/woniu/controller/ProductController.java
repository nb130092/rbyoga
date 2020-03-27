package com.woniu.controller;

import com.woniu.pojo.Product;
import com.woniu.pojo.ResultVO;
import com.woniu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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











}
