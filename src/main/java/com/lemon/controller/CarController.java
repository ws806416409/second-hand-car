package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.entity.Car;
import com.lemon.entity.Cart;
import com.lemon.entity.Comment;
import com.lemon.entity.User;
import com.lemon.service.ICarService;
import com.lemon.service.ICartService;
import com.lemon.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qaq
 * @since 2021-12-31
 */
@Controller
@Slf4j
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ICarService iCarService;

    @Autowired
    private ICartService iCartService;

    @Autowired
    private ICommentService iCommentService;

    @GetMapping("/info/{Cid}")
    public String getInfo(@PathVariable("Cid") Integer cid, Model model){
        Car carInfo = this.iCarService.getById(cid);
        model.addAttribute("info", carInfo);
        QueryWrapper<Comment> qu = new QueryWrapper<>();
        qu.eq("cid", cid);
        List<Comment> comments = iCommentService.list(qu);
        model.addAttribute("comments", comments);
        return "carInfo";
    }

    @GetMapping("/selectByType")
    public String selectByType(Model model, @RequestParam String type){
        QueryWrapper qu = new QueryWrapper();
        qu.eq("ctype",type);
        List cars = iCarService.list(qu);
        model.addAttribute("cars",cars);
        return "home";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Integer cid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Cart cart = new Cart();
        cart.setCid(cid);
        cart.setUid(user.getUid());
        iCartService.save(cart);
        return "redirect:/cart/";
    }
}
