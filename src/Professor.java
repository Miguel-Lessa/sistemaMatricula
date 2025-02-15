import java.util.List;

public class Professor {
    private List<Disciplina> disciplinas;


    public List<Aluno> consultarAlunos (Disciplina disciplina){
        for (Disciplina d : disciplinas){
            if (d.equals(disciplina)){
                return disciplina.getAlunosMatriculados();
            }
        }
        return null;
    }
}
