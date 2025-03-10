package model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private static final String FILE_PATH = "Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/alunos.csv";
    private static final String DISCIPLINAS_FILE_PATH = "Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/disciplinas.csv" ;
    private String nome;
    private static int matricula;
    private List<Disciplina> disciplinasMatriculadasOptativas;
    private List<Disciplina> disciplinasMatriculadasObrigatorias;
    private String senha;

    //Construtor
    public Aluno(String nome, int matricula, List<Disciplina> obrigatorias, List<Disciplina> optativas, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinasMatriculadasObrigatorias = obrigatorias;
        this.disciplinasMatriculadasOptativas = optativas;
        this.senha = senha;
    }

    //Métodos
    public void matricular(Disciplina disciplina) throws IOException, CsvValidationException {
        List<Disciplina> todasDisciplinas = Disciplina.carregarDeCSV(DISCIPLINAS_FILE_PATH);
        boolean disciplinaExiste = todasDisciplinas.stream().anyMatch(d -> d.getNome().equals(disciplina.getNome()));

        if (!disciplinaExiste) {
            System.out.println("Disciplina não encontrada no arquivo CSV.");
            return;
        }

        if (disciplina.isObrigatoria()) {
            if (disciplinasMatriculadasObrigatorias.size() <= 4) {
                disciplinasMatriculadasObrigatorias.add(disciplina);
                System.out.println("Matricula na disciplina " + disciplina.getNome() + " realizada com sucesso!");
            } else {
                System.out.println("Numero máximo de disciplinas obrigatórias atingido");
            }
        } else {
            if (disciplinasMatriculadasOptativas.size() <= 2) {
                disciplinasMatriculadasOptativas.add(disciplina);
                System.out.println("Matricula na disciplina " + disciplina.getNome() + " realizada com sucesso!");
            } else {
                System.out.println("Numero máximo de disciplinas optativas atingido");
            }
        }
        salvarParaCSV(FILE_PATH, List.of(this));
    }

    public void cancelarMatricula(Disciplina disciplina) throws IOException {
        boolean removed = false;
        if (disciplina.isObrigatoria()) {
            removed = disciplinasMatriculadasObrigatorias.removeIf(d -> d.getNome().equals(disciplina.getNome()));
            if (removed) {
                System.out.println("Disciplina obrigatória " + disciplina.getNome() + " removida com sucesso!");
            } else {
                System.out.println("Disciplina não encontrada entre as obrigatórias.");
            }
        } else {
            removed = disciplinasMatriculadasOptativas.removeIf(d -> d.getNome().equals(disciplina.getNome()));
            if (removed) {
                System.out.println("Disciplina optativa " + disciplina.getNome() + " removida com sucesso!");
            } else {
                System.out.println("Disciplina não encontrada entre as optativas.");
            }
        }
        if (removed) {
            salvarParaCSV(FILE_PATH, List.of(this));
        }
    }
    //Getter e Setter
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

    public String getSenha() {
        return senha;
    }

    public static List<Aluno> carregarDeCSV(String filePath) throws IOException, CsvValidationException {
        List<Aluno> alunos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                if (line.length < 5) {
                    System.out.println("Invalid line: " + String.join(", ", line));
                    continue;
                }
                String nome = line[0].trim();
                int matricula = Integer.parseInt(line[1].trim());
                List<Disciplina> obrigatorias = new ArrayList<>();
                List<Disciplina> optativas = new ArrayList<>();
                String[] disciplinasObrigatorias = line[2].split(", ");
                String[] disciplinasOptativas = line[3].split(", ");
                String senha = line[4].trim();
                for (String nomeDisciplina : disciplinasObrigatorias) {
                    obrigatorias.add(new Disciplina(nomeDisciplina.trim(), true));
                }
                for (String nomeDisciplina : disciplinasOptativas) {
                    optativas.add(new Disciplina(nomeDisciplina.trim(), false));
                }
                alunos.add(new Aluno(nome, matricula, obrigatorias, optativas, senha));
            }
        }
        return alunos;
    }

    public static void salvarParaCSV(String filePath, List<Aluno> alunos) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"Nome", "Matrícula", "DisciplinasObrigatorias", "DisciplinasOptativas", "Senha"});
            for (Aluno aluno : alunos) {
                String[] line = new String[5];
                line[0] = aluno.getNome();
                line[1] = String.valueOf(aluno.getMatricula());
                line[2] = String.join(", ", aluno.getDisciplinasMatriculadasObrigatorias().stream().map(Disciplina::getNome).toArray(String[]::new));
                line[3] = String.join(", ", aluno.getDisciplinasMatriculadasOptativas().stream().map(Disciplina::getNome).toArray(String[]::new));
                line[4] = aluno.getSenha();
                writer.writeNext(line);
            }
        }
    }
}