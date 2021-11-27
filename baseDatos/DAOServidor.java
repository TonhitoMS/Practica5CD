/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author antonio
 */
public class DAOServidor extends AbstractDAO {
    
    public DAOServidor(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public ArrayList<String> obterAmigos(String nome){
        Connection con;
        PreparedStatement stmAmigos = null;
        ResultSet rsAmigos;
        ArrayList<String> Amigos = new ArrayList();
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmAmigos = con.prepareStatement("select id_cliente2 "
                    + "from SerAmigo "
                    + "where id_cliente1 = ?");
            stmAmigos.setString(1, nome);
            rsAmigos = stmAmigos.executeQuery();
            System.out.println("inicio");
            while (rsAmigos.next()) {
//                empresaActual = new Empresa(rsAmigos.getString("id_usuario"), rsAmigos.getString("nombre"));
                Amigos.add(rsAmigos.getString("id_cliente2"));
                  //System.out.println(rsAmigos.getString("id_cliente2"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmAmigos.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return Amigos;
    }
}
