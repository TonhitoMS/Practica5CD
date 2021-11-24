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
   
    
    public void registerForCallback(
    String nome,
    ICliente callbackClientObject
    ) throws java.rmi.RemoteException;


    public void unregisterForCallback(
    String nome,
    ICliente callbackClientObject)
    throws java.rmi.RemoteException;
}
