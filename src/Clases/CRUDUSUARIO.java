/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Database;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.*;
import java.time.Instant;

/**
 *
 * @author usuario
 */
public class CRUDUSUARIO {
    public ArrayList<Usuario> Listar_Usuario(){
        ArrayList<Usuario> list = new ArrayList<Usuario>();
        Database cn = new Database();
        //String sql = "SELECT u.rut, u.pass, u.nombre, u.a_paterno, u.a_materno, u.fecha_Ingreso, u.mail,u.fono, u.habilitado, u.nacionalidad, t.nombre, d.nombre from usuario u INNER JOIN Tipo_Usuario t ON u.id_tipousuario = t.id_tipousuario INNER JOIN unidad d on u.id_unidad = d.id_unidad ";
       String sql ="SELECT * FROM usuario";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                Usuario usu = new Usuario();
                usu.setRut(lista.getString(1));
                usu.setPass(lista.getString(2));
                usu.setNombre(lista.getString(3)); 
                usu.setPaterno(lista.getString(4));
                usu.setMaterno(lista.getString(5));
                usu.setFechaIngreso(lista.getDate(6));
                usu.setCorreo(lista.getString(7));
                usu.setFono(lista.getString(8));
                usu.setHabilitado(lista.getInt(9));
                usu.setNacionalidad(lista.getString(10));
                usu.setId_tipoUsuario(lista.getInt(11));
                usu.setId_Unidad(lista.getInt(12));
                usu.setDias(lista.getInt(13));
 
                
                list.add(usu);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                lista.close();
                cn.desconectar();
            }catch(Exception ex){}
        }
        return list;
    }
    
     public void Agregar_Usuario(Usuario us){
        Database cn = new Database();
        String sql = "INSERT INTO usuario (rut,pass,nombre,paterno,materno,fechaIngreso,correo,fono,habilitado,nacionalidad,id_tipoUsuario,id_unidad, dias) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
                ps.setString(1 ,us.getRut());
                ps.setString(2 , us.getPass());
                ps.setString(3 ,us.getNombre()); 
                ps.setString(4 , us.getPaterno());
                ps.setString(5 , us.getMaterno());
                ps.setString(6, String.valueOf(us.getFechaIngreso()));
                ps.setString(7 ,us.getCorreo());
                ps.setString(8,us.getFono());
                ps.setInt(9,us.getHabilitado());
                ps.setString(10,us.getNacionalidad());
                ps.setInt(11,us.getId_tipoUsuario());
                ps.setInt(12,us.getId_Unidad());
                ps.setInt(13, us.getDias());
                System.out.println("Se guardo correctamente");
            JOptionPane.showMessageDialog(null, "El usuario se ha agregado correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Usuario ya existe en el sistema", "Aviso", JOptionPane.ERROR_MESSAGE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                cn.desconectar();
            }catch(Exception ex){}
        }
    }


/*Metodo Modificar*/
    public void Modificar_Usuario(Usuario us){
        Database cn = new Database();
        String sql = "UPDATE usuario SET pass = ?, nombre = ?, a_paterno = ?, a_materno = ?, mail = ?, fono = ?, habilitado = ?, nacionalidad = ?, id_tipousuario = ?, id_unidad = ? WHERE rut = ?";
        PreparedStatement ps = null;
       
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1 , us.getPass());
                ps.setString(2 ,us.getNombre()); 
                ps.setString(3 , us.getPaterno());
                ps.setString(4 , us.getMaterno());
               //ps.setString(5, String.valueOf(us.getFechaIngreso()));
               //ps.setString(5, us.getFechaIngreso().toString());
                ps.setString(5 ,us.getCorreo());
                ps.setString(6,us.getFono());
                ps.setInt(7,us.getHabilitado());
                ps.setString(8,us.getNacionalidad());
                ps.setInt(9,us.getId_tipoUsuario());
                ps.setInt(10,us.getId_Unidad());
                //ps.setInt(11,us.getDias());
                ps.setString(11, us.getRut());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El usuario se ha modificado correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                cn = null;
            }catch(Exception ex){}
        }
    }

public void HabilitarUsuario(Usuario usu){
        Database cn = new Database();
        String sql = "UPDATE usuario SET habilitado = ?, WHERE rut = ? ";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(1, (usu.getHabilitado()));
            ps.setString(2, usu.getRut());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                cn = null;
            }catch(Exception ex){}
        }
    }
/*Metodo Eliminar*/
    public void Eliminar_Usuario(Usuario us){
        Database cn = new Database();
        String sql = "DELETE FROM usuario WHERE rut = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, us.getRut());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El usuario se ha eliminado correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                cn = null;
            }catch(Exception ex){}
        }
    }
}
