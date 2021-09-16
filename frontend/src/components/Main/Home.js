import React from "react";
import { Button } from "react-bootstrap";

import "./css/Home.css"
import Contents from "./Contents";
import BestMember from "./BestMember";
import Ssafycial from "./Ssafycial";
import { Link } from "react-router-dom";

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
      <div className="main">
        <div className="main-header">
          <Link to="/">
            <div className="main-logo">
              <img width="180rem" src={logo} alt="알쓸싸잡" />
            </div>
          </Link>
          <div className="main-search">
            <input className="search-input" autoFocus />
            <button className="search-btn"><h4>🔍</h4></button>
          </div>
          <div className="main-profile">
            <div className="mp-image">
              이미지
            </div>
            <div className="mp-info">
              <div className="mp-nn"><b>{ nickname }</b></div>
              <div className="mp-un">@{ username }</div>
              <div className="mp-point">150 point</div>
            </div>
          </div>
          <div className="main-btns">
            <Button variant="outline-primary" className="main-btn" size="sm">즐겨찾기</Button>{' '}
            <Link to="/edupro">
              <Button variant="outline-primary" className="main-btn" size="sm">PRO설정</Button>{' '}
            </Link>
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
      </div>
    )
  }
}

export default Home