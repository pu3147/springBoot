package cc.fbksy.edu.springBoot.user.controller;

import cc.fbksy.edu.springBoot.user.ao.UserAo;
import cc.fbksy.edu.springBoot.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping(value = "/login")
    @ResponseBody
    public UserAo doLogin(UserAo loinInfo){
        return  userManager.login(loinInfo);
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
}
