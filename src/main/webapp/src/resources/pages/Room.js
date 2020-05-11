import React from "react";
import {useParams, withRouter} from "react-router-dom";
import "../styles/Room.css";

class Room extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            dataJSON: ""
        };
    }

    componentDidMount() {
        const id = this.props.match.params.id;
        fetch("/room/" + id)
            .then(response => {
                if (response.ok) {
                    return response;
                }
                throw Error(response.status);
            })
            .then(response => response.json())
            .then(data => {
                this.setState({dataJSON: data});
            })
            .catch(error => console.log("Following error occurred: " + error));
    }

    render() {
        console.log(this.state.dataJSON);

        return (
            <div>
                {
                    this.state.dataJSON !== ""
                        ?
                        <div>
                            <div className="container">
                                <div className="row">
                                    <div id="title" className="col">
                                        <h1>{this.state.dataJSON.title}</h1>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col">
                                        <h3>{this.state.dataJSON.price}</h3>

                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col">
                                        <h3>{this.state.dataJSON.area}</h3>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col">
                                        <h3>{this.state.dataJSON.address.country}</h3>
                                    </div>
                                    <div className="col">
                                        <h3>{this.state.dataJSON.address.city}</h3>
                                    </div>
                                    <div className="col">
                                        <h3>{this.state.dataJSON.address.zipcode}</h3>
                                    </div>
                                    <div className="col">
                                        <h3>{this.state.dataJSON.address.street}</h3>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col">
                                        <h3>{this.state.dataJSON.description}</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                        : <h3/>
                }

            </div>
        );
    }

}

export default withRouter(Room);