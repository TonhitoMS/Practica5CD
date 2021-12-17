/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import Cliente.Peer;
import Cliente.Solicitud;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.*;

public class ModeloTablaSolicitudes extends AbstractTableModel{
    private ArrayList<Solicitud> solicitudes;


    public ModeloTablaSolicitudes(){
        this.solicitudes = new ArrayList<>();
    }
    
    public int getColumnCount (){
        return 2;
    }

    public int getRowCount(){
        return this.solicitudes.size();
    }
    

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Usuario"; break;
            case 1: nombre= "Fecha"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado= this.solicitudes.get(row).getUsuario1(); break;
            case 1: resultado= this.solicitudes.get(row).getFecha().toString(); break;
        }
        return resultado;
    } 

    public void setFilas(ArrayList<Solicitud> solicitudes){
        this.solicitudes = solicitudes;
        fireTableDataChanged();
    }

    public Solicitud obtenerNombre(int i){
        return this.solicitudes.get(i);
    }

}
