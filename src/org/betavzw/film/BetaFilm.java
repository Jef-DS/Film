package org.betavzw.film;

import java.util.Objects;

public class BetaFilm implements Comparable<BetaFilm> {
    private int id;
    private String titel;
    private String regisseur;
    private int jaartal;

    public BetaFilm(int id) {
        this.id = id;
    }

    public BetaFilm(int id, String titel, String regisseur, int jaartal) {
        this.id = id;
        this.titel = titel;
        this.regisseur = regisseur;
        this.jaartal = jaartal;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getRegisseur() {
        return regisseur;
    }

    public void setRegisseur(String regisseur) {
        this.regisseur = regisseur;
    }

    public int getJaartal() {
        return jaartal;
    }

    public void setJaartal(int jaartal) {
        this.jaartal = jaartal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetaFilm betaFilm = (BetaFilm) o;
        return id == betaFilm.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(BetaFilm o) {
        if (o == null) return +1;
        return this.titel.compareTo(o.titel);
    }
}
