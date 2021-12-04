/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import java.util.ArrayList;

/**
 * 
 * @author
 */
public class Amigo {
    
    private Peer amigo;
    private ArrayList<ArrayList<String>> mensajes;  // mensajes que envie yo mismo
    private ArrayList<ArrayList<String>> horas;
    private boolean nuevoMensaje;

    public Amigo(Peer amigo) {
        this.amigo = amigo;
        this.mensajes = new ArrayList<>();
        this.horas = new ArrayList<>();    
        this.nuevoMensaje = false;
    }

    public Peer getAmigo() {
        return amigo;
    }

    public void setAmigo(Peer amigo) {
        this.amigo = amigo;
    }

    public ArrayList<ArrayList<String>> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<ArrayList<String>> mensajes) {
        this.mensajes = mensajes;
    }

    public ArrayList<ArrayList<String>> getHoras() {
        return horas;
    }

    public void setHoras(ArrayList<ArrayList<String>> horas) {
        this.horas = horas;
    }

    public boolean isNuevoMensaje() {
        return nuevoMensaje;
    }

    public void setNuevoMensaje(boolean nuevoMensaje) {
        this.nuevoMensaje = nuevoMensaje;
    }
    
    

    

    
    
    
    
}
