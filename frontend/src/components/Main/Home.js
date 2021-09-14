import React from "react";
import { Link } from "react-router-dom"; 

class Home extends React.Component {
  state = {

  };

  render() {
    return (
      <div>
        <h1>메인화면</h1>
        <Link to="/login">로그인</Link>
      </div>
    )
  }
}

export default Home