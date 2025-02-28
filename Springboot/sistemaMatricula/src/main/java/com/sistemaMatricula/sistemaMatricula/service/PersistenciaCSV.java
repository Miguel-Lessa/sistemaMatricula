package com.sistemaMatricula.sistemaMatricula.service;

import java.util.List;

public interface PersistenciaCSV<T> {
    void salvar(List<T> lista, String caminhoArquivo);
    List<T> carregar(String caminhoArquivo);
}
