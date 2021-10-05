import React from "react";
import Slider from "react-slick";
import DatePicker, { registerLocale } from "react-datepicker";
import { AiOutlineStar, AiFillStar } from 'react-icons/ai';
import $ from "jquery";
import { Link } from "react-router-dom";

import "react-datepicker/dist/react-datepicker.css";
import ko from 'date-fns/locale/ko';

import "./css/Contents.css";

class Contents extends React.Component {
  state = {
    Group: [],
    Channel: {},
    ChkChannel: [],
    selectedDate: new Date(),
    Content: [],
  }

  componentDidMount() {
    // 서버에서 api 받아와서 데이터 정리!!
    this.setState({
      Group: ["그룹1", "그룹2", "그룹3", "그룹4", "그룹5", "개인 메시지"],
      // 채널 dict 정리할 때, 비공개 채널은 자물쇠 표시!!
      Channel: {
        "그룹1": ["공지사항", "잡담", "Q&A", "Q&A(자기주도 PJT)", "🔒광주 1반"],
        "그룹2": ["1. SW 스타트 캠프", "2. 온라인 코칭", "공지사항", "이벤트", "일타싸피", "잡담", "종강식 이벤트", "BGM", "🔒5미자차", "🔒광주1반 거북컴들"],
        "그룹3": ["공지사항", "Q&A"],
        "그룹4": ["공지사항", "수다방", "팀구성:D", "QnA"],
        "그룹5": ["공지사항", "수다방", "팀구성:D", "QnA"],
        // 개인 메시지 Group dict에 추가해주기
        "개인 메시지": ["곽동희(교육프로)", "곽온겸(광주실습코치)", "광주 1반 이민교(전임교수)", "광주_1반_김세희"]
      },
      Content: [
        {
          "id": 1, 
          "group": "그룹1", 
          "channel": "공지사항", 
          "username": "황성안[광주_1반_C103] 팀원", 
          "nickname": "sjd0051", 
          "image": "", 
          "content": "성규님 고백해도 되나요?", 
          "scrap": "n", 
          "date": "2021-09-27"
        },
        {
          "id": 2, 
          "group": "그룹1", 
          "channel": "공지사항", 
          "username": "김성규[광주_1반_C103] 팀장", 
          "nickname": "rkttjdrb", 
          "image": "", 
          "content": "네 해도 됩니다", 
          "scrap": "y", 
          "date": "2021-09-27"
        },
        {
          "id": 3, 
          "group": "그룹1", 
          "channel": "공지사항", 
          "username": "이태성[광주_2반_C204] 팀장", 
          "nickname": "lts", 
          "image": "", 
          "content": "안녕하세요.<br/> SSAFY 사무국입니다.<br/> 09/24 진행된 라이브 강의 이벤트 당첨자 안내드립니다 <br/> 라이브 강의를 적극적으로 참여해 주는 교육생에게는 선물이 팡팡 터집니다 <br/> 적극적인 참여와 채팅 부탁드립니다!<br/><br/>축하드립니다", 
          "scrap": "n", 
          "date": "2021-09-27"
        },
        {
          "id": 7, 
          "group": "그룹1", 
          "channel": "공지사항", 
          "username": "황성안[광주_1반_C103] 팀원", 
          "nickname": "sjd0051", 
          "image": "", 
          "content": "오늘은 하체할 예정", 
          "scrap": "n", 
          "date": "2021-09-27"
        },
      ]
    })
    
    // 스크롤바 밑으로
    $(document).ready(function () {
      if ($(".c-contents").length) {
        var scroll_h = $(".c-contents")[0].scrollHeight;
        $(".c-contents").scrollTop(scroll_h);
      }
    });
  }

  // 그룹 버튼 클릭
  onClickGroup = (e) => {
    // state에 선택한 그룹의 채널들 넣기
    var v = e.target.innerText
    this.setState({
      "ChkChannel": this.state.Channel[v]
    })

    // 버튼 취소
    if (document.querySelector(".group-chk")) {
      document.querySelector(".group-chk").classList.remove("group-chk")
    }
    // 버튼 체크
    e.target.classList.add("group-chk")

    // 채널 nav바
    document.querySelector(".nav-down").classList.add("nav-none")
    document.querySelector(".sub-nav").classList.remove("nav-none")
  }

  // nav바의 화살표
  onClickNavArrow = (e) => {    
    if (e.target.classList[0] === "nav-down") {
      document.querySelector(".nav-down").classList.add("nav-none")
      document.querySelector(".sub-nav").classList.remove("nav-none")
    }
    if (e.target.className === "nav-up") {      
      document.querySelector(".sub-nav").classList.add("nav-none")
      document.querySelector(".nav-down").classList.remove("nav-none")
    } 
  }

  // 날짜 변경
  onChangeDate = (date) => {
    if (date > new Date()) {
      alert("오늘 이후의 날짜를 탐색할 수 없습니다.")
    }
    else {
      this.setState({
        "selectedDate": date
      })
      console.log((date))
      // 선택한 날짜의 게시글들 보여주기 //
    }

  }

  // 즐겨찾기
  noKeepChk(chk) {
    if (chk === "y") {
      return "no-show"
    }
    else {
      return "ch-no-keep"
    }
  }

  keepChk(chk) {
    if (chk === "n") {
      return "no-show"
    }
    else {
      return "ch-keep"
    }
  }

  onClickKeep = (id) => {
    const idx = this.state.Content.findIndex(p => {
      return p.id === id;
    })

    const changeContent = this.state.Content
    if (changeContent[idx]["scrap"] === "n") {
      changeContent[idx]["scrap"] = "y"
    } else {
      changeContent[idx]["scrap"] = "n"
    }

    this.setState({"Contents": changeContent})

    // 즐겨찾기 post 보내기 //
  }  

  render() {
    const { Group, ChkChannel, selectedDate, Content } = this.state
    const logo = "/image/logo_1.png"

    const settings = {
      infinite: false,
      arrows: true,
      speed: 500,
      slidesToShow: 4,
      slidesToScroll : 1,
      draggable: false,
    };

    // 그룹 이름
    const groupName = Group.map((name, idx) => 
      <div className="c-group" key={idx}>
        <div className="group-title" onClick={this.onClickGroup}>{ name }</div>
      </div>
    );

    // 채널 이름
    const channelName = ChkChannel.map((name, idx) => 
      <div className="c-channel" key={idx}>
        { name }
      </div>
    );

    registerLocale("ko", ko);

    console.log(this.props.search)

    // 게시글
    const contents = Content.map((item, idx) =>
      <div className="cc-container" key={idx}>
        <div className="contents-header">
          <div className="ch-profile">
            <div className="ch-image">
            </div>
            <div>
              <h5><b>{item["username"]}</b></h5>
              <h6>@{item["nickname"]}</h6>
            </div>
          </div>
          <div 
            className={this.noKeepChk(item["scrap"])} 
            onClick={() => this.onClickKeep(item["id"])}
          >
            <h3><AiOutlineStar className={this.noKeepChk(item["scrap"])} /></h3>
          </div>
          <div 
            className={this.keepChk(item["scrap"])} 
            onClick={() => this.onClickKeep(item["id"])}
          >
            <h3><AiFillStar className={this.keepChk(item["scrap"])} /></h3>
          </div>
        </div>
        <div dangerouslySetInnerHTML={{ __html: item["content"] }}>
        </div>
        <hr/>
      </div>
    );

    return (
      <div className="c-container">
        <div className="left-header">
          <Link to="/">
            <div className="main-logo">
              <img width="180rem" src={logo} alt="알쓸싸잡" />
            </div>
          </Link>
          <div className="main-search">
            <input className="search-input" onChange={this.onSearchChange} onKeyUp={this.onSearchEnter} autoFocus />
            <button className="search-btn" onClick={this.onSearch}><h4>🔍</h4></button>
          </div>
        </div>
        
        <div className="c-header">
          <div className="c-title">
            <h2>Find Contents</h2>
            <div className="c-title-right">
              <button className="c-title-cal">
                📅
                <DatePicker
                  selected={selectedDate}
                  onChange={this.onChangeDate}
                  dateFormat="yyyy년 MM월 dd일"
                  locale="ko"
                  className="calender"
                />
              </button>
              <button className="c-title-btn"><h6>전체보기</h6></button>
            </div>            
          </div>       
          <hr />
        </div>
        <div className="c-nav">
          <Slider className="c-slider" {...settings}>
            { groupName }
          </Slider>
        </div>
        <div className="c-body">
          <div className="nav-down nav-none" onClick={this.onClickNavArrow}>
            ▼
          </div>
          <div className="sub-nav nav-none">
            <div className="c-channels">
              {channelName}
            </div>
            <div className="nav-up" onClick={this.onClickNavArrow}>
              ▲
            </div>
            <div className="sub-nav-bottom"></div>
          </div>
          <div className="c-contents">
            { contents }
          </div>
        </div>
      </div>
    )
  }
}

export default Contents