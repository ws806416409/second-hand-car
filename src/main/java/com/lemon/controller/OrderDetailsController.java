package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.entity.OrderDetails;
import com.lemon.service.IOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qaq
 * @since 2021-12-31
 */
@Controller
@RequestMapping("/order")
public class OrderDetailsController {

    @Autowired
    private IOrderDetailsService iOrderDetailsService;

    @GetMapping("/getInfo")
    public String get(Model model, @RequestParam Integer oid){
        QueryWrapper<OrderDetails> qu=new QueryWrapper<>();
        qu.eq("oid",oid);
        OrderDetails orderDetail = iOrderDetailsService.getOne(qu);
        model.addAttribute("order", orderDetail);
        return "carInfo";
    }



}
