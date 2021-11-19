

package aplicacion;

import java.rmi.Remote;
import java.rmi.RemoteException;



/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface ICliente extends Remote{
    
    public void message(String s) throws RemoteException;
    
    
    
}
