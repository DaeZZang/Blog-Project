package com.sj.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sj.blog.config.auth.PrincipalDetail;
import com.sj.blog.model.KakaoProfile;
import com.sj.blog.model.OAuthToken;
import com.sj.blog.model.User;
import com.sj.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userservice;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
        return "user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public Object kakaoCallback(String code) {//앞에 ResponseBody를 붙이면 데이터를 돌려주는 컨트롤러 함수가 됨
        //POST방식으로 key=value 카카오쪽으로 데이터를 요청
        RestTemplate rt = new RestTemplate();
        //Httpheader 만들기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        //HttpBody 만들기
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "35c25bdac670f486e59ab066a2c4811a");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);
        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기=>왜 담냐? exchange 함수가 HttpEntity를 받기 때문에!
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);
        //Http 요청하기 Post방식으로 response 변수의 응답을 받음
        ResponseEntity response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        //json데이터를 오브젝트에 담기
        ObjectMapper obMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = obMapper.readValue((String) response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 엑세스 토큰 : " + oAuthToken.getAccess_token());


        ///////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////


        //POST방식으로 kakao profile 요청
        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        ResponseEntity response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper obMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = obMapper2.readValue((String) response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        System.out.println("카카오 아이디 : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password("1234")
                .email(kakaoProfile.getKakao_account().getEmail())
                .oauth("kakao")
                .build();

        User originUser = userservice.회원찾기(kakaoUser.getUsername());
        if(originUser.getUsername()!=null){
            System.out.println("기존 회원입니다!");
        }else{
            userservice.회원가입(kakaoUser);
        }
        //로그인처리
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }
}
