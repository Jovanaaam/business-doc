/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Faktura {
    private int sifrafakture;
    private Date datumfakturisanja;
    private Date datumprometa;
    private StopaPDV stopa;
    private Zaposleni z;
    private OdgovornoLice odgLice;
    private double ukupno;
    private int sifrazaposlenog;
    private int sifraodglica;
    private int sifradobavljaca;
    private int sifrastopepdv;

    public Faktura(int sifrafakture) {
        this.sifrafakture = sifrafakture;
    }

    
    public int getSifrastopepdv() {
        return sifrastopepdv;
    }

    public void setSifrastopepdv(int sifrastopepdv) {
        this.sifrastopepdv = sifrastopepdv;
    }

    
    public Faktura() {
    }
    
      public Faktura(int sifrafakture, Date datumfakturisanja, Date datumprometa, int sifrastopepdv, int sifrazaposlenog, int sifradobavljaca, int sifraodglica, double ukupno) {
        this.sifrafakture = sifrafakture;
        this.datumfakturisanja = datumfakturisanja;
        this.datumprometa = datumprometa;
        this.sifrastopepdv = sifrastopepdv;
        this.sifrazaposlenog = sifrazaposlenog;
        this.sifradobavljaca = sifradobavljaca;
        this.sifraodglica = sifraodglica;
        this.ukupno = ukupno;
    }

    public Faktura(int sifrafakture, Date datumfakturisanja, Date datumprometa, StopaPDV stopa, Zaposleni z, OdgovornoLice odgLice, double ukupno) {
        this.sifrafakture = sifrafakture;
        this.datumfakturisanja = datumfakturisanja;
        this.datumprometa = datumprometa;
        this.stopa = stopa;
        this.z = z;
        this.odgLice = odgLice;
        this.ukupno = ukupno;
    }
      public Faktura(Date datumfakturisanja, Date datumprometa, StopaPDV stopa, Zaposleni z, OdgovornoLice odgLice, double ukupno) {
       
        this.datumfakturisanja = datumfakturisanja;
        this.datumprometa = datumprometa;
        this.stopa = stopa;
        this.z = z;
        this.odgLice = odgLice;
        this.ukupno = ukupno;
    }


    
    @Override
    public String toString() {
        return "SIFRA: " + sifrafakture  + "UKUPNO: " + ukupno + " ";
    }
   

    public int getSifrafakture() {
        return sifrafakture;
    }

    public void setSifrafakture(int sifrafakture) {
        this.sifrafakture = sifrafakture;
    }

    public Date getDatumfakturisanja() {
        return datumfakturisanja;
    }

    public void setDatumfakturisanja(Date datumfakturisanja) {
        this.datumfakturisanja = datumfakturisanja;
    }

    public Date getDatumprometa() {
        return datumprometa;
    }

    public void setDatumprometa(Date datumprometa) {
        this.datumprometa = datumprometa;
    }

    public StopaPDV getStopa() {
        return stopa;
    }

    public void setStopa(StopaPDV stopa) {
        this.stopa = stopa;
    }

    public Zaposleni getZ() {
        return z;
    }

    public void setZ(Zaposleni z) {
        this.z = z;
    }

    public OdgovornoLice getOdgLice() {
        return odgLice;
    }

    public void setOdgLice(OdgovornoLice odgLice) {
        this.odgLice = odgLice;
    }

    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

    public int getSifrazaposlenog() {
        return sifrazaposlenog;
    }

    public void setSifrazaposlenog(int sifrazaposlenog) {
        this.sifrazaposlenog = sifrazaposlenog;
    }

    public int getSifraodglica() {
        return sifraodglica;
    }

    public void setSifraodglica(int sifraodglica) {
        this.sifraodglica = sifraodglica;
    }

    public int getSifradobavljaca() {
        return sifradobavljaca;
    }

    public void setSifradobavljaca(int sifradobavljaca) {
        this.sifradobavljaca = sifradobavljaca;
    }


    
    

  
    
    
    
}
