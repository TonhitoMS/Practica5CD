/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

/**
 *
 * @author basesdatos
 */
public abstract class AbstractDAO {
   private java.sql.Connection conexion;

   
    protected java.sql.Connection getConexion(){
        return this.conexion;
    }
    
    protected void setConexion(java.sql.Connection conexion){
        this.conexion=conexion;
    }
   
   
}
