import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import App from "./App.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <a href="/login">Tela de Login | </a>
    <a href="/alunoprofile">Tela do Aluno | </a>
    <a href="/professorprofile">Tela do Professor | </a>
    <a href="/admprofile">Tela do Adm | </a>
    <a href="/matricula">Tela de Matrícula</a>

    <App />
  </StrictMode>
);
