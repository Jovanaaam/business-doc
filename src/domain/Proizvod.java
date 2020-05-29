/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class Proizvod {
    private int sifraproizvoda;
    private String nazivproizvoda;
    private JedinicaMere jm;
    private double trenutnacena;

    public Proizvod(int sifraproizvoda, String nazivproizvoda) {
        this.sifraproizvoda = sifraproizvoda;
        this.nazivproizvoda = nazivproizvoda;
    }

    
    public Proizvod(int sifraproizvoda, String nazivproizvoda, JedinicaMere jm, double trenutnacena) {
        this.sifraproizvoda = sifraproizvoda;
        this.nazivproizvoda = nazivproizvoda;
        this.jm = jm;
        this.trenutnacena = trenutnacena;
    }

    public Proizvod(String nazivproizvoda, JedinicaMere jm, double trenutnacena) {
        this.nazivproizvoda = nazivproizvoda;
        this.jm = jm;
        this.trenutnacena = trenutnacena;
    }

    public Proizvod(String nazivproizvoda, JedinicaMere jm) {
        this.nazivproizvoda = nazivproizvoda;
        this.jm = jm;
    }

    public Proizvod(int sifraproizvoda, String nazivproizvoda, JedinicaMere jm) {
        this.sifraproizvoda = sifraproizvoda;
        this.nazivproizvoda = nazivproizvoda;
        this.jm = jm;
    }
    
    

    public Proizvod() {
    }

    public int getSifraproizvoda() {
        return sifraproizvoda;
    }

    public void setSifraproizvoda(int sifraproizvoda) {
        this.sifraproizvoda = sifraproizvoda;
    }

    public String getNazivproizvoda() {
        return nazivproizvoda;
    }

    public void setNazivproizvoda(String nazivproizvoda) {
        this.nazivproizvoda = nazivproizvoda;
    }

    public JedinicaMere getJm() {
        return jm;
    }

    public void setJm(JedinicaMere jm) {
        this.jm = jm;
    }

    public double getTrenutnacena() {
        return trenutnacena;
    }

    public void setTrenutnacena(double trenutnacena) {
        this.trenutnacena = trenutnacena;
    }

    @Override
    public String toString() {
        
        return sifraproizvoda + "-" + nazivproizvoda;
    }
    
     @Override
    public boolean equals(Object o) {
        Proizvod p = (Proizvod) o;
        if(this.sifraproizvoda!=p.getSifraproizvoda())
            return false;
        if(!Objects.equals(this.nazivproizvoda,p.getNazivproizvoda())) {
            return false;
        }
        return true;
    }
    
    
}
