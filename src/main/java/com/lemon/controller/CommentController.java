package com.lemon.controller;


import com.lemon.entity.Comment;
import com.lemon.entity.User;
import com.lemon.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qaq
 * @since 2021-12-31
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService iCommentService;

    @PostMapping("/add")
    public String addComment(Model model, HttpSession session, String msg, @RequestParam Integer cid) {
        if (msg != null) {

            //System.out.println("----------------------");
            User user = (User) session.getAttribute("user");
            //System.out.println("----------------------");
            Comment comment = new Comment();
            comment.setCid(cid);
            comment.setMsg(msg);
            comment.setUid(user.getUid());
            iCommentService.save(comment);
        } else {
            model.addAttribute("status", "fail");
        }
        return "redirect:/index";
    }

}
