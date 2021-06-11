import React, { useState} from "react";
import { TextField, Button} from "@material-ui/core";
import 'fontsource-roboto';
import { Typography } from "@material-ui/core"
import axios from 'axios';

import Snackbar from "@material-ui/core/Snackbar";
import MyAlert from "@material-ui/lab/Alert";

function FormularioCadastro() {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirmation, setPasswordConfirmation] = useState("");

  const [open, setOpen] = useState(false);
  const [severity, setSeverity] = useState("info");
  const [message, setMessage] = useState("");

  const [erros, setErros] = useState({ nome: { valido: true, texto: "" } })
  const [errosEmail, setErrosEmail] = useState({ email: { valido: true, texto: "" } })

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
        aoEnviar({ nome, email, password, passwordConfirmation });
      }}>
      <Typography variant="h3" component="h1" align="center" >Cadastro de Usuário</Typography>
      <TextField
        value={nome}
        onChange={(event) => {
          setNome(event.target.value);
        }}
        onBlur={(event) => {
          const ehValido = validarCampo(nome);
          setErros({ nome: ehValido })
        }}
        error={!erros.nome.valido}
        helperText={erros.nome.texto}
        id="nome"
        label="Nome"
        variant="outlined"
        margin="normal"
        fullWidth
      />
      <TextField
        value={email}
        onChange={(event) => {
          setEmail(event.target.value);
        }}
        onBlur={(event) => {
          const ehValido = validarCampo(email);
          setErrosEmail({ email: ehValido })
        }}
        error={!errosEmail.email.valido}
        helperText={errosEmail.email.texto}
        id="email"
        label="e-mail"
        variant="outlined"
        margin="normal"
        fullWidth
      />
      <TextField
        value={password}
        onChange={(event) => {
          setPassword(event.target.value);
        }}
        id="password"
        type="password"
        label="senha"
        variant="outlined"
        margin="normal"
        fullWidth
      />

      <TextField
        value={passwordConfirmation}
        onChange={(event) => {
          setPasswordConfirmation(event.target.value);
        }}
        id="passwordConfirmation"
        type="password"
        label="confirme sua senha"
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
    if (dados["nome"].length <= 0 ||
      dados["email"].length <= 0 ||
      dados["password"].length <= 0 ||
      dados["passwordConfirmation"].length <= 0) {
      console.log("Todos campos sao obrigatorios");
      setMessage("Todos os campos sao obrigatórios");
      setOpen(true);
      setSeverity("error");
      return;
    }
    if(!validateEmail(dados["email"])) {

    }
    if (dados["password"].length < 8) {
      console.log("A senha tem que ter no mínimo 8 dígitos!");
      setMessage("A senha tem que ter no mínimo 8 dígitos!");
      setOpen(true);
      setSeverity("error");
      return;
    }
    if (dados["password"] !== dados["passwordConfirmation"]) {
      console.log("A senha não confere!");
      setMessage("A senha não confere!");
      setOpen(true);
      setSeverity("error");
      return;
    }

    const uri = 'https://eglicemia-victor.herokuapp.com/usuarios';
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

function validateEmail(email) {
  const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
}

function Alert(props) {
  return <MyAlert elevation={6} variant="filled" {...props} />;
}

export default FormularioCadastro;


