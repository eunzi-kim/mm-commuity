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
        {id: 'tjdrb', nickname:'김성규[광주_1반_C103] 팀장', post_n:115},
        {id: 'dmswl', nickname:'김은지[광주_1반_C103] 팀원', post_n:105},
        {id: 'wjddnr', nickname:'김정욱[광주_2반_C204] 팀원', post_n:811},
        {id: 'thgus', nickname:'박소현[광주_1반_C103] 팀원', post_n:125},
        {id: 'alwjd', nickname:'오미정[광주_1반_C103] 팀원', post_n:4105},
        {id: 'tjddks', nickname:'황성안[광주_2반_C204] 팀원', post_n:1515},
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

    //정렬 어캐하지 2
    // const sort_users_info = [].concat(this.state.data)
    // .sort((a, b) => a.users["id"] > b.users["id"] ? 1:-1)
    // .map((users["id"], i) => (<div key={i}>{users.id}</div>))


    // 정렬을 어캐하지
    const sort_m_lst = users_info
    // .sort((a, b) => a.users["id"] > b.users["id"] ? 1:-1)
    .map((users) =>
      <div className="profile">
        {/* { users["id"] } */}
        <div className="image">
          이미지
        </div>
        <div className="info">
          <div>
            <div className="name"><b>{users["nickname"]}</b> <br /></div>
            <div className="id">@{users["id"]}<br /></div>
            <div className="detail">게시글 : {users["post_n"]} 개<br /></div>
          </div>
        </div>
      </div>
    );

    return (
      <div className="container">
        <div className="list">
          <div className="sorted_m_list">
            { sort_m_lst }
          </div>          
        </div>
      </div>
    )
  }

}

export default ProBestMember