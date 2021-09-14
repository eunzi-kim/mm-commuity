import React from "react";
import { Form, Button } from "react-bootstrap";
import "./Login.css";

class Login extends React.Component {
  state = {
    alert: false, 
    id: "",
    password: "",
  };

  // state 직접 변경 불가능
  // this.setState 이용하여 state 데이터 변경

  alertClassName() {
    var alert  = this.state.alert
    // console.log(alert)
    return alert === false ? 'login-alert' : 'login-alert-view' // 삼항연산
  }

  onClickLogin = () => {
    // this.setState({alert: true})
    console.log(this.state)
  }

  onChangeId = (e) => {
    this.setState({id:e.target.value})
  } 

  onChangePassword = (e) => {
    this.setState({password:e.target.value})
  }

  render() {
    const logo = '/image/logo_2.png'

    return (
      <div className="login-container">
        <div className="login-left">
          <div className="login-left-body">
            <img className="login-logo" src={logo} />
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
                <Form.Control className="login-box" type="email" onChange={this.onChangeId} placeholder="Enter email or username" />
              </Form.Group>

              <Form.Group className="mb-5 login-password" controlId="formBasicPassword">
                <Form.Label>패스워드</Form.Label>
                <Form.Control className="login-box" type="password" onChange={this.onChangePassword} placeholder="Password" />
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