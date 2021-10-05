import React from "react";

import "./css/Ssafycial.css"
import SsafycialDetail from "./SsafycialDetail";

class Ssafycial extends React.Component {
  state = {
    article: []
  }

  onClickSsafycialDetail() {
    document.querySelector(".s-detail-none").className = "s-detail"
  }

  componentDidMount() {
    this.setState({
      article: [
        {'id':0, 'title': '[9월 2주차] 고래가족', 'reporter': '박소현', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':1, 'title': '[9월 2주차] 상어가족', 'reporter': '김정욱', 'link': 'https://www.netflix.com/browse'},
        {'id':2, 'title': '[9월 2주차] 개미가족', 'reporter': '곽온겸', 'link': 'https://www.work.go.kr/cyberedu/main.do'},
        {'id':3, 'title': '[9월 2주차] 코딩가족', 'reporter': '이민교', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':4, 'title': '[9월 2주차] 근육가족', 'reporter': '황성안', 'link': 'https://pythontoomuchinformation.tistory.com/448'}
      ]
    })
  }
  
  render() {
    const { article } = this.state;

    // 각 기사들
    const articles = article.slice(0,4).map((item, idx) => 
      <div className="a-title" key={idx}>
        <p onClick={() => window.open(item['link'], '_blank')}>{ item['title'] } ({ item['reporter'] } 기자)</p>
      </div>
    )

    return (
      <div className="s-container">
        <div className="s-header">
          <div className="s-title">
            <h3>Ssafycial</h3>
            <h2 className="s-plus" onClick={this.onClickSsafycialDetail}>+</h2>
          </div>
          <hr />
        </div>
        <div className="s-body">
          { articles }
        </div>

        <>
          <SsafycialDetail />
        </>
      </div>
    )
  }
}

export default Ssafycial