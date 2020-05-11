import React, {Component} from "react";
import {NavLink} from "react-router-dom";
import "../styles/Navigation.css";
import Button from "react-bootstrap/Button";

const navList = [
    // { name: "supermasters", path: "/supermasters" },
    // { name: "domains", path: "/domains" },
    // { name: "records", path: "/records" },
    // { name: "administration", path: "/administration" },
    {name: "Find room", path: "/find-room"},
    {name: "Rent room", path: "/rent-room"},
    {name: "Map", path: "/map"},
    {name: "Your rooms", path: "/map"},
    {name: "Requested rooms", path: "/map"},
    {name: "Statistics", path: "/statistics"}
];

class Navigation extends Component {
    constructor(props) {
        super(props);

        this.state = {
            redirect: false,
        };
        fetch("/username")
            .then(res => res.json())
            .then((result) => {
                this.setState({
                    userName: result.username
                });

            });
    }

    static redirectToLogin() {
        window.location.replace("/login");
    }

    state = {
        userPanelShow: false
    };
    userPanelShow = () => {
        const header = document.getElementsByTagName("header")[0];
        if (!this.state.userPanelShow) header.classList.add("deleteOverflow");
        else header.classList.remove("deleteOverflow");

        const icon = document.getElementsByClassName("toggle")[0];
        if (!this.state.userPanelShow) icon.classList.add("active");
        else icon.classList.remove("active");

        const userBlock = document.getElementsByClassName("userBlock")[0];
        if (!this.state.userPanelShow) userBlock.classList.add("showBlock");
        else userBlock.classList.remove("showBlock");

        this.setState({
            userPanelShow: !this.state.userPanelShow
        });
    };

    render() {
        const menu = navList.map(item => (
            <li key={item.name}>
                <NavLink to={item.path} exact={item.exact ? item.exact : false}>
                    {item.name}
                </NavLink>
            </li>
        ));
        return (

            <>
                <nav className="menu">
                    <div className="menuContent">
                        <ul>{menu}</ul>
                    </div>
                </nav>

                {
                    this.state.userName !== "anonymousUser"
                        ? <span className="toggle" onClick={this.userPanelShow}>
                                     <i className="fas fa-user"></i>
                        </span>
                        : <div className="userBlock"><Button onClick={Navigation.redirectToLogin}>LOGIN</Button></div>
                }


            </>


        );
    }
}

export default Navigation;
