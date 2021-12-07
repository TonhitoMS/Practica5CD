/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aplicacion.IServidor;
import aplicacion.Peer;
import aplicacion.RSA;
import aplicacion.Solicitud;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo Lago Poza
 */
public class VSolicitudes extends javax.swing.JFrame {
    
    private String username;
    private String password;
    private IServidor h;
    private VCliente c;
    private RSA rsa;
    /**
     * Creates new form VSolicitudes
     */
    public VSolicitudes(String username, String password, IServidor h, VCliente c) {
        
        this.username = username;
        this.password = password;
        this.h = h;
        this.c = c;
        try {
            this.rsa = this.h.obterClave();
        } catch (Exception ex) {
            Logger.getLogger(VSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setVisible(true);
        
        setLocationRelativeTo(null);  // localizar el JFRame en el centro de la pantalla
        
        initComponents();
        
        this.setTitle("Ventana solicitudes");
        
        try {
            actualizarTablaSolicitudes(this.h.obterSolicitudes(this.username, rsa.Encrypt(this.password)));
            
        } catch (Exception ex) {
            Logger.getLogger(VSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Confirmacion por si cerramos la ventana
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        
        this.c.getTxtNuevaSolicitud().setVisible(false);
    }
    
    
    private void actualizarTablaSolicitudes(ArrayList<Solicitud> solicitudes){
        ModeloTablaSolicitudes m = new ModeloTablaSolicitudes();
        
        tablaSolicitudes.setModel(m);
        
        m.setFilas(solicitudes);
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
        jTable1 = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaSolicitudes = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        btnRechazar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tablaSolicitudes.setModel(new ModeloTablaSolicitudes());
        jScrollPane2.setViewportView(tablaSolicitudes);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnRechazar.setText("Rechazar");
        btnRechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechazarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addComponent(btnRechazar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnAceptar)
                    .addComponent(btnRechazar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if(tablaSolicitudes.getSelectedRow() != -1){
                
            int row = tablaSolicitudes.getSelectedRow();
            
            try {
                System.out.println((String)tablaSolicitudes.getValueAt(row, 0));
                System.out.println((String)tablaSolicitudes.getValueAt(row, 1));
                this.h.aceptaSolicitude(this.username, (String)tablaSolicitudes.getValueAt(row, 0), rsa.Encrypt(this.password));
                
                actualizarTablaSolicitudes(this.h.obterSolicitudes(this.username, rsa.Encrypt(this.password)));
                
                this.h.actualizarListaAmigos();
                
            } catch (Exception ex) {
                Logger.getLogger(VSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud.");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazarActionPerformed
        // TODO add your handling code here:
        if(tablaSolicitudes.getSelectedRow() != -1){
                
            int row = tablaSolicitudes.getSelectedRow();
            
            try {
                this.h.borrarSolicitude(this.username, (String)tablaSolicitudes.getValueAt(row, 0), rsa.Encrypt(this.password));
                
                actualizarTablaSolicitudes(this.h.obterSolicitudes(this.username, rsa.Encrypt(this.password)));
                
            } catch (Exception ex) {
                Logger.getLogger(VSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud.");
        }
    }//GEN-LAST:event_btnRechazarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnRechazar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tablaSolicitudes;
    // End of variables declaration//GEN-END:variables
}
