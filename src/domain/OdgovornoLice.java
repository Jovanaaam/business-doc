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
public class OdgovornoLice {
    private int sifraOdgLica;
    private Dobavljac dobavljac;
    private String imeprezime;
    private String telefon;
    private String email;

    public OdgovornoLice() {
    }

    public OdgovornoLice(int sifraOdgLica, Dobavljac dobavljac, String imeprezime, String telefon, String email) {
        this.sifraOdgLica = sifraOdgLica;
        this.dobavljac = dobavljac;
        this.imeprezime = imeprezime;
        this.telefon = telefon;
        this.email = email;
    }
      public OdgovornoLice(int sifraOdgLica, Dobavljac dobavljac, String imeprezime) {
        this.sifraOdgLica = sifraOdgLica;
        this.dobavljac = dobavljac;
        this.imeprezime = imeprezime; 
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSifraOdgLica() {
        return sifraOdgLica;
    }

    public void setSifraOdgLica(int sifraOdgLica) {
        this.sifraOdgLica = sifraOdgLica;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public String getImeprezime() {
        return imeprezime;
    }

    public void setImeprezime(String imeprezime) {
        this.imeprezime = imeprezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
       return "DOBAVLJAC sifra: " + dobavljac.getSifradobavljaca() + " naziv : " + dobavljac.getNazivdobavljaca() + "------ODGOVORNO LICE: "+ sifraOdgLica + " " + imeprezime;
    }
    
    
     public boolean equals(Object o) {
        OdgovornoLice odg = (OdgovornoLice) o;
      if(this.sifraOdgLica!=odg.getSifraOdgLica())
         return false;
    if(!Objects.equals(this.dobavljac.getSifradobavljaca(),odg.getDobavljac().getSifradobavljaca())) {
            return false;
        }
         if(!Objects.equals(this.dobavljac.getNazivdobavljaca(),odg.getDobavljac().getNazivdobavljaca())) {
            return false;
        }
         
         if(!Objects.equals(this.imeprezime, odg.getImeprezime())) {
            return false;
        }
           
        return true;
    }
    
}
