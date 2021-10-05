import React from "react";
import { Button, Dropdown, DropdownButton } from "react-bootstrap";
import { GiHamburgerMenu } from 'react-icons/gi';

import "./css/Scrap.css"
import "./css/Myscrap.css"
import MyScrap from "./MyScrap"
import { Link } from "react-router-dom";

class Scrap extends React.Component {
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
  
  // 검색
  onSearch = () => {
    alert("검색!")
  }

  onSearchEnter = (e) => {
    if (e.keyCode === 13) {
      this.onSearch()
    }
  }

  componentDidMount() {
    this.takeUserInfo()

    // 다크모드
    if (localStorage.getItem('darkmode')) {
      document.querySelector('.scrap-main').classList.add('dark-scrap-main')
      document.querySelector('.MyScrap-container').classList.add('dark-MyScrap-container')
      document.querySelector(".scrap-search-input").classList.add("dark-search-input")
    }
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
      <div className="scrap-main">        
        <div className="scrap-main-header">
          <Link to="/">
            <div className="scrap-main-logo">
              <img width="180rem" src={logo} alt="알쓸싸잡" />
            </div>
          </Link>
          <div className="scrap-main-search">
            <input className="scrap-search-input" onKeyUp={this.onSearchEnter} autoFocus />
            <button className="scrap-search-btn" onClick={this.onSearch}><h4>🔍</h4></button>
          </div>
          <div className="scrap-main-profile">
            <div className="scrap-mp-image">
              이미지
            </div>
            <div className="scrap-mp-info">
              <div className="scrap-mp-nn"><b>{ nickname }</b></div>
              <div className="scrap-mp-un">@{ username }</div>
              <div className="scrap-mp-point">150 point</div>
            </div>
          </div>
          <div className="scrap-main-btns">
            <Link to="/">
              <Button variant="outline-primary" className="scrap-main-btn" size="sm">알쓸싸잡</Button>{' '}
            </Link>
            <Link to="/edupro">
              <Button variant="outline-primary" className="scrap-main-btn" size="sm">PRO설정</Button>{' '}
            </Link>
            <Button variant="outline-danger" className="scrap-main-btn" size="sm" onClick={this.onLogout}>로그아웃</Button>{' '}
          </div>
        </div>

        <div className="scrap-main-header-responsive">
          <DropdownButton className="hamburger" align="end" variant="secondary" id="dropdown-item-button" title={<GiHamburgerMenu />}>
            <Dropdown.ItemText className="scrap-dd-image">
              <div className="scrap-mp-image">
                이미지
              </div>
              <div className="scrap-mr-mp">
                { nickname }<br />
                150 point
              </div>
            </Dropdown.ItemText>
            <Dropdown.ItemText><div className="scrap-line"></div></Dropdown.ItemText>
            <Dropdown.Item as="button">즐겨찾기</Dropdown.Item>
            <Dropdown.Item as="button"><Link to="/edupro" className="scrap-link">PRO설정</Link></Dropdown.Item>
            <Dropdown.Item as="button" onClick={this.onLogout} className="scrap-logout-text">로그아웃</Dropdown.Item>
          </DropdownButton> 
          <Link to="/">
            <div className="scrap-main-logo">
              <img width="180rem" src={logo} alt="알쓸싸잡" />
            </div>
          </Link>         
          <div className="scrap-main-search">
            <input className="scrap-search-input" onKeyUp={this.onSearchEnter} autoFocus />
            <button className="scrap-search-btn" onClick={this.onSearch}><h4>🔍</h4></button>
          </div>          
        </div>

        <div className="MyScrap-container">
          <div className="MyScrap-list">
            {/* <h1> 즐겨찾기 목록 </h1> */}
            <MyScrap />
          </div>
        </div>
      </div>
    )
  }
}

export default Scrap