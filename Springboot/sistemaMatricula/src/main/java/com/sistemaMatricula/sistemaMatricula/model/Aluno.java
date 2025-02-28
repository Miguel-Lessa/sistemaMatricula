package com.sistemaMatricula.sistemaMatricula.model;

import com.sistemaMatricula.sistemaMatricula.service.ArquivoService;

import java.util.List;

public class Aluno {
    private String nome;
    private int matricula;
    private List<Disciplina> disciplinasMatriculadasObrigatorias;
    private List<Disciplina> disciplinasMatriculadasOptativas;

    //Construtor
    public Aluno(String nome, int matricula, List<Disciplina> disciplinasMatriculadasObrigatorias, List<Disciplina> disciplinasMatriculadasOptativas ) {
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinasMatriculadasObrigatorias = disciplinasMatriculadasObrigatorias;
        this.disciplinasMatriculadasOptativas = disciplinasMatriculadasOptativas;
    }

    //toString
    @Override
    public String toString() {
        String disciplinasObrigatorias = String.join(";", disciplinasMatriculadasObrigatorias.stream().map(Disciplina::getNome).toArray(String[]::new));
        String disciplinasOptativas = String.join(";", disciplinasMatriculadasOptativas.stream().map(Disciplina::getNome).toArray(String[]::new));
        return nome + "," + matricula + "," + disciplinasObrigatorias + ";" + disciplinasOptativas;
    }

    //Métodos
    public void matricular(Disciplina disciplina) {
        if (disciplina.isObrigatoria() == true){
            if (disciplinasMatriculadasObrigatorias.size() <= 4 && disciplina.isAtiva() == true){
                disciplinasMatriculadasObrigatorias.add(disciplina);

                System.out.println("Matricula na disciplina " + disciplina.getNome() + " realizada com sucesso!");
            } else {
                System.out.println("Numero máximo de disciplinas obrigatórias atingido");
            }
        } else if (disciplina.isObrigatoria() == false && disciplina.isAtiva() == true){
            if (disciplinasMatriculadasOptativas.size() <= 2){
                disciplinasMatriculadasOptativas.add(disciplina);
                System.out.println("Matricula na disciplina "+ disciplina.getNome() +" realizada com sucesso!");
            } else {
                System.out.println("Numero máximo de disciplinas optativas atingido");
            }
        }

        List<Aluno> alunos = ArquivoService.getInstance().carregarAlunos();
        alunos.add(this);
        ArquivoService.getInstance().salvarAlunos(alunos);
    }




    public void cancelarMatricula (Disciplina disciplina){
        if (disciplinasMatriculadasObrigatorias.size() > 0 && disciplinasMatriculadasOptativas.size() > 0){
            if (disciplina.isObrigatoria() == true){
                disciplinasMatriculadasObrigatorias.remove(disciplina);
                System.out.println("Disciplina obrigatória removida com sucesso!");
            } else if (disciplina.isObrigatoria() == false){
                disciplinasMatriculadasOptativas.remove(disciplina);
                System.out.println("Disciplina optativa removida com sucesso!");
            }
        }
        List<Aluno> alunos = ArquivoService.getInstance().carregarAlunos();
        alunos.add(this);
        ArquivoService.getInstance().salvarAlunos(alunos);
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
}
