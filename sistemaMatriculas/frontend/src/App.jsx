import React from "react";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Login from "./Components/Login/Login";
import AlunoProfile from "./Components/Aluno/AlunoProfile";
import ProfessorProfile from "./Components/Professor/ProfessorProfile";
import Matricula from "./Components/Matricula/Matricula";
import AdmProfile from "./Components/Adm/AdmProfile";
import Header from "./Header";
import "./App.css";

const App = () => {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/alunoprofile" element={<AlunoProfile />} />
          <Route path="/matricula" element={<Matricula />} />
          <Route path="/professorprofile" element={<ProfessorProfile />} />
          <Route path="/admprofile" element={<AdmProfile />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
