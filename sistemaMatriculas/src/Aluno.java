import java.util.List;

public class Aluno {
    private int matricula;
    private List<Disciplina> disciplinasMatriculadas;


    public void matricular(Disciplina disciplina) {
        System.out.println("Matricula realizada na disciplina " + disciplina.getNome());

    }


    public void cancelarMatricula (Disciplina disciplina){
        System.out.println("Matricula cancelada na disciplina " + disciplina.getNome());

    }
}
