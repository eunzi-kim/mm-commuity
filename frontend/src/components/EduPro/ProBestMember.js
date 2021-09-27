import React, { Component } from "react";

import "./ProBestMember.css"

class ProBestMember extends Component {
  state = {
    post_rank: [],
    emo_rank: [],
    comm_rank:[],
  }
  
  componentDidMount() {
    //멤버 조회하기 ( 포인트 순? 오카지)

    this.setState({
      post_rank: ["닉네임", "아이디", "[게시물 수]"],
      emo_rank: ["닉네임", "아이디", "이모티콘 수"],
      comm_rank: ["닉네임", "아이디", "댓글 수"],
    })
  }
  render() {
    const {
      post_rank,
      emo_rank,
      comm_rank,
    } = this.state;

    const settings = {
      
    };

    return (
      <div className="container">
        <div className="list">
          <div className="profile">
            <div className="image">
              이미지
            </div>
            <div className="info">
              <div> 
                <div className="name"><h1><b>{post_rank[0]}</b> <br /></h1></div>
                <div className="id">@{post_rank[1]}<br /></div>
                <div className="detail">게시글 : {post_rank[2]} 개<br /></div>
              </div>
            </div>
          </div>
          <div className="profile">
            <div className="image">
              이미지
            </div>
            <div className="info">
              <div> 
                <div className="name"><h1><b>{post_rank[0]}</b> <br /></h1></div>
                <div className="id">@{post_rank[1]}<br /></div>
                <div className="detail">게시글 : {post_rank[2]} 개<br /></div>
              </div>
            </div>
          </div>
          <div className="profile">
            <div className="image">
              이미지
            </div>
            <div className="info">
              <div> 
                <div className="name"><h1><b>{post_rank[0]}</b> <br /></h1></div>
                <div className="id">@{post_rank[1]}<br /></div>
                <div className="detail">게시글 : {post_rank[2]} 개<br /></div>
              </div>
            </div>
          </div>
          <div className="profile">
            <div className="image">
              이미지
            </div>
            <div className="info">
              <div> 
                <div className="name"><h1><b>{post_rank[0]}</b> <br /></h1></div>
                <div className="id">@{post_rank[1]}<br /></div>
                <div className="detail">게시글 : {post_rank[2]} 개<br /></div>
              </div>
            </div>
          </div>
          <div className="profile">
            <div className="image">
              이미지
            </div>
            <div className="info">
              <div> 
                <div className="name"><h1><b>{post_rank[0]}</b> <br /></h1></div>
                <div className="id">@{post_rank[1]}<br /></div>
                <div className="detail">게시글 : {post_rank[2]} 개<br /></div>
              </div>
            </div>
          </div>
          <div className="profile">
            <div className="image">
              이미지
            </div>
            <div className="info">
              <div> 
                <div className="name"><h1><b>{post_rank[0]}</b> <br /></h1></div>
                <div className="id">@{post_rank[1]}<br /></div>
                <div className="detail">게시글 : {post_rank[2]} 개<br /></div>
              </div>
            </div>
          </div>
          <div className="profile">
            <div className="image">
              이미지
            </div>
            <div className="info">
              <div> 
                <div className="name"><h1><b>{post_rank[0]}</b> <br /></h1></div>
                <div className="id">@{post_rank[1]}<br /></div>
                <div className="detail">게시글 : {post_rank[2]} 개<br /></div>
              </div>
            </div>
          </div>
          <div className="profile">
            <div className="image">
              이미지
            </div>
            <div className="info">
              <div> 
                <div className="name"><h1><b>{post_rank[0]}</b> <br /></h1></div>
                <div className="id">@{post_rank[1]}<br /></div>
                <div className="detail">게시글 : {post_rank[2]} 개<br /></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }

}

export default ProBestMember