import React from "react";
import { Button, Dropdown, DropdownButton } from "react-bootstrap";
import { GiHamburgerMenu } from 'react-icons/gi';
import { Link } from "react-router-dom";

import Contents from "./Contents";
import BestMember from "./BestMember";
import Ssafycial from "./Ssafycial";

import "./css/Home.css"
import axios from "axios";

class Home extends React.Component {
  state = {
    nickname: "",
    username: "",
    profileImg: "",
    search: "",
  };

  takeUserInfo = () => {    
    const userInfo = JSON.parse(sessionStorage.getItem('userInfo'))
    this.setState({
      nickname: userInfo.nickname,
      username: userInfo.username,
      profileImg: userInfo.image
    })

  }

  componentDidMount() {
    this.takeUserInfo()

    // 다크모드 확인
    if (localStorage.getItem('darkmode')) {
      document.querySelector(".main").classList.add("dark-main-body")
      document.querySelector(".main-right").classList.add("dark-main-body")
      document.querySelector(".c-nav").classList.add("dark-nav")
      document.querySelector(".sub-nav").classList.add("dark-sub-nav")
      document.querySelector(".sub-nav-bottom").classList.add("dark-sub-nav-bottom")
      document.querySelector(".nav-down").classList.add("dark-nav-down")
      document.querySelector(".search-input").classList.add("dark-search-input")
      document.querySelector(".c-title-btn").classList.add("dark-c-title-btn")
      document.querySelector(".c-title-cal").classList.add("dark-c-title-cal")
      document.querySelector(".calender").classList.add("dark-calender")
      document.querySelector(".c-channels").classList.add("dark-c-channels")
    }
  }

  // 로그아웃
  fetchLogout = async (data) => {
    const url = 'http://j5c103.p.ssafy.io:8083/api/auth/logout'
    await axios.post(url, data)
    .then(res => {
      if (res.status === 204) {
        alert("로그아웃")
        sessionStorage.clear()
        window.location.replace("/login");
      }
    })
    .catch(err => {
      alert("로그아웃에 실패하였습니다😭")
    })
  }

  // 로그아웃 함수
  onLogout = () => {
    const userInfo = JSON.parse(sessionStorage.getItem('userInfo'))
    const data = {'userId': userInfo['userId']}
    this.fetchLogout(data)
  }

  render() {
    const { nickname, username, profileImg } = this.state;

    return (
      <div className="main">
        <div className="main-header-responsive">
          <DropdownButton className="hamburger" align="end" variant="secondary" id="dropdown-item-button" title={<GiHamburgerMenu />}>
            <Dropdown.ItemText className="dd-image">
              <div className="mp-image">
                <img src={ profileImg } alt={ username } className="profile-img" />
              </div>
              <div className="mr-mp">
                { nickname }<br />
                150 point
              </div>
            </Dropdown.ItemText>
            <Dropdown.ItemText><div className="line"></div></Dropdown.ItemText>
            <Link to="/scrap" className="link"><Dropdown.Item as="button">즐겨찾기</Dropdown.Item></Link>
            <Link to="/edupro" className="link"><Dropdown.Item as="button">PRO설정</Dropdown.Item></Link>
            <Dropdown.Item as="button" onClick={this.onLogout} className="logout-text">로그아웃</Dropdown.Item>
          </DropdownButton>       
        </div>

        <div className="main-container">
          <div className="main-left">
            <div className="find-contents">
              <Contents />
            </div>
          </div>

          <div className="main-right">
            <div className="right-header">
              <div className="main-profile">
                <div className="mp-image">
                  <img src={ profileImg } alt={ username } className="profile-img" />
                </div>
                <div className="mp-info">
                  <div className="mp-nn"><b>{ nickname }</b></div>
                  <div className="mp-un">@{ username }</div>
                </div>
              </div>
              <div className="main-btns">
                <Link to="/scrap">
                  <Button variant="outline-primary" className="main-btn" size="sm">즐겨찾기</Button>{' '}
                </Link>
                <Link to="/edupro">
                  <Button variant="outline-primary" className="main-btn" size="sm">PRO설정</Button>{' '}
                </Link>
                <Button variant="outline-danger" className="main-btn" size="sm" onClick={this.onLogout}>로그아웃</Button>{' '}
              </div>
            </div>
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