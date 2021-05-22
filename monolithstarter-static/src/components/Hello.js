import React, { Component } from 'react';
import { getHelloMessage } from "../actions/helloAction";


class Hello extends Component {
  constructor(props) {
    super(props);
    this.state = {
      nonduplicates: [],
      duplicates: [],
      message:""
    };
  }


  componentDidMount() {
    this._isMounted = true;
    getHelloMessage().then(message => {
      console.log(message.body);
      if (this._isMounted)
        this.setState({nonduplicates: message.nonduplicates});
        this.setState({duplicates: message.duplicates});
    }).catch(() => {
      if (this._isMounted)
        this.setState({message: 'The server did not respond so...hello from the client!'});
    });
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  render() {
    return (
      <div>
        <div>NonDuplicates</div>
        {this.state.nonduplicates.map(record =>
          <div>{record}</div>
        )}
        <br />
        <div>Duplicates</div>
        {this.state.duplicates.map(record =>
          <div>{record}</div>
        )}
      </div>

    );
  }
}

export default Hello;
