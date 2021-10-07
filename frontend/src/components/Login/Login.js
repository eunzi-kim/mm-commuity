import React from "react";
import axios from "axios";
import { Form, Button } from "react-bootstrap";
import "./Login.css";

class Login extends React.Component {
  state = {
    alert: false, 
    id: "",
    password: "",
    darkmode: "",
  };

  // state 직접 변경 불가능
  // this.setState 이용하여 state 데이터 변경
 
  alertClassName() {
    var alert  = this.state.alert
    return alert === false ? 'login-alert' : 'login-alert-view' // 삼항연산
  }

  // 로그인 실행 함수
  fetchLogin = async ( data ) => {
    const url = "http://j5c103.p.ssafy.io:8083/api/auth/login"

    await axios.post(url, data)
    .then(res => {
      const userInfo = {
        "userId": res.data.userId,
        "username": res.data.userName,
        "nickname": res.data.nickName,
        "image": res.data.image       
      }
      const token = res.data.token

      sessionStorage.setItem('userInfo', JSON.stringify(userInfo))
      sessionStorage.setItem('token', JSON.stringify(token))
      window.location.replace("/");
    })
    .catch(err => {
      this.setState({alert: true})
    })
  }

  // 로그인 버튼 클릭
  onClickLogin = () => {
    const data = {
      "id": this.state.id,
      "password": this.state.password
    }
    this.fetchLogin(data)
  }

  onChangeId = (e) => {
    this.setState({id:e.target.value})
  } 

  onChangePassword = (e) => {
    this.setState({password:e.target.value})
  }

  componentDidMount() {
    // 엔터키 눌렀을 때, 로그인
    document.addEventListener("keyup", (e) => {
      if (e.keyCode === 13) {
        this.onClickLogin()
      }
    })

    // 다크모드 확인
    if (localStorage.getItem('darkmode')) {
      document.querySelector(".login-container").classList.add("dark-login-bg")
      document.querySelector(".login-left").classList.add("dark-left-bg")
      document.querySelector(".login-right").classList.add("dark-right-bg")
      document.querySelector(".login-id-box").classList.add("dark-login-box")
      document.querySelector(".login-password-box").classList.add("dark-login-box")
      document.querySelector(".btn-primary").classList.add("btn-secondary")
      document.querySelector(".signin-btn").classList.add("dark-login-btn")
      document.querySelector(".login-left-footer").classList.add("dark-login-left-footer")
    }
  }

  render() {
    const logo = '/image/logo_2.png'

    return (
      <div className="login-container">
        <div className="login-left">
          <div className="login-left-body">
            <img className="login-logo" src={logo} alt="알쓸싸잡" />
            <h1 className="login-left-text">Mattermost & SSAFY</h1>
          </div>
          <div className="login-left-footer">
            <h5>ⓒ 성규는 못말려</h5>
          </div>
        </div>

        <div className="login-right">
          <div className="login-right-all">
            <div className="login-right-header">
              <p>Login</p>
            </div>
            <Form className="login-form">
              <Form.Group className="mb-4 login-id" controlId="formBasicEmail">
                <Form.Label>전자우편 또는 사용자 이름</Form.Label>
                <Form.Control className="login-id-box" type="email" onChange={this.onChangeId} placeholder="Enter email or username" />
              </Form.Group>

              <Form.Group className="mb-5 login-password" controlId="formBasicPassword">
                <Form.Label>패스워드</Form.Label>
                <Form.Control className="login-password-box" type="password" onChange={this.onChangePassword} placeholder="Password" />
              </Form.Group>

              <div className={this.alertClassName()}>
                <h5>회원정보를 확인해주세요</h5>
              </div>

              <Button className="signin-btn" onClick={this.onClickLogin}>
                <h4>Sign In</h4>
              </Button>
            </Form>
          </div>
        </div>
      </div>
    )
  }
}

export default Login