import React from "react";
// import axios from "axios";
import { Button } from "react-bootstrap";

import "./css/EduPro.css";
import ProBestMember from "./ProBestMember";
import { Link } from "react-router-dom";



class EduPro extends React.Component {
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

    onClickPost = () => {
    }
    onClickEmo = () => {
    }
    onClickComm = () => {
    }

    render() {
        const { nickname, username } = this.state;
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
                            <Link to="/">
                                <Button variant="outline-primary" className="main-btn" size="sm">알쓸싸잡</Button>{' '}
                            </Link>
                            <Button variant="outline-danger" className="main-btn" size="sm" onClick={this.onLogout}>로그아웃</Button>{' '}
                        </div>
                    </div>
                </div>
                
                <div className="pro-body-container">
                    <div className="headtag"><h2> 교육생 조회 </h2>
                    <hr /></div>
                    <div className="pro-body-head">
                        <Button className="category-btn" onClick={this.onClickPost}>게시글</Button>
                        <Button className="category-btn" onClick={this.onClickEmo}>이모지</Button>
                        <Button className="category-btn" onClick={this.onClickComm}>댓  글</Button>
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