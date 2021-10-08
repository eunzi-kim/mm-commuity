import React from "react";

import "./css/SsafycialDetail.css";

import res from "./../../ssafycials.json";

class SsafycialDetail extends React.Component {
  state = {
    article: []
  }

  onCloseDetail() {
    if (document.querySelector(".s-detail")) {
      document.querySelector(".s-detail").className = "s-detail-none"
    }
  }

  componentDidMount() {
    this.setState({
      article: res
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
        <p onClick={() => window.open(item['link'], '_blank')}>{ item['title'] } ({ item['nickname'] } 기자)</p>
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