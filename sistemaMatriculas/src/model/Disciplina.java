package model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private int codigo;
    private String nome;
    private boolean obrigatoria;
    private List<Aluno> alunosMatriculados;
    private Professor professor;
    private Double valor;

    public Disciplina(int codigo, String nome, boolean obrigatoria, List<Aluno> alunosMatriculados, Professor professor, Double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.obrigatoria = obrigatoria;
        this.alunosMatriculados = alunosMatriculados;
        this.professor = professor;
        this.valor = valor;
    }

    public Disciplina(int codigo, String nome, boolean obrigatoria, Professor professor, Double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.obrigatoria = obrigatoria;
        this.professor = professor;
        this.valor = valor;
        this.alunosMatriculados = new ArrayList<>(); // Inicializa a lista vazia
    }


    /// /    public boolean verificarAtivacao() {
    /// /        int i = 0;
    /// /
    /// /        for (model.Aluno alunosMatriculados : alunosMatriculados) {
    /// /            i++;
    /// /        }
    /// /        if (i >= 3 && i <= 60) {
    /// /            return true;
    /// /        }
//
//        return false;
//    }

    public boolean verificarAtivacao() {
        int numeroAlunos = alunosMatriculados.size();
        return numeroAlunos >= 3 && numeroAlunos <= 60;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public int getNumeroAlunos() {
        return alunosMatriculados.size();
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }
}
