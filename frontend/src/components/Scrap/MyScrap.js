import React from "react";
import { registerLocale } from "react-datepicker";
// import { AiOutlineStar, AiFillStar } from 'react-icons/ai';
import $ from "jquery";

import "react-datepicker/dist/react-datepicker.css";
import ko from 'date-fns/locale/ko';

import "./css/Myscrap.css";
import axios from "axios";

class MyScrap extends React.Component {
  state = {
    Myscrap: []
  }
  //스크랩 가져오기 API
  fetchScraps = async ( scrap_list ) => {
    const url = "http://j5c103.p.ssafy.io:8081/api/posts/scrap"

    await axios.post(url, scrap_list)
    .then(res => {
      const all_scraps = []

      const scrapInfo = {
        "id":res.data.id,
        "group":res.data.group,
        "channel":res.data.channel,
        "username":res.data.username,
        "nickname":res.data.nickname,
        "image":res.data.image,
        "content":res.data.content,
        "scrap":res.data.scrap,
        "date":res.data.date,
      }
      all_scraps.push(scrapInfo)

      console.log(all_scraps) // 확인해주기
      this.setState({
        Myscrap : all_scraps
      })
    })
    .catch(err => {
      this.setState({alert: true})
    })
  }


  componentDidMount() {
    // 서버에서 API 받을때 쓰기
    this.fetchScraps()
    // this.setState({
    //   Myscrap: [
    //     {
    //       "id": 1, 
    //       "group": "그룹1", 
    //       "channel": "공지사항", 
    //       "username": "황성안[광주_1반_C103] 팀원", 
    //       "nickname": "sjd0051", 
    //       "image": "", 
    //       "content": "3분할 <br /> 월 : 가슴, 어깨, 삼두 <br /> 화 : 등, 이두 <br /> 수 : 하체, 코어 <br /> 6일간 반복 <br />남은 1일은 유산소", 
    //       "scrap": "n", 
    //       "date": "2021-09-27"
    //     },
    //     {
    //       "id": 2, 
    //       "group": "그룹1", 
    //       "channel": "공지사항", 
    //       "username": "김성규[광주_1반_C103] 팀장", 
    //       "nickname": "rkttjdrb", 
    //       "image": "", 
    //       "content": "즐겨 찾기 데이터 넣어뒀습니다 한번 즐찾해보실?", 
    //       "scrap": "y", 
    //       "date": "2021-09-27"
    //     },
    //     {
    //       "id": 3, 
    //       "group": "그룹1", 
    //       "channel": "공지사항", 
    //       "username": "이태성[광주_2반_C204] 팀장", 
    //       "nickname": "lts", 
    //       "image": "", 
    //       "content": "안녕하세요.<br/> SSAFY 사무국입니다.<br/> 09/24 진행된 라이브 강의 이벤트 당첨자 안내드립니다 <br/> 라이브 강의를 적극적으로 참여해 주는 교육생에게는 선물이 팡팡 터집니다 <br/> 적극적인 참여와 채팅 부탁드립니다!<br/><br/>축하드립니다", 
    //       "scrap": "n", 
    //       "date": "2021-09-27"
    //     },
    //     {
    //       "id": 7, 
    //       "group": "그룹1", 
    //       "channel": "공지사항", 
    //       "username": "황성안[광주_1반_C103] 팀원", 
    //       "nickname": "sjd0051", 
    //       "image": "", 
    //       "content": "하체 루틴 스쿼트, 핵스쿼트, 수평레그프레스, 레그 컬, 레그 익스텐션", 
    //       "scrap": "n", 
    //       "date": "2021-09-27"
    //     },
    //   ]
    // })
    
    // 스크롤바 밑으로
    $(document).ready(function () {
      if ($(".c-contents").length) {
        var scroll_h = $(".c-contents")[0].scrollHeight;
        $(".c-contents").scrollTop(scroll_h);
      }
    });
  }

  render() {
    const { Myscrap } = this.state

    registerLocale("ko", ko);

    // 스크랩 된 게시글
    const MyContents = Myscrap.map((item, idx) =>
        <div className="scrap-cc-container" key={idx}>
          {/* <h1>확인용</h1> */}
          <div className="scrap-ccc-container">
            <div className="scrap-contents-header">
              <div className="scrap-ch-profile">
                <div className="scrap-ch-image">
                </div>
                <div>
                  <h5><b>{item["username"]}</b></h5>
                  <h6>@{item["nickname"]}</h6>
                </div>
              </div>
              {/* 채널명 , 날짜 */}
              <div className="chname-data-container">
                <div className="scrap-contents-header-date"><h4>📌</h4></div>
                <div className="scrap-contents-header-date"><h3>{item["date"]}</h3></div>
              </div>
              {/* 별표 스크랩 버튼 */}
              {/* <div 
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
              </div> */}
            </div>
            <div className="scrap-c-hr"><hr /></div>
            <div className="scrap-cc-body-content" dangerouslySetInnerHTML={{ __html: item["content"] }}>
            </div>
         </div> 
      </div>
    );

    return (
      <div className="scrap-c-container">
        <div className="scrap-c-header">
          <div className="scrap-c-title">
            <h2>즐겨찾기</h2>
            <div className="scrap-c-title-right">
            </div>            
          </div>       
          <hr />
        </div>

        <div className="scrap-c-body">
          <div className="scrap-c-contents">
            { MyContents }
          </div>
        </div>        
      </div>
    )
  }
}

export default MyScrap