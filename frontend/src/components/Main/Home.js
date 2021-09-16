import React from "react";
import { Link } from "react-router-dom"; 
import { Button } from "react-bootstrap";

import "./css/Home.css"
import Contents from "./Contents";
import BestMember from "./BestMember";
import Ssafycial from "./Ssafycial";

class Home extends React.Component {
  state = {
    nickname: "",
    username: ""
  };

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

  // 로그아웃 함수
  onLogout = () => {
    sessionStorage.clear()
    window.location.replace("/login");
  }

  render() {
    const { nickname, username } = this.state;
    const logo = "/image/logo_1.png"

    return (
<<<<<<< HEAD
      <div>
        <h1>메인화면</h1>
        <p>{nickname}</p>
        <Link to="/login" className="login">로그인</Link>
        <div></div>
        <Link to="/" onClick={this.onLogout}>로그아웃</Link>
        <div></div>
        <Link to="/edupro" className="edupro">프로님들만</Link>
=======
      <div className="main">
        <div className="main-header">
          <div className="main-logo">
            <img width="250rem" src={logo} />
          </div>
          <div className="main-search">
            <input className="search-input" />
            <button className="search-btn"><h4>🔍</h4></button>
          </div>
          <div className="main-profile">
            <div className="mp-image">
              이미지
            </div>
            <div className="mp-info">
              <div>{ nickname }</div>
              <div>@{ username }</div>
              <div>150 point</div>
            </div>
          </div>
          <div className="main-btns">
            <Button variant="outline-primary" className="main-btn" size="sm">즐겨찾기</Button>{' '}
            <Button variant="outline-primary" className="main-btn" size="sm">설  정</Button>{' '}
            <Button variant="outline-danger" className="main-btn" size="sm" onClick={this.onLogout}>로그아웃</Button>{' '}
          </div>
        </div>
        <div className="main-body">
          <div className="main-left">
            <div className="find-contents">
              <Contents />
            </div>
          </div>
          <div className="main-right">
            <div className="best-member">
              <BestMember />
            </div>
            <div className="ssafycial">
              <Ssafycial />
            </div>
          </div>
        </div>
>>>>>>> 0f14fcd ([S05P21C103-105] [FE-rladmswlek] feat: 메인페이지 구현)
      </div>
    )
  }
}

export default Home