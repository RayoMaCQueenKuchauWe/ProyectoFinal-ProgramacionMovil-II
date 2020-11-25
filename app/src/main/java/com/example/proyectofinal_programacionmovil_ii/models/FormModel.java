package com.example.proyectofinal_programacionmovil_ii.models;

public class FormModel {

    /*------ Attributes ------*/
    private int idForm;
    private String name;
    private String month;
    private int year;

    /*------ Builders and properties ------*/
    public FormModel() {
    }

    public FormModel(String name, String month, int year) {
        this.name = name;
        this.month = month;
        this.year = year;
    }

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /*------ Methods ------*/
}
