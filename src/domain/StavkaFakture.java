/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Korisnik
 */
public class StavkaFakture {
    private Faktura f;
    private int rbrstavkefakture;
    private double kolicina;
    private Proizvod p;

    public StavkaFakture() {
    }

    public StavkaFakture(Faktura f, int rbrstavkefakture, double kolicina, Proizvod p) {
        this.f = f;
        this.rbrstavkefakture = rbrstavkefakture;
        this.kolicina = kolicina;
        this.p = p;
    }
    
     public StavkaFakture(Faktura f, double kolicina, Proizvod p) {
        this.f = f;
     
        this.kolicina = kolicina;
        this.p = p;
    }

    public Proizvod getP() {
        return p;
    }

    public void setP(Proizvod p) {
        this.p = p;
    }

    public Faktura getF() {
        return f;
    }

    public void setF(Faktura f) {
        this.f = f;
    }

    public int getRbrstavkefakture() {
        return rbrstavkefakture;
    }

    public void setRbrstavkefakture(int rbrstavkefakture) {
        this.rbrstavkefakture = rbrstavkefakture;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "Faktura: " + f.getSifrafakture() + " rbr: " + rbrstavkefakture + " kolicina: " + kolicina + " proizvod " + p.getNazivproizvoda();
    }
     
    
    
    
}
