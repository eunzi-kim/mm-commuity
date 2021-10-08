import React, { Component } from "react";
import Slider from "react-slick";
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

import "./css/BestMember.css"
import axios from "axios";

class BestMember extends Component {
  state = {
    best1: [],
    best2: [],
    best3: [],
    bestReaction1: [],
    bestReaction2: [],
    bestReaction3: [],
    bestUploader1: [],
    bestUploader2: [],
    bestUploader3: [],
  }

  fetchMostReaction = async () => {
    const url = 'http://j5c103.p.ssafy.io:8082/api/students/most-reaction'
    await axios.get(url)
    .then(res => {
      console.log("mr", res)
    })
    .catch(err => {
      console.log(err)
    })
  }

  fetchBestUploader = async () => {
    const url = 'http://j5c103.p.ssafy.io:8082/api/students/best-uploader'
    await axios.get(url)
    .then(res => {
      console.log("bu", res)
    })
    .catch(err => {
      console.log(err)
    })
  }

  fetchBestReaction = async () => {
    const url = 'http://j5c103.p.ssafy.io:8082/api/students/best-reaction'
    await axios.get(url)
    .then(res => {
      console.log("br", res)
    })
    .catch(err => {
      console.log(err)
    })
  }


  componentDidMount() {
    // this.fetchBestReaction()
    // this.fetchBestUploader()
    // this.fetchMostReaction()

    // 예시
    this.setState({
      best1: ["김태현[광주_1반_C106]팀원", "kimth1113", "56"],
      best2: ["구미_6반_윤상일", "sangilyoon.dev", "44"],
      best3:  ["서울_2반_정현정", "jung55120", "25"],
      bestUploader1: ["신준희[대전_3반_B306]팀원", "jhshin0028", "34"],
      bestUploader2: ["박소현[광주_1반_C103]팀원", "mymysuzy0627", "22"],
      bestUploader3:  ["김성규[광주_1반_C103]팀장", "kskyu610", "10"],
      bestReaction1: ["오미정[광주_1반_C203]팀원", "ks03102", "64"],
      bestReaction2: ["김우재[광주_2반_C201]팀원", "kwj1270", "55"],
      bestReaction3:  ["이화정[대전_3반_B301]팀원", "honeylee999", "43"],
    })
    
  }
  
  render() {
    const { 
      best1, best2, best3, 
      bestUploader1, bestUploader2, bestUploader3,
      bestReaction1, bestReaction2, bestReaction3, 
    } = this.state;

    const settings = {
      arrows: false,
      dots: true,
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1,
      // 자동 넘김
      autoplay: true,
      autoplaySpeed: 3000,
    };

    const gold = "/image/medal_1.png"
    const silver = "/image/medal_2.png"
    const bronze = "/image/medal_3.png"

    return (
      <div className="bm-container">
        <div className="bm-header">
          <h3>Best Member</h3>
          <hr />
        </div>
        <div className="bm-slider">
          <Slider {...settings}>
            <div className="bm-body">
              <h5 className="bm-title">💜 최다 반응 획득 💜</h5>
              <div className="bm-content">
                <div className="bm-medal"><img src={gold} alt="1" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ best1[0] }</b></div>
                    <div className="bm-un">@{ best1[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ best1[2] }개</div>
                </div>
              </div>
              <div className="bm-content">
                <div className="bm-medal"><img src={silver} alt="2" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ best2[0] }</b></div>
                    <div className="bm-un">@{ best2[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ best2[2] }개</div>
                </div>
              </div>
              <div className="bm-content">
                <div className="bm-medal"><img src={bronze} alt="3" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ best3[0] }</b></div>
                    <div className="bm-un">@{ best3[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ best3[2] }개</div>
                </div>
              </div>
            </div>

            <div className="bm-body">
              <h5 className="bm-title">❤ 우수 업로더 ❤</h5>
              <div className="bm-content">
                <div className="bm-medal"><img src={gold} alt="1" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ bestUploader1[0] }</b></div>
                    <div className="bm-un">@{ bestUploader1[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ bestUploader1[2] }개</div>
                </div>
              </div>
              <div className="bm-content">
                <div className="bm-medal"><img src={silver} alt="2" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ bestUploader2[0] }</b></div>
                    <div className="bm-un">@{ bestUploader2[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ bestUploader2[2] }개</div>
                </div>
              </div>
              <div className="bm-content">
                <div className="bm-medal"><img src={bronze} alt="3" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ bestUploader3[0] }</b></div>
                    <div className="bm-un">@{ bestUploader3[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ bestUploader3[2] }개</div>
                </div>
              </div>
            </div>

            <div className="bm-body">
              <h5 className="bm-title">💚 우수 리액션 💚</h5>
              <div className="bm-content">
                <div className="bm-medal"><img src={gold} alt="1" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ bestReaction1[0] }</b></div>
                    <div className="bm-un">@{ bestReaction1[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ bestReaction1[2] }개</div>
                </div>
              </div>
              <div className="bm-content">
                <div className="bm-medal"><img src={silver} alt="2" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ bestReaction2[0] }</b></div>
                    <div className="bm-un">@{ bestReaction2[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ bestReaction2[2] }개</div>
                </div>
              </div>
              <div className="bm-content">
                <div className="bm-medal"><img src={bronze} alt="3" /></div>
                <div className="bm-profile">
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ bestReaction3[0] }</b></div>
                    <div className="bm-un">@{ bestReaction3[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ bestReaction3[2] }개</div>
                </div>
              </div>
            </div>
          </Slider>
        </div>
        
      </div>
    )
  }
}

export default BestMember