import React, { useState } from "react";
import { TextField, Button } from "@material-ui/core";
import 'fontsource-roboto';
import { Typography } from "@material-ui/core"
import axios from 'axios';

import Snackbar from "@material-ui/core/Snackbar";
import MyAlert from "@material-ui/lab/Alert";

function FormularioGlicemia() {
  const [data, setData] = useState("2021-05-28T10:30");
  const [valor, setValor] = useState("");

  const [open, setOpen] = useState(false);
  const [severity, setSeverity] = useState("info");
  const [message, setMessage] = useState("");

  const [erros, setErros] = useState({ valor: { valido: true, texto: "" } })

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
        aoEnviar({ data, valor });
      }}>
      <Typography variant="h3" component="h1" align="center" >Cadastro de Glicemia</Typography>
      <TextField
        value={data}
        onChange={(event) => {
          setData(event.target.value);
        }}
        id="data"
        label="data"
        type="datetime-local"
        //defaultValue="2021-05-28T10:30"
        variant="outlined"
        margin="normal"
        fullWidth
      />

      <TextField
        value={valor}
        onChange={(event) => {
          setValor(event.target.value);
        }}
        onBlur={(event) => {
          const ehValido = validarCampo(valor);
          setErros({ valor: ehValido })
        }}
        error={!erros.valor.valido}
        helperText={erros.valor.texto}
        id="valor"
        type="number"
        label="mg/dL"
        variant="outlined"
        margin="normal"
        fullWidth
      />


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

    if (dados["valor"].length <= 0) {
      console.log("Todos campos sao obrigatorios");
      setMessage("Todos os campos sao obrigatórios");
      setOpen(true);
      setSeverity("error");
      return;
    }

    const uri = 'https://eglicemia-victor.herokuapp.com/glicemias';
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


function validarCampo(campo) {
  if (campo.length <= 0) {
    return { valido: false, texto: "Não pode ser vazio." }
  } else {
    return { valido: true, texto: "" }
  }
}

function Alert(props) {
  return <MyAlert elevation={6} variant="filled" {...props} />;
}

export default FormularioGlicemia;
