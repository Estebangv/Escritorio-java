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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador de motivos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 623, 238));

        panel.setBackground(new java.awt.Color(204, 204, 204));
        panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        panel.add(txtIdMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 45, 164, 30));

        lblId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblId.setText("ID");
        panel.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, -1, 30));

        btn_modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });
        panel.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 120, 30));

        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        panel.add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 120, 30));

        btn_limpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });
        panel.add(btn_limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 120, 30));
        panel.add(lbl_rutRetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 139, -1, -1));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        panel.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 120, 30));

        lblDesc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDesc.setText("Descripción");
        panel.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 76, -1, 30));
        panel.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 76, 164, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registro.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-16, -260, 650, 520));

        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 623, 247));

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
       int a = JOptionPane.showConfirmDialog(this, "Confirmar habilitación", "SI/NO", JOptionPane.YES_NO_OPTION);
if(a==0){
        modificar();
        mo.visualizar_Motivo(tab_motivo);
        activa_boton(false, false, true);
        lim.limpiar_texto(panel);}
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
    private javax.swing.JLabel jLabel1;
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
