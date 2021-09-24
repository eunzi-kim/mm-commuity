import React from "react";
import axios from "axios";
import { Form, Button } from "react-bootstrap";

import "./EduPro.css";
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
        const data = {

        }
    }
    onClickEmo = () => {
        const data = {

        }
    }
    onClickComm = () => {
        const data = {

        }
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
                </div>
                
                <div className="pro-body-container">
                <hr />
                    <div className="pro-body-head">
                    <Button className="category-btn" onClick={this.onClickPost}>게시글</Button>
                    <Button className="category-btn" onClick={this.onClickEmo}>이모지</Button>
                    <Button className="category-btn" onClick={this.onClickComm}>댓  글</Button>
                    </div>
                    <div className="pro-body-body">
                        <div className="pro-body-bodylist">
                            <div>학생 1</div>
                            <hr />
                            <div>학생 2</div>
                            <hr />
                            <div>학생 3</div>
                            <hr />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default EduPro