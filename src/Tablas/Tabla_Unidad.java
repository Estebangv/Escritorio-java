/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDUnidad;
import Clases.Unidad;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class Tabla_Unidad {
    
    CRUDUnidad crud_un = null;

    public void visualizar_Unidad(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("ID UNIDAD");
        dt.addColumn("NOMBRE");
        dt.addColumn("DESCRIPCIÓN");
        dt.addColumn("MODIFICAR");
        dt.addColumn("ELIMINAR");
        
        JButton btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setName("mod");
        JButton btn_eliminar = new JButton("ELIMINAR");
        btn_eliminar.setName("eli");

        crud_un = new CRUDUnidad();
        Unidad un = new Unidad();
        ArrayList<Unidad> list = crud_un.Listar_Unidad();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[5];
                un = list.get(i);
                fila[0] = un.getId_unidad();
                fila[1] = un.getNombre();
                fila[2] = un.getDescripcion();
                fila[3] = btn_modificar;
                fila[4] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
    
}
