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
public class StopaPDV {
    private int sifrastopepdv;
    private double procenat;

    public StopaPDV() {
    }

    public StopaPDV(int sifrastopepdv, double procenat) {
        this.sifrastopepdv = sifrastopepdv;
        this.procenat = procenat;
    }

    public StopaPDV(double procenat) {
        this.procenat = procenat;
    }
    
    public double getProcenat() {
        return procenat;
    }

    public void setProcenat(double procenat) {
        this.procenat = procenat;
    }

    public int getSifrastopepdv() {
        return sifrastopepdv;
    }

    public void setSifrastopepdv(int sifrastopepdv) {
        this.sifrastopepdv = sifrastopepdv;
    }

    @Override
    public String toString() {
      return "sifra: " +sifrastopepdv + " PDV: " + +procenat; 
    }
     public boolean equals(Object o) {
        StopaPDV s = (StopaPDV) o;
        if (this.sifrastopepdv != s.getSifrastopepdv()) {
            return false;
        }
       if(!Objects.equals(s.getProcenat(), this.getProcenat())) {
            return false;
        }
       
        return true;
    }
    
    
    
    
}
