/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import java.rmi.*;

import java.util.ArrayList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IServidor extends Remote{

    public String sayHello( )   
        throws java.rmi.RemoteException;
   
    
    public void registerForCallback(Peer p) 
            throws java.rmi.RemoteException;


    public void unregisterForCallback(Peer p)
    throws java.rmi.RemoteException;
    
    public ArrayList<String> obterAmigos(String nome)
            throws java.rmi.RemoteException;
    
    public String iniciarSesion(String nome, String contrasinal)
            throws java.rmi.RemoteException;
    
    public ArrayList<Solicitud> obterSolicitudes(String nome)
            throws java.rmi.RemoteException;
        
        
    public void novoCliente(String nome, String clave)
            throws java.rmi.RemoteException;
    
    public void novoAmigo(String nome1, String nome2)
            throws java.rmi.RemoteException;
    
    
    public void novaSolicitude(String nome1, String nome2)
            throws java.rmi.RemoteException;

        
    public void aceptaSolicitude(String nome1, String nome2)
            throws java.rmi.RemoteException;
    
    public void borrarSolicitude(String nome1, String nome2)
            throws java.rmi.RemoteException;
}
