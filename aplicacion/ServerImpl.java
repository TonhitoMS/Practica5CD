/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ServerImpl implements IServidor{

    public String sayHello( ) throws java.rmi.RemoteException {
        return("hello");
    }
    
}
