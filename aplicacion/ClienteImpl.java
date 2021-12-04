/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import gui.CustomTableRenderer;
import gui.ModeloTablaUsuarios;
import gui.VCliente;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
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
    public void message(String s, Peer user) throws RemoteException {
        try{
            
            for(Amigo amigo : c.getAmigos()){
                if(amigo.getAmigo().equals(user)){
                    if(c.getTablaUsuarios().getSelectedRow() != -1 && c.getTablaUsuarios().getValueAt(c.getTablaUsuarios().getSelectedRow(), c.getTablaUsuarios().getSelectedColumn()).equals(amigo.getAmigo().getNombre())){
                        amigo.getMensajes().add(new ArrayList<>(Arrays.asList(s, "izquierda")));
                        amigo.getHoras().add(new ArrayList<>(Arrays.asList(c.obtenerHora(), "izquierda")));
                        
                        c.imprimirMensaje("izquierda", s);
                        c.imprimirHora("izquierda", c.obtenerHora());
                    }
                    else{
                        amigo.getMensajes().add(new ArrayList<>(Arrays.asList(s, "izquierda")));
                        amigo.getHoras().add(new ArrayList<>(Arrays.asList(c.obtenerHora(), "izquierda")));     
                    }
                }
            }
            
//            StyledDocument doc = c.getPanelMensajes().getStyledDocument();
//
//            SimpleAttributeSet set = new SimpleAttributeSet();
//            StyleConstants.setAlignment(set, StyleConstants.ALIGN_LEFT);
//            
//            int length = doc.getLength();
//            doc.insertString(doc.getLength(), s + "\n", null);
//            doc.setParagraphAttributes(length+1, 1, set, false);
//            
//            // imprimimos la hora a la que recibimos el mensaje
//            c.imprimirHora("izquierda", c.obtenerHora());
            
        } catch(Exception e){
            System.out.println("Excepction: " +e);
        }
    }
    
    @Override
    public void nuevoMensaje(Peer user){
        
        Amigo amigo1 = null;
        
        for(Amigo amigo : c.getAmigos()){
            if(amigo.getAmigo().equals(user)){
                if(c.getTablaUsuarios().getSelectedRow() == -1 || !c.getTablaUsuarios().getValueAt(c.getTablaUsuarios().getSelectedRow(), c.getTablaUsuarios().getSelectedColumn()).equals(amigo.getAmigo().getNombre())){
                    amigo.setNuevoMensaje(true);
                }
            }
        }
        
        c.getTablaUsuarios().setDefaultRenderer(c.getTablaUsuarios().getColumnClass(0), new CustomTableRenderer(c.getAmigos()));

        c.getTablaUsuarios().repaint();
   
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
