/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Korisnik
 */
public class DBBrocker {
      Connection connection;
    private static DBBrocker broker;

    public DBBrocker() {
    }

    public static DBBrocker getDBBroker() {
        if (broker == null) {
            broker = new DBBrocker();
        }
        return broker;
    }

    public void loadDriver() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Drajver je uspesno ucitan.");
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }

    public void openConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "jovana", "jovana");
            connection.setAutoCommit(false);
            System.out.println("Konekcija sa bazom je uspesno uspostavljena.");
        } catch (SQLException ex) {
            System.err.println("Greska prilikom konekcije sa bazom.");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Konekcija sa bazom je uspesno zatvorena.");
        } catch (SQLException ex) {
            System.err.println("Greska prilikom zatvaranja konekcije sa bazom.");
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.err.println("Doslo je do greske prilikom komitovanja transakcije.");
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.err.println("Doslo je do greske prilikom rolbekovanja transakcije.");
        }
    }
    
    public List<JedinicaMere> pronadjiJM() throws Exception {

        List<JedinicaMere> listjm = new ArrayList<>();

        try {
            String query = "SELECT * FROM JEDINICAMERE";
            System.out.println(query);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {

                int sifrajm = rs.getInt("sifrajm");
                String nazivJM = rs.getString("nazivjm");

                JedinicaMere jm = new JedinicaMere(sifrajm, nazivJM);
                listjm.add(jm);
                System.out.println(jm);
            }
        } catch (SQLException ex) {
            throw new Exception("Greska prilikom vracanja JM" + ex.getMessage());
        }

        return listjm;

    }

       public int lastId(String tabela, String sifra) throws SQLException {
        
        int id = 1;
        
        String query = "SELECT MAX("+sifra+") AS maxsifra FROM "+tabela;
           System.out.println(query);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
             id = rs.getInt("maxsifra");
             System.out.println(id);
             id++;
         }
        return id;
        
    }
       
        public ArrayList<Proizvod> pronadjiProizvode() throws Exception  {
        ArrayList<Proizvod> proizvodi = new ArrayList<>();

        try {  
            String upit = "SELECT *"
                    + " FROM PROIZVOD p INNER JOIN JEDINICAMERE jm ON (p.sifrajm=jm.sifrajm) ORDER BY p.sifraproizvoda";
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                String nazivProizvoda = rs.getString("nazivproizvoda");
                int sifrajm = rs.getInt("sifrajm");
                String nazivjm = rs.getString("nazivjm");
                double trenutnacena = rs.getDouble("trenutnacena");
                int id = rs.getInt("sifraproizvoda");
                JedinicaMere jm = new JedinicaMere(sifrajm, nazivjm);
                Proizvod p = new Proizvod( nazivProizvoda, jm, trenutnacena);
                p.setSifraproizvoda(id);
                proizvodi.add(p);
            }
            
        } catch (SQLException ex) {
            throw new Exception("Greska prilikom ucitavanja proizvoda "+ex.getMessage());
        }
        
        return proizvodi;

    }

    public void ubaciProizvod(Proizvod p) throws SQLException, Exception {
       
          int id = lastId("proizvod", "sifraproizvoda");
 
        try {
            String query = "Insert into PROIZVOD VALUES(?,?,?,?)";
            PreparedStatement sqlStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            sqlStatement.setInt(1, id);
            sqlStatement.setString(2,p.getNazivproizvoda());
            sqlStatement.setInt(3, p.getJm().getSifrajm());
           sqlStatement.setDouble(4,p.getTrenutnacena());
            sqlStatement.executeUpdate();
           
            p.setSifraproizvoda(id);
           

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom cuvanja proizvoda "+ex.getMessage());
        }
    }

    public void azurirajProizvod(Proizvod proizvod) throws Exception {
       try {
            String query = "UPDATE PROIZVOD SET NAZIVPROIZVODA=?, SIFRAJM = ?, TRENUTNACENA = ? WHERE SIFRAPROIZVODA=?";
            System.out.println(query);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, proizvod.getNazivproizvoda());
            ps.setInt(2, proizvod.getJm().getSifrajm());
            ps.setDouble(3, proizvod.getTrenutnacena());
            ps.setInt(4,proizvod.getSifraproizvoda());
            
       
            ps.executeUpdate();
           
            
     

        } catch (SQLException ex) {
            throw new Exception("Greska prilikom izmene proizvoda "+ex.getMessage());
        }
    }

    public List<Proizvod> pronadjiProizvodPoNazivu(String naziv) throws SQLException {
        
           ArrayList<Proizvod> proizvodi = new ArrayList<>();

       
          
               String query = "SELECT *"
                    + " FROM PROIZVOD p INNER JOIN JEDINICAMERE jm ON (p.sifrajm=jm.sifrajm) " 
                    + "WHERE p.nazivproizvoda LIKE "+ "'" + naziv + "%" + "'" +" ORDER BY p.sifraproizvoda";
           
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                String nazivProizvoda = rs.getString("nazivproizvoda");
                double trenutnaCena = rs.getDouble("trenutnacena");
                int sifrajm = rs.getInt("sifrajm");
                String nazivJM = rs.getString("nazivjm");
                int sifraproizvoda = rs.getInt("sifraproizvoda");
                JedinicaMere jm = new JedinicaMere(sifrajm, nazivJM);
                Proizvod p = new Proizvod(nazivProizvoda, jm, trenutnaCena);
                p.setSifraproizvoda(sifraproizvoda);
                proizvodi.add(p);
            }
            
      
        return proizvodi;
        
        
    }

    public void obrisiProizvod(Proizvod p) throws SQLException {
        String query1 = "DELETE PROIZVOD WHERE SIFRAPROIZVODA = " + p.getSifraproizvoda() + "";
         Statement s1 = connection.createStatement();
         System.out.println(query1);
         s1.executeUpdate(query1);
         
       // String query2 = "DELETE PROIZVOD WHERE SIFRAPROIZVODA = " + p.getSifraproizvoda();
           
        //Statement s2 = connection.createStatement();
       // s2.executeQuery(query2);
    }

    public List<Dobavljac> pronadjiDobavljace() throws SQLException {
        ArrayList<Dobavljac> dobavljaci= new ArrayList<>();

      
            String upit = "select * from dobavljac";
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                String nazivdobavljaca = rs.getString("nazivdobavljaca");
                int sifradobavljaca = rs.getInt("sifradobavljaca");
               
                Dobavljac d = new Dobavljac(sifradobavljaca, nazivdobavljaca);
                dobavljaci.add(d);
            }
            
     
        
        return dobavljaci;

    }

    public void sacuvajCenu(Cena c) throws SQLException, Exception {
      try{
       String query = "INSERT INTO CENA VALUES(?,?,?,?,?)";
       PreparedStatement ps = connection.prepareStatement(query);
       ps.setInt(1, c.getDobavljac().getSifradobavljaca());
       ps.setDate(2, new Date(c.getDatumpromenecene().getTime()));
       ps.setDouble(3, c.getNabavnacena());
       ps.setString(4, "");
       ps.setInt(5, c.getProizvod().getSifraproizvoda());
       
       ps.executeUpdate();
    } catch (SQLException ex) {
                
           throw new Exception("Greska " + ex.getMessage());
        }
        
    }

    public List<Cena> pronadjiCene() throws SQLException {
         ArrayList<Cena> cene= new ArrayList<>();

      
            String upit = "select * from cena c inner join dobavljac d on (c.sifradobavljaca=d.sifradobavljaca) inner join proizvod p on (c.sifraproizvoda = p.sifraproizvoda)";
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
              int sifrapro = rs.getInt("sifraproizvoda");
              String nazivProizvoda = rs.getString("nazivproizvoda");
              Proizvod p = new Proizvod(sifrapro, nazivProizvoda);
               int sifradob = rs.getInt("sifradobavljaca");
               String nazivdob = rs.getString("nazivdobavljaca");
               Dobavljac d = new Dobavljac(sifradob, nazivdob);
               
                java.util.Date datumpromenecene = new java.util.Date(rs.getDate("datumpromenecene").getTime());
                double nabavnacena = rs.getDouble("nabavnacena");
               
                Cena c = new Cena(d, datumpromenecene, p, nazivProizvoda, nabavnacena);
                cene.add(c);
            }
            
  
        
        return cene;

    }

    public void izmeniCenu(Cena cena) throws SQLException {
        String query = "UPDATE CENA SET NAZIVPROIZVODA=?, NABAVNACENA= ? WHERE SIFRAPROIZVODA =? AND SIFRADOBAVLJACA = ? AND DATUMPROMENECENE=?";
            PreparedStatement ps = connection.prepareStatement(query);
             ps.setString(1, cena.getNazivproizvoda());
              ps.setDouble(2, cena.getNabavnacena());
               ps.setInt(3, cena.getProizvod().getSifraproizvoda());
              
            ps.setInt(4, cena.getDobavljac().getSifradobavljaca());
            ps.setDate(5, new java.sql.Date(cena.getDatumpromenecene().getTime()));
          
           
         
           
            ps.executeUpdate();
    }

    public boolean proveriDaLiVecPostojiDatum(Cena c) throws SQLException {
       int brojac = 0;
        String query= "SELECT COUNT(datumpromenecene) as brojac from cena where TO_CHAR(DATUMPROMENECENE, 'YYYY-MM-DD') = " + "'" + new Date(c.getDatumpromenecene().getTime()) + "'"+" and sifradobavljaca = "+  "'" + c.getDobavljac().getSifradobavljaca()+  "'"+"and sifraproizvoda =" + "'" + c.getProizvod().getSifraproizvoda()+ "'";
       
       
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        
        while(rs.next()){
            brojac = rs.getInt("brojac");
        }
        System.out.println("*********************" + brojac);
        if(brojac != 0)return false;
        else
        return true;
    }

    public void obrisiCenu(Cena c) throws SQLException, ParseException {
   
        String query1 = "DELETE CENA WHERE TO_CHAR(DATUMPROMENECENE, 'YYYY-MM-DD') =" + "'" + new Date(c.getDatumpromenecene().getTime()) + "'";
     
        System.out.println(query1);
         Statement s1 = connection.createStatement();
         System.out.println(query1);
         s1.executeUpdate(query1);
    }

    public List<Cena> vratiCeneParticija(String vrsta) throws SQLException {
       ArrayList<Cena> cene= new ArrayList<>();
        String query = "select * from cena partition ("+ vrsta + ") c   inner join dobavljac d on (c.sifradobavljaca=d.sifradobavljaca) inner join proizvod p on (c.sifraproizvoda = p.sifraproizvoda) ";
     System.out.println(query);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
              int sifrapro = rs.getInt("sifraproizvoda");
              String nazivProizvoda = rs.getString("nazivproizvoda");
              Proizvod p = new Proizvod(sifrapro, nazivProizvoda);
               int sifradob = rs.getInt("sifradobavljaca");
               String nazivdob = rs.getString("nazivdobavljaca");
               Dobavljac d = new Dobavljac(sifradob, nazivdob);
               
                java.util.Date datumpromenecene = new java.util.Date(rs.getDate("datumpromenecene").getTime());
                double nabavnacena = rs.getDouble("nabavnacena");
               
                Cena c = new Cena(d, datumpromenecene, p, nazivProizvoda, nabavnacena);
                cene.add(c);
    
    }
return cene;
    }

    public List<Zaposleni> vratiZaposleniDetalji() throws Exception {
          List<Zaposleni> zaposleni = new ArrayList<>();
        String query = "SELECT * FROM ZAPOSLENI_DETALJI order by sifrazaposlenog";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int sifra = rs.getInt("sifrazaposlenog");
                String jmbg = rs.getString("jmbgzaposlenog");
                String telefon = rs.getString("telefonzaposlenog");
               
                Zaposleni z = new Zaposleni(sifra, jmbg, telefon);
                zaposleni.add(z);
            }
        } catch (SQLException ex) {
                
           throw new Exception("Greska " + ex.getMessage());
        }
        
        return zaposleni;
    }

    public List<Zaposleni> vratiZaposleniOsnovno() throws Exception {
         List<Zaposleni> zaposleni = new ArrayList<>();
        String query = "SELECT * FROM ZAPOSLENI_OSNOVNO order by sifrazaposlenog";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int sifra = rs.getInt("sifrazaposlenog");
                String ime = rs.getString("imezaposlenog");
                String prezime = rs.getString("prezimezaposlenog");
               
                Zaposleni z = new Zaposleni(sifra, ime, prezime,"");
                zaposleni.add(z);
            }
        } catch (SQLException ex) {
                
           throw new Exception("Greska " + ex.getMessage());
        }
        
        return zaposleni;
    }

    public void sacuvajZaposlenog(Zaposleni z) throws SQLException, Exception {
       int sifra = lastId("zaposleni_view", "sifrazaposlenog");
       
       
        try {
            String query = "Insert into ZAPOSLENI_VIEW VALUES(?,?,?,?,?)";
            PreparedStatement sqlStatement = connection.prepareStatement(query);
            sqlStatement.setInt(1, sifra);
            sqlStatement.setString(2,z.getIme());
             sqlStatement.setString(3,z.getPrezime());
              sqlStatement.setString(4,z.getJmbg());
               sqlStatement.setString(5,z.getTelefon());
            sqlStatement.executeUpdate();
           
            z.setSifrazaposlenog(sifra);
           

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom cuvanja zaposlenog "+ex.getMessage());
        }
    }

    public List<Zaposleni> vratiZaposleniView() throws Exception {
        List<Zaposleni> zaposleni = new ArrayList<>();
        String query = "SELECT * FROM ZAPOSLENI_VIEW order by sifrazaposlenog";
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int sifra = rs.getInt("sifrazaposlenog");
                String ime = rs.getString("imezaposlenog");
                String prezime = rs.getString("prezimezaposlenog");
                String jmbg = rs.getString("jmbgzaposlenog");
                String telefon = rs.getString("telefonzaposlenog");
               
               Zaposleni z = new Zaposleni(sifra, ime, prezime, telefon, jmbg);
                zaposleni.add(z);
            }
        } catch (SQLException ex) {
                
           throw new Exception("Greska " + ex.getMessage());
        }
        
        return zaposleni;
    }

    

    public void azurirajZaposlenogOsnovno(Zaposleni zaposleni) throws SQLException { 
         String  query = "UPDATE ZAPOSLENI_OSNOVNO SET IMEZAPOSLENOG = ?, PREZIMEZAPOSLENOG = ? WHERE SIFRAZAPOSLENOG = ?";
         PreparedStatement ps = connection.prepareStatement(query);
           
            ps.setString(1, zaposleni.getIme());
              ps.setString(2, zaposleni.getPrezime());
                ps.setInt(3, zaposleni.getSifrazaposlenog());                   
            ps.executeUpdate();
            
        
   
            
    }

   public void azurirajZaposlenogDetalji(Zaposleni zaposleni) throws SQLException {
        String  query1 = "UPDATE ZAPOSLENI_DETALJI SET JMBGZAPOSLENOG = ?, TELEFONZAPOSLENOG = ? WHERE SIFRAZAPOSLENOG = ?";
             PreparedStatement ps1 = connection.prepareStatement(query1);
           
              ps1.setString(1, zaposleni.getJmbg());
              ps1.setString(2, zaposleni.getTelefon());
                ps1.setInt(3, zaposleni.getSifrazaposlenog());
             
               
            ps1.executeUpdate();
    }

    public void obrisiZaposlenog(Zaposleni z) throws SQLException {
         String query1 = "DELETE FROM ZAPOSLENI_VIEW WHERE SIFRAZAPOSLENOG= " + z.getSifrazaposlenog();
     
     
         Statement s1 = connection.createStatement();
      
         s1.executeUpdate(query1);
    }

    public List<StopaPDV> vratiStopePDV() throws Exception {
         List<StopaPDV> stope = new ArrayList<>();
        String query = "select sifrastopepdv, procenat.dajbroj(PROCENATTIP) as tip from stopapdv order by sifrastopepdv";
        System.out.println(query);
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int sifra = rs.getInt("sifrastopepdv");
                double proc = rs.getDouble("tip");
                
               
              StopaPDV stopa = new StopaPDV(sifra, proc);
              stope.add(stopa);
            }
        } catch (SQLException ex) {
                
           throw new Exception("Greska " + ex.getMessage());
        }
        
        return stope;
    }

    public void sacuvajStopuPDV(StopaPDV stopa) throws SQLException, Exception {
         try {
        int sifra = lastId("stopapdv", "sifrastopepdv");
       
       
       
            String query = "Insert into STOPAPDV VALUES(?,procenat(?))";
         
             System.out.println(query);
            PreparedStatement sqlStatement = connection.prepareStatement(query);
            sqlStatement.setInt(1, sifra);
            sqlStatement.setDouble(2,stopa.getProcenat());
             
            sqlStatement.executeUpdate();
           
            stopa.setSifrastopepdv(sifra);
           

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom cuvanja stope pdv "+ex.getMessage());
        }
    }

    public List<Faktura> vratiFakture() throws SQLException {
         List<Faktura> fakture = new ArrayList<>();
       
            String upit = "select  f.sifrafakture, f.datumprometa, f.datumfakturisanja,f.ukupno, s.sifrastopepdv, procenat.dajbroj(procenattip) as proc, z.sifrazaposlenog, z.imezaposlenog, z.prezimezaposlenog, z1.telefonzaposlenog, z1.jmbgzaposlenog, ol.sifraodglica, ol.sifradobavljaca, ol.imeprezimeodglica, d.nazivdobavljaca from faktura f inner join stopapdv s on (f.sifrastopepdv=s.sifrastopepdv) inner join zaposleni_osnovno z on (f.sifrazaposlenog = z.sifrazaposlenog) inner join zaposleni_detalji z1 on (f.sifrazaposlenog = z1.sifrazaposlenog) inner join odgovornolice ol on (f.sifraodglica = ol.sifraodglica) inner join dobavljac d on(ol.SIFRADOBAVLJACA = d.sifradobavljaca) order by sifrafakture";
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
              int sifrafak = rs.getInt("sifrafakture");
              java.util.Date datumprometa = new java.util.Date(rs.getDate("datumprometa").getTime());
                 java.util.Date datumfakturisanja = new java.util.Date(rs.getDate("datumfakturisanja").getTime());
            double ukupno = rs.getDouble("ukupno");
               int sifrastope = rs.getInt("sifrastopepdv");
               double proc = rs.getDouble("proc");
          
            StopaPDV st = new StopaPDV(sifrastope, proc);
            
            int sifradob = rs.getInt("sifradobavljaca");
            String nazivDob = rs.getString("nazivdobavljaca");
            Dobavljac d = new Dobavljac(sifradob, nazivDob);
            
            int sifraOdgLica = rs.getInt("sifraodglica");
            String imeprezimeodglica = rs.getString("imeprezimeodglica");
            
                OdgovornoLice odgovornoLice = new OdgovornoLice(sifraOdgLica, d, imeprezimeodglica);
                
                int sifrazap = rs.getInt("sifrazaposlenog");
                String imezap = rs.getString("imezaposlenog");
                String prezimezap = rs.getString("prezimezaposlenog");
                  String tel = rs.getString("telefonzaposlenog");
                    String jmbg = rs.getString("jmbgzaposlenog");
                Zaposleni z = new Zaposleni(sifrazap, imezap, prezimezap, tel, jmbg);
            
            Faktura f = new Faktura(sifrafak, datumfakturisanja, datumprometa, st, z, odgovornoLice, ukupno);
               //Faktura f = new Faktura(sifrafak, datumfakturisanja, datumprometa, st, ukupno);
               fakture.add(f);
               
            }
      
        
        return fakture;
    }

    public List<OdgovornoLice> vratiOdgLicaSvihDobavljaca() throws SQLException {
        List<OdgovornoLice> odgovornaLica = new ArrayList<>();
        
        String upit = "select ol.imeprezimeodglica, ol.sifraodglica, d.nazivdobavljaca, d.sifradobavljaca from odgovornolice ol inner join dobavljac d on (ol.sifradobavljaca = d.sifradobavljaca) order by ol.sifraodglica";
    
         Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
              int sifradob = rs.getInt("sifradobavljaca");
            String nazivDob = rs.getString("nazivdobavljaca");
            Dobavljac d = new Dobavljac(sifradob, nazivDob);
            
            int sifraOdgLica = rs.getInt("sifraodglica");
            String imeprezimeodglica = rs.getString("imeprezimeodglica");
            
                OdgovornoLice odgovornoLice = new OdgovornoLice(sifraOdgLica, d, imeprezimeodglica);
                
                odgovornaLica.add(odgovornoLice);
            }
            return odgovornaLica;
    }

    public void azurirajFakturu(Faktura f) throws SQLException {
        String  query = "UPDATE FAKTURA SET DATUMPROMETA = ?, DATUMFAKTURISANJA =?, SIFRASTOPEPDV = ?, UKUPNO = ?, SIFRAZAPOSLENOG = ?, SIFRADOBAVLJACA = ?, SIFRAODGLICA =? WHERE SIFRAFAKTURE = ?";
         PreparedStatement ps = connection.prepareStatement(query);
           
            ps.setDate(1, new Date(f.getDatumprometa().getTime()));
            ps.setDate(2, new Date(f.getDatumfakturisanja().getTime()));
              ps.setInt(3, f.getStopa().getSifrastopepdv());
                ps.setDouble(4,f.getUkupno());   
            ps.setInt(5, f.getZ().getSifrazaposlenog());
            ps.setInt(6, f.getOdgLice().getDobavljac().getSifradobavljaca());
            ps.setInt(7, f.getOdgLice().getSifraOdgLica());
            ps.setInt(8, f.getSifrafakture());
            ps.executeUpdate();
    }

    public List<Faktura> vratiFaktureView() throws SQLException {
         ArrayList<Faktura> fakture= new ArrayList<>();
        String query = "Select sifrafakture, datumfakturisanja, datumprometa, sifrastopepdv, ukupno, sifrazaposlenog, sifradobavljaca, sifraodglica from faktura order by sifrafakture";
     System.out.println(query);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
              int sifrafak = rs.getInt("sifrafakture");
                java.util.Date datumfakturisanja = new java.util.Date(rs.getDate("datumfakturisanja").getTime());
                 java.util.Date datumprometa = new java.util.Date(rs.getDate("datumprometa").getTime());
                 int sifrastope = rs.getInt("sifrastopepdv");
                 Double ukupno = rs.getDouble("ukupno");
                 int sifrazaposlenog = rs.getInt("sifrazaposlenog");
                 int sifradobavljaca = rs.getInt("sifradobavljaca");
                 int sifraodglica = rs.getInt("sifraodglica");
                 
                Faktura f = new Faktura(sifrafak, datumfakturisanja, datumprometa, sifrastope, sifrazaposlenog, sifradobavljaca, sifraodglica ,ukupno);
                fakture.add(f);
    }
    return fakture;
    }

    public void obrisiFakturu(Faktura f) throws SQLException {
       String query1 = "DELETE FAKTURA WHERE SIFRAFAKTURE = " + f.getSifrafakture();
     
        System.out.println(query1);
         Statement s1 = connection.createStatement();
         System.out.println(query1);
         s1.executeUpdate(query1);
    }

    public void sacuvajFakturu(Faktura f) throws Exception {
         try {
        int sifrafak = lastId("faktura", "sifrafakture");
       
       
       
            String query = "Insert into FAKTURA VALUES(?,?,?,?,?,?,?,?)";
         
             
            PreparedStatement sqlStatement = connection.prepareStatement(query);
            sqlStatement.setInt(1, sifrafak);
            sqlStatement.setDate(2, new Date(f.getDatumprometa().getTime()));
            sqlStatement.setDate(3, new Date(f.getDatumfakturisanja().getTime()));
            sqlStatement.setInt(4, f.getStopa().getSifrastopepdv());
            sqlStatement.setDouble(5, f.getUkupno());
            sqlStatement.setInt(6, f.getZ().getSifrazaposlenog());
            sqlStatement.setInt(7, f.getOdgLice().getDobavljac().getSifradobavljaca());
             sqlStatement.setInt(8, f.getOdgLice().getSifraOdgLica());
            sqlStatement.executeUpdate();
           
            
           

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom cuvanja stope pdv "+ex.getMessage());
        }
    }
    public int lastIdFaktura(String tabela, String sifra, int sifrafakture) throws SQLException {
        
        int id = 1;
        
        String query = "SELECT MAX("+sifra+") AS maxsifra FROM "+tabela + " WHERE sifrafakture= " + sifrafakture + " ";
        System.out.println(query);
           System.out.println(query);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
             id = rs.getInt("maxsifra");
             System.out.println(id);
             id++;
         }
        return id;
        
    }

    public void unesiStavkuFakture(StavkaFakture sf) throws SQLException, Exception {
        try{
        int rbr = lastIdFaktura("stavkafakture", "rbrstavkefakture", sf.getF().getSifrafakture());
        String query = "Insert into STAVKAFAKTURE VALUES(?,?,?,?)";
         
             System.out.println(query);
            PreparedStatement sqlStatement = connection.prepareStatement(query);
            sqlStatement.setInt(1, sf.getF().getSifrafakture());
            sqlStatement.setInt(2,rbr);
              sqlStatement.setDouble(3,sf.getKolicina());
             sqlStatement.setInt(4,sf.getP().getSifraproizvoda());
            sqlStatement.executeUpdate();
        

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom cuvanja stope pdv "+ex.getMessage());
        }
    }

    public List<StavkaFakture> vratiStavkeFakture() throws SQLException {
       List<StavkaFakture> stavke = new ArrayList<>();
       
            String upit = "select sf.sifrafakture, sf.rbrstavkefakture, sf.kolicina, p.nazivproizvoda, p.sifraproizvoda from stavkafakture sf inner join proizvod p on (sf.sifraproizvoda=p.sifraproizvoda)";
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
              int sifrafak = rs.getInt("sifrafakture");
              Faktura f = new Faktura(sifrafak);
             int rbr = rs.getInt("rbrstavkefakture");
             double kol = rs.getDouble("kolicina");
             
             int sifrapro = rs.getInt("sifraproizvoda");
             String nazivpro = rs.getString("nazivproizvoda");
             
             Proizvod p = new Proizvod(sifrapro, nazivpro);
             StavkaFakture sf =  new StavkaFakture(f, rbr, kol, p);
             
           stavke.add(sf);
               
            }
      
        
        return stavke;
    }

    public void azurirajStavkuFakture(StavkaFakture sf) throws SQLException {
         String  query = "UPDATE STAVKAFAKTURE SET KOLICINA = ?, SIFRAPROIZVODA = ? WHERE SIFRAFAKTURE = ? AND RBRSTAVKEFAKTURE = ?";
         PreparedStatement ps = connection.prepareStatement(query);
           
              ps.setDouble(1, sf.getKolicina());  
                 ps.setInt(2, sf.getP().getSifraproizvoda());  
              ps.setInt(3, sf.getF().getSifrafakture());
          
           ps.setInt(4, sf.getRbrstavkefakture());
            ps.executeUpdate();
    }

    public void obrisiStavkuFakture(StavkaFakture sf) throws SQLException {
         String query1 = "DELETE STAVKAFAKTURE WHERE SIFRAFAKTURE = " + sf.getF().getSifrafakture() + " AND  RBRSTAVKEFAKTURE = " + sf.getRbrstavkefakture();
     
        System.out.println(query1);
         Statement s1 = connection.createStatement();
  
         s1.executeUpdate(query1);
    }

    public void izmeniStopuPDV(StopaPDV stopa) throws SQLException {
       String  query = "UPDATE STOPAPDV SET PROCENATTIP = PROCENAT(?) WHERE SIFRASTOPEPDV = ? ";
         PreparedStatement ps = connection.prepareStatement(query);
           
            ps.setDouble(1, stopa.getProcenat());  
              ps.setInt(2, stopa.getSifrastopepdv());  
              
            
            ps.executeUpdate();
    }

    public void obrisiStopu(StopaPDV stopa) throws SQLException {
        String query1 = "DELETE STOPAPDV WHERE SIFRASTOPEPDV = " + stopa.getSifrastopepdv();
     
        System.out.println(query1);
         Statement s1 = connection.createStatement();
         System.out.println(query1);
         s1.executeUpdate(query1);
    }

    public void unesiUslovPlacanja(UsloviPlacanja u) throws Exception {
        try{
        int sifra = lastId("uslovi_placanja", "sifrauslova");
        String query = "insert into uslovi_placanja values(" + sifra + ",usloviplacanja(" + u.getRok() + "," + "'" + u.getVrstaPlacanja() + "'" + "))";
            System.out.println(query);
            Statement s1 = connection.createStatement();
  
         s1.executeUpdate(query);
        

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom cuvanja stope pdv "+ex.getMessage());
        }
    }

    public List<UsloviPlacanja> vratiUslove() throws SQLException {
        List<UsloviPlacanja> uslovi = new ArrayList<>();
       
            String upit = "select sifrauslova, usloviplacanja.vratiFormiranString(USLOVIPLACANJA_TIP) as ispis, usloviplacanja.vratirok(USLOVIPLACANJA_TIP) as rok, usloviplacanja.vratiNaziv(USLOVIPLACANJA_TIP) as naziv from uslovi_placanja";
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                int sifra = rs.getInt("sifrauslova");
             int rok = rs.getInt("rok");
             
             String ispis = rs.getString("ispis");
                String naziv = rs.getString("naziv");
           UsloviPlacanja u = new UsloviPlacanja(sifra, rok, naziv, ispis);
             
           uslovi.add(u);
               
            }
      
        
        return uslovi;
    }

    public void izmeniUslov(UsloviPlacanja us) throws SQLException {
        String upit = "UPDATE USLOVI_PLACANJA SET USLOVIPLACANJA_TIP = USLOVIPLACANJA(" + us.getRok()+ ",'" +us.getVrstaPlacanja() + "'" +") where sifrauslova = " + us.getSifrauslovaplacanja();
       System.out.println(upit);
         Statement s1 = connection.createStatement();
  
         s1.executeUpdate(upit);
    }

    public void obrisiUslov(UsloviPlacanja u) throws SQLException {
       String upit = "DELETE USLOVI_PLACANJA WHERE SIFRAUSLOVA= " + u.getSifrauslovaplacanja();
       System.out.println(upit);
         Statement s1 = connection.createStatement();
  
         s1.executeUpdate(upit);
    }

    public List<Narudzbenica> vratiNarudzbenice() throws SQLException {
      List<Narudzbenica> narudzbenice = new ArrayList<>();
       
            String upit = "select sifranarudzbenice from narudzbenica";
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                int sifra = rs.getInt("sifranarudzbenice");
            Narudzbenica n = new Narudzbenica(sifra);
             
           narudzbenice.add(n);
               
            }
      
        
        return narudzbenice;
    }
        public int lastIdStavkaNar(String tabela, String sifra, int sifranarudzbenice) throws SQLException {
        
        int id = 1;
        
        String query = "SELECT MAX("+sifra+") AS maxsifra FROM "+tabela + " WHERE sifranarudzbenice= " + sifranarudzbenice + " ";
        System.out.println(query);
           System.out.println(query);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
             id = rs.getInt("maxsifra");
             System.out.println(id);
             id++;
         }
        return id;
        
    }

    public void sacuvajStavkuNarudzbenice(StavkaNarudzbenice sn) throws Exception {
        try{
        int rbr = lastIdStavkaNar("stavkanarudzbenice", "rbrstavkenarudzbenice", sn.getNarudzbenica().getSifranarudzbenice());
        String query = "Insert into STAVKANARUDZBENICE VALUES(?,?,?,?)";
         
             System.out.println(query);
            PreparedStatement sqlStatement = connection.prepareStatement(query);
              sqlStatement.setInt(1,rbr);
            sqlStatement.setInt(2, sn.getNarudzbenica().getSifranarudzbenice());
          
             sqlStatement.setInt(3,sn.getProizvod().getSifraproizvoda());
             sqlStatement.setInt(4,sn.getJm().getSifrajm());
            sqlStatement.executeUpdate();
        

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom cuvanja stavke narudzbenice "+ex.getMessage());
        }
    }

    public List<StavkaNarudzbenice> vratiStavkeNarudzbenice() throws SQLException {
         List<StavkaNarudzbenice> stavke = new ArrayList<>();
       
            String upit = "select n.sifranarudzbenice, sn.rbrstavkenarudzbenice, sn.sifraproizvoda, sn.sifrajm, p.nazivproizvoda, jm.nazivjm from stavkanarudzbenice sn inner join narudzbenica n on (sn.sifranarudzbenice = n.sifranarudzbenice) inner join proizvod p on (sn.sifraproizvoda=p.sifraproizvoda) inner join jedinicamere jm on (jm.sifrajm=sn.sifrajm) order by sn.sifranarudzbenice";
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
             int sifraNar = rs.getInt("sifranarudzbenice");
             Narudzbenica narudzbenica = new Narudzbenica(sifraNar);
             
             int sifrapro = rs.getInt("sifraproizvoda");
             String nazivpro = rs.getString("nazivproizvoda");
             
             Proizvod p = new Proizvod(sifrapro, nazivpro);
             int sifrajm = rs.getInt("sifrajm");
             String nazivjm = rs.getString("nazivjm");
             
             int rbr = rs.getInt("rbrstavkenarudzbenice");
             JedinicaMere jm = new JedinicaMere(sifrajm, nazivjm);
             
         StavkaNarudzbenice stavka = new StavkaNarudzbenice(narudzbenica, rbr, p, jm);
             
           stavke.add(stavka);
               
            }
      
        
        return stavke;
    }

    public void azurirajStavkuNarudzbenice(StavkaNarudzbenice stavka) throws SQLException {
        String upit = "UPDATE STAVKANARUDZBENICE SET SIFRAPROIZVODA =" + stavka.getProizvod().getSifraproizvoda() +", SIFRAJM= " + stavka.getJm().getSifrajm() + " WHERE SIFRANARUDZBENICE = " + stavka.getNarudzbenica().getSifranarudzbenice() + " AND RBRSTAVKENARUDZBENICE = " +stavka.getRbrstavke();
       System.out.println(upit);
         Statement s1 = connection.createStatement();
  
         s1.executeUpdate(upit);
    }
    public List<StavkaNarudzbenice> vratiStavkeNarudzbenicePoProizvodu(Proizvod proizvod) throws SQLException {
         List<StavkaNarudzbenice> stavke = new ArrayList<>();
       
            String upit = "select n.sifranarudzbenice, sn.rbrstavkenarudzbenice, sn.sifraproizvoda, sn.sifrajm, p.nazivproizvoda, jm.nazivjm from stavkanarudzbenice sn inner join narudzbenica n on (sn.sifranarudzbenice = n.sifranarudzbenice) inner join proizvod p on (sn.sifraproizvoda=p.sifraproizvoda) inner join jedinicamere jm on (jm.sifrajm=sn.sifrajm) WHERE sn.SIFRAPROIZVODA =" + proizvod.getSifraproizvoda();
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
             int sifraNar = rs.getInt("sifranarudzbenice");
             Narudzbenica narudzbenica = new Narudzbenica(sifraNar);
             
             int sifrapro = rs.getInt("sifraproizvoda");
             String nazivpro = rs.getString("nazivproizvoda");
             
             Proizvod p = new Proizvod(sifrapro, nazivpro);
             int sifrajm = rs.getInt("sifrajm");
             String nazivjm = rs.getString("nazivjm");
             
             int rbr = rs.getInt("rbrstavkenarudzbenice");
             JedinicaMere jm = new JedinicaMere(sifrajm, nazivjm);
             
         StavkaNarudzbenice stavka = new StavkaNarudzbenice(narudzbenica, rbr, p, jm);
             
           stavke.add(stavka);
               
            }
      
        
        return stavke;
    }

    public void obrisiStavkuNarudzbenice(StavkaNarudzbenice sn) throws SQLException {
         String upit = "DELETE STAVKANARUDZBENICE WHERE SIFRANARUDZBENICE= " + sn.getNarudzbenica().getSifranarudzbenice() + " AND RBRSTAVKENARUDZBENICE = " + sn.getRbrstavke();
       System.out.println(upit);
         Statement s1 = connection.createStatement();
  
         s1.executeUpdate(upit);
    }
}
