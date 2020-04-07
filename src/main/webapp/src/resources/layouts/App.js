import React, { Component } from "react";
import { HashRouter as Router } from "react-router-dom";
import "../styles/App.css";
import Header from "./Header";
import Navigation from "./Navigation";
import UserBlock from "./UserBlock";
// import MainPage from "./MainPage";
// import Footer from "./Footer";

class App extends Component {
  render() {
    return (
        <Router>
            <div className="app">
                <header>
                    {<Header />}
                    {<Navigation />}
                </header>
                <aside>{<UserBlock />}</aside>
            </div>
        </Router>
    );
  }
}

export default App;
