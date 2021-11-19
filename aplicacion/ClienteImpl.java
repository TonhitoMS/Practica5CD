/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.rmi.RemoteException;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ClienteImpl implements ICliente{

    @Override
    public void message(String s) throws RemoteException {
        System.out.println(s + "\n");
    }

}
