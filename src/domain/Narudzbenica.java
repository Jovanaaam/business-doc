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
public class Narudzbenica {
    private int sifranarudzbenice;
    private Date datumOd;
    private Date datumDo;
    private String napomena;
    private UsloviPlacanja usloviPlacanja;

    public Narudzbenica() {
    }

    public Narudzbenica(int sifranarudzbenice, Date datumOd, Date datumDo, String napomena, UsloviPlacanja usloviPlacanja) {
        this.sifranarudzbenice = sifranarudzbenice;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.napomena = napomena;
        this.usloviPlacanja = usloviPlacanja;
    }
    
      public Narudzbenica(int sifranarudzbenice) {
        this.sifranarudzbenice = sifranarudzbenice;
     
    }

    public int getSifranarudzbenice() {
        return sifranarudzbenice;
    }

    public void setSifranarudzbenice(int sifranarudzbenice) {
        this.sifranarudzbenice = sifranarudzbenice;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public UsloviPlacanja getUsloviPlacanja() {
        return usloviPlacanja;
    }

    public void setUsloviPlacanja(UsloviPlacanja usloviPlacanja) {
        this.usloviPlacanja = usloviPlacanja;
    }

    @Override
    public String toString() {
        return "SIFRA NARUDZBENICE: " + sifranarudzbenice;
    }
      
      
    
    
    
    
}
