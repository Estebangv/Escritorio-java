/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.Database;
import Clases.Usuario;
import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Esteban
 */
public class AgregarUsuario extends javax.swing.JFrame {

    static Login home = new Login();
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    Database cn;
    Connection reg;
    Connection conexion;
    Statement st;
    Database bd = new Database("");
    String ruta, nombre;
    int contador = 0;
    boolean fechaIngreso = false;

    public AgregarUsuario() {
        initComponents();
        cn = new Database();
        reg = cn.getConnection();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);

        setResizable(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHabilitado = new javax.swing.JLabel();
        dtFecha = new com.toedter.calendar.JDateChooser();
        btnAgregar = new javax.swing.JButton();
        txtFono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtMaterno = new javax.swing.JTextField();
        txtPaterno = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtRUT = new javax.swing.JTextField();
        lblFono = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblNacionalidad = new javax.swing.JLabel();
        lblContra = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        txtUnidad = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        lblUnidad = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblTipoUsuario = new javax.swing.JLabel();
        lblRut = new javax.swing.JLabel();
        txtTipoUsuario = new javax.swing.JTextField();
        txtDatos = new javax.swing.JLabel();
        txtHab = new javax.swing.JTextField();
        btnVerUsuarios = new javax.swing.JButton();
        txtPass = new javax.swing.JTextField();
        btn_salir = new javax.swing.JButton();
        txtNacionalidad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agregar Usuario");

        lblHabilitado.setText("HABILITADO");

        dtFecha.setDateFormatString("yyyy-MM-dd");
        dtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dtFechaFocusLost(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtFono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFonoKeyTyped(evt);
            }
        });

        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        txtMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaternoKeyTyped(evt);
            }
        });

        txtPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaternoKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtRUT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRUTFocusLost(evt);
            }
        });
        txtRUT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRUTKeyTyped(evt);
            }
        });

        lblFono.setText("FONO");

        lblCorreo.setText("CORREO");

        lblNacionalidad.setText("NACIONALIDAD");

        lblContra.setText("CONTRASEÑA");

        lblFecha.setText("FECHA INGRESO");

        lblApellidos.setText("APELLIDOS");

        lblUnidad.setText("UNIDAD");

        lblNombre.setText("NOMBRE:");

        lblTipoUsuario.setText("TIPO USUARIO");

        lblRut.setText("RUT");

        txtDatos.setText("Ingrese datos del usuario");

        btnVerUsuarios.setText("Ver  Usuarios");
        btnVerUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerUsuariosActionPerformed(evt);
            }
        });

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        txtNacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacionalidadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblApellidos)
                    .addComponent(lblFecha)
                    .addComponent(lblRut)
                    .addComponent(lblContra)
                    .addComponent(lblNacionalidad)
                    .addComponent(lblCorreo)
                    .addComponent(lblFono)
                    .addComponent(lblHabilitado)
                    .addComponent(lblTipoUsuario))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTipoUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtHab, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPass))
                        .addGap(31, 31, 31)
                        .addComponent(lblUnidad)
                        .addGap(18, 18, 18)
                        .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtRUT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPaterno, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(txtDatos)
                .addContainerGap(246, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_salir)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDatos)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRut)
                            .addComponent(txtRUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblApellidos)
                        .addGap(18, 18, 18)
                        .addComponent(lblFecha))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPaterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContra)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNacionalidad)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFono)
                    .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHabilitado)
                    .addComponent(txtHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnidad)
                    .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoUsuario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnVerUsuarios)
                    .addComponent(btn_salir))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dtFechaFocusLost

    }//GEN-LAST:event_dtFechaFocusLost

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        int ultimoId = 0;
        ArrayList<Integer> idsUsuarios = new ArrayList<Integer>();
        String fechaI = "";

        Date dateI = null;
        Date today = new Date();
        today.setHours(0);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FileInputStream fis = null;

        if (txtRUT.getText().equals("") || txtNombre.getText().equals("") || dtFecha.equals("")
                || txtPaterno.getText().equals("") || txtMaterno.getText().equals("") || txtCorreo.getText().equals("") || txtFono.getText().equals("") || txtNacionalidad.getText().equals("")
                || txtTipoUsuario.getText().equals("") || txtUnidad.getText().equals("") || txtHab.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ingrese informacion en el formulario.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (dtFecha.getDate().after(today)) {
            JOptionPane.showMessageDialog(this, "Ingrese fecha valida, la fecha no puede ser despues de la fecha actual.", "ERROR", JOptionPane.ERROR_MESSAGE);
            dtFecha.setDate(null);

        } else {

            try {

                dateI = dtFecha.getDate();

                fechaI = dateI.toString();

                String rut = txtRUT.getText();
                String nombre = txtNombre.getText();
                String paterno = txtPaterno.getText();
                String materno = txtMaterno.getText();
                String nacionalidad = txtNacionalidad.getText();
                String habilitado = txtHab.getText();
                String correo = txtCorreo.getText();
                String fono = txtFono.getText();
                String tipoUsuario = txtTipoUsuario.getText();
                String unidad = txtUnidad.getText();
                String pass = txtPass.getText();

                ArrayList<Object> usuarios = new ArrayList<Object>();
                PreparedStatement pst = null;

                PreparedStatement coleccionUsuarios = null;
                java.sql.Date sqlFechaIng = new java.sql.Date(dateI.getTime());
                coleccionUsuarios = reg.prepareStatement("SELECT * FROM usuario");
                ResultSet lista = coleccionUsuarios.executeQuery();
                pst = reg.prepareStatement("INSERT INTO usuario VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1, rut);
                pst.setString(2, pass);
                pst.setString(3, nombre);
                pst.setString(4, paterno);
                pst.setString(5, materno);
                pst.setDate(6, sqlFechaIng);
                pst.setString(7, correo);
                pst.setString(8, fono);
                pst.setInt(9, Integer.parseInt(habilitado));
                pst.setString(10, nacionalidad);
                pst.setInt(11, Integer.parseInt(tipoUsuario));
                pst.setInt(12, Integer.parseInt(unidad));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Usuario Registrado Correctamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "hay errores " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnAgregarActionPerformed

    public boolean isEmail(String correo) {

        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }

    }
    private void txtFonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFonoKeyTyped
           char validar= evt.getKeyChar();
           if(Character.isLetter(validar))
           {
               getToolkit().beep();
               evt.consume();
              JOptionPane.showMessageDialog(null, "Solo se puede ingresar numeros en el telfono", "Aviso", JOptionPane.ERROR_MESSAGE);
           }
    }//GEN-LAST:event_txtFonoKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
     int maximoCaracter = 30;
        char validarCaracter = evt.getKeyChar();
        if (txtCorreo.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaternoKeyTyped
     int maximoCaracter = 99;
        char validarCaracter = evt.getKeyChar();
        if (txtPaterno.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMaternoKeyTyped

    private void txtPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaternoKeyTyped
     int maximoCaracter = 99;
        char validarCaracter = evt.getKeyChar();
        if (txtPaterno.getText().length() >= maximoCaracter) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtPaternoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        int maximoCaracter = 99;
        char validarCaracter = evt.getKeyChar();
        if (txtNombre.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtRUTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRUTFocusLost

    }//GEN-LAST:event_txtRUTFocusLost

    private void txtRUTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUTKeyTyped
        /*  int maximoCaracter = 29;
        char validarCaracter = evt.getKeyChar();
        if (txtRUT.getText().length() >= maximoCaracter) {
            evt.consume();
        } */
    }//GEN-LAST:event_txtRUTKeyTyped

    private void btnVerUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerUsuariosActionPerformed
        this.dispose();
        new AdminUsuario().setVisible(true);
    }//GEN-LAST:event_btnVerUsuariosActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();
        new Menus().setVisible(true);

    }//GEN-LAST:event_btn_salirActionPerformed

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        if (isEmail(txtCorreo.getText())) {
            txtCorreo.setForeground(Color.black);
        } else {
            txtCorreo.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "Formato de correo incorrecto", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtNacionalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyTyped
        // TODO add your handling code here:
        int maximoCaracter = 15;
        char validarCaracter = evt.getKeyChar();
        if ((validarCaracter < 'a' || validarCaracter > 'z') && (validarCaracter < 'A' || validarCaracter > 'Z') || txtNacionalidad.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNacionalidadKeyTyped

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnVerUsuarios;
    private javax.swing.JButton btn_salir;
    private com.toedter.calendar.JDateChooser dtFecha;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblContra;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFono;
    private javax.swing.JLabel lblHabilitado;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRut;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblUnidad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JLabel txtDatos;
    private javax.swing.JTextField txtFono;
    private javax.swing.JTextField txtHab;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPaterno;
    private javax.swing.JTextField txtRUT;
    private javax.swing.JTextField txtTipoUsuario;
    private javax.swing.JTextField txtUnidad;
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
