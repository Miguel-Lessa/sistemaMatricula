package com.sistemaMatricula.sistemaMatricula.model;

import com.sistemaMatricula.sistemaMatricula.service.ArquivoService;

import java.util.List;

public class Aluno {
    private String nome;
    private int matricula;
    private List<Disciplina> disciplinasMatriculadasObrigatorias;
    private List<Disciplina> disciplinasMatriculadasOptativas;

    // Construtor
    public Aluno(String nome, int matricula, List<Disciplina> disciplinasMatriculadasObrigatorias, List<Disciplina> disciplinasMatriculadasOptativas) {
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinasMatriculadasObrigatorias = disciplinasMatriculadasObrigatorias;
        this.disciplinasMatriculadasOptativas = disciplinasMatriculadasOptativas;
    }

    // toString
    @Override
    public String toString() {
        String disciplinasObrigatorias = String.join(";", disciplinasMatriculadasObrigatorias.stream().map(Disciplina::getNome).toArray(String[]::new));
        String disciplinasOptativas = String.join(";", disciplinasMatriculadasOptativas.stream().map(Disciplina::getNome).toArray(String[]::new));
        return nome + "," + matricula + "," + disciplinasObrigatorias + ";" + disciplinasOptativas;
    }

    // Métodos
    public void matricular(Disciplina disciplina) {
        if (disciplina.isObrigatoria()) {
            if (disciplinasMatriculadasObrigatorias.size() <= 4 && disciplina.isAtiva()) {
                disciplinasMatriculadasObrigatorias.add(disciplina);
                System.out.println("Matrícula na disciplina obrigatória " + disciplina.getNome() + " realizada com sucesso!");
            } else {
                System.out.println("Número máximo de disciplinas obrigatórias atingido ou disciplina inativa.");
            }
        } else if (disciplina.isAtiva()) {
            if (disciplinasMatriculadasOptativas.size() <= 2) {
                disciplinasMatriculadasOptativas.add(disciplina);
                System.out.println("Matrícula na disciplina optativa " + disciplina.getNome() + " realizada com sucesso!");
            } else {
                System.out.println("Número máximo de disciplinas optativas atingido.");
            }
        }

        // Atualiza a lista de alunos no arquivo
        List<Aluno> alunos = ArquivoService.getInstance().carregarAlunos();
        alunos.add(this);  // Atualiza o aluno na lista
        ArquivoService.getInstance().salvarAlunos(alunos);  // Salva os alunos novamente no arquivo
    }

    public void cancelarMatricula(Disciplina disciplina) {
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

        // Atualiza a lista de alunos no arquivo
        List<Aluno> alunos = ArquivoService.getInstance().carregarAlunos();
        alunos.add(this);  // Atualiza a lista de alunos após o cancelamento
        ArquivoService.getInstance().salvarAlunos(alunos);  // Salva novamente no arquivo
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
