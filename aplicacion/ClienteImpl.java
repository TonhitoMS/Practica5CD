/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import gui.VCliente;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.server.UnicastRemoteObject;

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
        System.out.println(s + "\n");
    }
    
    @Override
    public void notifyMe(ArrayList<Peer> usuarios){
        
        this.usuarios = usuarios;
        System.out.println(usuarios);
        //c.setUsuarios(usuarios);  // Pasamos la lista al cliente
        
        // Actualizamos la tabla de los usuarios
        actualizarTablaUsuarios(usuarios);
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
