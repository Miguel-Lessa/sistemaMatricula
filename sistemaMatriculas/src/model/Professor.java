package model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Professor {
    private static final String FILE_PATH = "Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/professores.csv";
    private List<Disciplina> disciplinas;
    private String nome;
    private int id;
    private String senha;

    public Professor(List<Disciplina> disciplinas, String nome, int id, String senha) {
        this.disciplinas = disciplinas;
        this.nome = nome;
        this.id = id;
        this.senha = senha;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @SneakyThrows
    public List<String> buscarAlunosMatriculados(Disciplina disciplina) {
        for (Disciplina d : disciplinas) {
            if (d.getNome().equals(disciplina.getNome())) {
                return disciplina.getAlunosMatriculados();
            }
        }
        return null;
    }

    public static List<Professor> carregarDeCSV(String filePath) throws IOException, CsvValidationException {
        List<Professor> professores = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                String nome = line[0].trim();
                int id = Integer.parseInt(line[1].trim());
                String senha = line[2].trim();
                List<Disciplina> disciplinas = new ArrayList<>();
                String[] disciplinasNomes = line[3].split(", ");
                for (String nomeDisciplina : disciplinasNomes) {
                    disciplinas.add(new Disciplina(nomeDisciplina.trim(), true));
                }
                professores.add(new Professor(disciplinas, nome, id, senha));
            }
        }
        return professores;
    }

    public static void salvarParaCSV(String filePath, List<Professor> professores) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"Nome", "ID", "Senha", "Disciplinas"});
            for (Professor professor : professores) {
                String[] line = new String[4];
                line[0] = professor.getNome();
                line[1] = String.valueOf(professor.getId());
                line[2] = professor.getSenha();
                line[3] = String.join(", ", professor.getDisciplinas().stream().map(Disciplina::getNome).toArray(String[]::new));
                writer.writeNext(line);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}