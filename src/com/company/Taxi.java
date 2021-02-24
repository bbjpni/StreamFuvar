package com.company;

import java.util.Date;

public class Taxi {
    private int azon;
    private String indulasIdo;
    private int idotartam;
    private double megtettTavolsag;
    private double viteldij;
    private double borravalo;
    private String fizMod;

    public Taxi(String sor) {
        String[] tomb = sor.split(";");
        this.azon = Integer.parseInt(tomb[0]);
        this.indulasIdo = tomb[1];
        this.idotartam = Integer.parseInt(tomb[2]);
        this.megtettTavolsag = Double.parseDouble(tomb[3].replace(",", "."));
        this.viteldij = Double.parseDouble(tomb[4].replace(",", "."));
        this.borravalo = Double.parseDouble(tomb[5].replace(",", "."));
        this.fizMod = tomb[6];
    }

    public int getAzon() {
        return azon;
    }

    public String getIndulasIdo() {
        return indulasIdo;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public double getMegtettTavolsag() {
        return megtettTavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public String getFizMod() {
        return fizMod;
    }

    @Override
    public String toString() {
        return String.format("Taxi: %d | %s (%d mp) > %.2f m | $%.2f + $%.2f (borravaló) - %s ",
                this.azon, this.indulasIdo, this.idotartam, this.megtettTavolsag, this.viteldij, this.borravalo, this.fizMod);

    }
}
