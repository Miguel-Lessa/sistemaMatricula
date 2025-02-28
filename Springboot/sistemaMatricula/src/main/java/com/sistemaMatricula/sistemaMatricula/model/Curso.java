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
    private List<Disciplina> disciplinasObrigatorias = new ArrayList<>();
    private List<Disciplina> disciplinasOptativas = new ArrayList<>();

    // Caminho fixo para o arquivo cursos.csv
    private static final String CAMINHO_ARQUIVO = "Springboot/sistemaMatricula/src/main/java/com/sistemaMatricula/sistemaMatricula/files/cursos.csv";

    public Curso(String nome, int creditosTotais, List<Disciplina> disciplinasObrigatorias, List<Disciplina> disciplinasOptativas) {
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.disciplinasObrigatorias = disciplinasObrigatorias;
        this.disciplinasOptativas = disciplinasOptativas;
    }

    // Método para salvar os dados do curso no CSV, incluindo disciplinas
    public void salvarParaCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            List<String[]> dados = new ArrayList<>();
            String disciplinasObrigatoriasStr = String.join(";", disciplinasObrigatorias.stream().map(Disciplina::getNome).toArray(String[]::new));
            String disciplinasOptativasStr = String.join(";", disciplinasOptativas.stream().map(Disciplina::getNome).toArray(String[]::new));
            dados.add(new String[]{nome, String.valueOf(creditosTotais), disciplinasObrigatoriasStr, disciplinasOptativasStr});
            writer.writeAll(dados);
            System.out.println("Curso salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para ler cursos de um arquivo CSV
    public static List<Curso> lerDeCSV() {
        List<Curso> cursos = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CAMINHO_ARQUIVO))) {
            List<String[]> registros = reader.readAll();
            for (String[] dados : registros) {
                String nome = dados[0];
                int creditosTotais = Integer.parseInt(dados[1]);
                String[] disciplinasObrigatoriasArray = dados[2].split(";");
                String[] disciplinasOptativasArray = dados[3].split(";");

                List<Disciplina> disciplinasObrigatorias = new ArrayList<>();
                List<Disciplina> disciplinasOptativas = new ArrayList<>();

                for (String nomeDisciplina : disciplinasObrigatoriasArray) {
                    disciplinasObrigatorias.add(new Disciplina(nomeDisciplina, disciplinasObrigatorias ));  // Criar disciplina pelo nome
                }

                for (String nomeDisciplina : disciplinasOptativasArray) {
                    boolean add = disciplinasOptativas.add(new Disciplina(nomeDisciplina, disciplinasOptativas ));// Criar disciplina pelo nome
                }

                cursos.add(new Curso(nome, creditosTotais, disciplinasObrigatorias, disciplinasOptativas));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    // Método para atualizar um curso no CSV
    public static boolean atualizarCursoNoCSV(Curso cursoAtualizado) {
        List<Curso> cursos = lerDeCSV();
        boolean cursoEncontrado = false;

        // Atualiza o curso se encontrado
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getNome().equals(cursoAtualizado.getNome())) {
                cursos.set(i, cursoAtualizado);
                cursoEncontrado = true;
                break;
            }
        }

        // Se o curso foi encontrado e atualizado, sobrescreve o arquivo CSV
        if (cursoEncontrado) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(CAMINHO_ARQUIVO))) {
                for (Curso curso : cursos) {
                    String disciplinasObrigatoriasStr = String.join(";", curso.getDisciplinasObrigatorias().stream().map(Disciplina::getNome).toArray(String[]::new));
                    String disciplinasOptativasStr = String.join(";", curso.getDisciplinasOptativas().stream().map(Disciplina::getNome).toArray(String[]::new));
                    writer.writeNext(new String[]{curso.getNome(), String.valueOf(curso.getCreditosTotais()), disciplinasObrigatoriasStr, disciplinasOptativasStr});
                }
                System.out.println("Curso atualizado com sucesso!");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Método para excluir um curso do CSV
    public static boolean excluirCursoDoCSV(String nomeCurso) {
        List<Curso> cursos = lerDeCSV();
        boolean cursoEncontrado = false;

        // Remove o curso se encontrado
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getNome().equals(nomeCurso)) {
                cursos.remove(i);
                cursoEncontrado = true;
                break;
            }
        }

        // Se o curso foi encontrado e removido, sobrescreve o arquivo CSV
        if (cursoEncontrado) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(CAMINHO_ARQUIVO))) {
                for (Curso curso : cursos) {
                    String disciplinasObrigatoriasStr = String.join(";", curso.getDisciplinasObrigatorias().stream().map(Disciplina::getNome).toArray(String[]::new));
                    String disciplinasOptativasStr = String.join(";", curso.getDisciplinasOptativas().stream().map(Disciplina::getNome).toArray(String[]::new));
                    writer.writeNext(new String[]{curso.getNome(), String.valueOf(curso.getCreditosTotais()), disciplinasObrigatoriasStr, disciplinasOptativasStr});
                }
                System.out.println("Curso excluído com sucesso!");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Getters e Setters
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

    public void adicionarDisciplinaObrigatoria(Disciplina disciplina) {
        disciplinasObrigatorias.add(disciplina);
        System.out.println("Disciplina obrigatória " + disciplina.getNome() + " adicionada");
    }

    public void adicionarDisciplinaOptativa(Disciplina disciplina) {
        disciplinasOptativas.add(disciplina);
        System.out.println("Disciplina optativa " + disciplina.getNome() + " adicionada");
    }

    @Override
    public String toString() {
        String disciplinasObrigatoriasStr = String.join(";", disciplinasObrigatorias.stream().map(Disciplina::getNome).toArray(String[]::new));
        String disciplinasOptativasStr = String.join(";", disciplinasOptativas.stream().map(Disciplina::getNome).toArray(String[]::new));
        return nome + "," + creditosTotais + "," + disciplinasObrigatoriasStr + "," + disciplinasOptativasStr;
    }
}
