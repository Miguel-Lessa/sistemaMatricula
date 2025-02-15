import java.util.List;

public class Disciplina {
    private int codigo;
    private String nome;
    private boolean obrigatoria;
    private List<Aluno> alunosMatriculados;
    private Professor professor;
    private Double valor;

    public boolean verificarAtivacao(){
        int i = 0;

        for (Aluno alunosMatriculados : alunosMatriculados){
            i++;
        }
        if (i >= 3 && i <=60){
            return true;
        }

        return false;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public int getNumeroAlunos(){
        return alunosMatriculados.size();
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }
}
