package com.example.proyectofinal_programacionmovil_ii;

public class Formulario {
    private int año;
    private String mes;
    public Formulario(){}

    public Formulario(String mes, int año) {
        this.mes = mes;
        this.año = año;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
}
