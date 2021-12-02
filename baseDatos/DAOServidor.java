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
                    + "where id_cliente1 = ? "
                    + "union "
                    + "select id_cliente1 "
                    + "from SerAmigo "
                    + "where id_cliente2 = ? ");
            stmAmigos.setString(1, nome);
            stmAmigos.setString(2, nome);
            rsAmigos = stmAmigos.executeQuery();
            while (rsAmigos.next()) {
                Amigos.add(rsAmigos.getString("id_cliente2"));
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
    
    public String iniciarSesion(String nome, String contrasinal){
        Connection con;
        PreparedStatement stmAmigos = null;
        ResultSet rsAmigos;
        String result = null;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmAmigos = con.prepareStatement("select id_cliente "
                    + "from Cliente "
                    + "where id_cliente = ? "
                    + "and clave = ?");
            stmAmigos.setString(1, nome);
            stmAmigos.setString(2, contrasinal);
            rsAmigos = stmAmigos.executeQuery();
            if (rsAmigos.next()) {
                result = rsAmigos.getString("id_cliente1");
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
        return result;
    }
    
    public ArrayList<String> obterSolicitudes(String nome){
        Connection con;
        PreparedStatement stmAmigos = null;
        ResultSet rsAmigos;
        ArrayList<String> Amigos = new ArrayList();
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmAmigos = con.prepareStatement("select id_cliente1 "
                    + "from Solicitude "
                    + "where id_cliente2 = ? ");
            stmAmigos.setString(1, nome);
            rsAmigos = stmAmigos.executeQuery();
            while (rsAmigos.next()) {
                Amigos.add(rsAmigos.getString("id_cliente2"));
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
        
        
    public void novoCliente(String nome, String clave){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmCliente = con.prepareStatement("insert into cliente values(?, ?)");
            stmCliente.setString(1, nome);
            stmCliente.setString(2, clave);
            
            stmCliente.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCliente.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
    public void novoAmigo(String nome1, String nome2){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmCliente = con.prepareStatement("insert into SerAmigo values(?, ?)");
            stmCliente.setString(1, nome1);
            stmCliente.setString(2, nome2);
            
            stmCliente.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCliente.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
    
    public void novaSolicitude(String nome1, String nome2){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmCliente = con.prepareStatement("insert into Soliciude values(?, ?, ?)");
            stmCliente.setString(1, nome1);
            stmCliente.setString(2, nome2);
            stmCliente.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            stmCliente.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCliente.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

        
    public void aceptaSolicitude(String nome1, String nome2){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmCliente = con.prepareStatement("delete from Soliciude "
                    + "where id_usuario2 = ? "
                    + "and id_usuario1 = ?");
            stmCliente.setString(1, nome1);
            stmCliente.setString(2, nome2);

            stmCliente.executeUpdate();
            this.novoAmigo(nome1, nome2);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmCliente.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    
    




}




