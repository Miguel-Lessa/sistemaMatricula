package model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private static final String FILE_PATH = "Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/cursos.csv";
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinasObrigatorias;
    private List<Disciplina> disciplinasOptativas;

    public Curso(String nome, int creditosTotais, List<Disciplina> disciplinasObrigatorias, List<Disciplina> disciplinasOptativas) {
        this.nome = nome;
        this.disciplinasObrigatorias = disciplinasObrigatorias;
        this.disciplinasOptativas = disciplinasOptativas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditosTotais() {
        return creditosTotais;
    }

    public void setCreditosTotais(int creditosTotais) {
        this.creditosTotais = creditosTotais;
    }

    public List<Disciplina> getDisciplinasObrigatorias() {
        return disciplinasObrigatorias;
    }

    public void setDisciplinasObrigatorias(List<Disciplina> disciplinasObrigatorias) {
        this.disciplinasObrigatorias = disciplinasObrigatorias;
    }

    public List<Disciplina> getDisciplinasOptativas() {
        return disciplinasOptativas;
    }

    public void setDisciplinasOptativas(List<Disciplina> disciplinasOptativas) {
        this.disciplinasOptativas = disciplinasOptativas;
    }

    public static List<Curso> carregarDeCSV(String filePath) throws IOException, CsvValidationException {
        List<Curso> cursos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                String nome = line[0];
                int creditosTotais = Integer.parseInt(line[1]);
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
                cursos.add(new Curso(nome, creditosTotais, obrigatorias, optativas));
            }
        }
        return cursos;
    }

    public static void salvarParaCSV(String filePath, List<Curso> cursos) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"Nome", "CreditosTotais", "DisciplinasObrigatorias", "DisciplinasOptativas"});
            for (Curso curso : cursos) {
                String[] line = new String[4];
                line[0] = curso.getNome();
                line[1] = String.valueOf(curso.getCreditosTotais());
                line[2] = String.join(", ", curso.getDisciplinasObrigatorias().stream().map(Disciplina::getNome).toArray(String[]::new));
                line[3] = String.join(", ", curso.getDisciplinasOptativas().stream().map(Disciplina::getNome).toArray(String[]::new));
                writer.writeNext(line);
            }
        }
    }
}