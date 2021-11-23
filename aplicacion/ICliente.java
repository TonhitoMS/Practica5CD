

package aplicacion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface ICliente extends Remote{
    
    public void message(String s) throws RemoteException;
    
    // Metodo para mandar a un cliente las referencias al resto de clientes conectados
    public void notifyMe(ArrayList<ICliente> usuarios) 
            throws java.rmi.RemoteException;
    
}
