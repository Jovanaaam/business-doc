/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.horizontalnoparticinoisanje;

import controller.Controller;
import domain.Cena;
import forms.cena.CenaView;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Korisnik
 */
public class HorizontalnoParticionisanjeView extends javax.swing.JFrame {

    /**
     * Creates new form HorizontalnoParticionisanjeView
     */
    public HorizontalnoParticionisanjeView() {
        initComponents();
        initFroms();
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
        tableNizeCene = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableViseCene = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableNizeCene.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Sifra dobavljaca", "Datum promene cene", "Sifra proizvoda", "Naziv proizvoda", "Trenutna cena"
            }
        ));
        jScrollPane1.setViewportView(tableNizeCene);

        jLabel1.setText("Cena - nize cene:");

        jLabel2.setText("Cena - vise cene: ");

        tableViseCene.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Sifra dobavljaca", "Datum promene cene", "Sifra proizvoda", "Naziv proizvoda", "Nabavna cena"
            }
        ));
        jScrollPane2.setViewportView(tableViseCene);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(HorizontalnoParticionisanjeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HorizontalnoParticionisanjeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HorizontalnoParticionisanjeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HorizontalnoParticionisanjeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HorizontalnoParticionisanjeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableNizeCene;
    private javax.swing.JTable tableViseCene;
    // End of variables declaration//GEN-END:variables

    private void initFroms() {
        try {
            List<Cena> nizeCene = Controller.getInstance().vratiCeneParticija("nizecene");
            srediTabeluNizihCena(nizeCene);
            List<Cena> viseCene = Controller.getInstance().vratiCeneParticija("visecene");
            srediTabeluVisihCena(viseCene);
        } catch (Exception ex) {
            Logger.getLogger(CenaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void srediTabeluNizihCena(List<Cena> nizeCene) {
          DefaultTableModel dtm = (DefaultTableModel) tableNizeCene.getModel();
        removeAllRows(dtm);
               for (Cena c : nizeCene) {
                Object [] row = new Object[5];
                row[0] = c.getDobavljac().getSifradobavljaca();
                
                row[1] = c.getDatumpromenecene();
                row[2] = c.getProizvod().getSifraproizvoda();
                  row[3] = c.getNazivproizvoda();
                row[4] = c.getNabavnacena();
              
                
                
                dtm.addRow(row);
            }
    }

    private void srediTabeluVisihCena(List<Cena> viseCene) {
         DefaultTableModel dtm = (DefaultTableModel) tableViseCene.getModel();
        removeAllRows(dtm);
               for (Cena c : viseCene) {
                Object [] row = new Object[5];
                row[0] = c.getDobavljac().getSifradobavljaca();
                
                row[1] = c.getDatumpromenecene();
                row[2] = c.getProizvod().getSifraproizvoda();
                  row[3] = c.getNazivproizvoda();
                row[4] = c.getNabavnacena();
              
                
                
                dtm.addRow(row);
            }
    }

    private void removeAllRows(DefaultTableModel dtm) {
         dtm.setRowCount(0);
    }
}
