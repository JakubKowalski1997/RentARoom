import React from "react";
import {withRouter} from "react-router-dom";
import {Map as LeafletMap, Marker, Popup, TileLayer} from 'react-leaflet';
import {OpenStreetMapProvider} from 'leaflet-geosearch';
import "../../../node_modules/leaflet/dist/leaflet.css";
import "../styles/RoomMap.css";
import icon from "leaflet/dist/images/marker-icon.png";
import iconShadow from "leaflet/dist/images/marker-shadow.png";
import L from 'leaflet';

class RoomMap extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            addressList: [],
            markers: [],
            dataJSON: "",
            labels:[],
            ids:[]
        };

    }

    componentDidMount() {
        fetch("/room")
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
            .then(() => {
                for (let i = 0; i < this.state.dataJSON.length; i++) {
                    this.state.addressList.push(this.state.dataJSON[i].address)
                }

                let markersLoc = this.state.markers;
                for (let i = 0; i < this.state.addressList.length; i++) {
                    var query_addr = this.state.addressList[i].street + ", " + this.state.addressList[i].city + ", PL";
                    const provider = new OpenStreetMapProvider();
                    var query_promise = provider.search({query: query_addr});
                    console.log(query_promise);
                    query_promise.then(value => {
                        // Success!
                        var x_coor = value[0].x;
                        var y_coor = value[0].y;
                        var label = value[0].label;
                        this.state.labels.push(label);
                        console.log(this.state.dataJSON[i].id);
                        this.state.ids.push("/#/room/" + this.state.dataJSON[i].id);
                        markersLoc.push([parseFloat(y_coor), parseFloat(x_coor)]);
                        this.setState({markers: markersLoc})
                    }, reason => {
                        console.log(reason); // Error!
                    });

                }
                console.log(markersLoc);

            })
            .catch(error => console.log("Following error occurred: " + error));

    }

    render() {
        L.Marker.prototype.options.icon = L.icon({
            iconUrl: icon,
            shadowUrl: iconShadow
        });
        console.log(this.state.markers);

        return (
            <LeafletMap
                center={[52, 19]}
                zoom={6}
                maxZoom={10}
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
                {this.state.markers.map((position, idx) =>
                    <Marker key={`marker-${idx}`} position={position}>
                        <Popup>
                            <div>
                                <span>{this.state.labels[idx]}</span>
                                <br/>
                                <a href={this.state.ids[idx]}>Go to room</a>
                            </div>
                        </Popup>
                    </Marker>
                )}
            </LeafletMap>
        );
    }
}

export default RoomMap;