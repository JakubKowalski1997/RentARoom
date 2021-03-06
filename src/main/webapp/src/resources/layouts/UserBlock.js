import React, {Component} from "react";
import {NavLink, Redirect, Route, Switch} from "react-router-dom";
import "../styles/UserBlock.css";

class UserBlock extends Component {
    // state = {
    //   userName: "Admin",
    //   redirect: false
    // };

    setRedirect = () => {
        this.setState({
            redirect: true
        })
    };
    renderRedirect = () => {
        if (this.state.redirect) {
            return <Redirect to='/privacy-policy'/>
        }
    };

    constructor(props) {
        super(props);
        // fetch("/username")
        //     .then(response => {
        //         if (response.ok) { return response; }
        //         throw Error(response.status);
        //     })
        //     .then(response => response.json())
        //     .then(data => { this.setState({ userName: data}); })
        //     .catch(error => console.log("Following error occurred: " + error));
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

    componentDidMount() {
        const that = this;
        fetch("/username")
            .then(res => res.json())
            .then((result) => {
                this.setState({
                    userName: result.username
                });

            });
    }
    redirectToChangePassword() {
        window.location.replace("/login");
    }

  render() {
    return (
        <div>
            {
                this.state.userName !== "anonymousUser"
                    ?<div className="userBlock">
                        <h3>
                            <span> Welcome {this.state.userName}</span>!
                        </h3>
                        <div className="userButtons">
                            <NavLink to="/settings">Settings</NavLink>
                            {this.renderRedirect()}
                            <button onClick={this.setRedirect}>Logout</button>
                            <Route path = '/privacy-policy' component={() => {
                                window.location.href = "/logout";
                                return null;
                            }}/>
                        </div>
                    </div>
                    : <div/>
            }
        </div>
    );
  }
}

export default UserBlock;
