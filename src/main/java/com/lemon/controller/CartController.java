package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.entity.Car;
import com.lemon.entity.Cart;
import com.lemon.entity.OrderDetails;
import com.lemon.entity.User;
import com.lemon.service.ICarService;
import com.lemon.service.ICartService;
import com.lemon.service.IOrderDetailsService;
import com.lemon.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qaq
 * @since 2021-12-31
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICarService iCarService;

    @Autowired
    private ICartService iCartService;

    @Autowired
    private IOrderDetailsService iOrderDetailsService;


    /***
     * 访问 “购物车” 页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String loadCart(HttpSession session,Model model){

        User user = (User) session.getAttribute("user");
        Integer uid = user.getUid();

        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",uid);
        List<Cart> list = this.iCartService.list(qw);
        List<Integer> cid  = new ArrayList<>();
        for(Cart x : list){
            cid.add(x.getCid());
        }

        System.out.println("cid是"+cid);
        List<Car> list1 = new ArrayList<>();
        for(Integer x:cid){
            //System.out.println("这个x是"+x);
            QueryWrapper qu = new QueryWrapper<>();
            qu.eq("cid",x);
            Car car = this.iCarService.getOne(qu);
            //   System.out.println("这个Car是"+car);
            list1.add(car);
        }

        //获取卖家邮箱
        String[] email = new String[list.size()+1];
        int j = 1;
        for(Cart x : list){
            email[j] = this.iUserService.getById(x.getUid()).getEmail();
            j++;
        }
        model.addAttribute("email",email);

        //System.out.println(list1);
        model.addAttribute("list",list1);
        return "cart";
    }


    /**
     * 在 “购物车”页面，点击购买，加入购物车
     * @param cid
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/buycar")
    public String buyCar(@RequestParam("cid")int cid,  Model model, HttpSession session ){


        User user = (User) session.getAttribute("user");
        Car car1 = this.iCarService.getById(cid);

        int uid = user.getUid();
        int sid = car1.getUid();
        String cname = car1.getCname();
        double cost = car1.getPrice();
        LocalDateTime dateTime = car1.getTime();
        OrderDetails detail = new OrderDetails();
        detail.setBid(uid);
        detail.setCname(cname);
        detail.setCost(cost);
        detail.setSid(sid);
        detail.setTime(dateTime);
        // this.iCarService.removeById(cid);
        this.iOrderDetailsService.save(detail);

        return "redirect:/cart/";
    }


    /**
     * 访问 “历史订单” 页面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/history")
    public String indexHistory(HttpSession session, Model model){

        User user = (User)session.getAttribute("user");
        int uid = user.getUid();
        QueryWrapper qw = new QueryWrapper();
        qw.eq("bid",uid);
        List<OrderDetails> list = new LinkedList<>();
        list = this.iOrderDetailsService.list(qw);
        model.addAttribute("orders",list);

        //格式化日期
        int i = 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String[] buyTime = new String[list.size()+1];
        for(OrderDetails x : list){
            buyTime[i] = dtf.format(x.getTime());
            i++;
        }
        model.addAttribute("buytime",buyTime);

        //获取卖家邮箱
        String[] email = new String[list.size()+1];
        int j = 1;
        for(OrderDetails x : list){
            email[j] = this.iUserService.getById(x.getSid()).getEmail();
            j++;
        }
        model.addAttribute("email",email);
        return "history";
    }

    /***
     * 访问 “我的二手车” 页面
     */
    @RequestMapping("/mycar")
    public String indexMyCar(Model model, HttpSession session){

        //获取用户id
        User user = (User)session.getAttribute("user");
        int uid = user.getUid();

        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",uid);
        List<Car> list = this.iCarService.list(qw);
        model.addAttribute("cars",list);

        //格式化日期
        int i = 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String[] buyTime = new String[list.size()+1];
        for(Car x : list){
            buyTime[i] = dtf.format(x.getTime());
            i++;
        }
        model.addAttribute("buytime",buyTime);

        return "myCar";
    }


    /***
     * 添加我的二手车
     * @param car
     * @return
     */
    @PostMapping("/addcar")
    public String addCar(HttpSession session, Car car){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //获取用户id
        User user = (User)session.getAttribute("user");
        int uid = user.getUid();
        //对表单进行非空校验
        if(car.getUid() == null)
            car.setUid(uid);
        if(car.getImg() == null)
            car.setImg("https://lemon-blog-img.oss-cn-beijing.aliyuncs.com/car/car1.jpg");
        if(car.getTime()==null)
            car.setTime(LocalDateTime.now());
        this.iCarService.save(car);
        return "redirect:/cart/mycar";
    }



}
