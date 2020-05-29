/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBBrocker;
import domain.Zaposleni;
import domain.Cena;
import domain.Dobavljac;
import domain.Faktura;
import domain.JedinicaMere;
import domain.Narudzbenica;
import domain.OdgovornoLice;
import domain.Proizvod;
import domain.StavkaFakture;
import domain.StavkaNarudzbenice;
import domain.StopaPDV;
import domain.UsloviPlacanja;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class Controller {
    private static Controller instance;

    private DBBrocker db ;

    private Controller() {
        db = DBBrocker.getDBBroker();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

      public List<JedinicaMere> selectJM() throws Exception {
        
        List<JedinicaMere> jm = null;
        try {
            
            db.loadDriver();
            db.openConnection();
            jm = db.pronadjiJM();
            
        } catch (SQLException ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
        return jm;

    }
       public void sacuvajProizvod(Proizvod p) throws Exception {
         try {
            
            db.loadDriver();
            db.openConnection();
            db.ubaciProizvod(p);
            db.commit();
            
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }
      public List<Proizvod> vratiProizvode() throws Exception {
        
        List<Proizvod> proizvodi = null;

        try {
            db.loadDriver();
            db.openConnection();
            proizvodi = db.pronadjiProizvode();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return proizvodi;
    }

    public void azurirajProizvod(Proizvod proizvod) throws Exception {
        try {
            db.loadDriver();
            db.openConnection();
            db.azurirajProizvod(proizvod);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<Proizvod> pronadjiProizvodPoNazivu(String naziv) throws Exception {
         List<Proizvod> proizvodi = null;

        try {
            db.loadDriver();
            db.openConnection();
            proizvodi = db.pronadjiProizvodPoNazivu(naziv);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
        return proizvodi;
    }

    public void obrisiProizvod(Proizvod p) throws Exception {
        try {
            db.loadDriver();
            db.openConnection();
            db.obrisiProizvod(p);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
   
    }

    public List<Dobavljac> vratiDobavljace() throws Exception {
         
        List<Dobavljac> dobavljaci = null;

        try {
            db.loadDriver();
            db.openConnection();
            dobavljaci = db.pronadjiDobavljace();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return dobavljaci;
    }

    public void sacuvajCenu(Cena c) throws Exception {
         try {
            
            db.loadDriver();
            db.openConnection();
            db.sacuvajCenu(c);
            db.commit();
            
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<Cena> vratiCene() throws Exception{
       List<Cena> cene = null;

        try {
            db.loadDriver();
            db.openConnection();
            cene = db.pronadjiCene();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return cene;
    }

    public void izmeniCenu(Cena cena) throws Exception {
        try {
            db.loadDriver();
            db.openConnection();
            db.izmeniCenu(cena);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public boolean proveriDaLiVecPostojiDatum(Cena c) throws Exception {
        boolean provera = true;
         try {
            db.loadDriver();
            db.openConnection();
            provera = db.proveriDaLiVecPostojiDatum(c);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
       
        return provera;
    }

    public void obrisiCenu(Cena c) throws Exception  {
       try {
            db.loadDriver();
            db.openConnection();
            db.obrisiCenu(c);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }


    public List<Cena> vratiCeneParticija(String vrsta)throws Exception {
          List<Cena> cene = null;

        try {
            db.loadDriver();
            db.openConnection();
            cene = db.vratiCeneParticija(vrsta);
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return cene;
    }

    public List<Zaposleni> vratiZaposleniDetalji() throws Exception {
          List<Zaposleni> zaposleniDetalji = null;

        try {
            db.loadDriver();
            db.openConnection();
            zaposleniDetalji = db.vratiZaposleniDetalji();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return zaposleniDetalji;
    }

    public List<Zaposleni> vratiZaposleniOsnovno() throws Exception {
        List<Zaposleni> zaposleniOsnovno = null;

        try {
            db.loadDriver();
            db.openConnection();
            zaposleniOsnovno = db.vratiZaposleniOsnovno();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return zaposleniOsnovno;
    }

    public void sacuvajZaposlenog(Zaposleni z) throws Exception{
         try {
            
            db.loadDriver();
            db.openConnection();
            db.sacuvajZaposlenog(z);
            db.commit();
            
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<Zaposleni> vratiZaposleneView() throws Exception {
        List<Zaposleni> zaposleni= null;

        try {
            db.loadDriver();
            db.openConnection();
            zaposleni = db.vratiZaposleniView();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return zaposleni;
    }


    public void azurirajZaposlenogOsnovno(Zaposleni zaposleni) throws Exception {
         try {
            db.loadDriver();
            db.openConnection();
            db.azurirajZaposlenogOsnovno(zaposleni);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }
    public void azurirajZaposlenogDetalji(Zaposleni zaposleni) throws Exception {
         try {
            db.loadDriver();
            db.openConnection();
            db.azurirajZaposlenogDetalji(zaposleni);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public void obrisiZaposlenog(Zaposleni z)throws Exception  {
         try {
            db.loadDriver();
            db.openConnection();
            db.obrisiZaposlenog(z);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<StopaPDV> vratiStopePDV() throws Exception {
         List<StopaPDV> stope= null;

        try {
            db.loadDriver();
            db.openConnection();
            stope = db.vratiStopePDV();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return stope;
    }

    public void unesiStopuPDV(StopaPDV stopa) throws Exception {
         try {
            
            db.loadDriver();
            db.openConnection();
            db.sacuvajStopuPDV(stopa);
            db.commit();
            
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<Faktura> vratiFakture() throws Exception {
      List<Faktura> fakture= null;

        try {
            db.loadDriver();
            db.openConnection();
            fakture = db.vratiFakture();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return fakture;
    }

  

    public List<OdgovornoLice> vratiOdgLicaSvihDobavljaca() throws Exception {
         List<OdgovornoLice> odgLica= null;

        try {
            db.loadDriver();
            db.openConnection();
            odgLica = db.vratiOdgLicaSvihDobavljaca();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return odgLica;
    }

    public void azurirajFakturu(Faktura f)throws Exception {
        try {
            db.loadDriver();
            db.openConnection();
            db.azurirajFakturu(f);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<Faktura> vratiFaktureView() throws Exception{
         List<Faktura> fakture= null;

        try {
            db.loadDriver();
            db.openConnection();
            fakture = db.vratiFaktureView();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return fakture;
    }

    public void obrisiFakturu(Faktura f)throws Exception {
        try {
            db.loadDriver();
            db.openConnection();
            db.obrisiFakturu(f);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public void sacuvajFakturu(Faktura f) throws Exception{
          try {
            
            db.loadDriver();
            db.openConnection();
            db.sacuvajFakturu(f);
            db.commit();
            
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public void unesiStavkuFakture(StavkaFakture sf)throws Exception {
         try {
            
            db.loadDriver();
            db.openConnection();
            db.unesiStavkuFakture(sf);
            db.commit();
            
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<StavkaFakture> vratiStavkeFakture()throws Exception  {
        List<StavkaFakture> stavkefakture= null;

        try {
            db.loadDriver();
            db.openConnection();
            stavkefakture = db.vratiStavkeFakture();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return stavkefakture;
    }

    public void azurirajStavkuFakture(StavkaFakture sf) throws Exception {
      try {
            db.loadDriver();
            db.openConnection();
            db.azurirajStavkuFakture(sf);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public void obrisiStavkuFakture(StavkaFakture sf) throws Exception {
       try {
            db.loadDriver();
            db.openConnection();
            db.obrisiStavkuFakture(sf);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public void izmeniStopuPDV(StopaPDV stopa)throws Exception {
       try {
            db.loadDriver();
            db.openConnection();
            db.izmeniStopuPDV(stopa);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public void obrisiStopu(StopaPDV stopa) throws Exception {
        try {
            db.loadDriver();
            db.openConnection();
            db.obrisiStopu(stopa);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public void unesiUslovPlacanja(UsloviPlacanja u) throws Exception {
          try {
            
            db.loadDriver();
            db.openConnection();
            db.unesiUslovPlacanja(u);
            db.commit();
            
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<UsloviPlacanja> vratiUslove() throws Exception{
            List<UsloviPlacanja> uslovi= null;

        try {
            db.loadDriver();
            db.openConnection();
            uslovi = db.vratiUslove();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return uslovi;
    }

    public void izmeniUslov(UsloviPlacanja us) throws Exception {
               try {
            db.loadDriver();
            db.openConnection();
            db.izmeniUslov(us);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public void obrisiUslov(UsloviPlacanja u) throws Exception  {
         try {
            db.loadDriver();
            db.openConnection();
            db.obrisiUslov(u);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<Narudzbenica> vratiNarudzbenice() throws Exception{
          List<Narudzbenica> narudzbenice= null;

        try {
            db.loadDriver();
            db.openConnection();
            narudzbenice = db.vratiNarudzbenice();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return narudzbenice;
    }

    public void sacuvajStavkuNarudzbenice(StavkaNarudzbenice sn) throws Exception {
       try {
            
            db.loadDriver();
            db.openConnection();
            db.sacuvajStavkuNarudzbenice(sn);
            db.commit();
            
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }

    public List<StavkaNarudzbenice> vratiStavkeNarudzbenice() throws Exception{
        List<StavkaNarudzbenice> stavke= null;

        try {
            db.loadDriver();
            db.openConnection();
            stavke = db.vratiStavkeNarudzbenice();
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return stavke;
    }

    public void azurirajStavkuNarudzbenice(StavkaNarudzbenice stavka) throws Exception {
         try {
            db.loadDriver();
            db.openConnection();
            db.azurirajStavkuNarudzbenice(stavka);
            db.commit();
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }
       public List<StavkaNarudzbenice> vratiStavkeNarudzbenicePoProizvodu(Proizvod proizvod) throws Exception{
        List<StavkaNarudzbenice> stavke= null;

        try {
            db.loadDriver();
            db.openConnection();
            stavke = db.vratiStavkeNarudzbenicePoProizvodu(proizvod);
            db.closeConnection();
        } catch (Exception ex) {
            throw ex;
        }
        
        return stavke;
    }

    public void obrisiStavkuNarudzbenice(StavkaNarudzbenice sn) throws Exception {
          try {
            db.loadDriver();
            db.openConnection();
            db.obrisiStavkuNarudzbenice(sn);
        } catch (Exception ex) {
            db.rollback();
            throw ex;
        }
        db.closeConnection();
    }
}
