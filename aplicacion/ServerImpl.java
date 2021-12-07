/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import baseDatos.FachadaBaseDatos;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ServerImpl extends UnicastRemoteObject implements IServidor{
    private FachadaBaseDatos fbd;
    
    private ArrayList<Peer> clientList;

    public ServerImpl() throws RemoteException {
        super( );
        clientList = new ArrayList<>();
        fbd = new FachadaBaseDatos();
    }
    
    @Override
    public String sayHello( ) throws java.rmi.RemoteException {
        return("hello");
    }
    
    @Override
    public synchronized void registerForCallback(
    Peer p)
    throws java.rmi.RemoteException{
        // store the callback object into the vector        
        if (!(clientList.contains(p))) {
            clientList.add(p);
            
            System.out.println("Registered new client ");
            
            doCallbacks();
        } // end if
    }  

    @Override
    public synchronized void unregisterForCallback(
    Peer p) 
    throws java.rmi.RemoteException{

        if (clientList.remove(p)) {
            
            System.out.println("Unregistered client ");
            
            doCallbacks();
        } else {
            
            System.out.println("unregister: clientwasn't registered.");
        }
    } 
    
    @Override
    public void actualizarListaAmigos() throws java.rmi.RemoteException{
        doCallbacks();
    }
    
    // funcion para realizar las callbacks
    private synchronized void doCallbacks() throws java.rmi.RemoteException{
    // make callback to each registered client
        System.out.println(
           "**************************************\n"
            + "Callbacks initiated ---");

        for (int i = 0; i < clientList.size(); i++){
            System.out.println("doing "+ i +"-th callback\n");    
            // convert the vector object to a callback object
            Peer nextClient = (Peer)clientList.get(i);
            
            // creamos la lista para enviar al cliente, quitandole a el mismo de la lista
            ArrayList<Peer> newClientList = new ArrayList<>();
            
            for (Peer p : clientList){
                if(!p.equals(nextClient)){
                    newClientList.add(p);
                }
            }
            // mandamos la lista de usuarios conectados al cliente
            nextClient.getCl().notifyMe(obterAmigos(nextClient.getNombre(), "ola"));
            System.out.println(nextClient.getNombre());
            System.out.println(this.obterAmigos(nextClient.getNombre(), "ola"));
//            
        }// end for
        System.out.println("********************************\n" +
                           "Server completed callbacks ---");
    } // doCallbacks

    @Override
    public ArrayList<Peer> obterAmigos(String nome, String clave) throws RemoteException {
        ArrayList<String> amigos = fbd.obterAmigos(nome, clave);
        ArrayList<Peer> result = new ArrayList();
        
        
        ArrayList<Peer> newClientList = new ArrayList<>();
            
       
        for (Peer  p: clientList){
            if(!p.getNombre().equals(nome)){
                newClientList.add(p);
            }
        }
        
        System.out.println(newClientList);
        System.out.println(amigos);
        
        for(String s: amigos){
            for(Peer p: newClientList){
                if(p.getNombre().equals(s))
                    result.add(p);
            }
        }
        return result;
    }

    @Override
    public String iniciarSesion(String nome, String clave) throws RemoteException {
        return(fbd.iniciarSesion(nome, clave));
    }

    @Override
    public ArrayList<Solicitud> obterSolicitudes(String nome, String clave) throws RemoteException {
        return(fbd.obterSolicitudes(nome, clave));
    }

    @Override
    public void novoCliente(String nome, String clave) throws RemoteException {
        fbd.novoCliente(nome, clave);
    }

    @Override
    public void novoAmigo(String nome1, String nome2, String clave) throws RemoteException {
        fbd.novoAmigo(nome1, nome2, clave);
    }

    @Override
    public void novaSolicitude(String nome1, String nome2, String clave) throws RemoteException {
        fbd.novaSolicitude(nome1, nome2, clave);
    }

    @Override
    public void aceptaSolicitude(String nome1, String nome2, String clave) throws RemoteException {
        fbd.aceptaSolicitude(nome1, nome2, clave);
    }

    @Override
    public void borrarSolicitude(String nome1, String nome2, String clave) throws RemoteException {
        fbd.borrarSolicitude(nome1, nome2, clave);
    }
    @Override
    public void modificarCliente(String nome, String clave, String claveNova) throws RemoteException{
        fbd.modificarCliente(nome, clave, claveNova);
    }

    @Override
    public RSA obterClave() throws RemoteException {
        RSA rsa = new RSA();
        try {
            rsa.openFromDiskPublicKey("C:\\Users\\pablo\\AppData\\Local\\Temp\\rsa.pub");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsa;
    }
    

    @Override
    public Boolean existeCliente(String nome) throws RemoteException {
        return fbd.existeCliente(nome);    
    }
    
}
