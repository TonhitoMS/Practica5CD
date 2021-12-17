/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import Cliente.FachadaAplicacion;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FachadaGui {
    
    VCliente cliente;
    FachadaAplicacion fa;
    
    public FachadaGui(FachadaAplicacion fa){
        this.fa = fa;
//        try {
//            // Set System L&F
//            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.setLookAndFeel(new FlatDarkLaf());
//            UIManager.put( "TabbedPane.showTabSeparators", true );
//        }
//        catch (Exception e) {
//           e.printStackTrace();
//        }
    }
    
    public void nuevoAviso(String message){
        JOptionPane.showMessageDialog(cliente, message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}
