package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.entity.Car;
import com.lemon.service.ICarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Slf4j
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ICarService iCarService;

    @GetMapping("/info/{Cid}")
    public String getInfo(@PathVariable("Cid") Integer cid, Model model){
        Car carInfo = this.iCarService.getById(cid);
        model.addAttribute("info", carInfo);
        return "carInfo";
    }

}
