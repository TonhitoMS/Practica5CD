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
        
        
//        DefaultListModel model = new DefaultListModel();
//        
//        
//        String nombreFecha = String.format("%-10s %40s", "pablo", "26/11/2021");
//            //String paquete = nombreFecha + "hola me llamo tonto";
//        
//        PaqueteChat paquete = new PaqueteChat(nombreFecha, "hola soy tonto");
//            
//        model.addElement(paquete);
//        //model.addElement(new JSeparator());
//        //model.addElement(new JSeparator());
//
//        c.getListaTexto().setModel(model);
//        c.getListaTexto().setCellRenderer(new ListCellRenderer());
    }

}
