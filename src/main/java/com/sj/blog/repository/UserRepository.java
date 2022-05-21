package com.sj.blog.repository;

import com.sj.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    //JPA 네이밍 쿼리
    //SELECT * FROM user WHERE username = ?1 AND password = ?2 의 의미와 같다.
    User findByUsernameAndPassword(String username,String password);
//    @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2" ,nativeQuery = true)
//    User login(String username,String password); 이것도 같다!
}
