import React from "react";
import ReactDOM from 'react-dom'
import { Route, Switch } from "react-router-dom";
import RentRoom from "../pages/RentRoom";
import FindRoom from "../pages/FindRoom";


const MainPage = () => {
  return (
    <>
      <Switch>
        <Route path="/find-room" component={FindRoom} />
        <Route path="/rent-room" component={RentRoom} />
      </Switch>
    </>
  );
};

ReactDOM.render(MainPage, document.getElementById('root'))

export default MainPage;
