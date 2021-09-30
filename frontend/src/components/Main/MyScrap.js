import React from "react";
import { registerLocale } from "react-datepicker";
import { AiOutlineStar, AiFillStar } from 'react-icons/ai';
import $ from "jquery";

import "react-datepicker/dist/react-datepicker.css";
import ko from 'date-fns/locale/ko';

import "./css/Myscrap.css";

class MyScrap extends React.Component {
  state = {
    "selectedDate": new Date(),
    "Myscrap": []
  }

  componentDidMount() {
    // 서버에서 api 받아와서 데이터 정리!!
    this.setState({
      "Myscrap": [
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



  // 날짜 변경
  onChangeDate = (date) => {
    this.setState({
      "selectedDate": date
    })

    // 선택한 날짜의 게시글들 보여주기 //
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
    const idx = this.state.Myscrap.findIndex(p => {
      return p.id === id;
    })

    const changeContent = this.state.Myscrap
    if (changeContent[idx]["scrap"] === "n") {
      changeContent[idx]["scrap"] = "y"
    } else {
      changeContent[idx]["scrap"] = "n"
    }

    this.setState({"Contents": changeContent})

    // 즐겨찾기 post 보내기 //
  }  

  render() {
    const { Myscrap } = this.state

    registerLocale("ko", ko);

    // 스크랩 된 게시글
    const MyContents = Myscrap.map((item) =>
        <div className="cc-container">
          {/* <h1>확인용</h1> */}
          <div className="ccc-container">
            <div className="contents-header">
              <div className="ch-profile">
                <div className="ch-image">
                </div>
                <div>
                  <h5><b>{item["username"]}</b></h5>
                  <h6>@{item["nickname"]}</h6>
                </div>
              </div>
              {/* 채널명 , 날짜 */}
              <div>
                <div className="contents-header-date"><h4>📌{item["channel"]}</h></div>
                <div className="contents-header-date"><h3>{item["date"]}</h3></div>
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
            <div className="c-hr"><hr /></div>
            <div className="cc-body-content" dangerouslySetInnerHTML={{ __html: item["content"] }}>
            </div>
         </div> 
      </div>
    );

    return (
      <div className="c-container">
        <div className="c-header">
          <div className="c-title">
            <h2>즐겨찾기</h2>
            <div className="c-title-right">
            </div>            
          </div>       
          <hr />
        </div>

        <div className="c-body">
          <div className="c-contents">
            { MyContents }
          </div>
        </div>        
      </div>
    )
  }
}

export default MyScrap