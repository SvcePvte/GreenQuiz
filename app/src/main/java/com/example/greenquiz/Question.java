package com.example.greenquiz;

public class Question {



    private String libelle;
    private int nbOui;
    private int nbNon;

    public Question(String libelle, int nbOui, int nbNon) {
        this.libelle = libelle;
        this.nbOui = nbOui;
        this.nbNon = nbNon;
    }

    public String getLibelle()
    {
        return libelle;
    }

    public int getNbOui()
    {
        return nbOui;
    }

    public int getNbNon()
    {
        return nbNon;
    }
}
