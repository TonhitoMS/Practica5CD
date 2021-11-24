/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ServerImpl extends UnicastRemoteObject implements IServidor{

    
    private ArrayList<Peer> clientList;

    public ServerImpl() throws RemoteException {
        super( );
        clientList = new ArrayList<>();
    }
    
    @Override
    public String sayHello( ) throws java.rmi.RemoteException {
        return("hello");
    }
    
    @Override
    public synchronized void registerForCallback(
    String nome,
    ICliente callbackClientObject)
    throws java.rmi.RemoteException{
        Peer p = new Peer(nome, callbackClientObject);
        // store the callback object into the vector        
        if (!(clientList.contains(p))) {
            clientList.add(p);
            
            System.out.println("Registered new client ");
            
            doCallbacks();
        } // end if
    }  

    @Override
    public synchronized void unregisterForCallback(
    String nome,
    ICliente callbackClientObject) 
    throws java.rmi.RemoteException{
        Peer p = new Peer(nome, callbackClientObject);

        if (clientList.remove(p)) {
            
            System.out.println("Unregistered client ");
            
            doCallbacks();
        } else {
            
            System.out.println("unregister: clientwasn't registered.");
        }
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
            
            // mandamos la lista de usuarios conectados al cliente
            nextClient.getCl().notifyMe(clientList);
            
        }// end for
        System.out.println("********************************\n" +
                           "Server completed callbacks ---");
    } // doCallbacks
    
}
