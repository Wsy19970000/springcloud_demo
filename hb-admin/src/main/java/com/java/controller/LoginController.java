package com.java.controller;

import com.java.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String toIndex(@RequestParam(name = "username") String username ,
                          @RequestParam(name = "pwd") String pwd , HttpSession session){
        boolean flag = loginService.getUser(username, pwd);
        if(flag){
            session.setAttribute("username",username);
            return "/index.jsp";
        }
        return "/login_v2.jsp";
    }
}
