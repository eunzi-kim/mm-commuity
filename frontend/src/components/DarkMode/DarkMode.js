import React from "react";

import "./DarkMode.css";

class DarkMode extends React.Component{

  componentDidMount() {
    if (localStorage.getItem('darkmode')) {
      document.querySelector(".dark-mode").classList.add("darkmode-none")
    } else {
      document.querySelector(".light-mode").classList.add("darkmode-none")
    }
  }
  
  onClickDarkmode() {  
    localStorage.setItem('darkmode', 'dark');
    if (document.querySelector(".darkmode-none")) {
      document.querySelector(".darkmode-none").classList.remove("darkmode-none")
    }
    document.querySelector(".dark-mode").classList.add("darkmode-none")

    var url = document.location.href.split("/");
    var page = url[url.length-1]

    // 로그인 화면
    if (page === "login") {
      document.querySelector(".login-container").classList.add("dark-login-bg")
      document.querySelector(".login-left").classList.add("dark-left-bg")
      document.querySelector(".login-right").classList.add("dark-right-bg")
      document.querySelector(".login-id-box").classList.add("dark-login-box")
      document.querySelector(".login-password-box").classList.add("dark-login-box")
      document.querySelector(".btn-primary").classList.add("btn-secondary")
      document.querySelector(".signin-btn").classList.add("dark-login-btn")
    }

    // 메인 화면
    else if (page === "") {
      document.querySelector(".main").classList.add("dark-main-body")
      document.querySelector(".main-right").classList.add("dark-main-body")
      document.querySelector(".c-nav").classList.add("dark-nav")
      document.querySelector(".sub-nav").classList.add("dark-sub-nav")
      document.querySelector(".sub-nav-bottom").classList.add("dark-sub-nav-bottom")
      document.querySelector(".nav-down").classList.add("dark-nav-down")
      document.querySelector(".search-input").classList.add("dark-search-input")
      document.querySelector(".c-title-btn").classList.add("dark-c-title-btn")
      document.querySelector(".c-title-cal").classList.add("dark-c-title-cal")
      document.querySelector(".calender").classList.add("dark-calender")
      document.querySelector(".c-channels").classList.add("dark-c-channels")
      document.querySelector(".s-detail-body").classList.add("dark-s-detail-body")
    }

    // 프로페이지
    else if (page === "edupro") {
      document.querySelector('.pro-container').classList.add('dark-pro-container')
    }

    // 스크랩(즐겨찾기) 페이지
    else if (page ==="scrap") {
      document.querySelector('.scrap-main').classList.add('dark-scrap-main')
      document.querySelector('.MyScrap-container').classList.add('dark-MyScrap-container')
      document.querySelector(".scrap-search-input").classList.add("dark-search-input")
    }
  }


  onClickLightmode() {
    if (localStorage.getItem('darkmode')) {
      localStorage.removeItem('darkmode');
    } 
    if (document.querySelector(".darkmode-none")) {
      document.querySelector(".darkmode-none").classList.remove("darkmode-none")
    }
    document.querySelector(".light-mode").classList.add("darkmode-none")

    var url = document.location.href.split("/");
    var page = url[url.length-1]

    // 로그인 화면
    if (page === "login") {
      document.querySelector(".login-container").classList.remove("dark-login-bg")
      document.querySelector(".login-left").classList.remove("dark-left-bg")
      document.querySelector(".login-right").classList.remove("dark-right-bg")
      document.querySelector(".login-id-box").classList.remove("dark-login-box")
      document.querySelector(".login-password-box").classList.remove("dark-login-box")
      document.querySelector(".btn-primary").classList.remove("btn-secondary")
      document.querySelector(".signin-btn").classList.remove("dark-login-btn")
    }

    // 메인 화면
    else if (page === "") {
      document.querySelector(".main").classList.remove("dark-main-body")
      document.querySelector(".main-right").classList.remove("dark-main-body")
      document.querySelector(".c-nav").classList.remove("dark-nav")
      document.querySelector(".sub-nav").classList.remove("dark-sub-nav")
      document.querySelector(".sub-nav-bottom").classList.remove("dark-sub-nav-bottom")
      document.querySelector(".nav-down").classList.remove("dark-nav-down")
      document.querySelector(".search-input").classList.remove("dark-search-input")
      document.querySelector(".c-title-btn").classList.remove("dark-c-title-btn")
      document.querySelector(".c-title-cal").classList.remove("dark-c-title-cal")
      document.querySelector(".calender").classList.remove("dark-calender")
      document.querySelector(".c-channels").classList.remove("dark-c-channels")
      document.querySelector(".s-detail-body").classList.remove("dark-s-detail-body")
    }

    // 프로페이지
    else if (page === "edupro") {
      document.querySelector('.pro-container').classList.remove('dark-pro-container')
    }

    // 스크랩(즐겨찾기) 페이지
    else if (page ==="scrap") {
      document.querySelector('.scrap-main').classList.remove('dark-scrap-main')
      document.querySelector('.MyScrap-container').classList.remove('dark-MyScrap-container')
      document.querySelector(".scrap-search-input").classList.remove("dark-search-input")
    }

  }


  render() {
    return (
      <div className="darkmode-container">
        <button className="dark-mode" onClick={this.onClickDarkmode}>
          <h3>🌑</h3>
        </button>
        <button className="light-mode" onClick={this.onClickLightmode}>
          <h3>🌕</h3>
        </button>
      </div>
    )
  }
}

export default DarkMode;