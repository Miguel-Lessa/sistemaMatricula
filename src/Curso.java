import java.util.List;

public class Curso {
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinasObrigatorias;
    private List<Disciplina> disciplinasOptativas;


    public void adicionarDisciplinaObrigatoria(Disciplina disciplina){
        disciplinasObrigatorias.add(disciplina);
        System.out.println("Disciplina obrigatoria " +disciplina.getNome()+", adicionada");
    }

    public void adicionarDisciplinaOptativa(Disciplina disciplina){
        disciplinasOptativas.add(disciplina);
        System.out.println("Disciplina optativa "+disciplina.getNome()+ "adicionada");
    }

}
