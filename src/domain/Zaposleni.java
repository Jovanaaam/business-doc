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
public class Zaposleni {
    private int sifrazaposlenog;
    private String ime;
    private String prezime;
    private String telefon;
    private String jmbg;
    private String prazan;

    public Zaposleni() {
    }
     public Zaposleni(String ime, String prezime, String telefon, String jmbg) {
     
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.jmbg = jmbg;
    }


    public Zaposleni(int sifrazaposlenog, String ime, String prezime, String telefon, String jmbg) {
        this.sifrazaposlenog = sifrazaposlenog;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.jmbg = jmbg;
    }
   
    public Zaposleni(int sifrazaposlenog, String jmbg, String telefon) {
        this.sifrazaposlenog = sifrazaposlenog;
        this.jmbg = jmbg;
        this.telefon = telefon;
    }
      public Zaposleni(int sifrazaposlenog, String ime, String prezime, String prazan) {
        this.sifrazaposlenog = sifrazaposlenog;
        this.ime = ime;
        this.prezime = prezime;
      
    }
    public Zaposleni(String ime, String prezime) {
       
        this.ime = ime;
        this.prezime = prezime;
      
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public int getSifrazaposlenog() {
        return sifrazaposlenog;
    }

    public void setSifrazaposlenog(int sifrazaposlenog) {
        this.sifrazaposlenog = sifrazaposlenog;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return sifrazaposlenog + " " + ime + " " + prezime + " " +jmbg + " " + telefon;
    }
    
     public String toString2() {
        return sifrazaposlenog + " " + ime + " " + prezime + "Telefon: " + telefon;
    }

  
  
     
      public boolean equals(Object o) {
        Zaposleni z = (Zaposleni) o;
      if(this.sifrazaposlenog!=z.getSifrazaposlenog())
         return false;
    if(!Objects.equals(this.ime,z.getIme())) {
            return false;
        }
         if(!Objects.equals(this.prezime,z.getPrezime())) {
            return false;
        }
         
         if(!Objects.equals(this.telefon,z.getTelefon())) {
            return false;
        }
           if(!Objects.equals(this.jmbg,z.getJmbg())) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
