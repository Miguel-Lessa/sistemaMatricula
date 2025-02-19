package com.sistemaMatricula.sistemaMatricula.model;

import java.util.ArrayList;
import java.util.List;

public class Universidade {

    private String nome;
    private List<Curso> cursos;

    public Universidade(String nome, List<Curso> cursos) {
        this.nome = nome;
        this.cursos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
