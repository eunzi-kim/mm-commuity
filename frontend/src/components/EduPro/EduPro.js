import React from "react";
import axios from "axios";
import { Button, Dropdown, DropdownButton } from "react-bootstrap";
import { GiHamburgerMenu } from 'react-icons/gi';

import "./css/EduPro.css";
import ProBestMember from "./ProBestMember";
import { Link } from "react-router-dom";

function compare(prev, post) {
    const prevpost_n = prev.post_n
    const postpost_n = post.post_n

    if (prevpost_n > postpost_n )return -1;
    else if(postpost_n > prevpost_n)return 1;
    return 0;
}
function compare2(prev, post) {
    const prevpost_n = prev.emoji
    const postpost_n = post.emoji

    if (prevpost_n > postpost_n )return -1;
    else if(postpost_n > prevpost_n)return 1;
    return 0;
}
function compare3(prev, post) {
    const prevpost_n = prev.comment
    const postpost_n = post.comment

    if (prevpost_n > postpost_n )return -1;
    else if(postpost_n > prevpost_n)return 1;
    return 0;
}

class EduPro extends React.Component {
    state = {
        users_info: [],
        nickname: "",
        username: "",
        profileImg: ""
    }
    // 교육생정보 api 호출
    fetchStudents = async (student_list) => {
        const url = `http://j5c103.p.ssafy.io:8082/api/edu-pro/best-students`
        
        
        await axios.get(url)
        .then(res => {
            const all_students = []
            console.log(res) // 확인 해주기 잘들어오면 _> .data 확인
            const student_list = res.data

            for (let i=0; i<student_list.length; i++){
                const student_info = {
                    "id":student_list[i]["userId"],
                    "image":student_list[i]["image"],
                    "username":student_list[i]["username"],
                    "postCount":student_list[i]["postCount"],
                    "reactingCount":student_list[i]["reactingCount"],
                    "reactedCount":student_list[i]["reactedCount"],
                    "point":student_list[i]["point"],
                }
                all_students.push(student_info)
            }
            console.log(all_students) // 확인 해주기
            this.setState({
                users_info : all_students
            })
        })
        .catch(err => {
          console.log(err)
        })
    
        // setState로 state 관리!!
      }
    

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
        this.fetchStudents()
        

        // 다크모드
        if (localStorage.getItem('darkmode')) {
            document.querySelector('.pro-container').classList.add('dark-pro-container')
            // document.querySelector('.pro-body-bodylist').classList.add('dark-pro-body-bodylist::-webkit-scrollbar-thumb')
        }
    }



    // 게시글 순서
    onClickPost = (e) => {
        // 버튼이 이동되거나 취소될경우
        if (document.querySelector(".btnchk")) {
            document.querySelector(".btnchk").classList.remove("btnchk")
            this.setState({
                users_info : this.state.users_info.sort(compare)
            })
        }

        // 버튼이 체크돼있을 경우
        e.target.classList.add("btnchk")
    }
    // 이모지 순
    onClickEmo = (e) => {
        if (document.querySelector(".btnchk")) {
            document.querySelector(".btnchk").classList.remove("btnchk")
            this.setState({
                users_info : this.state.users_info.sort(compare2)
            })
        }
        // 버튼이 체크돼있을 경우
        e.target.classList.add("btnchk")
    }
    // 댓글 순
    onClickComm = (e) => {
        if (document.querySelector(".btnchk")) {
            document.querySelector(".btnchk").classList.remove("btnchk")
            this.setState({
                users_info : this.state.users_info.sort(compare3)
            })
        }
        // 버튼이 체크돼있을 경우
        e.target.classList.add("btnchk")
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
                            
                            <div><ProBestMember sortmember={ this.state.users_info } /></div>
                            
                        </div>
                    </div>
                </div>

            </div>
        )
    }
}

export default EduPro