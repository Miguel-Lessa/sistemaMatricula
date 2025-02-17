package model;

import java.util.List;

public class Curso {
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinasObrigatorias;
    private List<Disciplina> disciplinasOptativas;

    public Curso(String nome, int creditosTotais, List<Disciplina> disciplinasObrigatorias, List<Disciplina> disciplinasOptativas) {
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.disciplinasObrigatorias = disciplinasObrigatorias;
        this.disciplinasOptativas = disciplinasOptativas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditosTotais() {
        return creditosTotais;
    }

    public void setCreditosTotais(int creditosTotais) {
        this.creditosTotais = creditosTotais;
    }

    public List<Disciplina> getDisciplinasObrigatorias() {
        return disciplinasObrigatorias;
    }

    public void setDisciplinasObrigatorias(List<Disciplina> disciplinasObrigatorias) {
        this.disciplinasObrigatorias = disciplinasObrigatorias;
    }

    public List<Disciplina> getDisciplinasOptativas() {
        return disciplinasOptativas;
    }

    public void setDisciplinasOptativas(List<Disciplina> disciplinasOptativas) {
        this.disciplinasOptativas = disciplinasOptativas;
    }

    public void adicionarDisciplinaObrigatoria(Disciplina disciplina){
        disciplinasObrigatorias.add(disciplina);
        System.out.println("model.Disciplina obrigatoria " +disciplina.getNome()+", adicionada");
    }

    public void adicionarDisciplinaOptativa(Disciplina disciplina){
        disciplinasOptativas.add(disciplina);
        System.out.println("model.Disciplina optativa "+disciplina.getNome()+ "adicionada");
    }

}
