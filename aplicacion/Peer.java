/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author antonio
 */
public class Peer {
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
    
    

    public Peer(String nombre, String ip, int puerto) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
    
    
}
