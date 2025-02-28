import React from "react";
import styles from "./Professor.module.css";

const ProfessorProfile = () => {
  return (
    <div>
      <h1>Prof. Jane Doe</h1>
      <table className={styles.table}>
        <thead>
          <tr>
            <th>Nome</th>
            <th>Matrícula</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Ana Souza</td>
            <td>20241001</td>
          </tr>
          <tr>
            <td>Bruno Lima</td>
            <td>20241002</td>
          </tr>
          <tr>
            <td>Carla Mendes</td>
            <td>20241003</td>
          </tr>
          <tr>
            <td>Diego Santos</td>
            <td>20241004</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default ProfessorProfile;
