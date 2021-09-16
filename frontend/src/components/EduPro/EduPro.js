import React from "react";
import axios from "axios";
import { Form, Button } from "react-bootstrap";
import "./EduPro.css";

class EduPro extends React.Component {
    state = {
    };

    render() {
        const logo = '/image/logo_2.png'

        return (
            <div className="pro-container">
                <div className="pro-head">
                    <div className="pro-head-left">
                        <img className="pro-logo" src={logo}/>
                    </div>

                    <div className="pro-head-right">
                        <h1>교육생 조회</h1>
                    </div>
                </div>
                
                <div className="pro-body-container">
                <hr />
                    <div className="pro-body-head">
                    <Button className="category-btn" onClick={this.onClickLogin}>게시글</Button>
                    <Button className="category-btn" onClick={this.onClickLogin}>이모지</Button>
                    <Button className="category-btn" onClick={this.onClickLogin}>댓  글</Button>
                    </div>
                    <div className="pro-body-body">
                        <div className="pro-body-bodylist">
                            <div>학생 1</div>
                            <div>학생 2</div>
                            <div>학생 3</div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default EduPro