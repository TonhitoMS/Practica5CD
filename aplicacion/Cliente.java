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
    
    private ArrayList<Peer> usuarios;
    //private ICliente callbackObj;
    
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
          //Código para rexistrar o peer no servidor nas probas iniciais
          ICliente IC = new ClienteImpl(null);
          Peer peer = new Peer(PeerId, IC);
          System.out.println(h.sayHello());
//          System.out.println(peer.getCl());
          h.registerForCallback(peer);//cambiar método para que pida un obxecto Peer e devolva amigos conectados
          //crear método que actualice os amigos conectados cada vez que se conecta un.
          //cambiar atributo da ServerImpl para que sexa un ArrayList de obxecto Peer(id e referencia)
          
            while(true);
          
          } // end try 
        catch (Exception e) {
          System.out.println(
            "Exception in CallbackClient: " + e);
        } 
    }

    public void setUsuarios(ArrayList<Peer> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}