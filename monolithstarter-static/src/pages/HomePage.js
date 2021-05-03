import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';

class HomePage extends Component {
  render() {
    return (
        <div className="home-page">
          <Link to="/csvdupl">Click to see duplicates and nonduplicates</Link>
        </div>
    );
  }
}

export default withRouter(HomePage);
