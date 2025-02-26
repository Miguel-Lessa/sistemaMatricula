package com.sistemaMatricula.sistemaMatricula.service;

import com.sistemaMatricula.sistemaMatricula.model.Matricula;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Secretaria {

    public void gerenciarCurriculo() {
        // Implementação
    }

    public void verificarPeriodoMatricula() {
        // Implementação
    }

    public void cadastrarProfessor() {
        // Implementação
    }

    public void salvarMatriculasEmArquivo(List<Matricula> matriculas, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Matricula matricula : matriculas) {
                writer.write(matricula.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}