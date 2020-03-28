package com.woniu.controller;

import com.woniu.pojo.Orderz;
import com.woniu.pojo.ResultVO;
import com.woniu.service.IOrderzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Bobo
 * @Date 2020/3/26 0026 17:54
 */

@RestController
@RequestMapping("orderz")
public class OrderzController {

    @Autowired
    private IOrderzService orderzService;


    @GetMapping
    public ResultVO findAllOrderz(){
        List<Orderz> orderzList = orderzService.findAllOrderz();
        ResultVO resultVO = null;
        if(orderzList != null && orderzList.size() > 0) {
            resultVO = new ResultVO(200,"查询所有订单成功", orderzList);
        } else {
            resultVO = new ResultVO(500, "查询所有订单失败");
        }
        return resultVO;
    }
    @PostMapping
    public ResultVO saveOrderz(@RequestBody Orderz orderz) {
        try {
            System.out.println(orderz);
            orderzService.saveOrderz(orderz);
            return new ResultVO(200, "增加订单成功", orderz);
        } catch (Exception e) {
            return new ResultVO(500, "增加订单失败", orderz);
        }
    }

    @GetMapping("{o_id}")
    public ResultVO findOneOrderz(@PathVariable Integer o_id) {
        Orderz orderz = orderzService.findOneOrderz(o_id);
        if(orderz != null) {
            return new ResultVO(200, "查询订单成功", orderz);
        } else {
            return new ResultVO(500, "查询订单失败");
        }
    }

    @DeleteMapping("{o_id}")
    public ResultVO deleteOrderz(@PathVariable Integer o_id) {
        try {
            orderzService.deleteOrderz(o_id);
            return new ResultVO(200, "删除数据成功", o_id);
        } catch (Exception e) {
            return new ResultVO(500, "删除数据失败", o_id);
        }
    }

    @PutMapping
    public ResultVO updateOrderz(@RequestBody Orderz orderz) {
        try {
            orderzService.updateOrderz(orderz);
            System.out.println(orderz);

            return new ResultVO(200, "修改数据成功", orderz);
        } catch (Exception e) {
            return new ResultVO(500, "修改数据失败", orderz);
        }
    }
}
