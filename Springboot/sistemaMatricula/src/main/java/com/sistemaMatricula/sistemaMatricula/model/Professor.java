package com.sistemaMatricula.sistemaMatricula.model;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nome;
    private List<Disciplina> disciplinas;

    public Professor(String nome, List<Disciplina> disciplinas) {
        this.nome = nome;
        this.disciplinas = disciplinas;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public String getNome() {
        return nome;
    }

    public List<String> lerNomesAlunosDeCSV(String caminhoArquivo, String nomeDisciplina) {
        List<String> nomesAlunos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(caminhoArquivo))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                String nomeAluno = registro[0];
                String[] disciplinasAluno = registro[2].split(", ");
                for (String disciplinaAluno : disciplinasAluno) {
                    if (disciplinaAluno.equals(nomeDisciplina)) {
                        nomesAlunos.add(nomeAluno);
                        break;
                    }
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return nomesAlunos;
    }
}