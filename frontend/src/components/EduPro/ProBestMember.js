import React, { Component } from "react";

import "./css/ProBestMember.css"

class ProBestMember extends Component {
  state = {
    users_info: [],
  }
  

  componentDidMount() {
    //멤버 조회하기 ( 포인트 순? 오카지)

    this.setState({
      users_info: [
        {id: 'tjdrb', nickname:'김성규[광주_1반_C103] 팀장', post_n:115, emoji: 123, comment: 123, point: 123},
        {id: 'dmswl', nickname:'김은지[광주_1반_C103] 팀원', post_n:105, emoji: 123, comment: 123, point: 123},
        {id: 'wjddnr', nickname:'김정욱[광주_2반_C204] 팀원', post_n:811, emoji: 123, comment: 123, point: 123},
        {id: 'thgus', nickname:'박소현[광주_1반_C103] 팀원', post_n:125, emoji: 123, comment: 123, point: 123},
        {id: 'alwjd', nickname:'오미정[광주_1반_C103] 팀원', post_n:4105, emoji: 123, comment: 123, point: 123},
        {id: 'tjddks', nickname:'황성안[광주_2반_C204] 팀원', post_n:1515, emoji: 123, comment: 123, point: 123},
      ]

    })
    
  }
  
  // 정렬 알고리즘 함수 만들기
  // 게시글
  // const temp = this.stae.user_infos
      // 정렬끝난거 넣어주기
      // temp 정렬 알고리즘
      // this.setState=((user_info:temp))
  // 이모지

  // 댓글


  render() {
    const { users_info } = this.state
    // 조회된 교육생
    const sort_m_lst = users_info
    // .sort((a, b) => a.users["id"] > b.users["id"] ? 1:-1)
    .map((users, idx) =>
      <div className="profile" key={idx}>
        {/* { users["id"] } */}
        <div className="image">
          이미지
        </div>
        <div className="info">
          <div className="info-body">
            <div className="name"><b>{users["nickname"]}</b> <br /></div>
            <div className="id">@{users["id"]}<br /></div>
            <div className="detail">
              <div>게시글 : {users["post_n"]} 개</div>
              <div>이모지 : {users["emoji"]} 개</div>
              <div>댓　글 : {users["comment"]} 개</div>
            </div>
            <div className="detail">
              {users["point"]} point
            </div>
          </div>
        </div>
      </div>
    );

    return (
      <div>
        <div className="container">
          <div className="list">
            <div className="sorted_m_list">
              { sort_m_lst }
            </div>          
          </div>
        </div>
      </div>
    )
  }

}

export default ProBestMember