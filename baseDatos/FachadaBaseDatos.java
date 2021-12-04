/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Solicitud;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FachadaBaseDatos {
    private java.sql.Connection conexion;
    private DAOServidor daoServidor;
    
    // constructor
    public FachadaBaseDatos (){
        
        Properties configuracion = new Properties();
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("./src/baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
            
            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));

            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);
            daoServidor = new DAOServidor(conexion);
            daoServidor.modificarCliente("pablo", "o[M", "ola");
            daoServidor.modificarCliente("pepe", "o[M", "ola");
            daoServidor.modificarCliente("lucas", "o[M", "ola");


        } catch (IOException | java.sql.SQLException e){
            // Manejo de los errores de conexión a la bd
            System.out.println(e.getMessage());
            System.out.println("No se ha podido establecer una conexión con la base de datos. Se cerrará el programa.\nInfo del error: " + e.getMessage());
            System.exit(1);
        }
    }
        
    public ArrayList<String> obterAmigos(String nome, String clave){
     return(daoServidor.obterAmigos(nome, clave));
        
    }
    
    public String iniciarSesion(String nome, String contrasinal){
        return(daoServidor.iniciarSesion(nome, contrasinal));
    }
    
    public ArrayList<Solicitud> obterSolicitudes(String nome, String clave){
        return(daoServidor.obterSolicitudes(nome, clave));
    }
        
        
    public void novoCliente(String nome, String clave){
        daoServidor.novoCliente(nome, clave);
    }
    
    public void novoAmigo(String nome1, String nome2){
        daoServidor.novoAmigo(nome1, nome2);
    }
    
    
    public void novaSolicitude(String nome1, String nome2, String clave){
        daoServidor.novaSolicitude(nome1, nome2, clave);
    }

        
    public void aceptaSolicitude(String nome1, String nome2, String clave){
        daoServidor.aceptaSolicitude(nome1, nome2, clave);
    }
    
    public void borrarSolicitude(String nome1, String nome2, String clave){
        daoServidor.borrarSolicitude(nome1, nome2, clave);
    }
        
    public void modificarCliente(String nome, String clave, String claveNova){//actualizar clave que almacena o servidor
        daoServidor.modificarCliente(nome, clave, claveNova);
    }
        
    
    
    
}
