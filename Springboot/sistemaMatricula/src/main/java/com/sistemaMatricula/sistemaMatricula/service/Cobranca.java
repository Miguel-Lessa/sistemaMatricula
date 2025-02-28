package com.sistemaMatricula.sistemaMatricula.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.sistemaMatricula.sistemaMatricula.model.Aluno;
import com.sistemaMatricula.sistemaMatricula.model.Disciplina;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cobranca {
    private Double total;

    public Cobranca(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void gerarCobranca(Aluno aluno, String caminhoArquivoDisciplinas) {
        Map<String, Double> valoresDisciplinas = carregarValoresDisciplinas(caminhoArquivoDisciplinas);
        total = 0.0;

        for (Disciplina disciplina : aluno.getDisciplinasMatriculadasObrigatorias()) {
            total += valoresDisciplinas.getOrDefault(disciplina.getNome(), 0.0);
        }

        for (Disciplina disciplina : aluno.getDisciplinasMatriculadasOptativas()) {
            total += valoresDisciplinas.getOrDefault(disciplina.getNome(), 0.0);
        }

        System.out.println("O valor total é de: " + total);
    }

    private Map<String, Double> carregarValoresDisciplinas(String caminhoArquivoDisciplinas) {
        Map<String, Double> valoresDisciplinas = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(caminhoArquivoDisciplinas))) {
            List<String[]> registros = reader.readAll();
            for (String[] registro : registros) {
                String nomeDisciplina = registro[1];
                Double valor = Double.parseDouble(registro[3]);
                valoresDisciplinas.put(nomeDisciplina, valor);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return valoresDisciplinas;
    }
}