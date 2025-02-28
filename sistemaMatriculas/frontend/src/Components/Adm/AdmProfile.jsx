import React from "react";
import styles from "./AdmProfile.module.css";

const AdmProfile = () => {
  return (
    <div>
      <h1>Administração</h1>
      <form>
        <div>
          <label>
            <input
              className={styles.input}
              type="text"
              placeholder="Digite o nome da disciplina"
            />
          </label>
        </div>

        <button className={styles.button} type="submit">
          Adicionar
        </button>
      </form>

      <h2>Disciplinas</h2>
      <table className={styles.table}>
        <thead>
          <tr>
            <th>Disciplina</th>
            <th>Quantidade de alunos</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Cálculo</td>
            <td>50</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default AdmProfile;
