import React from "react";

import "./css/SsafycialDetail.css";

class SsafycialDetail extends React.Component {
  state = {
    article: []
  }

  onCloseDetail() {
    document.querySelector(".s-detail").className = "s-detail-none"
  }

  componentDidMount() {
    this.setState({
      article: [
        {'id':0, 'title': '[9월 2주차] 고래가족', 'reporter': '박소현', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':1, 'title': '[9월 2주차] 상어가족', 'reporter': '김정욱', 'link': 'https://www.netflix.com/browse'},
        {'id':2, 'title': '[9월 2주차] 개미가족', 'reporter': '곽온겸', 'link': 'https://www.work.go.kr/cyberedu/main.do'},
        {'id':3, 'title': '[9월 2주차] 코딩가족', 'reporter': '이민교', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':4, 'title': '[9월 2주차] 근육가족', 'reporter': '황성안', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':5, 'title': '[9월 2주차] 근육가족', 'reporter': '황성안', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':6, 'title': '[9월 2주차] 근육가족', 'reporter': '황성안', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':7, 'title': '[9월 2주차] 근육가족', 'reporter': '황성안', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':8, 'title': '[9월 2주차] 근육가족', 'reporter': '황성안', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':9, 'title': '[9월 2주차] 근육가족', 'reporter': '황성안', 'link': 'https://pythontoomuchinformation.tistory.com/448'},
        {'id':10, 'title': '[9월 2주차] 근육가족', 'reporter': '황성안', 'link': 'https://pythontoomuchinformation.tistory.com/448'}
      ]
    })

    if (localStorage.getItem('darkmode')) {
      document.querySelector(".s-detail-body").classList.add("dark-s-detail-body")
    }

    document.addEventListener("keyup", e => {
      if (e.keyCode === 27) {
        this.onCloseDetail()
      }
    })
  }

  render() {
    const { article } = this.state;

    // 각 기사들
    const all_articles = article.map((item, idx) => 
      <div className="a-detail-title" key={idx}>
        <p onClick={() => window.open(item['link'], '_blank')}>{ item['title'] } ({ item['reporter'] } 기자)</p>
      </div>
    )

    return (
      <div className="s-detail-none">
        <div className="s-detail-header">
          <h3 onClick={this.onCloseDetail}>❌</h3>
        </div>
        <div className="s-detail-body">        
          <div className="s-detail-title">
            <h3>Ssafycial</h3>
          </div>
          <hr /> 
          <div className="s-detail-contents">
            { all_articles }
          </div>
        </div>
      </div>
    )
  }
}

export default SsafycialDetail