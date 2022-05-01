package com.sj.blog.test;

import org.springframework.web.bind.annotation.*;


@RestController
public class HttpControllerTest {
    private static final String TAG = "HttpControllerTest : ";
    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder().username("sj4225").password("123").email("sj4225@test.com").build();
        System.out.println(TAG + "getter : " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "setter : " + m.getId());
        return "lombok test finished";
    }
    //http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest(@RequestParam int id, @RequestParam String username){
        return "get 요청: "+id+", "+username;
    }
    //http://localhost:8080/http/post
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }
    //http://localhost:8080/http/put
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "post 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }
    //http://localhost:8080/http/delete
    @DeleteMapping("/http/delete")
    public String deleteTest(@RequestBody Member m){
        return "post 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }
}
