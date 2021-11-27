/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import gui.ModeloTablaUsuarios;
import gui.VCliente;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.DefaultListModel;
import javax.swing.JSeparator;
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
public class ClienteImpl extends UnicastRemoteObject implements ICliente {
    
    private ArrayList<Peer> usuarios;
    private VCliente c;
    
    
    public ClienteImpl(VCliente c) throws RemoteException {
        super();
        this.c = c;
    }
    
    @Override
    public void message(String s) throws RemoteException {
        try{
            StyledDocument doc = c.getPanelMensajes().getStyledDocument();

            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setAlignment(set, StyleConstants.ALIGN_LEFT);
            
            int length = doc.getLength();
            doc.insertString(doc.getLength(), s + "\n", null);
            doc.setParagraphAttributes(length+1, 1, set, false);
            
            // imprimimos la hora a la que recibimos el mensaje
            c.imprimirHora("izquierda");
            
        } catch(Exception e){
            System.out.println("Excepction: " +e);
        }
    }
    
    @Override
    public void notifyMe(ArrayList<Peer> usuarios){
        
        this.usuarios = usuarios;
        System.out.println(usuarios);
        //c.setUsuarios(usuarios);  // Pasamos la lista al cliente
        
        // Actualizamos la tabla de los usuarios
        actualizarTablaUsuarios(usuarios);
        
        c.setListaUsuarios(usuarios);
    } 
    
    public Peer getPeer(String nome){
        Peer result = null;
        
        for(Peer p: this.usuarios){
            if(p.getNombre().equals(nome))
                return p;
        }
        
        return result;
    }
    
    private void actualizarTablaUsuarios(ArrayList<Peer> usuarios){
        ModeloTablaUsuarios m = new ModeloTablaUsuarios();
        
        c.getTablaUsuarios().setModel(m);
        
        m.setFilas(usuarios);
    }

}
