import React from "react";
import Slider from "react-slick";

import "./css/Contents.css";

class Contents extends React.Component {
  state = {
    "Group": [],
    "Channel": {},
    "ChkChannel": [],
  }

  componentDidMount() {
    this.setState({
      "Group": ["그룹1", "그룹2", "그룹3", "그룹4", "그룹5", "개인 메시지"],
      // 채널 dict 정리할 때, 비공개 채널은 자물쇠 표시!!
      "Channel": {
        "그룹1": ["공지사항", "잡담", "Q&A", "Q&A(자기주도 PJT)", "🔒광주 1반"],
        "그룹2": ["1. SW 스타트 캠프", "2. 온라인 코칭", "공지사항", "이벤트", "일타싸피", "잡담", "종강식 이벤트", "BGM", "🔒5미자차", "🔒광주1반 거북컴들"],
        "그룹3": ["공지사항", "Q&A"],
        "그룹4": ["공지사항", "수다방", "팀구성:D", "QnA"],
        "그룹5": ["공지사항", "수다방", "팀구성:D", "QnA"],
        // 개인 메시지 Group dict에 추가해주기
        "개인 메시지": ["곽동희(교육프로)", "곽온겸(광주실습코치)", "광주 1반 이민교(전임교수)", "광주_1반_김세희"]
      }
    })
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
    if (e.target.className === "nav-down") {
      document.querySelector(".nav-down").classList.add("nav-none")
      document.querySelector(".sub-nav").classList.remove("nav-none")
    }
    if (e.target.className === "nav-up") {      
      document.querySelector(".sub-nav").classList.add("nav-none")
      document.querySelector(".nav-down").classList.remove("nav-none")
    } 
  }
  
  render() {
    const { Group, ChkChannel } = this.state

    const settings = {
      infinite: false,
      arrows: true,
      speed: 500,
      slidesToShow: 4,
      slidesToScroll : 1,
      draggable: false,
    };

    // 그룹 이름
    const groupName = Group.map((name) => 
      <div className="c-group">
        <div className="group-title" onClick={this.onClickGroup}>{ name }</div>
      </div>
    );

    // 채널 이름
    const channelName = ChkChannel.map((name) => 
      <div className="c-channel">
        { name }
      </div>
    );

    return (
      <div className="c-container">
        <div className="c-header">
          <div className="c-title">
            <h2>Find Contents</h2>
            <button className="c-all"><h6>전체보기</h6></button>
          </div>       
          <hr />
        </div>
        <div className="c-nav">
          <Slider className="c-slider" {...settings}>
            { groupName }
          </Slider>
          <div className="nav-down nav-none" onClick={this.onClickNavArrow}>
            ▼
          </div>
        </div>
        <div className="sub-nav nav-none">
          <div className="c-channels">
            {channelName}
          </div>
          <div className="nav-up" onClick={this.onClickNavArrow}>
            ▲
          </div>
        </div>
        <div className="c-contents">
        </div>
      </div>
    )
  }
}

export default Contents