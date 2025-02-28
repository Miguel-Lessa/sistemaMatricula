package com.sistemaMatricula.sistemaMatricula.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private int codigo;
    private String nome;
    private boolean obrigatoria;
    private List<Aluno> alunosMatriculados;
    private Professor professor; // Agora o professor é um objeto completo
    private Double valor;
    private boolean ativa;

    public Disciplina() {
        this.alunosMatriculados = new ArrayList<>();
    }

    public Disciplina(String nome) {
        this.nome = nome;
        this.alunosMatriculados = new ArrayList<>(); // Inicializa a lista vazia
    }

    public Disciplina(int codigo, String nome, boolean obrigatoria, Professor professor, Double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.obrigatoria = obrigatoria;
        this.professor = professor; // Usando o professor completo
        this.valor = valor;
        this.alunosMatriculados = new ArrayList<>(); // Inicializa a lista vazia
    }

    @Override
    public String toString() {
        // Agora inclui o nome do professor na string
        return codigo + "," + nome + "," + obrigatoria + "," + valor + "," + professor.getNome();
    }

    public boolean isAtiva() {
        int numeroAlunos = alunosMatriculados.size();
        if (numeroAlunos >= 3 && numeroAlunos < 60) {
            return true;
        }
        return false;
    }

    // Método para salvar os dados da disciplina no CSV
    public void salvarParaCSV(String caminhoArquivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(caminhoArquivo))) {
            List<String[]> dados = new ArrayList<>();
            // Salvando o nome do professor junto com a disciplina
            dados.add(new String[]{
                    String.valueOf(codigo), nome, String.valueOf(obrigatoria), String.valueOf(valor), professor.getNome()
            });
            writer.writeAll(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para ler uma disciplina e o professor do arquivo CSV
    public static Disciplina lerDeCSV(String caminhoArquivo) {
        try (CSVReader reader = new CSVReader(new FileReader(caminhoArquivo))) {
            List<String[]> registros = reader.readAll();
            if (!registros.isEmpty()) {
                String[] dados = registros.get(0);
                // Criação do objeto professor com base no nome
                Professor professor = new Professor(dados[4], new ArrayList<>()); // Assumimos que o professor já existe
                return new Disciplina(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        Boolean.parseBoolean(dados[2]),
                        professor, // Atribui o professor
                        Double.parseDouble(dados[3])
                );
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Métodos de getter e setter
    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public int getNumeroAlunos() {
        return alunosMatriculados.size();
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }
}
