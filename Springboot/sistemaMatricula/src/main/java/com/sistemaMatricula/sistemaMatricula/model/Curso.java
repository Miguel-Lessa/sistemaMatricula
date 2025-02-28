package com.sistemaMatricula.sistemaMatricula.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinasObrigatorias;
    private List<Disciplina> disciplinasOptativas;

    public Curso(String nome, int creditosTotais, List<Disciplina> disciplinasObrigatorias, List<Disciplina> disciplinasOptativas) {
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.disciplinasObrigatorias = disciplinasObrigatorias;
        this.disciplinasOptativas = disciplinasOptativas;
    }


    public void salvarParaCSV(String caminhoArquivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(caminhoArquivo))) {
            List<String[]> dados = new ArrayList<>();
            dados.add(new String[]{nome, String.valueOf(creditosTotais)});
            writer.writeAll(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Curso lerDeCSV(String caminhoArquivo) {
        try (CSVReader reader = new CSVReader(new FileReader(caminhoArquivo))) {
            List<String[]> registros = reader.readAll();
            if (!registros.isEmpty()) {
                String[] dados = registros.get(0);
                return new Curso(dados[0], Integer.parseInt(dados[1]), new ArrayList<>(), new ArrayList<>());
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return null;
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

    public void adicionarDisciplinaObrigatoria(Disciplina disciplina){
        disciplinasObrigatorias.add(disciplina);
        System.out.println("model.Disciplina obrigatoria " +disciplina.getNome()+", adicionada");
    }

    public void adicionarDisciplinaOptativa(Disciplina disciplina){
        disciplinasOptativas.add(disciplina);
        System.out.println("model.Disciplina optativa "+disciplina.getNome()+ "adicionada");
    }


}
