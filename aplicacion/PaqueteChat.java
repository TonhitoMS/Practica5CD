/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PaqueteChat {

    private String nombre;
    private String mensaje;

    
    public PaqueteChat(String nombre, String mensaje) {
        this.nombre = nombre;
        this.mensaje = mensaje;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
