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
public class StavkaNarudzbenice {
    private Narudzbenica narudzbenica;
    private int rbrstavke;
    private Proizvod proizvod;
    private JedinicaMere jm;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(Narudzbenica narudzbenica, int rbrstavke, Proizvod proizvod, JedinicaMere jm) {
        this.narudzbenica = narudzbenica;
        this.rbrstavke = rbrstavke;
        this.proizvod = proizvod;
        this.jm = jm;
    }
    public StavkaNarudzbenice( Proizvod proizvod, JedinicaMere jm) {
        this.proizvod = proizvod;
        this.jm = jm;
    }
       public StavkaNarudzbenice(Narudzbenica narudzbenica, Proizvod proizvod, JedinicaMere jm) {
        this.narudzbenica = narudzbenica;
      
        this.proizvod = proizvod;
        this.jm = jm;
    }

    public JedinicaMere getJm() {
        return jm;
    }

    public void setJm(JedinicaMere jm) {
        this.jm = jm;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public int getRbrstavke() {
        return rbrstavke;
    }

    public void setRbrstavke(int rbrstavke) {
        this.rbrstavke = rbrstavke;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    @Override
    public String toString() {
       return "Sifra: " + narudzbenica.getSifranarudzbenice() + " rbr: " + this.rbrstavke + " proizvod: " + proizvod.getNazivproizvoda()+ " jm: " + jm.getNazivjm();
    }
    
    
    
}
