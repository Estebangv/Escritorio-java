/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Database {
        
    private static String db="VistaHermosa";
    private static String user="root";
    private static String pass="";
    private static String host="vistahermosa.c2kbiyqwrivf.us-east-2.rds.amazonaws.com:1521";
    private static String server="jdbc:mysql://"+host+"/"+db;
    
    Statement st;
    Connection cn = null;
    
    public Database(){
        try{
//        Class.forName("com.mysql.jdbc.Driver");
//        cn = DriverManager.getConnection(server,user,pass);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@vistahermosa.c2kbiyqwrivf.us-east-2.rds.amazonaws.com:1521:ORCL","admin_portafoliog3","hola.123");
           
        if (cn!=null){
            System.out.println("Conexión a base de datos "+db+" OK\n");
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
  public Database(String n1){
        try{
//        Class.forName("com.mysql.jdbc.Driver");
//        cn = DriverManager.getConnection(server,user,pass);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@vistahermosa.c2kbiyqwrivf.us-east-2.rds.amazonaws.com:1521:ORCL","admin_portafoliog3","hola.123");
            st = cn.createStatement();
        if (cn!=null){
            System.out.println("Conexión a base de datos "+db+" OK\n");
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
  }
    
    public Connection getConnection(){
        return cn;
    }

    public void desconectar(){
        cn = null;
    }
    
    public static Connection getConexion(){
        Connection cn=null;
        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            cn=DriverManager.getConnection(server,user,pass);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@vistahermosa.c2kbiyqwrivf.us-east-2.rds.amazonaws.com:1521:ORCL","admin_portafoliog3","hola.123");
            System.out.println("Conexión");
        }
        catch(Exception e){
            System.out.println(String.valueOf(e));
        }
        return cn;        
    }
    
    public static ResultSet crearConsulta(String Consulta){
        Connection cn=getConexion();
        Statement st;
        ResultSet lista=null;
        try{
            st=cn.createStatement();
            lista=st.executeQuery(Consulta);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return lista;
    }
    
   
    
}