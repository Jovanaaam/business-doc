/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.stavkanarudzbenice;

import controller.Controller;
import domain.Proizvod;
import domain.StavkaNarudzbenice;
import forms.proizvod.ProizvodUpdate;
import forms.proizvod.ProizvodView;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Korisnik
 */
public class StavkaNarudzbeniceView extends javax.swing.JFrame {

    /**
     * Creates new form StavkaNarudzbeniceView
     */
    public StavkaNarudzbeniceView() {
        initComponents();
        initForm();
        CBProizvod();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableStavke = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbProizvodi = new javax.swing.JComboBox();
        btnPretrazi = new javax.swing.JButton();
        jrbVratiSve = new javax.swing.JRadioButton();
        jrbPretraziPoProizvodu = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableStavke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sifra nrudzbenice", "Rbr stavke", "Proizvod", "Jedinica mere"
            }
        ));
        jScrollPane1.setViewportView(tableStavke);

        jLabel1.setText("Proizvod za pretragu stavki: ");

        btnPretrazi.setText("Pretrazi");
        btnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziActionPerformed(evt);
            }
        });

        jrbVratiSve.setText("Vrati Sve Stavke");
        jrbVratiSve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbVratiSveActionPerformed(evt);
            }
        });

        jrbPretraziPoProizvodu.setText("PretraziPoProizvodu");
        jrbPretraziPoProizvodu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbPretraziPoProizvoduActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbVratiSve)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbProizvodi, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPretrazi))
                            .addComponent(jrbPretraziPoProizvodu))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrbPretraziPoProizvodu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPretrazi)
                    .addComponent(cmbProizvodi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jrbVratiSve)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
         try {
            Proizvod proizvod = (Proizvod) cmbProizvodi.getSelectedItem();
            List<StavkaNarudzbenice> stavke = Controller.getInstance().vratiStavkeNarudzbenicePoProizvodu(proizvod);
            initTable(stavke);
        } catch (Exception ex) {
            Logger.getLogger(ProizvodView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPretraziActionPerformed

    private void jrbVratiSveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbVratiSveActionPerformed
        initForm();
        
    }//GEN-LAST:event_jrbVratiSveActionPerformed

    private void jrbPretraziPoProizvoduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbPretraziPoProizvoduActionPerformed
         jrbVratiSve.setSelected(false);
        btnPretrazi.setEnabled(true);
        cmbProizvodi.setEnabled(true);
    }//GEN-LAST:event_jrbPretraziPoProizvoduActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StavkaNarudzbeniceView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StavkaNarudzbeniceView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StavkaNarudzbeniceView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StavkaNarudzbeniceView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StavkaNarudzbeniceView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JComboBox cmbProizvodi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrbPretraziPoProizvodu;
    private javax.swing.JRadioButton jrbVratiSve;
    private javax.swing.JTable tableStavke;
    // End of variables declaration//GEN-END:variables

    private void initForm() {
        jrbPretraziPoProizvodu.setSelected(false);
        jrbVratiSve.setSelected(true);
        cmbProizvodi.setEnabled(false);
        btnPretrazi.setEnabled(false);
        try {
            List<StavkaNarudzbenice> stavke = Controller.getInstance().vratiStavkeNarudzbenice();
            initTable(stavke);
        } catch (Exception ex) {
            Logger.getLogger(StavkaNarudzbeniceView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTable(List<StavkaNarudzbenice> stavke) {
         DefaultTableModel dtm = (DefaultTableModel) tableStavke.getModel();
        removeAllRows(dtm);
            for (StavkaNarudzbenice sn : stavke) {
                Object [] row = new Object[4];
                row[0] = sn.getNarudzbenica().getSifranarudzbenice();
                row[1] = sn.getRbrstavke();
                row[2] = sn.getProizvod().getNazivproizvoda();
                row[3] = sn.getJm().getNazivjm();
                
                
                dtm.addRow(row);
            }
    }
     private void removeAllRows(DefaultTableModel dtm) {
           dtm.setRowCount(0);
    }

    private void CBProizvod() {
    
        try {
            cmbProizvodi.removeAllItems();
            List<Proizvod> proizvodi = Controller.getInstance().vratiProizvode();
            for (Proizvod p : proizvodi) {
                cmbProizvodi.addItem(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(StavkaNarudzbeniceView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
