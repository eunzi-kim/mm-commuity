import React from "react";
import { Link } from "react-router-dom"; 

class Home extends React.Component {
  state = {
    nickname: ""
  };

  takeUserInfo = () => {
    if (JSON.parse(sessionStorage.getItem('userInfo'))) {
      const userInfo = JSON.parse(sessionStorage.getItem('userInfo'))
      this.setState({nickname: userInfo.nickname})
      console.log(userInfo)
    }
  }  

  componentDidMount() {
    this.takeUserInfo()
  }

  onLogout = () => {
    sessionStorage.clear()
    window.location.replace("/login");
  }

  render() {
    const { nickname } = this.state;

    return (
      <div>
        <h1>메인화면</h1>
        <p>{nickname}</p>
        <Link to="/login" className="login">로그인</Link>
        <div></div>
        <Link to="/" onClick={this.onLogout}>로그아웃</Link>
      </div>
    )
  }
}

export default Home