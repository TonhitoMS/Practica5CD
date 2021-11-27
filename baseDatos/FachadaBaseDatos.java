/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOServidor daoServidor;
    
    // constructor
    public FachadaBaseDatos (aplicacion.FachadaAplicacion fa){
        
        Properties configuracion = new Properties();
        this.fa=fa;
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
            daoServidor = new DAOServidor(conexion, fa);
            System.out.println(daoServidor.obterAmigos("antonio"));
            


        } catch (IOException | java.sql.SQLException e){
            // Manejo de los errores de conexión a la bd
            System.out.println(e.getMessage());
            fa.nuevoAviso("No se ha podido establecer una conexión con la base de datos. Se cerrará el programa.\nInfo del error: " + e.getMessage());
            System.exit(1);
        }
        
        
        
    }
    
    
}
