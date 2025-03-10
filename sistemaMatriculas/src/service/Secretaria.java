// File: sistemaMatriculas/src/service/Secretaria.java
package service;

import model.Curso;
import model.Disciplina;
import model.Professor;
import java.util.List;

public class Secretaria {

    private List<Curso> cursos;
    private List<Professor> professores;

    public Secretaria(List<Curso> cursos, List<Professor> professores) {
        this.cursos = cursos;
        this.professores = professores;
    }

    public void gerenciarCurriculo(Curso curso, List<Disciplina> novasDisciplinasObrigatorias, List<Disciplina> novasDisciplinasOptativas) {
        curso.setDisciplinasObrigatorias(novasDisciplinasObrigatorias);
        curso.setDisciplinasOptativas(novasDisciplinasOptativas);
        System.out.println("Currículo do curso " + curso.getNome() + " atualizado com sucesso!");
    }

    public void verificarPeriodoMatricula(PeriodoMatricula periodoMatricula) {
        if (periodoMatricula.verificarPeriodo()) {
            System.out.println("Estamos no período de matrícula.");
        } else {
            System.out.println("Fora do período de matrícula.");
        }
    }

    public void cadastrarProfessor(Professor professor) {
        professores.add(professor);
        System.out.println("Professor cadastrado com sucesso!");
    }
}