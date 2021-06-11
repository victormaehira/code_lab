import React from "react";
import 'fontsource-roboto';
import { Typography } from "@material-ui/core"
import logo from './img/glicemia.png'

function Home() {
  
  return (
    <div align="center">
     <Typography variant="h3" component="h1" align="center" >E-glicemia</Typography>
     <pre></pre><pre></pre>
     <img src={logo} /> 
    </div>
  );
}

export default Home;


