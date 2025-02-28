package com.sistemaMatricula.sistemaMatricula.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    String filePath = "Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/alunos.csv";
    private String nome;
    private int matricula;
    private List<Disciplina> disciplinasMatriculadasObrigatorias;
    private List<Disciplina> disciplinasMatriculadasOptativas;

    // Construtor
    public Aluno(String nome, int matricula, List<Disciplina> disciplinasMatriculadasObrigatorias, List<Disciplina> disciplinasMatriculadasOptativas) {
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinasMatriculadasOptativas = disciplinasMatriculadasOptativas;
    }

    // toString
    @Override
    public String toString() {
        String disciplinasObrigatorias = String.join(", ", disciplinasMatriculadasObrigatorias.stream().map(Disciplina::getNome).toArray(String[]::new));
        String disciplinasOptativas = String.join(", ", disciplinasMatriculadasOptativas.stream().map(Disciplina::getNome).toArray(String[]::new));
        return nome + "," + matricula + "," + disciplinasObrigatorias + "," + disciplinasOptativas;
    }

    // Métodos
    public void matricular(Disciplina disciplina) throws IOException {
        if (disciplina.isObrigatoria()) {
            if (disciplinasMatriculadasObrigatorias.size() < 4 && disciplina.isAtiva()) {
                disciplinasMatriculadasObrigatorias.add(disciplina);
                System.out.println("Matrícula na disciplina obrigatória " + disciplina.getNome() + " realizada com sucesso!");
            } else {
                System.out.println("Número máximo de disciplinas obrigatórias atingido ou disciplina inativa.");
            }
        } else if (disciplina.isAtiva()) {
            if (disciplinasMatriculadasOptativas.size() < 2) {
                disciplinasMatriculadasOptativas.add(disciplina);
                System.out.println("Matrícula na disciplina optativa " + disciplina.getNome() + " realizada com sucesso!");
            } else {
                System.out.println("Número máximo de disciplinas optativas atingido.");
            }
        }
        salvarParaCSV("alunos.csv", List.of(this));
    }

    public void cancelarMatricula(Disciplina disciplina) throws IOException {
        if (disciplina.isObrigatoria()) {
            if (disciplinasMatriculadasObrigatorias.remove(disciplina)) {
                System.out.println("Disciplina obrigatória " + disciplina.getNome() + " removida com sucesso!");
            } else {
                System.out.println("Disciplina não encontrada entre as obrigatórias.");
            }
        } else {
            if (disciplinasMatriculadasOptativas.remove(disciplina)) {
                System.out.println("Disciplina optativa " + disciplina.getNome() + " removida com sucesso!");
            } else {
                System.out.println("Disciplina não encontrada entre as optativas.");
            }
        }
        salvarParaCSV("alunos.csv", List.of(this));
    }

    // Métodos de persistência
    public static List<Aluno> carregarDeCSV(String filePath) throws IOException, CsvValidationException {
        List<Aluno> alunos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                String nome = line[0];
                int matricula = Integer.parseInt(line[1]);
                List<Disciplina> obrigatorias = new ArrayList<>();
                List<Disciplina> optativas = new ArrayList<>();
                String[] disciplinasObrigatorias = line[2].split(", ");
                String[] disciplinasOptativas = line[3].split(", ");
                for (String nomeDisciplina : disciplinasObrigatorias) {
                    obrigatorias.add(new Disciplina(nomeDisciplina, true));
                }
                for (String nomeDisciplina : disciplinasOptativas) {
                    optativas.add(new Disciplina(nomeDisciplina, false));
                }
                alunos.add(new Aluno(nome, matricula, obrigatorias, optativas));
            }
        }
        return alunos;
    }

    public static void salvarParaCSV(String filePath, List<Aluno> alunos) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"Nome", "Matrícula", "DisciplinasObrigatorias", "DisciplinasOptativas"});
            for (Aluno aluno : alunos) {
                String[] line = new String[4];
                line[0] = aluno.getNome();
                line[1] = String.valueOf(aluno.getMatricula());
                line[2] = String.join(", ", aluno.getDisciplinasMatriculadasObrigatorias().stream().map(Disciplina::getNome).toArray(String[]::new));
                line[3] = String.join(", ", aluno.getDisciplinasMatriculadasOptativas().stream().map(Disciplina::getNome).toArray(String[]::new));
                writer.writeNext(line);
            }
        }
    }

    // Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public List<Disciplina> getDisciplinasMatriculadasObrigatorias() {
        return disciplinasMatriculadasObrigatorias;
    }

    public void setDisciplinasMatriculadasObrigatorias(List<Disciplina> disciplinasMatriculadasObrigatorias) {
        this.disciplinasMatriculadasObrigatorias = disciplinasMatriculadasObrigatorias;
    }

    public List<Disciplina> getDisciplinasMatriculadasOptativas() {
        return disciplinasMatriculadasOptativas;
    }

    public void setDisciplinasMatriculadasOptativas(List<Disciplina> disciplinasMatriculadasOptativas) {
        this.disciplinasMatriculadasOptativas = disciplinasMatriculadasOptativas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}