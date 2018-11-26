/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Clases.CRUDMotivo;
import Clases.Limpiar;
import Clases.Motivo;
import Tablas.Tabla_Motivo;
import Tablas.Tabla_Unidad;
import java.awt.Color;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Esteban
 */
public class AdminMotivos extends javax.swing.JFrame {

    static Login home = new Login();

    Tabla_Motivo mo = new Tabla_Motivo();
    Limpiar lim = new Limpiar();
    CRUDMotivo crud_mo;
    int clic_tabla = 0;

    public AdminMotivos() {
        initComponents();
        mo.visualizar_Motivo(tab_motivo);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);

        setResizable(false);
        
        activa_boton(false, false, true);
        tab_motivo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void modificar() {
        int errores = 0;
        if (txtIdMotivo.getText().equals("") || txtDescripcion.getText().equals("")) {
            errores = errores + 1;
        }

        if (errores >= 1) {
            JOptionPane.showMessageDialog(null, "Falta información, rellene los campos", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            crud_mo = new CRUDMotivo();
            Motivo mo = new Motivo();

            mo.setId_motivo(Integer.parseInt(txtIdMotivo.getText()));
            mo.setNombreMotivo(txtDescripcion.getText());

            crud_mo.Modificar_Motivo(mo);
        }

    }

    public void eliminar() {
        crud_mo = new CRUDMotivo();
        Motivo mo = new Motivo();

        mo.setId_motivo(Integer.parseInt(txtIdMotivo.getText()));
        crud_mo.Eliminar_Motivo(mo);
    }

    public void activa_boton(boolean a2, boolean a3, boolean a4) {
        btn_modificar.setEnabled(a2);
        btn_eliminar.setEnabled(a3);
        txtIdMotivo.setEnabled(a4);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tab_motivo = new javax.swing.JTable();
        panel = new javax.swing.JPanel();
        txtIdMotivo = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        lbl_rutRetail = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        lblDesc = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador de motivos");

        tab_motivo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tab_motivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_motivo.getTableHeader().setReorderingAllowed(false);
        tab_motivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_motivoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_motivo);

        panel.setBackground(new java.awt.Color(204, 204, 204));
        panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtIdMotivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdMotivoFocusLost(evt);
            }
        });
        txtIdMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdMotivoKeyTyped(evt);
            }
        });

        lblId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblId.setText("ID");

        btn_modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_limpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        lblDesc.setText("Descripción");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_salir)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblId)
                            .addComponent(lblDesc))
                        .addGap(10, 10, 10)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(txtDescripcion))))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(lbl_rutRetail))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 245, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId)
                    .addComponent(txtIdMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificar))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDesc)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btn_eliminar)))
                .addGap(18, 18, 18)
                .addComponent(btn_limpiar)
                .addGap(9, 9, 9)
                .addComponent(lbl_rutRetail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(btn_salir)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tab_motivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_motivoMouseClicked
        clic_tabla = this.tab_motivo.rowAtPoint(evt.getPoint());

        String id_unidad = "" + tab_motivo.getValueAt(clic_tabla, 0);
      
        String nombremotivo = "" + tab_motivo.getValueAt(clic_tabla, 1);

       
        txtIdMotivo.setText(id_unidad);
        
        txtDescripcion.setText(nombremotivo);

        int column = tab_motivo.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tab_motivo.getRowHeight();

        if (row < tab_motivo.getRowCount() && row >= 0 && column < tab_motivo.getColumnCount() && column >= 0) {
            Object value = tab_motivo.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("mod")) {
                    System.out.println("Click en el boton modificar");
                    activa_boton(true, false, false);
                }
                if (boton.getName().equals("eli")) {
                    System.out.println("Click en el boton eliminar");
                    activa_boton(false, true, false);
                }
            }

        }
    }//GEN-LAST:event_tab_motivoMouseClicked

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        new Menus().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        lim.limpiar_texto(panel);
        activa_boton(false, false, true);
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

        int s = JOptionPane.showConfirmDialog(null, "Eliminar Unidad", "Si/no", JOptionPane.YES_NO_OPTION);
        if (s == 0) {
            eliminar();
            mo.visualizar_Motivo(tab_motivo);
            activa_boton(false, false, true);
            lim.limpiar_texto(panel);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        modificar();
        mo.visualizar_Motivo(tab_motivo);
        activa_boton(false, false, true);
        lim.limpiar_texto(panel);
    }//GEN-LAST:event_btn_modificarActionPerformed
    /**/
    private void txtIdMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdMotivoKeyTyped
        /*int maximorut = 8;
        int errores = 0;
        char validarCaracter = evt.getKeyChar();
        ArrayList<Character> lista = retornarListaCaracteres();

        for (int i = 0; i < lista.size(); i++) {
            char caracter = lista.get(i);
            if (validarCaracter == caracter) {
                errores = errores + 1;
            }
        }

        if ((Character.isLetter(validarCaracter) && validarCaracter != 'k') || txt_rut.getText().length() >= maximorut || errores > 0) {
            evt.consume();
        }*/
    }//GEN-LAST:event_txtIdMotivoKeyTyped

    private void txtIdMotivoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdMotivoFocusLost
        /*int minimo = 4;
        if (txtId.getText().length() > 0) {
            if (txtId.getText().length() < minimo) {
                txtId.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, "Ingrese id con un maximo de 4 numeros)", "Aviso", JOptionPane.ERROR_MESSAGE);
                ;
            } else {
                txtId.setForeground(Color.black);
                //rutCorrecto = true;
            }
        }*/

    }//GEN-LAST:event_txtIdMotivoFocusLost

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lbl_rutRetail;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tab_motivo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIdMotivo;
    // End of variables declaration//GEN-END:variables

    private ArrayList<Character> retornarListaCaracteres() {
        ArrayList<Character> validaciones = new ArrayList<Character>();
        validaciones.add('.');
        validaciones.add('/');
        validaciones.add('|');
        validaciones.add('=');
        validaciones.add('?');
        validaciones.add('¿');
        validaciones.add('´');
        validaciones.add('¨');
        validaciones.add('{');
        validaciones.add('}');
        validaciones.add(';');
        validaciones.add(':');
        validaciones.add('_');
        validaciones.add('^');
        validaciones.add('-');
        validaciones.add('!');
        validaciones.add('"');
        validaciones.add('#');
        validaciones.add('$');
        validaciones.add('%');
        validaciones.add('&');
        validaciones.add('(');
        validaciones.add(')');
        validaciones.add('¡');
        validaciones.add(']');
        validaciones.add('*');
        validaciones.add('[');
        validaciones.add(',');
        validaciones.add('°');

        return validaciones;
    }

}
