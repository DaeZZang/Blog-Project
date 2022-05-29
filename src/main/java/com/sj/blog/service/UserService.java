package com.sj.blog.service;

import com.sj.blog.model.User;
import com.sj.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌, Ioc를 해준다
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int 회원가입(User user){
        try {
            userRepository.save(user);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("UserService: 회원강비(): "+e.getMessage());
        }
        return -1;
    }
}
