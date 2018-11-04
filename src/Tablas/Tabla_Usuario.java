/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDUSUARIO;
import Clases.Usuario;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class Tabla_Usuario {
    
    CRUDUSUARIO crud_usu = null;

    public void visualizar_Usuario(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("RUT");
        dt.addColumn("CLAVE");
        dt.addColumn("NOMBRE");
        dt.addColumn("APELLIDO PATERNO");
        dt.addColumn("APELLIDO MATERNO");
        dt.addColumn("FECHA INGRESO");
        dt.addColumn("CORREO");
        dt.addColumn("FONO");
        dt.addColumn("HABILITADO");
        dt.addColumn("NACIONALIDAD");
        dt.addColumn("TIPO USUARIO");
        dt.addColumn("UNIDAD");
        dt.addColumn("MODIFICAR");
        dt.addColumn("HABILITAR");
        
        JButton btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setName("mod");
        JButton btn_eliminar = new JButton("HABILITAR");
        btn_eliminar.setName("hab");

        crud_usu = new CRUDUSUARIO();
        Usuario usu = new Usuario();
        ArrayList<Usuario> list = crud_usu.Listar_Usuario();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
               Object fila[] = new Object[14];
                usu = list.get(i);
                fila[0] = usu.getRut();
                fila[1] = usu.getPass();
                fila[2] = usu.getNombre();
                fila[3] = usu.getPaterno();
                fila[4] = usu.getMaterno();
                fila[5] = usu.getFechaIngreso();
                fila[6] = usu.getCorreo();
                fila[7] = usu.getFono();
                fila[8]=usu.getHabilitado();
                
              /*  String hab = ""+usu.getHabilitado();
                if(hab.equals("1")){
                    fila[8]="Si";
                }else{
                    fila[8]="No";
                }
                fila[9] = usu.getNacionalidad();
                String tu  = ""+usu.getId_tipoUsuario();
                
              /*  if(tu.equals("1")){
                    fila[10]="Administrador";
                }else if(tu.equals("2")){
                    fila[10]="Jefe Interno";
                }else if (tu.equals("3")){
                    fila[10]="Jefe superior";
                }else if (tu.equals("4")){
                    fila[10]="Alcalde";
                }else if (tu.equals("5")){
                    fila[10]= "funcionario";
                }else {
                    fila[10]="Sin asignar";
                }*/
                 fila[9] = usu.getNacionalidad();
                fila[10]=usu.getId_tipoUsuario();
                fila[11] = usu.getId_Unidad();
                fila[12] = btn_modificar;
                fila[13] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
    
}
