package com.example.codefellowship.Controllers;

import com.example.codefellowship.Models.ApplicationUser;
import com.example.codefellowship.Models.Post;
import com.example.codefellowship.Repos.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String getSignInPage(){
        return "login.html";
    }

    @GetMapping("/signup")
    public String signUpPage(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName,
                               @RequestParam(value = "dateOfBirth") String dateOfBirth,
                               @RequestParam(value = "bio") String bio
    ) {
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/login");
    }

    @GetMapping("/user/{id}")
    public String  findUser(@PathVariable("id") Integer id, Model m , Principal p ){
        ApplicationUser user = applicationUserRepository.findById(id).get();
        m.addAttribute("user",user);
        if(user.getUsername().equals(p.getName())){
            return "profile.html";
        }
        m.addAttribute("user", user);
        return "usersprofile.html";
//        if (user != null){
//            m.addAttribute("user",user);
//            System.out.println(p.getName()+"  "+p.getName().getClass().getSimpleName());
//            System.out.println(user.getUsername() +"  "+user.getUsername().getClass().getSimpleName());
//            return"usersprofile.html";
//        }else{
//            return "usersprofile.html";
//        }
    }

    @PostMapping("/follow/{id}")
    public RedirectView follow(Principal p ,
                               @PathVariable(value = "id") Integer id){
        ApplicationUser user = applicationUserRepository.findById(id).get();
        ApplicationUser theFollower = applicationUserRepository.findByUsername(p.getName());
        theFollower.addFollowing(user);
        applicationUserRepository.save(theFollower);
        return new RedirectView("/user/"+id);
    }

    @GetMapping("/feed")
    public String showFollowedUsersPosts (Principal p,Model m){
        ApplicationUser theFollower = applicationUserRepository.findByUsername(p.getName());
//        ArrayList postsList = new ArrayList();
//        for (int i = 0; i < theFollower.getFollowedUsers().size(); i++) {
//            postsList.add(theFollower.
//        }
//        <div th:each="users:${followingUser}">
//    <div th:text="${users.getUsername}" ></div>
//            <div th:each="post:${users.getPost()}" >
//                <span  th:text="'The Body : ' + ${post.getBody()}"></span>
//            </div>
//        </div>
//    </div>
                m.addAttribute("users",theFollower.getFollowedUsers());
        return "followedPosts.html";
    }

//    @GetMapping("/error")
//    public String  findUser(@PathVariable("id") Integer id, Model m ){
//        ApplicationUser user = applicationUserRepository.findById(id).get();
//        if (user != null){
//            m.addAttribute("user",user);
//            return"profile.html";
//        }
//        return "error.html";
//    }
}
