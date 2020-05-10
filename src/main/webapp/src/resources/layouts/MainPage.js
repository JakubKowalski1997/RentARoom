import React from "react";
import ReactDOM from 'react-dom'
import {Route, Switch} from "react-router-dom";
import RentRoom from "../pages/RentRoom";
import FindRoom from "../pages/FindRoom";
import Statistics from "../pages/Statistics";
import Error404 from "../pages/Error404";


const MainPage = () => {
    return (
        <>
            <Switch>
                <Route path="/" exact component={FindRoom}/>
                <Route path="/find-room" component={FindRoom}/>
                <Route path="/rent-room" component={RentRoom}/>
                <Route path="/statistics" component={Statistics}/>
                <Route component={Error404}/>
            </Switch>
        </>
    );
};

ReactDOM.render(MainPage, document.getElementById('root'));

export default MainPage;
