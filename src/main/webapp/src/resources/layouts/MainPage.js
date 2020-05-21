import React from "react";
import ReactDOM from 'react-dom'
import {Route, Switch} from "react-router-dom";
import RentRoom from "../pages/RentRoom";
import FindRoom from "../pages/FindRoom";
import Statistics from "../pages/Statistics";
import Error404 from "../pages/Error404";
import Room from "../pages/Room";
import RoomMap from "../pages/RoomMap";
import Settings from "../pages/Settings";


const MainPage = () => {
    return (
        <>
            <Switch>
                <Route path="/" exact component={FindRoom}/>
                <Route path="/find-room" component={FindRoom}/>
                <Route path="/rent-room" component={RentRoom}/>
                <Route path="/map" component={RoomMap}/>
                <Route path="/statistics" component={Statistics}/>
                <Route path="/room/:id" exact component={Room}/>
                <Route path="/settings" exact component={Settings}/>
                <Route component={Error404}/>
            </Switch>
        </>
    );
};

ReactDOM.render(MainPage, document.getElementById('root'));

export default MainPage;
