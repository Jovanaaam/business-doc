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
public class UsloviPlacanja {
    private int sifrauslovaplacanja;
    private int rok;
    private String vrstaPlacanja;
    private String rokIspis;

    public UsloviPlacanja() {
    }

    public UsloviPlacanja(int sifrauslovaplacanja, int rok, String vrstaPlacanja, String rokIspis) {
        this.sifrauslovaplacanja = sifrauslovaplacanja;
        this.rok = rok;
        this.vrstaPlacanja = vrstaPlacanja;
        this.rokIspis = rokIspis;
    }
     public UsloviPlacanja(int rok, String vrstaPlacanja) {
       
        this.rok = rok;
        this.vrstaPlacanja = vrstaPlacanja;
        
    }
      public UsloviPlacanja(int sifrauslovaplacanja, int rok, String vrstaPlacanja) {
        this.sifrauslovaplacanja = sifrauslovaplacanja;
        this.rok = rok;
        this.vrstaPlacanja = vrstaPlacanja;
   
    }
   
    
  

    @Override
    public String toString() {
        return "Sifra: " + sifrauslovaplacanja + " naziv: " + vrstaPlacanja + " rok: " + rok;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public String getVrstaPlacanja() {
        return vrstaPlacanja;
    }

    public void setVrstaPlacanja(String vrstaPlacanja) {
        this.vrstaPlacanja = vrstaPlacanja;
    }

    public String getRokIspis() {
        return rokIspis;
    }

    public void setRokIspis(String rokIspis) {
        this.rokIspis = rokIspis;
    }

    public int getSifrauslovaplacanja() {
        return sifrauslovaplacanja;
    }

    public void setSifrauslovaplacanja(int sifrauslovaplacanja) {
        this.sifrauslovaplacanja = sifrauslovaplacanja;
    }
    
    
}
