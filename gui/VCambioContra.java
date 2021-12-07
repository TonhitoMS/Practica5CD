/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aplicacion.IServidor;
import aplicacion.RSA;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VCambioContra extends javax.swing.JFrame {
    
    
    private String username;
    private String password;
    private IServidor h;
    private VCliente c;
    private RSA rsa;
    /**
     * Creates new form VCambioContra
     */
    public VCambioContra(String username, String password, IServidor h, VCliente c) {
        
        this.username = username;
        this.password = password;
        this.h = h;
        this.c = c;
        try {
            this.rsa = this.h.obterClave();
        } catch (Exception ex) {
            Logger.getLogger(VRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setVisible(true);
        
        setLocationRelativeTo(null);  // localizar el JFRame en el centro de la pantalla
        
        initComponents();
        
        textoRellenar.setVisible(false);
        textoNoCoinciden.setVisible(false);
        textoContraActual.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textoPassword1 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textoPassword3 = new javax.swing.JPasswordField();
        btnSalir1 = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        textoRellenar = new javax.swing.JLabel();
        textoNoCoinciden = new javax.swing.JLabel();
        textoContraActual = new javax.swing.JLabel();
        textoPassword2 = new javax.swing.JPasswordField();

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() & ~java.awt.Font.BOLD, jLabel1.getFont().getSize()+24));
        jLabel1.setText("Cambio contraseña");

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getSize()+2f));
        jLabel6.setText("Repita contraseña nueva");

        textoPassword1.setMinimumSize(new java.awt.Dimension(14, 27));
        textoPassword1.setPreferredSize(new java.awt.Dimension(14, 27));

        jLabel7.setFont(jLabel7.getFont().deriveFont(jLabel7.getFont().getSize()+2f));
        jLabel7.setText("Contraseña actual");

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getSize()+2f));
        jLabel8.setText("Contraseña nueva");

        textoPassword3.setMinimumSize(new java.awt.Dimension(14, 27));
        textoPassword3.setPreferredSize(new java.awt.Dimension(14, 27));

        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        textoRellenar.setFont(textoRellenar.getFont().deriveFont((float)12));
        textoRellenar.setForeground(new java.awt.Color(255, 0, 0));
        textoRellenar.setText("Rellena todos los campos.");

        textoNoCoinciden.setFont(textoNoCoinciden.getFont().deriveFont((float)12));
        textoNoCoinciden.setForeground(new java.awt.Color(255, 0, 0));
        textoNoCoinciden.setText("Las contraseñas no coinciden");

        textoContraActual.setFont(textoContraActual.getFont().deriveFont((float)12));
        textoContraActual.setForeground(new java.awt.Color(255, 0, 0));
        textoContraActual.setText("Contraseña actual incorrecta");

        textoPassword2.setMinimumSize(new java.awt.Dimension(14, 27));
        textoPassword2.setPreferredSize(new java.awt.Dimension(14, 27));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(textoContraActual)
                            .addComponent(textoPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoRellenar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textoNoCoinciden)
                                        .addComponent(textoPassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnAceptar)))
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(14, 14, 14)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel7)
                        .addGap(7, 7, 7)
                        .addComponent(textoPassword1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoRellenar)
                            .addComponent(textoContraActual))
                        .addGap(17, 17, 17)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoPassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(textoNoCoinciden)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir1)
                    .addComponent(btnAceptar)
                    .addComponent(textoPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            // TODO add your handling code here:
            // añadir un nuevo usuario
            // a la base de datos, y volvemos a la ventana de registro
            if(!String.valueOf(textoPassword1.getPassword()).isEmpty() && !String.valueOf(textoPassword2.getPassword()).isEmpty() && !String.valueOf(textoPassword3.getPassword()).isEmpty()){

                if(coincidirPasswords(String.valueOf(textoPassword2.getPassword()), String.valueOf(textoPassword3.getPassword()))){
                    this.h.modificarCliente(this.username, rsa.Encrypt(this.password), rsa.Encrypt(String.valueOf(textoPassword2.getPassword())));

                    this.dispose();
                }
                else if(!String.valueOf(textoPassword1.getPassword()).equals(this.password)){
                    textoContraActual.setVisible(true);
                    textoNoCoinciden.setVisible(false);
                    textoRellenar.setVisible(false);
                }
                else{
                    textoNoCoinciden.setVisible(true);
                    textoRellenar.setVisible(false);
                    textoContraActual.setVisible(false);
                }
            }
            else{
                textoRellenar.setVisible(true);
                textoNoCoinciden.setVisible(false);
                textoContraActual.setVisible(false);
            }

        } catch (Exception ex) {
            Logger.getLogger(VRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private boolean coincidirPasswords(String pass1, String pass2){
        return pass1.equals(pass2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel textoContraActual;
    private javax.swing.JLabel textoNoCoinciden;
    private javax.swing.JPasswordField textoPassword1;
    private javax.swing.JPasswordField textoPassword2;
    private javax.swing.JPasswordField textoPassword3;
    private javax.swing.JLabel textoRellenar;
    // End of variables declaration//GEN-END:variables
}