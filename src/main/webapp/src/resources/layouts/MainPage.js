import React from "react";
import ReactDOM from 'react-dom'
import {Route, Switch} from "react-router-dom";
import RentRoom from "../pages/RentRoom";
import FindRoom from "../pages/FindRoom";
import Statistics from "../pages/Statistics";


const MainPage = () => {
  return (
    <>
      <Switch>
        <Route path="/find-room" component={FindRoom} />
        <Route path="/rent-room" component={RentRoom} />
        <Route path="/statistics" component={Statistics} />
      </Switch>
    </>
  );
};

ReactDOM.render(MainPage, document.getElementById('root'));

export default MainPage;
