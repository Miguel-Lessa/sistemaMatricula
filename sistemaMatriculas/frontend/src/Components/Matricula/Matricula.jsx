import React from "react";
import styles from "./Matricula.module.css";

const Matricula = () => {
  return (
    <div className={styles.container}>
      <h1>Matrícula</h1>
      <form action="/action_page.php">
        <div className={styles.checkboxcontainer}>
          <input
            type="checkbox"
            id="disciplina1"
            name="disciplina1"
            value="Psicanalise"
          />
          <label htmlFor="disciplina1">Psicanálise</label>
        </div>

        <div className={styles.checkboxcontainer}>
          <input
            type="checkbox"
            id="disciplina2"
            name="disciplina2"
            value="Calculo"
          />
          <label htmlFor="disciplina2">Cálculo III</label>
        </div>

        <div className={styles.checkboxcontainer}>
          <input
            type="checkbox"
            id="disciplina3"
            name="disciplina3"
            value="Anatomia"
          />
          <label htmlFor="disciplina3">Anatomia Humana</label>
        </div>

        <div className={styles.checkboxcontainer}>
          <input
            type="checkbox"
            id="disciplina4"
            name="disciplina4"
            value="Analise"
          />
          <label htmlFor="disciplina4">Análise de Obras Clássicas</label>
        </div>

        <div className={styles.checkboxcontainer}>
          <input
            type="checkbox"
            id="disciplina5"
            name="disciplina5"
            value="Fisica"
          />
          <label htmlFor="disciplina5">Física Quântica</label>
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
