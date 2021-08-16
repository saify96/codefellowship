package com.example.codefellowship.Controllers;

import com.example.codefellowship.Models.ApplicationUser;
import com.example.codefellowship.Models.Post;
import com.example.codefellowship.Repos.ApplicationUserRepository;
import com.example.codefellowship.Repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;
@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    ApplicationUserRepository applicationUserRepository;


//    @GetMapping("/posts")
//    public String getSong(Model model) {
//        model.addAttribute("posts",postRepository.findAll());
//        return "profile.html";
//    }


//    @PostMapping("/addsong")
//    public RedirectView addSong(@RequestParam(value = "body") String body,
//                                @RequestParam(value = "createdAt") Date createdAt) {
//        Post newPost = new Post(body,createdAt);
//        applicationUserRepository.save(newPost);
//        return new RedirectView("/login");
//    }   String body , Date createdAt , int id

    @PostMapping("/addpost")
    public RedirectView addpost(Principal p ,String postBody) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        Post newPost = new Post(postBody,user);
        postRepository.save(newPost);
        return new RedirectView("/user/" + user.getId());
    }

}
