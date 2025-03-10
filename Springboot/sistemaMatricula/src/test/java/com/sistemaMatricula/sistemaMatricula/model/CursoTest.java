package com.sistemaMatricula.sistemaMatricula.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    private Aluno aluno;
    private Disciplina disciplinaObrigatoria;
    private Disciplina disciplinaOptativa;

    @BeforeEach
    void setUp() {
        disciplinaObrigatoria = new Disciplina(1, "Matemática", true, null, 100.0);
        disciplinaOptativa = new Disciplina(2, "Filosofia", false, null, 50.0);

        List<Disciplina> obrigatorias = new ArrayList<>();
        List<Disciplina> optativas = new ArrayList<>();

        aluno = new Aluno("João", 12345, obrigatorias, optativas);
    }

    @Test
    void testMatricularDisciplinaObrigatoria() {
        aluno.matricular(disciplinaObrigatoria);
        assertTrue(aluno.getDisciplinasMatriculadasObrigatorias().contains(disciplinaObrigatoria));
    }

    @Test
    void testMatricularDisciplinaOptativa() {
        aluno.matricular(disciplinaOptativa);
        assertTrue(aluno.getDisciplinasMatriculadasOptativas().contains(disciplinaOptativa));
    }

    @Test
    void testCancelarMatriculaDisciplinaObrigatoria() {
        aluno.matricular(disciplinaObrigatoria);
        aluno.cancelarMatricula(disciplinaObrigatoria);
        assertFalse(aluno.getDisciplinasMatriculadasObrigatorias().contains(disciplinaObrigatoria));
    }

    @Test
    void testCancelarMatriculaDisciplinaOptativa() {
        aluno.matricular(disciplinaOptativa);
        aluno.cancelarMatricula(disciplinaOptativa);
        assertFalse(aluno.getDisciplinasMatriculadasOptativas().contains(disciplinaOptativa));
    }

    @Test
    void testToString() {
        aluno.matricular(disciplinaObrigatoria);
        aluno.matricular(disciplinaOptativa);
        String expected = "João,12345,Matemática;Filosofia";
        assertEquals(expected, aluno.toString());
    }
}