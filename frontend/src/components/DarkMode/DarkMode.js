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
  }

  onClickLightmode() {
    if (localStorage.getItem('darkmode')) {
      localStorage.removeItem('darkmode');
    } 
    if (document.querySelector(".darkmode-none")) {
      document.querySelector(".darkmode-none").classList.remove("darkmode-none")
    }
    document.querySelector(".light-mode").classList.add("darkmode-none")
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