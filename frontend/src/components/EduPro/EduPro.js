import React from "react";
import axios from "axios";
import { Button, Dropdown, DropdownButton } from "react-bootstrap";
import { GiHamburgerMenu } from 'react-icons/gi';

import "./css/EduPro.css";
import ProBestMember from "./ProBestMember";
import { Link } from "react-router-dom";



class EduPro extends React.Component {
    state = {
        nickname: "",
        username: "",
        profileImg: ""
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
        
        // 다크모드
        if (localStorage.getItem('darkmode')) {
            document.querySelector('.pro-container').classList.add('dark-pro-container')
            // document.querySelector('.pro-body-bodylist').classList.add('dark-pro-body-bodylist::-webkit-scrollbar-thumb')
        }
    }

    onClickPost = (e) => {
        // 버튼이 이동되거나 취소될경우
        if (document.querySelector(".btnchk")) {
            document.querySelector(".btnchk").classList.remove("btnchk")
        }
        // 버튼이 체크돼있을 경우
        e.target.classList.add("btnchk")
    }
    onClickEmo = (e) => {
        if (document.querySelector(".btnchk")) {
            document.querySelector(".btnchk").classList.remove("btnchk")
        }
        // 버튼이 체크돼있을 경우
        e.target.classList.add("btnchk")
    }
    onClickComm = (e) => {
        if (document.querySelector(".btnchk")) {
            document.querySelector(".btnchk").classList.remove("btnchk")
        }
        // 버튼이 체크돼있을 경우
        e.target.classList.add("btnchk")
    }
    // 로그아웃
  fetchLogout = async (data) => {
    const url = 'https://j5c103.p.ssafy.io/api/auth/logout'
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
        const logo = '/image/logo_1.png'

        return (
            <div className="pro-container">
                <div className="pro-head">
                    <Link to="/">
                        <div className="pro-head-left">
                            <img width="180rem" src={logo} alt="알쓸싸잡"/>
                        </div>
                    </Link>
                    <div className="pro-head-right">
                        <h1>Pro 전용 교육생 조회</h1>
                    </div>
                    <div className="pro-head-profileall">
                        <div className="pro-main-profile">
                            <div className="mp-image">
                                <img src={ profileImg } alt={ username } className="profile-img" />
                            </div>
                            <div className="mp-info">
                                <div className="mp-nn"><b>{ nickname }</b></div>
                                <div className="mp-un">@{ username }</div>
                                </div>
                        </div>
                        <div className="pro-main-btns">
                            <Link to="/scrap">
                              <Button variant="outline-primary" className="main-btn" size="sm">즐겨찾기</Button>{' '}
                            </Link>
                            <Link to="/">
                                <Button variant="outline-primary" className="main-btn" size="sm">알쓸싸잡</Button>{' '}
                            </Link>
                            <Button variant="outline-danger" className="main-btn" size="sm" onClick={this.onLogout}>로그아웃</Button>{' '}
                        </div>
                    </div>
                </div>

                <div className="ep-header-responsive">
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
            <Link to="/" className="link"><Dropdown.Item as="button">알쓸싸잡</Dropdown.Item></Link>
            <Dropdown.Item as="button" onClick={this.onLogout} className="logout-text">로그아웃</Dropdown.Item>
          </DropdownButton>
                    <Link to="/">
                        <div className="ep-logo">
                        <img width="180rem" src={logo} alt="알쓸싸잡" />
                        </div>
                    </Link>     
                </div>
                
                <div className="pro-body-container">
                    <div className="headtag"><h2> 교육생 조회 </h2>
                    <hr /></div>
                    <div className="pro-body-head">
                        <div className="category-btn" onClick={this.onClickPost}>게시글</div>
                        <div className="category-btn" onClick={this.onClickEmo}>이모지</div>
                        <div className="category-btn" onClick={this.onClickComm}>댓  글</div>
                    </div>
                    <div className="pro-body-body">
                        <div className="pro-body-bodylist">
                            
                            <div><ProBestMember /></div>
                            
                        </div>
                    </div>
                </div>

            </div>
        )
    }
}

export default EduPro