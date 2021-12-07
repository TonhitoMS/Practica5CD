/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aplicacion.Amigo;
import aplicacion.ClienteImpl;
import aplicacion.ICliente;
import aplicacion.IServidor;
import aplicacion.PaqueteChat;
import aplicacion.Peer;
import aplicacion.RSA;
import java.awt.Panel;
import java.awt.Rectangle;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.PlainDocument;
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
    private RSA rsa;
    
    private String username;
    private String password;
    
    private ArrayList<Peer> listaUsuarios;
    private ArrayList<Amigo> amigos;
    
    private Peer peer;
    private Peer peerActual;
    private Amigo amigoActual;
    
    private boolean nuevaSolicitud;
    
    /**
     * Creates new form VCliente
     * @param password
     * @param h
     * @param username
     */
    public VCliente(String username, String password, IServidor h) {

        this.username = username;
        this.password = password;
        this.h = h;
        try {
            this.rsa = this.h.obterClave();
        } catch (Exception ex) {
            Logger.getLogger(VCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.amigos = new ArrayList<>();
        
        this.setVisible(true);
        
        initComponents();
        
        txtNuevaSolicitud.setVisible(false);
        
        TablaUsuarios.getSelectionModel().clearSelection();
        
        textoHola.setText("Hola, "+this.username);
        
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
        
        // evento click de la tabla
        TablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TablaUsuarios.rowAtPoint(evt.getPoint());
                int col = TablaUsuarios.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                   
                    String nombre =  (String) TablaUsuarios.getValueAt(row, col);
                
                    if(cambiarAmigoActual(nombre)){
                        if(amigoActual.isNuevoMensaje()){
                            amigoActual.setNuevoMensaje(false);
                            TablaUsuarios.setDefaultRenderer(TablaUsuarios.getColumnClass(0), new CustomTableRenderer(amigos));
                            TablaUsuarios.repaint();
                        }
                        panelMensajes.setText("");
                        
                        panelMensajes.setText("CHAT DE "+amigoActual.getAmigo().getNombre()+"\n\n");
                        
                        if(!amigoActual.getMensajes().isEmpty()){
                            for(int i=0; i<amigoActual.getMensajes().size(); i++){
                                imprimirMensaje(amigoActual.getMensajes().get(i).get(1), amigoActual.getMensajes().get(i).get(0));
                                imprimirHora(amigoActual.getHoras().get(i).get(1), amigoActual.getHoras().get(i).get(0));
                            }
                        }
                    }
                  
                }
            }
        });
        
        // comprobamos se temos solicitudes pendentes
        try{
            if(!this.h.obterSolicitudes(this.username, rsa.Encrypt(this.password)).isEmpty()){
                txtNuevaSolicitud.setVisible(true);
            }
        } catch(Exception ex){
            System.out.println("Excepcion: " +ex);
        }
        
        // Iniciamos el cliente
        startClient();
    }
    
    
    private boolean cambiarAmigoActual(String nombre){
        if(this.amigoActual == null || !this.amigoActual.getAmigo().getNombre().equals(nombre)){
            for(Amigo amigo : this.amigos){
                if(amigo.getAmigo().getNombre().equals(nombre)){
                    this.amigoActual = amigo;
                    return true;
                }
            }
        }
        return false;
    }
    
    private void startClient(){
        
        try {
            //Código para rexistrar o peer no servidor nas probas iniciais
            this.IC = new ClienteImpl(this);
            this.peer = new Peer(username, IC);
            
            System.out.println(h.sayHello());
    //          System.out.println(peer.getCl());
    
            this.h.registerForCallback(peer);
            
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un problema al conectarse al servidor.");
            System.exit(0);
        }
        
    }


    public ArrayList<Amigo> setListaUsuarios(ArrayList<Peer> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        System.out.println("Lo que manda el server: " +listaUsuarios);
        // añadimos los peer a la lista de amigos
        
        ArrayList<Amigo> amigosPeer = new ArrayList<>();
        ArrayList<Amigo> eliminar = new ArrayList<>();
        
        
        for(Peer peer : this.listaUsuarios){
            
            amigosPeer.add(new Amigo(peer));
        }
        //this.amigos.clear();
        if(listaUsuarios.size() > this.amigos.size()){
            for(Peer peer : this.listaUsuarios){
            
                Amigo temp = new Amigo(peer);
                
                if(!this.amigos.contains(temp)){
                    this.amigos.add(temp);
                }
            }
        }
        else if(listaUsuarios.size() < this.amigos.size()){
            for(Amigo amigo1 : this.amigos){
                if(!amigosPeer.contains(amigo1)){
                    eliminar.add(amigo1);
                }
            }     
        }
           
        this.amigos.removeAll(eliminar); 
         
        System.out.println("lista de amigos: "+this.amigos);
        
        return this.amigos;
    }
    
    
    // funcion para enviar un mensaje a otro peer
    private void enviarMensaje(){
        try {
            // TODO add your handling code here:
            if(TablaUsuarios.getSelectedRow() != -1){
                
                int row = TablaUsuarios.getSelectedRow();
                int col = TablaUsuarios.getSelectedColumn();
                
                String mensaje = textoEnviar.getText();
                
                if(mensaje.isEmpty()){
                    JOptionPane.showMessageDialog(null, "El mensaje está vacío.");
                }
                else{
                
                    StringBuilder nuevoMensaje = new StringBuilder(mensaje);

                    int i = 0;
                    while ((i = nuevoMensaje.indexOf(" ", i + 35)) != -1) {
                        nuevoMensaje.replace(i, i + 1, "\n");
                    }


                    amigoActual.getAmigo().getCl().message(nuevoMensaje.toString(), peer);
                    amigoActual.getAmigo().getCl().nuevoMensaje(peer);  // notificamos a nuestro amigo de que recibio un nuevo mensaje
                    amigoActual.getMensajes().add(new ArrayList<>(Arrays.asList(nuevoMensaje.toString(), "derecha")));  // añadimos el mensaje al array de mensajes
                    imprimirMensaje("derecha", nuevoMensaje.toString());

                    amigoActual.getHoras().add(new ArrayList<>(Arrays.asList(obtenerHora(), "derecha")));
                    imprimirHora("derecha", obtenerHora());

                    textoEnviar.setText("");
                }
                
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
        textoHola = new javax.swing.JLabel();
        textoEnviar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelMensajes = new javax.swing.JTextPane();
        btnEnviar = new javax.swing.JButton();
        txtNuevaSolicitud = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnCambiarContrasena = new javax.swing.JMenuItem();
        btnCerrarSesion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnVerSolicitudes = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaUsuarios.setModel(new ModeloTablaUsuarios());
        TablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaUsuarios);

        textoHola.setFont(textoHola.getFont().deriveFont(textoHola.getFont().getStyle() & ~java.awt.Font.BOLD, textoHola.getFont().getSize()+14));
        textoHola.setText("Hola, ");

        panelMensajes.setEditable(false);
        panelMensajes.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                panelMensajesInputMethodTextChanged(evt);
            }
        });
        jScrollPane3.setViewportView(panelMensajes);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        txtNuevaSolicitud.setFont(txtNuevaSolicitud.getFont().deriveFont((float)13));
        txtNuevaSolicitud.setForeground(new java.awt.Color(0, 150, 255));
        txtNuevaSolicitud.setText("Tienes nuevas solicitudes de amistad!");

        jMenu1.setText("Usuario");

        btnCambiarContrasena.setText("Cambiar contraseña");
        btnCambiarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContrasenaActionPerformed(evt);
            }
        });
        jMenu1.add(btnCambiarContrasena);

        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(btnCerrarSesion);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Solicitudes");

        btnVerSolicitudes.setText("Ver solicitudes");
        btnVerSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerSolicitudesActionPerformed(evt);
            }
        });
        jMenu2.add(btnVerSolicitudes);

        jMenuItem1.setText("Añadir amigo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textoHola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(txtNuevaSolicitud))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnviar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(1, 1, 1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoHola, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNuevaSolicitud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEnviar)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviarMensaje();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnVerSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerSolicitudesActionPerformed
        // TODO add your handling code here:
        VSolicitudes solicitudes = new VSolicitudes(this.username, this.password, this.h, this);
    }//GEN-LAST:event_btnVerSolicitudesActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        try {
            h.unregisterForCallback(peer);
        } catch (RemoteException ex) {
            Logger.getLogger(VCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.exit(0);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        String nombreAmigo = JOptionPane.showInputDialog(null, "Introduzca el nombre del usuario: ");
        
        try{
            if(nombreAmigo != null && !this.h.comprobarAmigos(this.username, nombreAmigo, rsa.Encrypt(this.password)) && this.h.existeCliente(nombreAmigo) && !this.h.existeSolicitude(this.username, nombreAmigo) && !this.h.existeSolicitude(nombreAmigo, this.username)){
                //this.h.novoAmigo(this.username, nombreAmigo, this.password);
                if(!nombreAmigo.equals(this.username)){
                    this.h.novaSolicitude(this.username, nombreAmigo, rsa.Encrypt(this.password));
                }
                else{
                    nuevoAviso("No te puedes agregar a ti mismo como amigo.");
                }
            }
            else if(!this.h.existeCliente(nombreAmigo) && nombreAmigo != null){
                System.out.println(this.h.existeCliente(nombreAmigo));
                nuevoAviso("El usuario introducido no existe.");
            }
            else if(this.h.existeSolicitude(this.username, nombreAmigo)){
                nuevoAviso("El usuario todavía tiene tu solicitud pendiente.");
            }
            else if(this.h.existeSolicitude(nombreAmigo, this.username)){
                nuevoAviso("Ya tienes una solicitud pendiente de ese usuario.");
            }
            else if(nombreAmigo != null){
                nuevoAviso("Ya eres amigo de ese usuario.");
            }
        } catch (Exception ex) {
            Logger.getLogger(VCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void TablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaUsuariosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaUsuariosMouseClicked

    private void panelMensajesInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_panelMensajesInputMethodTextChanged
        // TODO add your handling code here:
        int height = (int)this.panelMensajes.getPreferredSize().getHeight();
        Rectangle rect = new Rectangle(0,height,10,10);
        this.panelMensajes.scrollRectToVisible(rect);
    }//GEN-LAST:event_panelMensajesInputMethodTextChanged

    private void btnCambiarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContrasenaActionPerformed
        // TODO add your handling code here:
        VCambioContra cambioContra = new VCambioContra(this.username, this.password, this.h, this);
    }//GEN-LAST:event_btnCambiarContrasenaActionPerformed
    
    public void imprimirMensaje(String align, String mensaje){
        
        try{
            StyledDocument doc = panelMensajes.getStyledDocument();
            
            SimpleAttributeSet set = new SimpleAttributeSet();
            if(align.equals("derecha")){
                StyleConstants.setAlignment(set, StyleConstants.ALIGN_RIGHT);
            }
            if(align.equals("izquierda")){
                StyleConstants.setAlignment(set, StyleConstants.ALIGN_LEFT);
            }
            
            mensaje.trim();
            ArrayList<String> listaMensajes = new ArrayList<String>(Arrays.asList(mensaje.split("\n")));
            
            for(String s : listaMensajes){
                int length = doc.getLength();
                doc.insertString(doc.getLength(), s + "\n", null);
                doc.setParagraphAttributes(length+1, 1, set, false);
            }
            
        } catch(Exception e){
            System.out.println("Excepction: " +e);
        }       
    }
    
    public void imprimirHora(String align, String hora){
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
            doc.insertString(doc.getLength(), hora + "\n", null);
            doc.setParagraphAttributes(length+1, 1, set, false);
            
        } catch(Exception e){
            System.out.println("Excepction: " +e);
        }      
    }
    
    public String obtenerHora(){
        
        Formatter formate = new Formatter();
        // Creating a calendar
        Calendar gfg_calender = Calendar.getInstance();

        // '%tl' for hours and '%tM' for minutes
        formate.format("%tl:%tM", gfg_calender, gfg_calender);
        
        return formate.toString();
    }
    
    // funcion para comprobar so hay colicitudes pendientes
    public Boolean comprobarSolicitudes(){
        try {
            return !this.h.obterSolicitudes(this.username, rsa.Encrypt(this.password)).isEmpty();
        } catch (Exception ex){
            System.out.println("Excepcion: " +ex);
        }
        return false;
    } 
    
    // Funcion para que aparezca por pantalla un aviso si introducimos algo incorrectamente
    public void nuevoAviso(String message){
        JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public ArrayList<Amigo> getAmigos() {
        return amigos;
    }
    
    
    public JTable getTablaUsuarios() {
        return TablaUsuarios;
    }  

    public JTextPane getPanelMensajes() {
        return panelMensajes;
    }


    public JLabel getTxtNuevaSolicitud() {
        return txtNuevaSolicitud;
    }
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaUsuarios;
    private javax.swing.JMenuItem btnCambiarContrasena;
    private javax.swing.JMenuItem btnCerrarSesion;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JMenuItem btnVerSolicitudes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane panelMensajes;
    private javax.swing.JTextField textoEnviar;
    private javax.swing.JLabel textoHola;
    private javax.swing.JLabel txtNuevaSolicitud;
    // End of variables declaration//GEN-END:variables
}
