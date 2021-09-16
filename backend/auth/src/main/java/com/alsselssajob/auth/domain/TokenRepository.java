package com.alsselssajob.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
    //String : user_id라는 PK값의 데이터 타입
    //null값에 대응하기 위해 Optional


}
