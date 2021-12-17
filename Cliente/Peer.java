/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author antonio
 */
public class Peer implements Serializable{
    private String nombre;
    private ICliente cl;

    public Peer(String nombre, ICliente cl) {
        this.nombre = nombre;
        this.cl = cl;
    }

    public ICliente getCl() {
        return cl;
    }

    public void setCl(ICliente cl) {
        this.cl = cl;
    }
    
    


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Peer other = (Peer) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
  
    
    
}
