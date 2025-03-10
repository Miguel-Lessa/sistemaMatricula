package model;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Disciplina {
    private String nome;
    private boolean obrigatoria;
    private double valor;
    private String professor;

    public Disciplina(String nome, boolean obrigatoria) {
        this.nome = nome;
        this.obrigatoria = obrigatoria;
    }

    public Disciplina(String nome, boolean obrigatoria, double valor, String professor) {
        this.nome = nome;
        this.obrigatoria = obrigatoria;
        this.valor = valor;
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    public double getValor() {
        return valor;
    }

    public String getProfessor() {
        return professor;
    }

    public static List<Disciplina> carregarDeCSV(String filePath) throws IOException, CsvValidationException {
        List<Disciplina> disciplinas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                if (line.length < 4) {
                    System.out.println("Linha de disciplina inválida " + String.join(", ", line));
                    continue;
                }
                String nome = line[0].trim();
                boolean obrigatoria = Boolean.parseBoolean(line[1].trim());
                double valor = Double.parseDouble(line[2].trim());
                String professor = line[3].trim();
                disciplinas.add(new Disciplina(nome, obrigatoria, valor, professor));
            }
        }
        return disciplinas;
    }

    public List<String> getAlunosMatriculados() throws IOException, CsvValidationException {
        List<Aluno> alunos = Aluno.carregarDeCSV("Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/alunos.csv");
        return alunos.stream()
                .filter(aluno -> aluno.getDisciplinasMatriculadasObrigatorias().stream().anyMatch(d -> d.getNome().equals(this.nome)) ||
                        aluno.getDisciplinasMatriculadasOptativas().stream().anyMatch(d -> d.getNome().equals(this.nome)))
                .map(Aluno::getNome)
                .collect(Collectors.toList());
    }
}