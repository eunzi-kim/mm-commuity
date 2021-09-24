import React, { Component } from "react";
import Slider from "react-slick";
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

import "./css/BestMember.css"

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

  componentDidMount() {
    // 베스트멤버 api 받기
    // 예시
    this.setState({
      best1: ["MM이름으로오기", "sjd0051", "300point"],
      best2: ["성규는 못말려", "tjdrbapfhd", "200point"],
      best3:  ["금지은지동지", "xxnxi", "150point"],
      bestUploader1: ["우수업로더1", "sjd0051", "53"],
      bestUploader2: ["우수업로더2", "tjdrbapfhd", "12"],
      bestUploader3:  ["우수업로더3", "xxnxi", "7"],
      bestReaction1: ["황성안[광주_1반_C103] 팀원", "sjd0051", "52"],
      bestReaction2: ["김성규[광주_1반_C103] 팀장", "tjdrbapfhd", "10"],
      bestReaction3:  ["이태성[광주_2반_C204] 팀장", "lts", "4"],
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
                <div className="bm-medal">🥇</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ best1[0] }</b></div>
                    <div className="bm-un">@{ best1[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ best1[2] }</div>
                </div>
              </div>
              <div className="bm-content">
                <div className="bm-medal">🥈</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ best2[0] }</b></div>
                    <div className="bm-un">@{ best2[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ best2[2] }</div>
                </div>
              </div>
              <div className="bm-content">
                <div className="bm-medal">🥉</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
                  <div className="bm-info">
                    <div className="bm-nn"><b>{ best3[0] }</b></div>
                    <div className="bm-un">@{ best3[1] }</div>
                  </div>
                </div>
                <div>
                  <div className="bm-point">{ best3[2] }</div>
                </div>
              </div>
            </div>

            <div className="bm-body">
              <h5 className="bm-title">❤ 우수 업로더 ❤</h5>
              <div className="bm-content">
                <div className="bm-medal">🥇</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
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
                <div className="bm-medal">🥈</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
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
                <div className="bm-medal">🥉</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
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
                <div className="bm-medal">🥇</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
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
                <div className="bm-medal">🥈</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
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
                <div className="bm-medal">🥉</div>
                <div className="bm-profile">
                  <div className="bm-image">
                    이미지
                  </div>
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