import java.util.List;

public class Cobranca {
    public Double total;

    public void gerarCobraca (Aluno aluno, List<Disciplina> disciplinas){
        for (Disciplina disciplina : disciplinas){
            total = total = total + disciplina.getValor();
            System.out.println("O valor total é de: " + total );
        }

    }
}
