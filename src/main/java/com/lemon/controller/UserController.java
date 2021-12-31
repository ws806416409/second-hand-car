package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.entity.Car;
import com.lemon.entity.User;
import com.lemon.service.ICarService;
import com.lemon.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class UserController {


    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICarService iCarService;

    @RequestMapping("/")
    public String login(){
        return "page-login";
    }

    @RequestMapping("/login")
    public String check_pwd(User user, Model model, HttpSession session){
        QueryWrapper<User> qu = new QueryWrapper<>();
        qu.eq("password", user.getPassword());
        qu.eq("email",user.getEmail());
        User us = iUserService.getOne(qu);
        List<Car> cars = iCarService.list();
        model.addAttribute("cars",cars);
        if(us != null){
            session.setAttribute("user",user);
            return "home";
        }
        return "page-login";
    }




}
