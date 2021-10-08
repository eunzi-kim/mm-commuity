package com.alsselssajob.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    //Entity와 DB연결
    //String : user_id라는 PK값의 데이터 타입
    //null값에 대응하기 위해 Optional
    Optional<Token> findByUserIdAndIsActive(String userId, Boolean isActive);

    // userId를 사용하여 Token을 찾는 매서드
    Optional<Token> findByToken(String token);
}
