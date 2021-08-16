package com.example.codefellowship.Controllers;

import com.example.codefellowship.Models.ApplicationUser;
import com.example.codefellowship.Repos.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getHome(Principal p , Model m){
        if(p!=null){
            m.addAttribute("userName", p.getName());
            return "home.html";
        }
        return "home.html";
    }
    @GetMapping("/profile")
    public String getProfile(Principal p, Model m){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user" , user);
        return "profile.html";
    }


//    @GetMapping("/")
//    public String getHome(){
//        return "home.html";
//    }
}
