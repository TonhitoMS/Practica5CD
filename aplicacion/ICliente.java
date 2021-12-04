    

package aplicacion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface ICliente extends Remote{
    
    public void message(String s, Peer user) throws RemoteException;
    
    // Metodo para mandar a un cliente las referencias al resto de clientes conectados
    public void notifyMe(ArrayList<Peer> usuarios) 
            throws java.rmi.RemoteException;
    
    public Peer getPeer(String nome) throws java.rmi.RemoteException;
    
    public void nuevoMensaje(Peer user) throws java.rmi.RemoteException;

}
