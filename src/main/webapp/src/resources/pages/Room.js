import React from "react";
import {useParams, withRouter} from "react-router-dom";
import "../styles/Room.css";
import icon from "leaflet/dist/images/marker-icon.png";
import iconShadow from "leaflet/dist/images/marker-shadow.png";
import L from 'leaflet';
import {Map as LeafletMap, Marker, Popup, TileLayer} from "react-leaflet";
import {OpenStreetMapProvider} from "leaflet-geosearch";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

class Room extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            dataJSON: "",
            position: "",
            email: "",
            message: ""
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
                var query_addr = data.address.street + ", " + data.address.city + ", PL";
                console.log(query_addr);

                const provider = new OpenStreetMapProvider();
                var query_promise = provider.search({query: query_addr});
                console.log(query_promise);
                query_promise.then(value => {
                    var x_coor = value[0].x;
                    var y_coor = value[0].y;
                    this.setState({position: [parseFloat(y_coor), parseFloat(x_coor)]});
                }, reason => {
                    console.log(reason); // Error!
                });
                this.setState({dataJSON: data});

            })
            .catch(error => console.log("Following error occurred: " + error));
    }

    redirectToHome() {
        window.location.replace("/");
    }

    handleChange = e => {
        this.setState({[e.target.name]: e.target.value});
    };

    handleSubmit = e => {
        e.preventDefault();
        let email = {
            email: this.state.dataJSON.user.email,
            message: this.state.message
        };
        fetch('/room/send-message-to-room-owner', {
            method: 'POST',
            body: JSON.stringify(email),
            headers: {'Content-Type': 'application/json'}
        }).then((response) => {
            this.redirectToHome();
        });
        this.state = {
            email: '',
            message: '',
        };

    };

    render() {
        console.log(this.state.dataJSON);
        L.Marker.prototype.options.icon = L.icon({
            iconUrl: icon,
            shadowUrl: iconShadow
        });

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
                                <div className="row justify-content-center align-items-center top-buffer">
                                    {
                                        this.state.position !== ""
                                            ?
                                            <div className="row">
                                                <div id="map-container" className="col">
                                                    <LeafletMap id="map"
                                                                center={this.state.position}
                                                                zoom={12}
                                                                maxZoom={15}
                                                                attributionControl={true}
                                                                zoomControl={true}
                                                                doubleClickZoom={true}
                                                                scrollWheelZoom={true}
                                                                dragging={true}
                                                                animate={true}
                                                                easeLinearity={0.35}
                                                    >
                                                        <TileLayer
                                                            attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                                                            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                                                        />
                                                        <Marker position={this.state.position}>
                                                            <Popup>
                                                                <div>
                                                                    <span>{this.state.dataJSON.address.street + ", " + this.state.dataJSON.address.city}</span>

                                                                </div>
                                                            </Popup>
                                                        </Marker>
                                                    </LeafletMap>
                                                </div>
                                            </div>
                                            :
                                            <div/>
                                    }
                                </div>
                                <div className="row justify-content-center align-items-center top-buffer">
                                    <div className="col">
                                        <Form onSubmit={e => this.handleSubmit(e)}>
                                            <Form.Group controlId="formGridName">
                                                <Form.Label>Email to room owner</Form.Label>
                                                <Form.Control as="textarea" rows="3" type="text" name="message"
                                                              value={this.state.message}
                                                              onChange={this.handleChange}/>
                                            </Form.Group>
                                            <Button variant="primary" type="submit"> Send email </Button>
                                        </Form>
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