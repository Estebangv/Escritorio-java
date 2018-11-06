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
 * @author usuario
 */
public class CRUDMotivo {
    public ArrayList<Motivo> Listar_Motivo() {
        ArrayList<Motivo> list = new ArrayList<Motivo>();
        Database cn = new Database();
        String sql = "SELECT * FROM motivo";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                Motivo mo = new Motivo();
                mo.setId_motivo(lista.getInt(1));
                mo.setNombreMotivo(lista.getString(2));
              
                list.add(mo);
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
    
    public void Agregar_Motivo(Motivo mo){
        Database cn = new Database();
        String sql = "INSERT INTO motivo (id_motivo, nombreMotivo) VALUES(?,?)";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(1, mo.getId_motivo());
            ps.setString(2, mo.getNombreMotivo());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Motivo agregada existosamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Motivo ya se encuentra registrada", "Aviso", JOptionPane.ERROR_MESSAGE);
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
    public void Modificar_Motivo(Motivo mo){
        Database cn = new Database();
        String sql = "UPDATE motivo SET nombreMotivo = ? WHERE id_motivo = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(2, mo.getId_motivo());
            ps.setString(1, mo.getNombreMotivo());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Motivo modificado");
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
    public void Eliminar_Motivo(Motivo mo){
        Database cn = new Database();
        String sql = "DELETE FROM motivo WHERE id_motivo = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(1,mo.getId_motivo());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Motivo eliminado");
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
