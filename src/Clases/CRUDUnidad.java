/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Database;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Esteban
 */
public class CRUDUnidad {

    public ArrayList<Unidad> Listar_Unidad() {
        ArrayList<Unidad> list = new ArrayList<Unidad>();
        Database cn = new Database();
        String sql = "SELECT * FROM unidad";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                Unidad un = new Unidad();
                un.setId_unidad(lista.getInt(1));
                un.setNombre(lista.getString(2));
                un.setDescripcion(lista.getString(3));
                list.add(un);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                lista.close();
                cn.desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }
    
    public void Agregar_Unidad(Unidad un){
        Database cn = new Database();
        String sql = "INSERT INTO unidad (id_unidad, nombre, descripcion) VALUES(?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(1, un.getId_unidad());
            ps.setString(2, un.getNombre());
            ps.setString(3, un.getDescripcion());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Unida agregada existosamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Unidad ya se encuentra registrada", "Aviso", JOptionPane.ERROR_MESSAGE);
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
    public void Modificar_Unidad(Unidad un){
        Database cn = new Database();
        String sql = "UPDATE unidad SET nombre = ?, descripcion = ? WHERE id_unidad = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(3, un.getId_unidad());
            ps.setString(1, un.getNombre());
            ps.setString(2, un.getDescripcion());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Unidad modificada");
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
    public void Eliminar_Unidad(Unidad un){
        Database cn = new Database();
        String sql = "DELETE FROM unidad WHERE id_unidad = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(1,un.getId_unidad());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Unidad eliminada");
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
