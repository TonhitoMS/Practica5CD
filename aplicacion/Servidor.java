/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import baseDatos.FachadaBaseDatos;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the object server for a distributed
 * object of class Callback, which implements the remote 
 * interface CallbackInterface.
 * @author M. L. Liu
 */

public class Servidor  {
    
    public static void main(String args[]) {
        
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        
        String portNum, registryURL;
                
        try{     
            System.out.println("Enter the RMIregistry port number:");
            portNum = (br.readLine()).trim();
            int RMIPortNum = Integer.parseInt(portNum);
            
            startRegistry(RMIPortNum);
            
            ServerImpl exportedObj = new ServerImpl();
            
            String hostname = InetAddress.getLocalHost().getHostName();
            
            registryURL = "rmi://" + hostname + ":" + portNum + "/callback";
            Naming.rebind(registryURL, exportedObj);
            
            System.out.println(registryURL);
            System.out.println("Server ready.");
            
            FachadaAplicacion fa = new FachadaAplicacion();
        }
        catch (Exception re) {
            re.printStackTrace();
        } 
    } // end main

  //This method starts a RMI registry on the local host, if
  //it does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum) throws RemoteException{
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list( );  
            // This call will throw an exception
            // if the registry does not already exist
        }
        catch (RemoteException e) { 
          // No valid registry at that port.
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
        }
    } // end startRegistry

} // end class
