/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Clases.CRUDUSUARIO;
import Clases.Limpiar;
import Clases.Usuario;
import Conexion.Database;
import Tablas.Tabla_Usuario;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban
 */
public class AdminUsuario extends javax.swing.JFrame {

    static Login home = new Login();

    Tabla_Usuario usu = new Tabla_Usuario();
    Limpiar lim = new Limpiar();
    CRUDUSUARIO crud_usu;
    int Clic_tabla = 0;

    /**
     * Creates new form vista_Empresas
     */
    public AdminUsuario() {
        initComponents();
        usu.visualizar_Usuario(tab_Usuario);
        //mostrarUsuarios();
        //habilitacionDeTextos(false);
        listarTipoUsuario();
        listarTipoUnidad();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        activa_boton(false, false, true, true);
        tab_Usuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setResizable(false);
    }

    public void obtenerUsuarios() {
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet lista = Database.crearConsulta("SELECT * FROM USUARIO");
        modelo.setColumnIdentifiers(new Object[]{"RUT", "CLAVE", "NOMBRE", "PATERNO", "MATERNO", "FECHA INGRESO", "MAIL", "FONO", "HABILITADO", "NACIONALIDAD", "TIPO USUARIO", "UNIDAD", "DIAS"});

        try {
            while (lista.next()) {
                modelo.addRow(new Object[]{lista.getString("rut"), lista.getString("clave"), lista.getString("nombre"),
                     lista.getString("a_paterno"), lista.getString("a_materno"), lista.getDate("fecha_ingreso"),
                     lista.getString("mail"), lista.getString("fono"), lista.getInt("habilitado"), lista.getString("nacionalidad"),
                     lista.getInt("id_tipousuario"), lista.getInt("id_unidad"), lista.getInt("dias")});
            }
            tab_Usuario.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e);
        }
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

    public void modificar() {
        int errores = 0;
        if (txtRut.getText().equals("") || txtNombre.getText().equals("") || txtPass.getText().equals("")
                || txtPaterno.getText().equals("") || txtUnidad.getText().equals("") || txtMaterno.getText().equals("")
                || txtNacionalidad.getText().equals("") || txtTipoUsuario.getText().equals("") || txtHabilitado.getText().equals("")
                || dtFecha.getDate().equals("") || txtCorreo.getText().equals("") || txtFono.getText().equals("")) {
            errores = errores + 1;
        }

        if (errores >= 1) {
            JOptionPane.showMessageDialog(null, "Falta información, rellene los campos", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            crud_usu = new CRUDUSUARIO();
            Usuario usu = new Usuario();
            usu.setRut(txtRut.getText());
            usu.setPass(txtPass.getText());
            usu.setCorreo(txtCorreo.getText());
            //usu.setFechaIngreso(dtFecha.getDate());
            usu.setFono(txtFono.getText());
            usu.setHabilitado(Integer.parseInt(txtHabilitado.getText().toString()));
            usu.setId_tipoUsuario(Integer.parseInt(txtTipoUsuario.getText().toString()));
            usu.setId_Unidad(Integer.parseInt(txtUnidad.getText()));
            usu.setMaterno(txtMaterno.getText());
            usu.setPaterno(txtPaterno.getText());
            usu.setNombre(txtNombre.getText());
            usu.setNacionalidad(txtNacionalidad.getText());
            usu.setId_tipoUsuario(jComboBox1.getSelectedIndex()+1 );
            usu.setId_Unidad(jComboBox2.getSelectedIndex()+1);

            crud_usu.Modificar_Usuario(usu);
        }

    }

    public void activa_boton(boolean a1, boolean a2, boolean a3, boolean a4) {
        btnModificar.setEnabled(a1);
        btnHabilitar.setEnabled(a2);
        txtRut.setEnabled(a3);
        txtHabilitado.setEnabled(a4);

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
        tab_Usuario = new javax.swing.JTable();
        panel = new javax.swing.JPanel();
        lbl_rutRetail = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btn_salir = new javax.swing.JButton();
        txtPaterno = new javax.swing.JTextField();
        txtMaterno = new javax.swing.JTextField();
        txtNacionalidad = new javax.swing.JTextField();
        txtHabilitado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtUnidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTipoUsuario = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        txtRut = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lbl_rut = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_razonSocial = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnHabilitar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btn_limpiar = new javax.swing.JButton();
        dtFecha = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador de Usuarios");

        tab_Usuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tab_Usuario.setModel(new javax.swing.table.DefaultTableModel(
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
        tab_Usuario.getTableHeader().setReorderingAllowed(false);
        tab_Usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_UsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_Usuario);

        panel.setBackground(new java.awt.Color(204, 204, 204));
        panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Habilitado");

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        jLabel7.setText("Fono");

        jLabel8.setText("Correo");

        jLabel9.setText("Unidad");

        jLabel10.setText("Tipo Usuario");

        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });

        txtRut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRutFocusLost(evt);
            }
        });
        txtRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutKeyTyped(evt);
            }
        });

        jLabel1.setText("Nombre");

        lbl_rut.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_rut.setText("RUT:");

        jLabel2.setText("Paterno");

        lbl_razonSocial.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_razonSocial.setText("Clave");

        jLabel3.setText("Materno");

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha Ingreso");

        btnHabilitar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHabilitar.setText("Habilitar/Bloquear");
        btnHabilitar.setActionCommand("Habilitar");
        btnHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarActionPerformed(evt);
            }
        });

        jLabel5.setText("Nacionalidad");

        btn_limpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        dtFecha.setDateFormatString("yyyy-MM-dd");
        dtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dtFechaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(lbl_razonSocial, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_rut, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNombre)
                                        .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtCorreo))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHabilitado))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btn_salir))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnHabilitar)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 790, Short.MAX_VALUE)
                .addComponent(lbl_rutRetail)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(lbl_rutRetail))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_rut))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)
                                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_razonSocial))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(60, 60, 60)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_limpiar)
                                    .addComponent(btn_salir))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnModificar)
                                    .addComponent(btnHabilitar)))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtHabilitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1338, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tab_UsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_UsuarioMouseClicked
        Clic_tabla = this.tab_Usuario.rowAtPoint(evt.getPoint());
      
        Date fechaIngreso = (java.sql.Date) tab_Usuario.getValueAt(Clic_tabla, 5);
        String rut = "" + tab_Usuario.getValueAt(Clic_tabla, 0);
        String pass = "" + tab_Usuario.getValueAt(Clic_tabla, 1);
        String nombre = "" + tab_Usuario.getValueAt(Clic_tabla, 2);
        String paterno = "" + tab_Usuario.getValueAt(Clic_tabla, 3);
        String materno = "" + tab_Usuario.getValueAt(Clic_tabla, 4);
        //Date fechaIngreso = "" + tab_Usuario.getValueAt(Clic_tabla, 5); NOOOO
        String hab = "" + tab_Usuario.getValueAt(Clic_tabla, 8);
        String nacionalidad = "" + tab_Usuario.getValueAt(Clic_tabla, 9);
        String mail = "" + tab_Usuario.getValueAt(Clic_tabla, 6);
        String fono = "" + tab_Usuario.getValueAt(Clic_tabla, 7);
        String tipousuario = "" + tab_Usuario.getValueAt(Clic_tabla, 10);
        String unidad = "" + tab_Usuario.getValueAt(Clic_tabla, 11);
       int unidad2 = (int)  tab_Usuario.getValueAt(Clic_tabla, 11);
        int tipoUsuario2 = (int) tab_Usuario.getValueAt(Clic_tabla, 10);
       //String unidad2 = ""+ tab_Usuario.getValueAt(Clic_tabla, 10);
       //String tipoUsuario2 = ""+ tab_Usuario.getValueAt(Clic_tabla, 11);

        txtRut.setText(rut);
        txtPass.setText(pass);
        txtPaterno.setText(paterno);
        txtFono.setText(fono);
        txtMaterno.setText(materno);
        dtFecha.setDate(fechaIngreso);
        txtHabilitado.setText(hab);
        txtNacionalidad.setText(nacionalidad);
        txtNombre.setText(nombre);
        txtCorreo.setText(mail);
        txtUnidad.setText(unidad);
        txtTipoUsuario.setText(tipousuario);
        jComboBox1.setSelectedIndex(tipoUsuario2-1);
        jComboBox2.setSelectedIndex(unidad2-1);

        int column = tab_Usuario.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tab_Usuario.getRowHeight();

        if (row < tab_Usuario.getRowCount() && row >= 0 && column < tab_Usuario.getColumnCount() && column >= 0) {
            Object value = tab_Usuario.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("mod")) {
                    System.out.println("Click en el boton modificar");
                    //EVENTOS MODIFICAR
                    activa_boton(true, false, false, false);
                }
                if (boton.getName().equals("hab")) {
                    //JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("Click en el boton habilitar/bloquear");
                    //EVENTOS ELIMINAR
                    activa_boton(false, true, false, false);
                }
            }

        }
    }//GEN-LAST:event_tab_UsuarioMouseClicked

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        new AgregarUsuario().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        int maximoCaracter = 29;
        char validarCaracter = evt.getKeyChar();
        if (txtPass.getText().length() >= maximoCaracter) {
            evt.consume();

        }
    }//GEN-LAST:event_txtPassKeyTyped

    private void txtRutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRutFocusLost
        /*  int minimo = 7;
        if (txtRut.getText().length() > 0) {
            if (txtRut.getText().length() < minimo) {
                txtRut.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, "Ingrese rut con su maximo de caracteres correcto EJ:(123456781)", "Aviso", JOptionPane.ERROR_MESSAGE);
                rutCorrecto = false;
            } else {
                txtRut.setForeground(Color.black);
                rutCorrecto = true;
            }
        }*/
    }//GEN-LAST:event_txtRutFocusLost

    private void txtRutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutKeyTyped
        /*  int maximorut = 8;
        int errores = 0;
        char validarCaracter = evt.getKeyChar();
        ArrayList<Character> lista = retornarListaCaracteres();

        for (int i = 0; i < lista.size(); i++) {
            char caracter = lista.get(i);
            if (validarCaracter == caracter) {
                errores = errores + 1;
            }
        }

        if ((Character.isLetter(validarCaracter) && validarCaracter != 'k') || txtRut.getText().length() >= maximorut || errores > 0) {
            evt.consume();
        }*/
    }//GEN-LAST:event_txtRutKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
        usu.visualizar_Usuario(tab_Usuario);
        activa_boton(false, false, true, true);
        lim.limpiar_texto(panel);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarActionPerformed
int a = JOptionPane.showConfirmDialog(this, "Confirmar habilitación", "SI/NO", JOptionPane.YES_NO_OPTION);
if(a==0){
        int itemSeleccionado = tab_Usuario.getSelectedColumn();
        if (itemSeleccionado <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int fila = tab_Usuario.getSelectedRow();
            String rut = tab_Usuario.getValueAt(fila, 0).toString();
            int habilitado = (int) tab_Usuario.getValueAt(fila, 8);
            int habilitado2;
            System.out.println("valor id: " + rut + " valor de activo: " + habilitado);

   
            if (habilitado == 2) {
                habilitado2 = 1;
                JOptionPane.showMessageDialog(this, "Usuario activado");
            } else {
                habilitado2 = 2;
                JOptionPane.showMessageDialog(this, "Usuario bloqueado");
            }

            System.out.println("NUEVOS -> valor id: " + rut + " valor de activo: " + habilitado2);

            Database cn = new Database();
            String sql = "UPDATE usuario SET habilitado = ? WHERE rut = ?";
            PreparedStatement ps = null;
            try {
                ps = cn.getConnection().prepareStatement(sql);
                ps.setString(2, rut);
                ps.setInt(1, habilitado2);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                try {
                    ps.close();
                    cn = null;
                } catch (Exception ex) {
                }
            }
            usu.visualizar_Usuario(tab_Usuario);
        }
}
    }//GEN-LAST:event_btnHabilitarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        lim.limpiar_texto(panel);
        activa_boton(false, false, true, true);
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void dtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dtFechaFocusLost

    }//GEN-LAST:event_dtFechaFocusLost

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHabilitar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_salir;
    private com.toedter.calendar.JDateChooser dtFecha;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_razonSocial;
    private javax.swing.JLabel lbl_rut;
    private javax.swing.JLabel lbl_rutRetail;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tab_Usuario;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtFono;
    private javax.swing.JTextField txtHabilitado;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPaterno;
    private javax.swing.JTextField txtRut;
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
