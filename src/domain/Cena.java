/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Dobavljac;
import domain.Proizvod;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Cena {
    private Dobavljac dobavljac;
    private Date datumpromenecene;
    private Proizvod proizvod;
    private String nazivproizvoda;
    private double nabavnacena;

    public Cena() {
    }

    public Cena(Dobavljac dobavljac, Date datumpromenecene, Proizvod proizvod, String nazivproizvoda, double nabavnacena) {
        this.dobavljac = dobavljac;
        this.datumpromenecene = datumpromenecene;
        this.proizvod = proizvod;
        this.nazivproizvoda = nazivproizvoda;
        this.nabavnacena = nabavnacena;
    }

    public double getNabavnacena() {
        return nabavnacena;
    }

    public void setNabavnacena(double nabavnacena) {
        this.nabavnacena = nabavnacena;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public Date getDatumpromenecene() {
        return datumpromenecene;
    }

    public void setDatumpromenecene(Date datumpromenecene) {
        this.datumpromenecene = datumpromenecene;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public String getNazivproizvoda() {
        return nazivproizvoda;
    }

    public void setNazivproizvoda(String nazivproizvoda) {
        this.nazivproizvoda = nazivproizvoda;
    }

    @Override
    public String toString() {
           DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return "Dobavljac: " +dobavljac.getNazivdobavljaca() + " Proizvod: " + proizvod.getNazivproizvoda() + " Datum:" + df.format(datumpromenecene);
    }

 
    
    
    
    
    
}
