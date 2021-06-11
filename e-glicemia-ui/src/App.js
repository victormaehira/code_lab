import React, { Component } from "react";
import "./App.css";
import FormularioCadastro from "./components/FormularioCadastro/FormularioCadastro";
import FormularioGlicemia from "./components/FormularioGlicemia/FormularioGlicemia";
import FormularioAlarme from "./components/FormularioAlarme/FormularioAlarme";
import 'fontsource-roboto';
import {Container, Typography } from "@material-ui/core"
import Appbar from "./components/Appbar.js";

class App extends Component {
  render() {
    return (
      <Container component="article" maxWidth="sm">
        
        <Appbar />
      </Container>
    );
  }
}

export default App;