import React from "react";
import Slider from "react-slick";
import DatePicker, { registerLocale } from "react-datepicker";
import $ from "jquery";
import { Link } from "react-router-dom";
import ReactMarkdown from 'react-markdown';

import "react-datepicker/dist/react-datepicker.css";
import ko from 'date-fns/locale/ko';

import "./css/Contents.css";
import axios from "axios";

import res927 from "./../../2021-9-27.json";
import res928 from "./../../2021-9-28.json";
import res929 from "./../../2021-9-29.json";
import res930 from "./../../2021-9-30.json";
import res101 from "./../../2021-10-1.json";
import res104 from "./../../2021-10-4.json";
import res105 from "./../../2021-10-5.json";
import res106 from "./../../2021-10-6.json";
import res107 from "./../../2021-10-7.json";

class Contents extends React.Component {
  state = {
    Group: [],
    Channel: {},
    ChkChannel: [],
    selectedDate: new Date(new Date().setDate(new Date().getDate()-1)),
    AllContents: [],
    Content: [],
    ChkTeam: '',
  }

  // api
  fetchPost = async (today_date) => {
    const url = `http://j5c103.p.ssafy.io:8081/api/posts/${today_date}`
    const userInfo = JSON.parse(sessionStorage.getItem('userInfo'))
    const token = JSON.parse(sessionStorage.getItem('token'))

    const data = {
      "userId": userInfo.userId,
	    "token": token
    }
    // console.log(data)

    await axios.post(url, data)
    .then(res => {
      // console.log(res.data)
      const group = []
      const channel = {}
      const all_contents = []
      const contents = []

      for (let i=0; i<res.data.length; i++) {
        if (!group.includes(res.data[i]['teamName'])) {
          group.push(res.data[i]['teamName'])
        }

        if (channel[res.data[i]['teamName']] && !(channel[res.data[i]['teamName']].includes(res.data[i]['channelName']))) {
          channel[res.data[i]['teamName']].push(res.data[i]['channelName'])
        }
        else if (!channel[res.data[i]['teamName']]) {
          channel[res.data[i]['teamName']] = [res.data[i]['channelName']]
        }

        const content_info = {
          "id": res.data[i]["postId"],
          "group": res.data[i]["teamName"], 
          "channel": res.data[i]["channelName"], 
          "username": res.data[i]["username"], 
          "nickname": res.data[i]["nickname"], 
          "profileImg": res.data[i]["profileImg"], 
          "content": res.data[i]["message"]
        }
        all_contents.push(content_info)
        contents.push(content_info)
      }
        
      this.setState({
        Group: group,
        Channel: channel,
        AllContents : all_contents,
        Content: contents
      })
    })
    .catch(err => {
      console.log(err.response)
      alert("새로고침을 해주세요.")
    })
  }  

  dateContent(res) {
    console.log()
    const group = []
    const channel = {}
    const all_contents = []
    const contents = []

    for (let i=0; i<res.length; i++) {
      if (!group.includes(res[i]['teamName'])) {
        group.push(res[i]['teamName'])
      }

      if (channel[res[i]['teamName']] && !(channel[res[i]['teamName']].includes(res[i]['channelName']))) {
        channel[res[i]['teamName']].push(res[i]['channelName'])
      }
      else if (!channel[res[i]['teamName']]) {
        channel[res[i]['teamName']] = [res[i]['channelName']]
      }

      const content_info = {
        "id": res[i]["postId"],
        "group": res[i]["teamName"], 
        "channel": res[i]["channelName"], 
        "username": res[i]["username"], 
        "nickname": res[i]["nickname"], 
        "profileImg": res[i]["profileImg"], 
        "content": res[i]["message"]
      }
      all_contents.push(content_info)
      contents.push(content_info)
    }
      
    this.setState({
      Group: group,
      Channel: channel,
      AllContents : all_contents,
      Content: contents
    })
  }

  componentDidMount() {
    // 서버에서 api 받아오기!!
    // const now = this.state.selectedDate
    // const today_date = String(now.getMonth()+1)+String(now.getDate())
    this.dateContent(res107)
    
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
    var v = " " + e.target.innerText
    this.setState({
      "ChkChannel": this.state.Channel[v]
    })

    this.setState({
      ChkTeam: v
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


  // 채널 버튼 클릭
  onClickChannel = (e) => {
    // 버튼 취소
    if (document.querySelector(".c-channel-chk")) {
      document.querySelector(".c-channel-chk").classList.remove("c-channel-chk")
    }
    // 버튼 체크
    e.target.classList.add("c-channel-chk")

    const all_contents = this.state.AllContents
    const contents = []
    for (let i=0; i<all_contents.length; i++) {
      if (all_contents[i]['group'] === this.state.ChkTeam && all_contents[i]['channel'] === " "+e.target.innerText) {
        contents.push(all_contents[i])
      }
    }

    this.setState({
      Content: contents
    })
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
    const year = date.getFullYear()
    const month = date.getMonth()+1
    const day = date.getDate()

    var now = new Date()
    const t_year = now.getFullYear()
    const t_month = now.getMonth()+1
    const t_day = now.getDate()
    
    if (date > new Date()) {
      alert("오늘 이후의 날짜를 탐색할 수 없습니다.")
    }
    else if (year === t_year && month === t_month && day === t_day) {
      alert("오늘 날짜를 탐색할 수 없습니다.")
    }
    else {
      this.setState({
        "selectedDate": date
      })
    }

    if (month === 10) {
      if (day === 7) {
        this.dateContent(res107)
      } else if (day === 6) {
        this.dateContent(res106)
      } else if (day === 5) {
        this.dateContent(res105)
      } else if (day === 4) {
        this.dateContent(res104)
      } else if (day === 1) {
        this.dateContent(res101)
      }
    }
    else if (month === 9) {
      if (day === 30) {
        this.dateContent(res930)
      } else if (day === 29) {
        this.dateContent(res929)
      } else if (day === 28) {
        this.dateContent(res928)
      } else if (day === 27) {
        this.dateContent(res927)
      }
    }
  }

    
  // 검색
  onSearch = () => {
    const search_words = this.state.search
    const search_result = []
    var all_contents = this.state.AllContents
    for (let i=0; i<all_contents.length; i++) {
      if (all_contents[i]['username'].includes(search_words) || all_contents[i]['nickname'].includes(search_words) || all_contents[i]['content'].includes(search_words)) {
        search_result.push(all_contents[i])
      }
    }
    if (document.querySelector(".group-chk")) {
      document.querySelector(".group-chk").classList.remove("group-chk")
    }
    this.setState({
      Content: search_result
    })
  }

  onSearchEnter = (e) => {
    if (e.keyCode === 13) {
      this.onSearch()
    }
  }

  onSearchChange = (e) => {
    this.setState({
      search: e.target.value
    })
  }


  // 전체보기
  onWatchAll = () => {
    if (document.querySelector(".group-chk")) {
      document.querySelector(".group-chk").classList.remove("group-chk")
    }

    if (document.querySelector(".c-channel-chk")) {
      document.querySelector(".c-channel-chk").classList.remove("c-channel-chk")
    }

    if (document.querySelector(".nav-up")) {      
      document.querySelector(".sub-nav").classList.add("nav-none")
    } 
    
    if (this.state.AllContents !== this.state.Content) {
      var all_contents = this.state.AllContents
      this.setState({
        Content: all_contents,
        ChkTeam: ""
      })
    }
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
      <div className="c-channel" key={idx} onClick={this.onClickChannel}>
        { name }
      </div>
    );

    registerLocale("ko", ko);

    // 게시글
    const contents = Content.map((item, idx) =>
      <div className="cc-container" key={idx}>
        <div className="contents-header">
          <div className="ch-profile">
            <div className="ch-image">
              <img src={item["profileImg"]} alt="이미지" className="ch-profile-img" />
            </div>
            <div>
              <h5><b>{item["nickname"]}</b></h5>
              <h6>@{item["username"]}</h6>
            </div>
          </div>
        </div>
        <div>
          <ReactMarkdown>{item['content']}</ReactMarkdown>
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
              <div className="c-title-cal">
                📅
                <DatePicker
                  selected={selectedDate}
                  onChange={this.onChangeDate}
                  dateFormat="yyyy년 MM월 dd일"
                  locale="ko"
                  className="calender"
                />
              </div>
              <button className="c-title-btn" onClick={this.onWatchAll}><h6>전체보기</h6></button>
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