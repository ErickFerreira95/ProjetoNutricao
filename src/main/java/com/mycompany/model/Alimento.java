package com.mycompany.model;

import java.text.DecimalFormat;

public class Alimento {

    private int id;
    private int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    private String nomeAlimento;
    private String quantidade;
    private String proteina;
    private String carboidrato;
    private String gordura;
    private String kcal;
    private double kcalNumero;

    public String getNomeAlimento() {
        return nomeAlimento;
    }

    public void setNomeAlimento(String nomeAlimento) {
        this.nomeAlimento = nomeAlimento;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getProteina() {
        return proteina;
    }

    public void setProteina(String proteina) {
        this.proteina = proteina;
    }

    public String getCarboidrato() {
        return carboidrato;
    }

    public void setCarboidrato(String carboidrato) {
        this.carboidrato = carboidrato;
    }

    public String getGordura() {
        return gordura;
    }

    public void setGordura(String gordura) {
        this.gordura = gordura;
    }

    public String getKcal() {
        double quantidade = Double.parseDouble(getQuantidade().replace(",", "."));
        double proteina = Double.parseDouble(getProteina().replace(",", "."));
        double carboidrato = Double.parseDouble(getCarboidrato().replace(",", "."));
        double gordura = Double.parseDouble(getGordura().replace(",", "."));

        kcalNumero = (proteina * 4) + (carboidrato * 4) + (gordura * 9);
        DecimalFormat formato = new DecimalFormat("#0.0");
        String resultado = formato.format(kcalNumero);
        return resultado;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
