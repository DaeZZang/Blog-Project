package com.sj.blog.test;

import com.sj.blog.model.User;
import com.sj.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @Autowired//의존성 주입(DI)
    private UserRepository userRepository;

    //http://localhost:8000/blog/dummy/join
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("username: "+ user.getUsername());
        System.out.println("password: "+ user.getPassword());
        System.out.println("email: "+ user.getEmail());
        user.setRole("user");
        userRepository.save(user);

        return "회원가입";
    }

}
