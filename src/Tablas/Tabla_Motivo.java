/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDMotivo;
import Clases.CRUDUnidad;
import Clases.Motivo;
import Clases.Unidad;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario
 */
public class Tabla_Motivo {
     CRUDMotivo crud_mo = null;

    public void visualizar_Motivo(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("ID UNIDAD");
        dt.addColumn("DESCRIPCIÓN");
        dt.addColumn("MODIFICAR");
        dt.addColumn("ELIMINAR");
        
        JButton btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setName("mod");
        JButton btn_eliminar = new JButton("ELIMINAR");
        btn_eliminar.setName("eli");

        crud_mo = new CRUDMotivo();
        Motivo un = new Motivo();
        ArrayList<Motivo> list = crud_mo.Listar_Motivo();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[5];
                un = list.get(i);
                fila[0] = un.getId_motivo();
                fila[1] = un.getNombreMotivo();
                fila[2] = btn_modificar;
                fila[3] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
    
}
