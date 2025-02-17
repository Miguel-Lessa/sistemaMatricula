import java.util.List;

public class Aluno {
    private int matricula;
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(int matricula, List<Disciplina> disciplinasMatriculadas) {
        this.matricula = matricula;
        this.disciplinasMatriculadas = disciplinasMatriculadas;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    public void setDisciplinasMatriculadas(List<Disciplina> disciplinasMatriculadas) {
        this.disciplinasMatriculadas = disciplinasMatriculadas;
    }

    public void matricular(Disciplina disciplina) {
        System.out.println("Matricula realizada na disciplina " + disciplina.getNome());

    }


    public void cancelarMatricula (Disciplina disciplina){
        System.out.println("Matricula cancelada na disciplina " + disciplina.getNome());

    }
}
