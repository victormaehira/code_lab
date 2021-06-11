import React, { useState } from "react";
import { TextField, Button, Switch, FormControlLabel } from "@material-ui/core";
import 'fontsource-roboto';
import { Typography } from "@material-ui/core"
import axios from 'axios';

import Snackbar from "@material-ui/core/Snackbar";
import MyAlert from "@material-ui/lab/Alert";

function FormularioAlarme() {
  const [hora, setHora] = useState("07:30");
  const [ativo, setAtivo] = useState(true);

  const [open, setOpen] = useState(false);
  const [severity, setSeverity] = useState("info");
  const [message, setMessage] = useState("");

  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
  
    setOpen(false);
  };

  return (
    <form
      onSubmit={(event) => {
        event.preventDefault();
        aoEnviar({ hora, ativo });
      }}>
      <Typography variant="h3" component="h1" align="center" >Configurar Alarme</Typography>
      <TextField
        value={hora}
        onChange={(event) => {
          setHora(event.target.value);
        }}
        id="hora"
        label="hora"
        type="time"
        //defaultValue="07:30"
        variant="outlined"
        margin="normal"
        fullWidth
      />

      <FormControlLabel
        label="Ativo"
        control={
          <Switch
            checked={ativo}
            onChange={(event) => {
              setAtivo(event.target.checked);
            }}
            name="ativo"
            color="primary"
          />
        }
      />
      <Typography display="block" />


      <Button type="submit" variant="contained" color="primary">
        Salvar
      </Button>

      <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
        <Alert onClose={handleClose} severity={severity}>
          {message}
        </Alert>
      </Snackbar>
    </form>
  );


  function aoEnviar(dados) {
    console.log(dados); 
    const uri = 'https://eglicemia-victor.herokuapp.com/alarmes';
    axios.post(uri, dados)
    .then((response) => {
      console.log(response);
      setMessage("Inserido com sucesso!");
      setOpen(true);
      setSeverity("success");
    }, (error) => {
      console.log(error);
      setMessage("Erro ao inserir");
      setOpen(true);
      setSeverity("warning");
    });
  }
}

function Alert(props) {
  return <MyAlert elevation={6} variant="filled" {...props} />;
}

export default FormularioAlarme;
