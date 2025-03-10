package service;

import com.opencsv.exceptions.CsvValidationException;
import model.Aluno;
import model.Disciplina;

import java.io.IOException;
import java.util.List;

public class Cobranca {
    private static final String DISCIPLINAS_FILE_PATH = "Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/disciplinas.csv";

    public double gerarCobranca(Aluno aluno) throws IOException, CsvValidationException {
        List<Disciplina> todasDisciplinas = Disciplina.carregarDeCSV(DISCIPLINAS_FILE_PATH);
        double total = 0.0;

        for (Disciplina disciplina : aluno.getDisciplinasMatriculadasObrigatorias()) {
            total += todasDisciplinas.stream()
                    .filter(d -> d.getNome().equals(disciplina.getNome()))
                    .findFirst()
                    .map(Disciplina::getValor)
                    .orElse(0.0);
        }

        for (Disciplina disciplina : aluno.getDisciplinasMatriculadasOptativas()) {
            total += todasDisciplinas.stream()
                    .filter(d -> d.getNome().equals(disciplina.getNome()))
                    .findFirst()
                    .map(Disciplina::getValor)
                    .orElse(0.0);
        }

        return total;
    }
}