/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import Cliente.Amigo;
import Cliente.Peer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.*;

public class ModeloTablaUsuarios extends AbstractTableModel{
    private ArrayList<Amigo> usuarios;


    public ModeloTablaUsuarios(){
        this.usuarios = new ArrayList<>();
    }
    
    public int getColumnCount (){
        return 1;
    }

    public int getRowCount(){
        return this.usuarios.size();
    }
    

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Usuario"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
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
            case 0: resultado= this.usuarios.get(row).getAmigo().getNombre(); break;
        }
        return resultado;
    } 

    public void setFilas(ArrayList<Amigo> usuarios){
        this.usuarios = usuarios;
        fireTableDataChanged();
    }

    public String obtenerNombre(int i){
        return this.usuarios.get(i).getAmigo().getNombre();
    }

}
