package com.alsselssajob.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    //Entity와 DB연결
    //String : user_id라는 PK값의 데이터 타입
    //null값에 대응하기 위해 Optional
    //findbyuserId isActive에 true값을 false로 - update메서드 없음.
    //메서드 직접 만들어야함!
    Optional<Token> findByUserIdAndIsActive(String userId, Boolean isActive);

}
