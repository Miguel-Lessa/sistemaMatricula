import React from "react";
import styles from "./Professor.module.css";

const ProfessorProfile = () => {
  return (
    <div>
      <h1>Prof. Jane Doe</h1>
      <table className={styles.table}>
        <thead>
          <tr>
            <th>Aluno</th>
            <th>Matrícula</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Teste</td>
            <td>123</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default ProfessorProfile;
