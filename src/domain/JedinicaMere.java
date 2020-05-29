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
public class JedinicaMere {
    
    private int sifrajm;
    private String nazivjm;

    public JedinicaMere(int sifrajm, String nazivjm) {
        this.sifrajm = sifrajm;
        this.nazivjm = nazivjm;
    }

    public JedinicaMere() {
    }

    public int getSifrajm() {
        return sifrajm;
    }

    public void setSifrajm(int sifrajm) {
        this.sifrajm = sifrajm;
    }

    public String getNazivjm() {
        return nazivjm;
    }

    public void setNazivjm(String nazivjm) {
        this.nazivjm = nazivjm;
    }

    @Override
    public String toString() {
        return sifrajm +"-"+ nazivjm;
    }

  
    @Override
    public boolean equals(Object o) {
        JedinicaMere jm = (JedinicaMere) o;
        if (this.sifrajm != jm.getSifrajm()) {
            return false;
        }
       if(!Objects.equals(jm.getNazivjm(), this.getNazivjm())) {
            return false;
        }
       
        return true;
    }
    
    
    
}
