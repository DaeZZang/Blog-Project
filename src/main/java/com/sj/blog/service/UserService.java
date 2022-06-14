package com.sj.blog.service;

import com.sj.blog.model.RoleType;
import com.sj.blog.model.User;
import com.sj.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌, Ioc를 해준다
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager; // 얘를 통해 세션값 변경해야함

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
    @Transactional
    public void 회원수정(User user){
        //먼저 select을 해서 DB에서 정보 가져옴 => 영속화하기 위해
        //영속화한 오브젝트를 변경하면 자동으로 DB도 변경함
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
        //회원수정 함수 종료 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 됨
        //영속화된 객체의 변화가 감지되면 더티체킹되어 자동으로 update문을 DB에 날려줌 => 최종적으로 회원수정됨

        //세션등록하기
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
