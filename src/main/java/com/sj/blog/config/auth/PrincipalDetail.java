package com.sj.blog.config.auth;

import com.sj.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {
    private User user;//이렇게 객체를 품고 있는 걸 컴포지션이라고 한다.

    public PrincipalDetail(User user){
        this.user=user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되면 false 만료안되면 true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지 않았는지 리턴한다
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비번이 만료되면 false 만료안되면 true
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //계정이 활성화(사용가능)되었는지 리턴한다. (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정의 권한을 리턴한다. 근데 좀 리턴타입이 복잡하다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_"+user.getRole(); //spring에서는 무조건 ROLE_{role}로 이름 지어야 한다
            }
        });//collectors.add(()->{return "ROLE_"+user.getRole();};로 적어도 된다!!!
        //람다식으로 적는게 훨 낫다

        return collectors;
    }
}
