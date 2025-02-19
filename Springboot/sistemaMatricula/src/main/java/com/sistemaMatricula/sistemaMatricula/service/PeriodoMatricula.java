package com.sistemaMatricula.sistemaMatricula.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PeriodoMatricula {

    private Date inicio, fim;


    public PeriodoMatricula(Date inicio, Date fim) {
        this.inicio = inicio;
        this.fim = fim;
    }


    public boolean verificarPeriodo(){
        LocalDate hoje = LocalDate.now();
        LocalDate dataInicio = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFim = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (hoje.isEqual(dataInicio) || hoje.isAfter(dataInicio) && hoje.isEqual(dataFim) || hoje.isBefore(dataFim)) {
            return true;
        }
        return false;
    }

    public Date getFim() {
        return fim;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
}
