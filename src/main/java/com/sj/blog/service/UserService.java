package com.sj.blog.service;

import com.sj.blog.model.RoleType;
import com.sj.blog.model.User;
import com.sj.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌, Ioc를 해준다
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public int 회원가입(User user){
        try {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            user.setPassword(encPassword);
            user.setRole(RoleType.USER);
            userRepository.save(user);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("UserService: 회원가입(): "+e.getMessage());
        }
        return -1;
    }
}
