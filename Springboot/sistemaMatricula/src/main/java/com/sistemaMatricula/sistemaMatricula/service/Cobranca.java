package com.sistemaMatricula.sistemaMatricula.service;

import model.Aluno;
import model.Disciplina;

import java.util.List;

public class Cobranca {
    public Double total;


    public Cobranca(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void gerarCobraca (Aluno aluno, List<Disciplina> disciplinas){
        for (Disciplina disciplina : disciplinas){
            total = total = total + disciplina.getValor();
            System.out.println("O valor total é de: " + total );
        }

    }
}
