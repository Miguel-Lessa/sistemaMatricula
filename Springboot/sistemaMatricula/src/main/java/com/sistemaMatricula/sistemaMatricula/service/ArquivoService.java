package com.sistemaMatricula.sistemaMatricula.service;

import com.sistemaMatricula.sistemaMatricula.model.Aluno;
import com.sistemaMatricula.sistemaMatricula.model.Disciplina;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoService {
    private static ArquivoService instance;

    private ArquivoService() {
    }

    public static ArquivoService getInstance() {
        if (instance == null) {
            instance = new ArquivoService();
        }
        return instance;
    }


    public void salvarAlunos(List<Aluno> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.csv"))) {
            writer.write("nome,matricula,disciplinas_matriculadas\n");
            for (Aluno aluno : alunos) {
                String disciplinas = String.join(";", aluno.getDisciplinasMatriculadasObrigatorias().stream().map(Disciplina::getNome).toArray(String[]::new));
                disciplinas += ";" + String.join(";", aluno.getDisciplinasMatriculadasOptativas().stream().map(Disciplina::getNome).toArray(String[]::new));
                writer.write(aluno.getNome() + "," + aluno.getMatricula() + "," + disciplinas + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("alunos.csv"))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String nome = parts[0];
                int matricula = Integer.parseInt(parts[1]);
                String[] disciplinas = parts[2].split(";");
                List<Disciplina> disciplinasObrigatorias = new ArrayList<>();
                List<Disciplina> disciplinasOptativas = new ArrayList<>();
                for (String disciplinaNome : disciplinas) {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setNome(disciplinaNome);
                    if (disciplina.isObrigatoria()) {
                        disciplinasObrigatorias.add(disciplina);
                    } else {
                        disciplinasOptativas.add(disciplina);
                    }
                }
                Aluno aluno = new Aluno(nome, matricula, disciplinasObrigatorias, disciplinasOptativas  );
                aluno.setNome(nome);
                alunos.add(aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }
}