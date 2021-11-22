/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ServerImpl extends UnicastRemoteObject implements IServidor{

    @Override
    public String sayHello( ) throws java.rmi.RemoteException {
        return("hello");
    }
    
}
