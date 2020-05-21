import React from "react";
import {useParams, withRouter} from "react-router-dom";
import "../styles/Room.css";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "../styles/Settings.css"


class Settings extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userName: '',
            newPassword: '',
            newPasswordConfirm: '',
            message: '',
            proper: false
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

    }

    handleSubmit = e => {
        e.preventDefault();
        let password = {
            newPassword: this.state.newPassword,
            newPasswordConfirm: this.state.newPasswordConfirm
        };
        fetch('/change-passwd', {
            method: 'POST',
            body: JSON.stringify(password),
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                'Access-Control-Allow-Origin': '*'
            },
        }).then(function (response) {
            return response.json();
        }).then((responseData) => {
            console.log(responseData);
            this.setState({
                proper: responseData.proper,
                message: responseData.errorMessage
            });
            // if (response.url.indexOf("login") !== -1) {
            //     this.redirectToLogin();
            // } else {
            //     this.redirectToHome();
            // }
        });
        this.state = {
            newPassword: '',
            newPasswordConfirm: '',
        };

    };

    redirectToLogin() {
        window.location.replace("/login");
    }

    redirectToHome() {
        window.location.replace("/");

    }

    handleChange = e => {
        this.setState({[e.target.name]: e.target.value});
    };

    render() {

        return (
            <div>
                {
                    this.state.userName !== "anonymousUser" && this.state.userName !== ''
                        ? this.getChangePasswordForm()
                        : <div className="row h-100 justify-content-center align-items-center">
                            <Button onClick={this.redirectToLogin}>LOGIN</Button>
                        </div>
                }
            </div>

        );
    }

    getChangePasswordForm() {
        return <div>
            <div className="row h-100 justify-content-center align-items-center">
                <Form
                    onSubmit={e => this.handleSubmit(e)}>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>New password</Form.Label>
                        <Form.Control type="password" name="newPassword" value={this.state.newPassword}
                                      onChange={this.handleChange} placeholder="Password"/>
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Confirm password</Form.Label>
                        <Form.Control type="password" name="newPasswordConfirm" value={this.state.newPasswordConfirm}
                                      onChange={this.handleChange} placeholder="Confirm password"/>
                    </Form.Group>


                    <Button variant="primary" type="submit"> Change password </Button>
                </Form>
            </div>
            <div className="row h-100 justify-content-center align-items-center">
                <div className="col-sm-12 text-center">
                    {
                        this.state.proper === true
                            ? <span className="changed">{this.state.message}</span>
                            : <span className="notChanged">{this.state.message}</span>
                    }
                </div>
            </div>
        </div>;
    }
}

export default withRouter(Settings);