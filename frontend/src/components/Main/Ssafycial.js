import axios from "axios";
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

  fetchSsafycial = async () => {
    const url = 'http://j5c103.p.ssafy.io:8081/api/ssafycials'

    await axios.get(url)
    .then(res => {
      console.log(res.data)
      this.setState({
        article: res.data
      })
    })
    .catch(err => {
      console.log(err)
    })
  }

  componentDidMount() {
    this.fetchSsafycial()
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