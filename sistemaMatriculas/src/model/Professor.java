package model;

import java.util.List;

public class Professor {
    private List<Disciplina> disciplinas;

    public Professor(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Aluno> consultarAlunos (Disciplina disciplina){
        for (Disciplina d : disciplinas){
            if (d.equals(disciplina)){
                return disciplina.getAlunosMatriculados();
            }
        }
        return null;
    }
}
