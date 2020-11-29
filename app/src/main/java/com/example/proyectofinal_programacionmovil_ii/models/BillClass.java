package com.example.proyectofinal_programacionmovil_ii.models;

public class BillClass {
    int idBill, NIT, NroBill, NroAuthorization, idForm;
    double Import;
    String DateIssued, CodeControl;

    public BillClass (){}
    public BillClass(int idBill, int NIT, int nroBill, int nroAuthorization, double Import, String dateIssued, String codeControl, int idForm) {
        this.idBill = idBill;
        this.NIT = NIT;
        NroBill = nroBill;
        NroAuthorization = nroAuthorization;
        this.Import = Import;
        DateIssued = dateIssued;
        CodeControl = codeControl;
        this.idForm = idForm;
    }
    public BillClass( int NIT, int nroBill, int nroAuthorization, double Import, String dateIssued, String codeControl, int idForm) {
        this.NIT = NIT;
        NroBill = nroBill;
        NroAuthorization = nroAuthorization;
        this.Import = Import;
        DateIssued = dateIssued;
        CodeControl = codeControl;
        this.idForm = idForm;
    }
    public BillClass(int idBill, int nroBill, String date, int Import){
        this.idBill = idBill;
        NroBill = nroBill;
        DateIssued = date;
        this.Import = Import;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    public int getNroBill() {
        return NroBill;
    }

    public void setNroBill(int nroBill) {
        NroBill = nroBill;
    }

    public int getNroAuthorization() {
        return NroAuthorization;
    }

    public void setNroAuthorization(int nroAuthorization) {
        NroAuthorization = nroAuthorization;
    }

    public double getImport() {
        return Import;
    }

    public void setImport(double anImport) {
        Import = anImport;
    }

    public String getDateIssued() {
        return DateIssued;
    }

    public void setDateIssued(String dateIssued) {
        DateIssued = dateIssued;
    }

    public String getCodeControl() {
        return CodeControl;
    }

    public void setCodeControl(String codeControl) {
        CodeControl = codeControl;
    }

    public int getIdForm() {
        return idForm;
    }

    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }
}
