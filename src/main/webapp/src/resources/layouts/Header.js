import React from "react";
import { NavLink } from "react-router-dom";
import "../styles/Header.css";

const Header = () => {
  return (
    <div className="title">
      <NavLink to="/" exact>
        <span>
          RentARoom
        </span>
      </NavLink>
    </div>
  );
};

export default Header;
