/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Clases.Limpiar;
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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import sun.rmi.transport.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.MessagingException;

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
    Limpiar lim = new Limpiar();
    

    public String generarPass() {
        String rut = txtRUT.getText().substring(0, 2).toLowerCase();
        String pass;
        String nombre = txtNombre.getText().substring(0, 2).toLowerCase();
        String apellido = txtPaterno.getText().substring(0, 2).toLowerCase();

        return pass = rut + nombre + apellido;
    }

    public AgregarUsuario() {
        initComponents();
        cn = new Database();
        reg = cn.getConnection();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        listarTipoUsuario();
        listarTipoUnidad();

        setResizable(false);

    }

    public void listarTipoUsuario() {
        ArrayList<String> list = new ArrayList<String>();
        Database cn = new Database();
        String sql = "select nombre from tipo_usuario order by id_tipousuario asc";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                list.add(lista.getString(1));
                String n = lista.getString("nombre");
                
                jComboBox1.addItem(lista.getString(1));

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

    }
    
    
    public int obtenerIdTipoUsuario(String nombreTipoUsuario) {
        ArrayList<String> list = new ArrayList<String>();
        int[] numTipoUsuario = new int[1];
        //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select id_tipoUsuario from tipo_usuario where nombre like '" + nombreTipoUsuario + "'";
        ResultSet lista = null;
        PreparedStatement ps = null;
        int idTipo = 0;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                numTipoUsuario[0] = lista.getInt(1);
                idTipo = numTipoUsuario[0];
                list.add(lista.getString(1));
            
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
        System.out.println(idTipo);
        return idTipo;
    }
    
    public int obtenerIdUnidad(String nombreUnidad) {
        ArrayList<String> list = new ArrayList<String>();
        int[] numUnidad = new int[1];
        //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select id_unidad from unidad where nombre like '" + nombreUnidad + "'"; //scar numero
        ResultSet lista = null;
        PreparedStatement ps = null;
        int idUnidad = 0;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                numUnidad[0] = lista.getInt(1);
                idUnidad = numUnidad[0];
                list.add(lista.getString(1));
               
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
        System.out.println(idUnidad);
        return idUnidad;
    }
    
    public void listarTipoUnidad() {
        ArrayList<String> list = new ArrayList<String>();
        Database cn = new Database();
        String sql = "select nombre from unidad order by id_unidad asc";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                list.add(lista.getString(1));
                String n = lista.getString("nombre");
                jComboBox2.addItem(lista.getString(1));
                

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

    }

    public void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        String remitente = "munic.vistahermosa@gmail.com";
        String clave = "hola.12345678";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "hola.12345678");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipients(Message.RecipientType.TO, destinatario);
            message.setSubject(asunto);
            message.setText(cuerpo);
            javax.mail.Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
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
        lblFecha = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        lblUnidad = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblTipoUsuario = new javax.swing.JLabel();
        lblRut = new javax.swing.JLabel();
        txtHab = new javax.swing.JTextField();
        btnVerUsuarios = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        txtNacionalidad = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agregar Usuario");
        setBackground(new java.awt.Color(153, 255, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.green);

        lblHabilitado.setText("HABILITADO");

        dtFecha.setDateFormatString("yyyy-MM-dd");
        dtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dtFechaFocusLost(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(51, 204, 255));
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

        txtRUT.setToolTipText("");
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

        lblFecha.setText("FECHA INGRESO");

        lblApellidos.setText("APELLIDOS");

        lblUnidad.setText("UNIDAD");

        lblNombre.setText("NOMBRE:");

        lblTipoUsuario.setText("TIPO USUARIO");

        lblRut.setText("RUT");

        btnVerUsuarios.setText("Ver  Usuarios");
        btnVerUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerUsuariosActionPerformed(evt);
            }
        });

        btn_salir.setForeground(new java.awt.Color(255, 0, 0));
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

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registro.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_salir))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblApellidos)
                                    .addComponent(lblFecha)
                                    .addComponent(lblRut)
                                    .addComponent(lblNacionalidad)
                                    .addComponent(lblCorreo)
                                    .addComponent(lblFono)
                                    .addComponent(lblHabilitado)
                                    .addComponent(lblUnidad)
                                    .addComponent(lblTipoUsuario))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtRUT, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(39, 39, 39)
                                        .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 60, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVerUsuarios))
                                .addGap(270, 270, 270)))))
                .addGap(36, 36, 36))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRut)
                    .addComponent(txtRUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addGap(24, 24, 24)
                        .addComponent(lblApellidos)
                        .addGap(18, 18, 18)
                        .addComponent(lblFecha))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNacionalidad))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFono)
                    .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHabilitado)
                    .addComponent(txtHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnidad)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoUsuario)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerUsuarios)
                    .addComponent(btn_salir))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dtFechaFocusLost

    }//GEN-LAST:event_dtFechaFocusLost

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        String password = generarPass();

        //agregar
        int ultimoId = 0;
        ArrayList<Integer> idsUsuarios = new ArrayList<Integer>();
        String fechaI = "";

        Date dateI = null;
        Date today = new Date();
        //today.setHours(0);
        today.setDate(31 / 12 / 2018);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // FileInputStream fis = null;
        String destinatario = txtCorreo.getText(); //A quien le quieres escribir.
        String asunto = "CLAVE PARA ACCEDER AL SISTEMA DE LA MUNICIPALIDAD VISTA HERMOSA";
        String cuerpo = "HOLA! BIENVENID@ A LA MUNICIPALIDAD DE VISTA HERMOSA. \n"
                + "PARA ACCEDER AL SISTEMA DEBE INGRESAR CON SU RUT Y CONTRASEÑA \n"
                + "RUT : " + txtRUT.getText() + "\n"
                + "CONTRASEÑA : " + password;

        if (txtRUT.getText().equals("") || txtNombre.getText().equals("") || dtFecha.equals("")
                || txtPaterno.getText().equals("") || txtMaterno.getText().equals("") || txtCorreo.getText().equals("") || txtFono.getText().equals("") || txtNacionalidad.getText().equals("")
                /*|| txtTipoUsuario.getText().equals("") || txtUnidad.getText().equals("")*/ || txtHab.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Falta información, por favor rellene todos los campos del formulario.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
               // String tipoUsuario = txtTipoUsuario.getText();
               // String unidad = txtUnidad.getText();
                // String pass = txtPass.getText();
                int combo = jComboBox1.getSelectedIndex();
                int combo2 = jComboBox2.getSelectedIndex();

                ArrayList<Object> usuarios = new ArrayList<Object>();
                PreparedStatement pst = null;

                PreparedStatement coleccionUsuarios = null;
                java.sql.Date sqlFechaIng = new java.sql.Date(dateI.getTime());
                //java.sql.Date sqlFechaHoy = new java.sql.Date(today.getYear());
                coleccionUsuarios = reg.prepareStatement("SELECT * FROM usuario");
                ResultSet lista = coleccionUsuarios.executeQuery();
                pst = reg.prepareStatement("INSERT INTO usuario VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

                int ingreso = sqlFechaIng.getYear() + 1900;
                //int hoy = sqlFechaHoy.getYear();

                //int ingreso2 = dateI.getYear();
                int dias = 0;
                Calendar fecha = new GregorianCalendar();
                int actual = fecha.get(Calendar.YEAR);
                int anos = actual - ingreso;

                if (anos >= 1 && anos <= 15) {
                    dias = 15;
                } else if (anos >= 16 && anos < 20) {
                    dias = 20;
                } else if (anos >= 20) {
                    dias = 25;
                } else {
                    dias = 0;
                }
                System.out.println("DIAS: " + dias);
                System.out.println("INGRESO: " + ingreso);
                System.out.println("HOY: " + actual);
                System.out.println("AÑOS: " + anos);

                pst.setString(1, rut);
                pst.setString(2, password);
                pst.setString(3, nombre);
                pst.setString(4, paterno);
                pst.setString(5, materno);
                pst.setDate(6, sqlFechaIng);
                pst.setString(7, correo);
                pst.setString(8, fono);
                pst.setInt(9, Integer.parseInt(habilitado));
                pst.setString(10, nacionalidad);
                pst.setInt(11, combo+1);
                pst.setInt(12, combo2+1);
                //pst.setInt(11, Integer.parseInt(tipoUsuario));
               // pst.setInt(12, Integer.parseInt(unidad));
                pst.setInt(13, dias);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Usuario Registrado Correctamente.");
                JOptionPane.showMessageDialog(this, "Datos de ingreso enviados al correo del usuario.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hay errores, por favor revise los datos. " + e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            enviarConGMail(destinatario, asunto, cuerpo);
            //im.limpiar_texto(panel);
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
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo se puede ingresar numeros en el telfono.", "Aviso", JOptionPane.ERROR_MESSAGE);
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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
//listarTipoUsuario();
        //jComboBox1.getSelectedIndex();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnVerUsuarios;
    private javax.swing.JButton btn_salir;
    private com.toedter.calendar.JDateChooser dtFecha;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblApellidos;
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
    private javax.swing.JTextField txtFono;
    private javax.swing.JTextField txtHab;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPaterno;
    private javax.swing.JTextField txtRUT;
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
