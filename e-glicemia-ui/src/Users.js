import * as React from 'react';
import Axios from 'axios';
import { connect } from 'react-redux';
import { GET_USERS, PUT_USER, POST_USER, DELETE_USER, CANCEL_USER_UPDATE, EDIT_USER } from './actions';

class Users extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      users: []
    }
  }

  componentDidMount() {
    this.props.getUsers();
  }

  render() {
    return (
      <div className="container" >
        <div className="row mb-4">
          <div className="col-5" >
            <input id="name" onChange={(event) => this.inputChanged(event, 'nome')} />
          </div>
          <div className="col-5" >
            <input id="email" onChange={(event) => this.inputChanged(event, 'email')} />
          </div>
          <div className="col-2" >
            <button onClick={this.addUser}>Add User</button>
          </div>
        </div>
        {(this.props.users || []).map(user => {
          return (
            <div className="row mb-2">
              <div className="col-5" >
                {user.nome}
              </div>
              <div className="col-5">
                <a href={user.email}> {user.email}</a>
              </div>
              <div className="col-2">
                {user.editMode ?
                  <>
                    <button className="float-left mr-2" onClick={() => { this.updateUser(user) }}>Update</button>
                    <button className="float-left" onClick={() => { this.cancelUpdate(user) }}>Cancel</button>
                  </> :
                  <>
                    <button className="float-left mr-2" onClick={() => { this.editUser(user) }}>Edit</button>
                    <button className="float-left" onClick={() => { this.deleteUser(user) }}>Delete</button>
                  </>
                }
              </div>
            </div>
          )
        })}
      </div >
    )
  }

  inputChanged = (event, field) => {
    this.setState({ [field]: event.target.value });
  }

  addUser = () => {
    this.props.addUser({ nome: this.state.nome, email: this.state.email });
  }

  deleteUser = (user) => {
    this.props.deleteUser(user.id);
  }

  editUser = (user) => {
    this.props.editUser(user.id);
  }

  updateUser = (user) => {
    this.props.updateUser(user);
  }

  cancelUpdate = (user) => {
    this.props.cancelUpdate(user.id);
  }

}

const mapStateTopProps = (state) => {
  return {
    users: state.users
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    getUsers: () => {
      dispatch({ type: GET_USERS });
    },
    addUser: (user) => {
      dispatch({ type: POST_USER, value: user });
    },
    deleteUser: (userId) => {
      dispatch({ type: DELETE_USER, value: userId });
    },
    editUser: (userId) => {
      dispatch({ type: EDIT_USER, value: userId });
    },
    updateUser: (user) => {     
      console.log('dispatch update', user) 
      dispatch({ type: PUT_USER, value: user });
    },
    cancelUpdate: (userId) => {
      dispatch({ type: CANCEL_USER_UPDATE, value: userId });
    },
  }
}

export default connect(mapStateTopProps, mapDispatchToProps)(Users);
