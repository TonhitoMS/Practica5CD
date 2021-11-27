/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aplicacion.ClienteImpl;
import aplicacion.ICliente;
import aplicacion.IServidor;
import aplicacion.PaqueteChat;
import aplicacion.Peer;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VCliente extends javax.swing.JFrame {
    
    aplicacion.FachadaAplicacion fa;
    
    private IServidor h;
    private ICliente IC;
    
    private String hostName;
    private String portNum;
    private String username;
    
    private ArrayList<Peer> listaUsuarios;
    private Peer peer;
    private Peer peerActual;
    
    /**
     * Creates new form VCliente
     * @param fa
     * @param hostName
     * @param portNum
     * @param username
     */
    public VCliente(aplicacion.FachadaAplicacion fa, String hostName, String portNum, String username) {
        this.fa=fa;
        this.hostName = hostName;
        this.portNum = portNum;
        this.username = username;
        
        this.setVisible(true);
        
        initComponents();
        
        panelMensajes.setText("CHAT\n\n");
        
        textoEnviar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    enviarMensaje();
                }
            }

        });
        
        setLocationRelativeTo(null);  // localizar el JFRame en el centro de la pantalla
        
        this.setTitle("P2P-App");
        
        // Confirmacion por si cerramos la ventana
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(null,
                    "Quieres cerrar la aplicación?", "Confirmación de salida: ",
                    JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION){
                    
                    try {
                        h.unregisterForCallback(peer);
                    } catch (RemoteException ex) {
                        Logger.getLogger(VCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    System.exit(0);
                }
                else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        
        // Iniciamos el cliente
        startClient();
    }
    
    private void startClient(){
        
        try {
            String registryURL = "rmi://" +this.hostName+ ":" + this.portNum + "/callback";
            
            this.h = (IServidor)Naming.lookup(registryURL);
            
            System.out.println("Lookup completed " );
            
            //Código para rexistrar o peer no servidor nas probas iniciais
            this.IC = new ClienteImpl(this);
            this.peer = new Peer(username, IC);
            
            System.out.println(h.sayHello());
    //          System.out.println(peer.getCl());
    
            this.h.registerForCallback(peer);
            
        } catch(Exception e){
            System.out.println("Exception: "+ e);
            JOptionPane.showMessageDialog(null, "Hubo un problema al conectarse al servidor.");
            System.exit(0);
        }
        
    }

    public JTable getTablaUsuarios() {
        return TablaUsuarios;
    }  

    public JTextPane getPanelMensajes() {
        return panelMensajes;
    }

//    public JList<PaqueteChat> getListaTexto() {
//        return listaTexto;
//    }

    public void setListaUsuarios(ArrayList<Peer> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    // funcion para enviar un mensaje a otro peer
    private void enviarMensaje(){
        try {
            // TODO add your handling code here:
            if(TablaUsuarios.getSelectedRow() != -1){
                
                int row = TablaUsuarios.getSelectedRow();
                int col = TablaUsuarios.getSelectedColumn();
                String nombre =  (String) TablaUsuarios.getValueAt(row, col);
                
                if(peerActual == null || !peerActual.getNombre().equals(nombre)){
                    for(Peer peer : this.listaUsuarios){
                        if(peer.getNombre().equals(nombre)){
                            peerActual = peer;
                        }
                    }
                }

                peerActual.getCl().message(textoEnviar.getText());
                
                imprimirMensaje(textoEnviar.getText());
                imprimirHora("derecha");
                
                textoEnviar.setText("");
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar una persona a la que enviar el mensaje.");
            }
            
        } catch (RemoteException ex) {
            Logger.getLogger(VCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        textoEnviar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelMensajes = new javax.swing.JTextPane();
        btnEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaUsuarios.setModel(new ModeloTablaUsuarios());
        jScrollPane1.setViewportView(TablaUsuarios);

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() & ~java.awt.Font.BOLD, jLabel2.getFont().getSize()+14));
        jLabel2.setText("Whatsapp");

        jScrollPane3.setViewportView(panelMensajes);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnviar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(1, 1, 1))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEnviar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviarMensaje();
    }//GEN-LAST:event_btnEnviarActionPerformed
    
    private void imprimirMensaje(String mensaje){
        
        try{
            StyledDocument doc = panelMensajes.getStyledDocument();

            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setAlignment(set, StyleConstants.ALIGN_RIGHT);
            
            int length = doc.getLength();
            doc.insertString(doc.getLength(), mensaje + "\n", null);
            doc.setParagraphAttributes(length+1, 1, set, false);
            
        } catch(Exception e){
            System.out.println("Excepction: " +e);
        }       
    }
    
    public void imprimirHora(String align){
        try{
            StyledDocument doc = panelMensajes.getStyledDocument();

            SimpleAttributeSet set = new SimpleAttributeSet();
            if(align.equals("derecha")){
                StyleConstants.setAlignment(set, StyleConstants.ALIGN_RIGHT);
            }
            if(align.equals("izquierda")){
                StyleConstants.setAlignment(set, StyleConstants.ALIGN_LEFT);
            }
            
            StyleConstants.setFontSize(set, 8);
            
            int length = doc.getLength();
            doc.insertString(doc.getLength(), obtenerHora() + "\n", null);
            doc.setParagraphAttributes(length+1, 1, set, false);
            
        } catch(Exception e){
            System.out.println("Excepction: " +e);
        }      
    }
    
    private String obtenerHora(){
        
        Formatter formate = new Formatter();
        // Creating a calendar
        Calendar gfg_calender = Calendar.getInstance();

        // '%tl' for hours and '%tM' for minutes
        formate.format("%tl:%tM", gfg_calender, gfg_calender);
        
        return formate.toString();
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaUsuarios;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane panelMensajes;
    private javax.swing.JTextField textoEnviar;
    // End of variables declaration//GEN-END:variables
}
