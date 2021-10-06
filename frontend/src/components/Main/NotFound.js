import React from "react";

import "./css/NotFound.css";

class NotFound extends React.Component {
  componentDidMount() {
    // 다크모드 확인
    if (localStorage.getItem('darkmode')) {
      document.querySelector(".not-found").classList.add("dark-not-found")
    }
  }

  render() {
    const zzanggu = '/image/zzanggu.gif'

    return (
      <div className="not-found">
        <h1>404</h1>
        <h1>Not Found</h1>        
        <img className="not-found-img" src={zzanggu} alt="404" />
        <h5>존재하지 않는 페이지 입니다.</h5>
      </div>
    )
  }
};

export default NotFound;