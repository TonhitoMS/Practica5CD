/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.RSA;
import aplicacion.ServerImpl;
import aplicacion.Solicitud;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/**
 *
 * @author antonio
 */
public class DAOServidor extends AbstractDAO {
    
    private RSA rsa;
    
    public DAOServidor(Connection conexion) {
        super.setConexion(conexion);
        rsa = new RSA();
        try {
            rsa.openFromDiskPrivateKey("C:\\Users\\pablo\\AppData\\Local\\Temp\\rsa.pri");
        } catch (IOException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> obterAmigos(String nome, String clave){
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
    
    public String iniciarSesion(String nome, String clave){
        Connection con;
        PreparedStatement stmAmigos = null;
        ResultSet rsAmigos;
        String result = null;
        
        String claveBD = "";
        try {
            claveBD = rsa.Decrypt(clave);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DAOServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmAmigos = con.prepareStatement("select id_cliente "
                    + "from Cliente "
                    + "where id_cliente = ? "
                    + "and clave = ?");
            stmAmigos.setString(1, nome);
            stmAmigos.setString(2, this.encript(claveBD));
            rsAmigos = stmAmigos.executeQuery();
            if (rsAmigos.next()) {
                result = rsAmigos.getString("id_cliente");
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
    
    public ArrayList<Solicitud> obterSolicitudes(String nome, String clave){
        Connection con;
        PreparedStatement stmAmigos = null;
        ResultSet rsAmigos;
        ArrayList<Solicitud> Amigos = new ArrayList();
        
        String claveBD = "";
        try {
            claveBD = rsa.Decrypt(clave);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DAOServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(!this.Autentificación(nome, claveBD))
            return Amigos;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmAmigos = con.prepareStatement("select id_cliente1, id_cliente2, fecha "
                    + "from Solicitude "
                    + "where id_cliente2 = ? ");
            stmAmigos.setString(1, nome);
            rsAmigos = stmAmigos.executeQuery();
            while (rsAmigos.next()) {
                Amigos.add(new Solicitud(rsAmigos.getString("id_cliente1"), rsAmigos.getString("id_cliente2"), 
                rsAmigos.getDate("fecha")));
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
        
        String claveBD = "";
        try {
            claveBD = rsa.Decrypt(clave);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DAOServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmCliente = con.prepareStatement("insert into cliente values(?, ?)");
            stmCliente.setString(1, nome);
            stmCliente.setString(2, this.encript(claveBD));
            
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
    
    public void novoAmigo(String nome1, String nome2, String clave){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
//        String claveBD = "";
//        try {
//            claveBD = rsa.Decrypt(clave);
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
//                IllegalBlockSizeException | BadPaddingException ex) {
//            Logger.getLogger(DAOServidor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//         if(!this.Autentificación(nome1, clave))
//            return;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(false);
            stmCliente = con.prepareStatement("insert into SerAmigo values(?, ?)");
            stmCliente.setString(1, nome1);
            stmCliente.setString(2, nome2);
            
            stmCliente.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            if (con != null) {
                try {
                    con.rollback();   // Deshacer operaciones
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        } finally {
            try {
                stmCliente.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
    
    public void novaSolicitude(String nome1, String nome2, String clave){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
         String claveBD = "";
        try {
            claveBD = rsa.Decrypt(clave);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DAOServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(!this.Autentificación(nome1, claveBD))
            return;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmCliente = con.prepareStatement("insert into Solicitude values(?, ?, NOW())");
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

        
    public void aceptaSolicitude(String nome1, String nome2, String clave){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
         String claveBD = "";
        try {
            claveBD = rsa.Decrypt(clave);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DAOServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(!this.Autentificación(nome1, claveBD))
            return;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(false);
            stmCliente = con.prepareStatement("delete from Solicitude "
                    + "where id_cliente2 = ? "
                    + "and id_cliente1 = ?");
            stmCliente.setString(1, nome1);
            stmCliente.setString(2, nome2);
            System.out.println(stmCliente);

            stmCliente.executeUpdate();
            this.novoAmigo(nome1, nome2, clave);
            
            con.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
//            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            if (con != null) {
                try {
                    con.rollback();   // Deshacer operaciones
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        } finally {
            try {
                stmCliente.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
       
    }
    
    public void borrarSolicitude(String nome1, String nome2, String clave){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
         String claveBD = "";
        try {
            claveBD = rsa.Decrypt(clave);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DAOServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(!this.Autentificación(nome1, claveBD))
            return;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmCliente = con.prepareStatement("delete from Solicitude "
                    + "where id_cliente2 = ? "
                    + "and id_cliente1 = ?");
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
    
    public String desencript(String encriptado) {
        int i, ascii;
        char[] codigo = new char[encriptado.length()];

        codigo[0] = encriptado.charAt(0);

        for (i = 1; i < encriptado.length(); i++) {
            ascii = ((encriptado.charAt(i) + 128 - codigo[i - 1])) % 128;
            codigo[i] = (char) ascii;
        }

        return String.valueOf(codigo);
    }

    public String encript(String codigo) {
        int i, ascii;
        char[] encriptado = new char[codigo.length()];

        encriptado[0] = codigo.charAt(0);

        for (i = 1; i < codigo.length(); i++) {
            ascii = ((int) codigo.charAt(i) + (int) codigo.charAt(i - 1)) % 128;
            encriptado[i] = (char) ascii;
        }

        return String.valueOf(encriptado);
    }
    
    

    
    public void modificarCliente(String nome, String clave, String claveNova){
        Connection con;
        PreparedStatement stmCliente = null;
        ResultSet rsAmigos;
        
        String claveBD = "";
        String claveNovaBD = "";
        try {
            claveBD = rsa.Decrypt(clave);
            claveNovaBD = rsa.Decrypt(claveNova);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DAOServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         if(!this.Autentificación(nome, claveBD))
            return;
        con = super.getConexion();
        
        try {
            con.setAutoCommit(true);
            stmCliente = con.prepareStatement("update Cliente "
                    + "set clave = ? "
                    + "where id_cliente = ?");
            stmCliente.setString(1, this.encript(claveNovaBD));
            stmCliente.setString(2, nome);

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

    
    
    
    public Boolean Autentificación(String nome, String clave){
        Boolean result = false;
        Connection con;
        PreparedStatement stmAmigos = null;
        ResultSet rsAmigos;
        ArrayList<Solicitud> Amigos = new ArrayList();
        String nomeCliente;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmAmigos = con.prepareStatement("select id_cliente "
                    + "from Cliente "
                    + "where clave = ? "
                    + "and id_cliente = ?");
            stmAmigos.setString(1, this.encript(clave));
            stmAmigos.setString(2, nome);
            rsAmigos = stmAmigos.executeQuery();
            if (rsAmigos.next()) {
//                nomeCliente = rsAmigos.getString("id_cliente");
//                if(nomeCliente.equals(nome)){
//                    return true;
//                }
                System.out.println(rsAmigos.getString("id_cliente"));
                return true;
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
    
    public Boolean existeCliente(String nome){
        Boolean result = false;
        Connection con;
        PreparedStatement stmAmigos = null;
        ResultSet rsAmigos;
        
        con = super.getConexion();
        try {
            con.setAutoCommit(true);
            stmAmigos = con.prepareStatement("select id_cliente "
                    + "from Cliente ");
            stmAmigos.setString(1, nome);
            rsAmigos = stmAmigos.executeQuery();
            if (rsAmigos.next()) {
                System.out.println(rsAmigos.getString("id_cliente"));
                return true;
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

    public Boolean comprobarAmigo(String nome1, String nome2, String clave){
        Boolean result = false;
        
        
        
        return result;
    }
}




