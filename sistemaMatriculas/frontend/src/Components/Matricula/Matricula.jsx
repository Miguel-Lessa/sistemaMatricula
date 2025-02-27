import React from "react";
import styles from "./Matricula.module.css";

const Matricula = () => {
  return (
    <div className={styles.container}>
      <h1>Matrícula</h1>
      <p>Selecione as disciplinas:</p>
      <form action="/action_page.php">
        <div className={styles.checkboxcontainer}>
          <input type="checkbox" id="vehicle1" name="vehicle1" value="Bike" />
          <label htmlFor="vehicle1">Psicanálise</label>
        </div>

        <div className={styles.checkboxcontainer}>
          <input type="checkbox" id="vehicle2" name="vehicle2" value="Car" />
          <label htmlFor="vehicle2">Cálculo III</label>
        </div>

        <div className={styles.checkboxcontainer}>
          <input type="checkbox" id="vehicle3" name="vehicle3" value="Boat" />
          <label htmlFor="vehicle3">Anatomia Humana</label>
        </div>

        <div className={styles.checkboxcontainer}>
          <input type="checkbox" id="vehicle3" name="vehicle3" value="Boat" />
          <label htmlFor="vehicle3">Análise de Obras Clássicas</label>
        </div>

        <div className={styles.checkboxcontainer}>
          <input type="checkbox" id="vehicle3" name="vehicle3" value="Boat" />
          <label htmlFor="vehicle3">Física Quântica</label>
        </div>

        <div className={styles.botoes}>
          <input className={styles.cancelar} type="submit" value="Cancelar" />
          <input className={styles.button} type="submit" value="Matricular" />
        </div>
      </form>
    </div>
  );
};

export default Matricula;
