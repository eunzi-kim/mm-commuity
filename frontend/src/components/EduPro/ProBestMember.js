import axios from "axios";
import React, { Component } from "react";

import "./css/ProBestMember.css"

class ProBestMember extends Component {
  state = {
    // users_info: [],
  }
  constructor(props) {
    super(props)
  }
    
  componentDidMount() {
    // console.log(this.props.sortmember)
  
  }
  
  render() {
    // 조회된 교육생
    const sort_m_lst = this.props.sortmember
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