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
    public synchronized void message(String s, Peer user) throws RemoteException {
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
            
            //System.out.println(c.getAmigos());
            
        } catch(Exception e){
            System.out.println("Excepction: " +e);
        }
    }
    
    @Override
    public synchronized void nuevoMensaje(Peer user){
        
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
    public void nuevaSolicitud(){
        this.c.getTxtNuevaSolicitud().setVisible(true);
    }
    
    @Override
    public synchronized void notifyMe(ArrayList<Peer> usuarios){
        
        this.usuarios = usuarios;
        //System.out.println(usuarios);
        //c.setUsuarios(usuarios);  // Pasamos la lista al cliente
        
        // Actualizamos la tabla de los usuarios
        
        ArrayList<Amigo> amigos = c.setListaUsuarios(usuarios);
        
        actualizarTablaUsuarios(amigos);
    } 
    
    private void actualizarTablaUsuarios(ArrayList<Amigo> usuarios){
        ModeloTablaUsuarios m = new ModeloTablaUsuarios();
        
        c.getTablaUsuarios().setModel(m);
        
        m.setFilas(usuarios);
    }

}
