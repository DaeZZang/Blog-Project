package com.sj.blog.test;

import com.sj.blog.model.RoleType;
import com.sj.blog.model.User;
import com.sj.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

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
        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입";
    }

    @Transactional //함수 종료시 자동 커밋
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("Id: " + id);
        System.out.println("password: " +requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정 실패");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return "없는거를 왜 지워 이녀나";
        }
        return "삭제: "+id;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID 없음 id: "+ id));
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size =2 ,sort="id",direction= Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }
}
