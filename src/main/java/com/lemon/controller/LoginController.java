package com.lemon.controller;


import com.lemon.service.IBuyerService;
import com.lemon.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

   /* @Autowired
    private IBuyerService iBuyerService;

    @Autowired
    private ISellerService iSellerService;*/

    @RequestMapping("/")
    public String index() {
        return "login";
    }

}
