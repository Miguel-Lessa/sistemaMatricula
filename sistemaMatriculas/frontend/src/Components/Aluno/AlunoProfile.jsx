import React from "react";
import styles from "./AlunoProfile.module.css";

const AlunoProfile = () => {
  return (
    <div className={styles.container}>
      <h1>Minhas disciplinas</h1>
      <form action="/action_page.php">
        <div className={styles.checkboxcontainer}>
          <label>
            <input type="checkbox" className={styles.checkbox} />
            <span className={styles.checkmark}></span>Cálculo III
          </label>
        </div>
        <div className={styles.checkboxcontainer}>
          <label>
            <input type="checkbox" className={styles.checkbox} />
            <span className={styles.checkmark}></span>Psicanálise
          </label>
        </div>
        <div className={styles.checkboxcontainer}>
          <label>
            <input type="checkbox" className={styles.checkbox} />
            <span className={styles.checkmark}></span>Física Quântica
          </label>
        </div>

        <div className={styles.botoes}>
          <input className={styles.cancelar} type="submit" value="Cancelar" />
          <input className={styles.button} type="submit" value="Remover" />
        </div>
      </form>

      <h2>Cobranças</h2>

      <table className={styles.table}>
        <thead>
          <tr>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>R$ 5000,58</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default AlunoProfile;
