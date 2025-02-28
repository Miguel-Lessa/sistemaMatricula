import React from "react";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Login from "./Components/Login/Login";
import AlunoProfile from "./Components/Aluno/AlunoProfile";
import ProfessorProfile from "./Components/Professor/ProfessorProfile";
import Matricula from "./Components/Matricula/Matricula";
import Header from "./Header";
import "./App.css";

const App = () => {
  return (
    <>
      <BrowserRouter>
        <Login />
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/alunoprofile" element={<AlunoProfile />} />
          <Route path="/matricula" element={<Matricula />} />
          <Route path="/professorprofile" element={<ProfessorProfile />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
