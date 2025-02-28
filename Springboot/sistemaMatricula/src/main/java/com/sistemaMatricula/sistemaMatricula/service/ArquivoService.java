package com.sistemaMatricula.sistemaMatricula.service;

import com.sistemaMatricula.sistemaMatricula.model.Aluno;

import java.util.List;

public class ArquivoService {

    private static ArquivoService instance;

    public static ArquivoService getInstance() {
        if (instance == null) {
            instance = new ArquivoService();
        }
        return instance;
    }

    public List<Aluno> carregarAlunos() {
        // Carregar a lista de alunos do arquivo CSV
        return new AlunoCSVService().carregar("Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/alunos.csv");
    }

    public void salvarAlunos(List<Aluno> alunos) {
        // Salvar a lista de alunos no arquivo CSV
        // Aqui você pode usar o seu AlunoCSVService para salvar o arquivo
        new AlunoCSVService().salvar(alunos, "Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/alunos.csv");
    }


}
