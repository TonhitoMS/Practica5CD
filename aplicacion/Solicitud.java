/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @author pablo
 */
public class Solicitud implements Serializable{
    
    private String usuario1;
    private String usuario2;
    private Date fecha;

    public Solicitud(String usuario1, String usuario2, Date fecha) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.fecha = fecha;
    }

    public String getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    public String getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(String usuario2) {
        this.usuario2 = usuario2;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
