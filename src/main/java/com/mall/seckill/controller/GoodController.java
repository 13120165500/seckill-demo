package com.mall.seckill.controller;

import com.mall.seckill.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/good")
@Slf4j
public class GoodController {

    @RequestMapping("/toList")
    public String toList(Model model, HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("userTicket")) {
                model.addAttribute("user", session.getAttribute(cookie.getValue()));
            }
        }
        return "list";
    }

}
