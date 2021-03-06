/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Servidor.IServidor;
import Cliente.RSA;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VRegistro extends javax.swing.JFrame {
    
    private IServidor h;
    private RSA rsa;
    /**
     * Creates new form VRegistro
     */
    public VRegistro(IServidor h) {
        
        this.h = h;
        try {
            this.rsa = this.h.obterClave();
        } catch (Exception ex) {
            Logger.getLogger(VRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setVisible(true);
        
        setLocationRelativeTo(null);  // localizar el JFRame en el centro de la pantalla

        initComponents();
        
        this.setTitle("Ventana registro");
        
        textoNoCoinciden.setVisible(false);
        textoRellenar.setVisible(false);
        textoYaExiste.setVisible(false);
        
        // Confirmacion por si cerramos la ventana
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textoNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textoPassword2 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        textoPassword1 = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        textoNoCoinciden = new javax.swing.JLabel();
        textoRellenar = new javax.swing.JLabel();
        textoYaExiste = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() & ~java.awt.Font.BOLD, jLabel1.getFont().getSize()+24));
        jLabel1.setText("Registro");

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getSize()+2f));
        jLabel4.setText("Nombre");

        textoNombre.setMinimumSize(new java.awt.Dimension(14, 27));
        textoNombre.setPreferredSize(new java.awt.Dimension(14, 27));
        textoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNombreActionPerformed(evt);
            }
        });

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getSize()+2f));
        jLabel5.setText("Repetir contrase??a");

        textoPassword2.setMinimumSize(new java.awt.Dimension(14, 27));
        textoPassword2.setPreferredSize(new java.awt.Dimension(14, 27));

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getSize()+2f));
        jLabel6.setText("Contrase??a");

        textoPassword1.setMinimumSize(new java.awt.Dimension(14, 27));
        textoPassword1.setPreferredSize(new java.awt.Dimension(14, 27));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        textoNoCoinciden.setFont(textoNoCoinciden.getFont().deriveFont((float)12));
        textoNoCoinciden.setForeground(new java.awt.Color(255, 0, 0));
        textoNoCoinciden.setText("Las contrase??as no coinciden");

        textoRellenar.setFont(textoRellenar.getFont().deriveFont((float)12));
        textoRellenar.setForeground(new java.awt.Color(255, 0, 0));
        textoRellenar.setText("Rellena todos los campos.");

        textoYaExiste.setFont(textoYaExiste.getFont().deriveFont((float)12));
        textoYaExiste.setForeground(new java.awt.Color(255, 0, 0));
        textoYaExiste.setText("El usuario ya existe");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textoPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAceptar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel6)
                                        .addGap(100, 100, 100)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoNoCoinciden)
                                    .addComponent(jLabel5)
                                    .addComponent(textoPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoRellenar)
                                    .addComponent(textoYaExiste)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(textoYaExiste)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoRellenar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoPassword1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(textoNoCoinciden)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoPassword2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAceptar)
                    .addComponent(btnSalir))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoNombreActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            // TODO add your handling code here:
            // a??adir un nuevo usuario
            // a la base de datos, y volvemos a la ventana de registro
            if(!textoNombre.getText().isEmpty() && !String.valueOf(textoPassword1.getPassword()).isEmpty() && !String.valueOf(textoPassword2.getPassword()).isEmpty()){
                
                if(coincidirPasswords(String.valueOf(textoPassword1.getPassword()), String.valueOf(textoPassword2.getPassword()))){
                    if(!this.h.existeCliente(textoNombre.getText())){
                        this.h.novoCliente(textoNombre.getText(), rsa.Encrypt(String.valueOf(textoPassword1.getPassword())));
                
                        this.dispose();
                    }
                    else{
                        textoNoCoinciden.setVisible(false);
                        textoRellenar.setVisible(false);
                        textoYaExiste.setVisible(true);
                    }
                }
                else{
                    textoNoCoinciden.setVisible(true);
                    textoRellenar.setVisible(false);
                    textoYaExiste.setVisible(false);
                }
            }
            else{
                textoRellenar.setVisible(true);
                textoNoCoinciden.setVisible(false);
                textoYaExiste.setVisible(false);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(VRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed
    
    private boolean coincidirPasswords(String pass1, String pass2){
        return pass1.equals(pass2);
    }
    
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel textoNoCoinciden;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JPasswordField textoPassword1;
    private javax.swing.JPasswordField textoPassword2;
    private javax.swing.JLabel textoRellenar;
    private javax.swing.JLabel textoYaExiste;
    // End of variables declaration//GEN-END:variables
}
