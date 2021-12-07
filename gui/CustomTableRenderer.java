/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import aplicacion.Amigo;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * @author pablo antonio
 */
public class CustomTableRenderer extends DefaultTableCellRenderer{
    
    private ArrayList<Amigo> nombres;

    public CustomTableRenderer(ArrayList<Amigo> nombres) {
        this.nombres = nombres;
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        ModeloTablaUsuarios model = (ModeloTablaUsuarios) table.getModel();
        String nombre = model.obtenerNombre(row);
        
        if(this.nombres.get(row).isNuevoMensaje()){
            c.setForeground(Color.GREEN);
        }
        else{
            c.setForeground(Color.BLACK);
        }

        return c;
    }
}
