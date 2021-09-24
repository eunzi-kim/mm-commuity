[toc]

# 시작하기

## 프로토타입

![image-20210923163848739](README.assets/image-20210923163848739.png)



## 컴포넌트 구성

![image-20210923164002697](README.assets/image-20210923164002697.png)

![image-20210923164018262](README.assets/image-20210923164018262.png)





# 로그인

## 로그인 실패 경고창

- 삼항연산식을 이용하여 경고창 className으로 할당

  ```react
  // Login.js
  
  alertClassName() {
      var alert  = this.state.alert
      return alert === false ? 'login-alert' : 'login-alert-view'
    }
  
  return (
      <div className={this.alertClassName()}>
          <h5>회원정보를 확인해주세요</h5>
      </div>
  )
  ```

  ```css
  /* Login.css */
  
  .login-alert {
      visibility: hidden;  // div 공간 주기 위해 visibility 사용
  }
  
  .log-alert-view {
      display: flex;
      ...
  }
  ```

  

## 엔터키로 로그인 실행

- 엔터키 눌렀을 때, 로그인 실행시키기

  ```react
  // Login.js
  
  componentDidMount() {
      document.addEventListener("keyup", (e) => {  
          if (e.keyCode === 13) {
              this.onClickLogin()  // 로그인 실행 함수
          }
      })
  }
  ```

  

# 메인

## sessionStorage에 있는 값 가져오기

- `string`으로 되어있는 데이터를 `JSON`형태로 파싱해서 

  `state`에 입력하여 사용!

  ```react
  // Home.js
  
  takeUserInfo = () => {
      if (JSON.parse(sessionStorage.getItem('userInfo'))) {
          const userInfo = JSON.parse(sessionStorage.getItem('userInfo'))
          this.setState({nickname: userInfo.nickname})
          this.setState({username: userInfo.username})
      }
  }
  
  componentDidMount() {
      this.takeUserInfo()
  }
  ```



## 반응형 화면

- 너비가 992px미만이면, 모바일 화면으로 전환

  ```css
  /* Home.css */
  
  @media (max-width: 992px) {
      ...
  }
  ```

  -- 화면 캡쳐--



# 베스트 멤버

## Slider

- `react-slick` 이용하여 `carousel` 구현

  - 설치하기

  ```bash
  $ npm install react-slick --save  
  ```

  ```bash
  $ npm install slick-carousel --save  
  ```

  

  - 반드시 위에 `import` 해줘야함!!

  ```react
  import React, { Component } from "react";
  import Slider from "react-slick";
  import 'slick-carousel/slick/slick.css';
  import 'slick-carousel/slick/slick-theme.css';
  ```

  

  - 코드

  ```react
  class BestMemeber extends Component {
      ...
      render() {
          ...
          const settings = {  
              // 슬라이드 옵션들
              arrows: true,
              dots: true,
              infinite: true,
              speed: 500,
              slidesToShow: 1,
              slidesToScroll: 1,
              autoplay: true,
              autoplaySpeed: 3000,
          };
          
          return (
          	<div>
              	...
                  <Slider {...settings}>
                      // div에 슬라이드 각 페이지 구현
                  	<div>...</div>
                      <div>...</div>
                      <div>...</div>
                  </Slider>
              </div>
          )
      }
  }
  ```
  
  

