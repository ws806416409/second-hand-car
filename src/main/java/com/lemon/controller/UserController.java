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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        if(us != null){
            session.setAttribute("user",us);
            return "redirect:/index";
        }
        return "page-login";
    }

    @GetMapping("/index")
    public String main(HttpSession session, Model model) {
        List<Car> cars = iCarService.list();
        model.addAttribute("cars",cars);
        Set<String> carTypes = new HashSet<>();
        for(Car car : cars){
            carTypes.add(car.getCtype());
        }
        session.setAttribute("types", carTypes);
        return "home";
    }


    @GetMapping("/register")
    public String register(){
        System.out.println("-------------------------");
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(User user){
        //注册成功，则返回登录页面
        if(user.getEmail()!="" && user.getPassword()!=""){
            iUserService.save(user);
            return "page-login";
        }
        //注册失败，则继续返回注册页面
        else
            return "register";
    }

}
