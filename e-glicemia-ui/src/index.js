import React, { Component } from 'react';
import { render } from 'react-dom';
import Hello from './Hello';
import './style.css';
import Users from './Users';
import { Provider } from 'react-redux';
import { getStore } from './store';

const store = getStore();

class App extends Component {
  constructor() {
    super();
    this.state = {
      name: 'React',
      users: []
    };
  }

  render() {
    return (
      <Provider store={store}>
        <div>
          <Hello name={this.state.nome} />
          <h1 >
          React, Redux and Redux saga all CRUD operations!!
        </h1>
          <Users users={this.state.users} />
        </div>
      </Provider>
    );
  }
}

render(<App />, document.getElementById('root'));
