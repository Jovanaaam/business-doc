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
public class Dobavljac {
    private int sifradobavljaca;
    private String nazivdobavljaca;
    private String maticnibrojdobavljaca;
    private String pibdobavljaca;
    private String ziroracundobavljaca;
    private String mobilnitelefondobavljaca;
    private String faksdobavljaca;
    private String emaildobavljaca;

    public Dobavljac() {
    }

    public Dobavljac(int sifradobavljaca, String nazivdobavljaca) {
        this.sifradobavljaca = sifradobavljaca;
        this.nazivdobavljaca = nazivdobavljaca;
    }

    public Dobavljac(int sifradobavljaca) {
        this.sifradobavljaca = sifradobavljaca;
    }

    public int getSifradobavljaca() {
        return sifradobavljaca;
    }

    public void setSifradobavljaca(int sifradobavljaca) {
        this.sifradobavljaca = sifradobavljaca;
    }

    public String getNazivdobavljaca() {
        return nazivdobavljaca;
    }

    public void setNazivdobavljaca(String nazivdobavljaca) {
        this.nazivdobavljaca = nazivdobavljaca;
    }

    public String getMaticnibrojdobavljaca() {
        return maticnibrojdobavljaca;
    }

    public void setMaticnibrojdobavljaca(String maticnibrojdobavljaca) {
        this.maticnibrojdobavljaca = maticnibrojdobavljaca;
    }

    public String getPibdobavljaca() {
        return pibdobavljaca;
    }

    public void setPibdobavljaca(String pibdobavljaca) {
        this.pibdobavljaca = pibdobavljaca;
    }

    public String getZiroracundobavljaca() {
        return ziroracundobavljaca;
    }

    public void setZiroracundobavljaca(String ziroracundobavljaca) {
        this.ziroracundobavljaca = ziroracundobavljaca;
    }

    public String getMobilnitelefondobavljaca() {
        return mobilnitelefondobavljaca;
    }

    public void setMobilnitelefondobavljaca(String mobilnitelefondobavljaca) {
        this.mobilnitelefondobavljaca = mobilnitelefondobavljaca;
    }

    public String getFaksdobavljaca() {
        return faksdobavljaca;
    }

    public void setFaksdobavljaca(String faksdobavljaca) {
        this.faksdobavljaca = faksdobavljaca;
    }

    public String getEmaildobavljaca() {
        return emaildobavljaca;
    }

    public void setEmaildobavljaca(String emaildobavljaca) {
        this.emaildobavljaca = emaildobavljaca;
    }
    
     public boolean equals(Object o) {
        Dobavljac d= (Dobavljac) o;
        if (this.sifradobavljaca!= d.getSifradobavljaca()) {
            return false;
        }
       if(!Objects.equals(d.getNazivdobavljaca(), this.getNazivdobavljaca())) {
            return false;
        }
       
        return true;
    }
       @Override
    public String toString() {
        return sifradobavljaca +"-"+ nazivdobavljaca;
    }
}
