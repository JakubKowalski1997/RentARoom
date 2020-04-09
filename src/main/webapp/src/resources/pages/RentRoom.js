import React from "react"
import "../styles/RentRoom.css"
import 'bootstrap/dist/css/bootstrap.min.css'
import {Tab, Tabs} from 'react-bootstrap'

import RentRoomForm from "../components/RentRoomForm";

class RentRoom extends React.Component {

    render() {
        return (
            <div className="exactRoom">
                <RentRoomForm/>
            </div>
        );
    }
}

export default RentRoom;
