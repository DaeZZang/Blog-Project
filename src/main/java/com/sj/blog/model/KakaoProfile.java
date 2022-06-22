package com.sj.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class KakaoProfile {

    public Long id;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;
    @Data
    static public class Properties {
        public String nickname;
    }

    @Data
    static public class KakaoAccount {

        public Boolean profile_nickname_needs_agreement;
        public Profile profile;
        @Data
        static public class Profile {

            public String nickname;

        }
        public Boolean has_email;
        public Boolean email_needs_agreement;
        public Boolean is_email_valid;
        public Boolean is_email_verified;
        public String email;
    }
}




