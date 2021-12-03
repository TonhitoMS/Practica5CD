/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FachadaAplicacion {
    
    private FachadaBaseDatos fbd;
    private FachadaGui fgui;

    
    public FachadaAplicacion(){
        fgui = new FachadaGui(this);
        fbd = new FachadaBaseDatos(this);
    }
    
    public void nuevoAviso(String mensaje){
        fgui.nuevoAviso(mensaje);
    }
    

}
