package com.sistemaMatricula.sistemaMatricula.model;

import lombok.Setter;

import java.util.List;

public class Professor {
    private String nome;
    @Setter
    private List<Disciplina> disciplinas;

    public Professor(String nome, List<Disciplina> disciplinas) {
        this.nome = nome;
        this.disciplinas = disciplinas;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Aluno> consultarAlunos (Disciplina disciplina){
        for (Disciplina d : disciplinas){
            if (d.equals(disciplina)){
                return disciplina.getAlunosMatriculados();
            }
        }
        return null;
    }

    public String getNome() {
        return nome;
    }
}
