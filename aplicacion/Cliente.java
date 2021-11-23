/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;
import java.util.ArrayList;



public class Cliente {
    
    private ArrayList<ICliente> usuarios;
    private ICliente callbackObj;
    
    public static void main(String args[]) {
        try {
          int RMIPort;         
          String hostName;
          String PeerId;
          InputStreamReader is = 
            new InputStreamReader(System.in);
          BufferedReader br = new BufferedReader(is);
          System.out.println(
            "Enter the RMIRegistry host namer:");
          hostName = br.readLine();
          System.out.println(
            "Enter the RMIregistry port number:");
          String portNum = br.readLine();
          RMIPort = Integer.parseInt(portNum);
          String registryURL = 
            "rmi://localhost:" + portNum + "/callback";
          IServidor h =
            (IServidor)Naming.lookup(registryURL);
          System.out.println("Lookup completed " );          
          br = new BufferedReader(is);
          System.out.println(
            "Introduce o nome do usuario:");
          PeerId = br.readLine();
          
          } // end try 
        catch (Exception e) {
          System.out.println(
            "Exception in CallbackClient: " + e);
        } 
    }

    public void setUsuarios(ArrayList<ICliente> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}