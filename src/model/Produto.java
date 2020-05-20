/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elivelton
 */
public class Produto {

    private int idProduto;
    private String nomeProduto;
    private String descProduto;
    private double valProduto;
    private int estoProduto;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public double getValProduto() {
        return valProduto;
    }

    public void setValProduto(double valProduto) {
        this.valProduto = valProduto;
    }

    public int getEstoProduto() {
        return estoProduto;
    }

    public void setEstoProduto(int estoProduto) {
        this.estoProduto = estoProduto;
    }

}
