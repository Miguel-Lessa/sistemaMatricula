package model;

import java.util.List;

public class Aluno {
    private int matricula;
    private List<Disciplina> disciplinasMatriculadasObrigatorias;
    private List<Disciplina> disciplinasMatriculadasOptativas;

    //Construtor
    public Aluno(int matricula, List<Disciplina> disciplinasMatriculadas) {
        this.matricula = matricula;
        this.disciplinasMatriculadasObrigatorias = disciplinasMatriculadasObrigatorias;
        this.disciplinasMatriculadasOptativas = disciplinasMatriculadasOptativas;
    }

    //Métodos
    public void matricular(Disciplina disciplina) {
        if (disciplina.isObrigatoria() == true){
            if (disciplinasMatriculadasObrigatorias.size() <= 4){
                disciplinasMatriculadasObrigatorias.add(disciplina);
                System.out.println("Matricula na disciplina " + disciplina.getNome() + " realizada com sucesso!");
            } else {
                System.out.println("Numero máximo de disciplinas obrigatórias atingido");
            }
        } else if (disciplina.isObrigatoria() == false){
            if (disciplinasMatriculadasOptativas.size() <= 2){
                disciplinasMatriculadasOptativas.add(disciplina);
                System.out.println("Matricula na disciplina "+ disciplina.getNome() +" realizada com sucesso!");
            } else {
                System.out.println("Numero máximo de disciplinas optativas atingido");
            }
        }
    }

    public void cancelarMatricula (Disciplina disciplina){
        if (disciplinasMatriculadasObrigatorias.size() > 0 && disciplinasMatriculadasOptativas.size() > 0){
            if (disciplina.isObrigatoria() == true){
                disciplinasMatriculadasObrigatorias.remove(disciplina);
                System.out.println("Disciplina obrigatória removida com sucesso!");
            } else if (disciplina.isObrigatoria() == false){
                disciplinasMatriculadasOptativas.remove(disciplina);
                System.out.println("Disciplina optativa removida com sucesso!");
            }
        }
    }


    //Getter e Setter
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public List<Disciplina> getDisciplinasMatriculadasObrigatorias() {
        return disciplinasMatriculadasObrigatorias;
    }

    public void setDisciplinasMatriculadasObrigatorias(List<Disciplina> disciplinasMatriculadasObrigatorias) {
        this.disciplinasMatriculadasObrigatorias = disciplinasMatriculadasObrigatorias;
    }

    public List<Disciplina> getDisciplinasMatriculadasOptativas() {
        return disciplinasMatriculadasOptativas;
    }

    public void setDisciplinasMatriculadasOptativas(List<Disciplina> disciplinasMatriculadasOptativas) {
        this.disciplinasMatriculadasOptativas = disciplinasMatriculadasOptativas;
    }
}
