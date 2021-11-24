/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ClienteImpl extends UnicastRemoteObject implements ICliente {
    
    private ArrayList<Peer> usuarios;
    //private Cliente c;
    
    
    public ClienteImpl(/*Cliente c*/) throws RemoteException {
        super( );
        //this.c = c;
    }
    
    @Override
    public void message(String s) throws RemoteException {
        System.out.println(s + "\n");
    }
    
    @Override
    public void notifyMe(ArrayList<Peer> usuarios){
        
        this.usuarios = usuarios;
        //c.setUsuarios(usuarios);  // Pasamos la lista al cliente
    } 
    
    public Peer getPeer(String nome){
        Peer result = null;
        
        for(Peer p: this.usuarios){
            if(p.getNombre().equals(nome))
                return p;
        }
        
        return result;
    }

}
