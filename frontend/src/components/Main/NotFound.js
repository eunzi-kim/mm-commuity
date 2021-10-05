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
    return (
      <div className="not-found">
        <h1>404 Not Found</h1>
        <h2>존재하지 않는 페이지 입니다.</h2>
      </div>
    )
  }
};

export default NotFound;