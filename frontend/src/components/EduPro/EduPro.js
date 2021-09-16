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
                    <div className="pro-title-left">
                        왼
                    </div>

                    <div className="pro-title-right">
                        오
                    </div>
                </div>
                <div className="pro-student-list-container">
                    목록
                </div>
            </div>
        )
    }
}

export default EduPro