package com.sistemaMatricula.sistemaMatricula.model;

public class Matricula {
    private String aluno;
    private String disciplina;
    private Double valor;

    public Matricula(String aluno, String disciplina, Double valor) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valor = valor;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getDisciplina() {
        return disciplina;
    }


    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "aluno='" + aluno + '\'' +
                ", disciplina='" + disciplina + '\'' +
                ", valor=" + valor +
                '}';
    }
}