package com.sistemaMatricula.sistemaMatricula.service;

import com.sistemaMatricula.sistemaMatricula.model.Aluno;
import com.sistemaMatricula.sistemaMatricula.model.Disciplina;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoCSVService implements PersistenciaCSV<Aluno> {
    private static final String ARQUIVO_ALUNOS = "alunos.csv";

    @Override
    public void salvar(List<Aluno> alunos, String caminhoArquivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(caminhoArquivo))) {
            // Escreve o cabeçalho do arquivo CSV
            writer.writeNext(new String[]{"Nome", "Matricula", "DisciplinasObrigatorias", "DisciplinasOptativas"});

            // Escreve os dados dos alunos
            for (Aluno aluno : alunos) {
                String obrigatorias = String.join(";", aluno.getDisciplinasMatriculadasObrigatorias()
                        .stream().map(Disciplina::getNome).toArray(String[]::new));
                String optativas = String.join(";", aluno.getDisciplinasMatriculadasOptativas()
                        .stream().map(Disciplina::getNome).toArray(String[]::new));

                writer.writeNext(new String[]{
                        aluno.getNome(),
                        String.valueOf(aluno.getMatricula()),
                        obrigatorias,
                        optativas
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Aluno> carregar(String caminhoArquivo) {
        List<Aluno> alunos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(caminhoArquivo))) {
            List<String[]> registros = reader.readAll();
            registros.remove(0); // Remove cabeçalho

            for (String[] dados : registros) {
                String nome = dados[0];
                int matricula = Integer.parseInt(dados[1]);
                String[] obrigatorias = dados[2].split(";");
                String[] optativas = dados[3].split(";");

                List<Disciplina> disciplinasObrigatorias = new ArrayList<>();
                List<Disciplina> disciplinasOptativas = new ArrayList<>();

                // Carrega as disciplinas obrigatórias
                for (String disciplina : obrigatorias) {
                    disciplinasObrigatorias.add(new Disciplina(disciplina));  // Criando uma disciplina apenas com o nome
                }

                // Carrega as disciplinas optativas
                for (String disciplina : optativas) {
                    disciplinasOptativas.add(new Disciplina(disciplina));  // Criando uma disciplina apenas com o nome
                }

                // Cria o aluno com as disciplinas carregadas
                alunos.add(new Aluno(nome, matricula, disciplinasObrigatorias, disciplinasOptativas));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return alunos;
    }
}
