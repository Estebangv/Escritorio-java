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
        activa_boton(false, false, false,false,false,false,false,false,false,false,false,false,false,false);
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
                || txtPaterno.getText().equals("") || /*txtUnidad.getText().equals("") ||*/ txtMaterno.getText().equals("")
                || txtNacionalidad.getText().equals("") ||/* txtTipoUsuario.getText().equals("") ||*/ txtHabilitado.getText().equals("")
                || dtFecha.getDate().equals("") || txtCorreo.getText().equals("") || txtFono.getText().equals("")) {
            errores = errores + 1;
        }

        if (errores >= 1) {
            JOptionPane.showMessageDialog(null, "Falta información, rellene los campos", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            
            int habi=0;
            if(txtHabilitado.getText().equals("SI") || txtHabilitado.getText().equals("si") || txtHabilitado.getText().equals("Si")|| txtHabilitado.getText().equals("sI")){
                habi=1;
            }else if(txtHabilitado.getText().equals("NO") || txtHabilitado.getText().equals("no") || txtHabilitado.getText().equals("No")|| txtHabilitado.getText().equals("nO")){
               habi=2;
            }else{
                JOptionPane.showMessageDialog(this, "Solo puede ingresa Si o No en el recuadro de habilitado", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
                
            
            crud_usu = new CRUDUSUARIO();
            Usuario usu = new Usuario();
            usu.setRut(txtRut.getText());
            usu.setPass(txtPass.getText());
            usu.setCorreo(txtCorreo.getText());
            //usu.setFechaIngreso(dtFecha.getDate());
            usu.setFono(txtFono.getText());
            usu.setHabilitado(habi);
           // usu.setId_tipoUsuario(Integer.parseInt(txtTipoUsuario.getText().toString()));
           // usu.setId_Unidad(Integer.parseInt(txtUnidad.getText()));
            usu.setMaterno(txtMaterno.getText());
            usu.setPaterno(txtPaterno.getText());
            usu.setNombre(txtNombre.getText());
            usu.setNacionalidad(txtNacionalidad.getText());
            usu.setId_tipoUsuario(jComboBox1.getSelectedIndex()+1 );
            usu.setId_Unidad(jComboBox2.getSelectedIndex()+1);

            crud_usu.Modificar_Usuario(usu);
        }

    }

    public void activa_boton(boolean a1, boolean a2, boolean a3, boolean a4, boolean a5, boolean a6, boolean a7, boolean a8, boolean a9, boolean a10, boolean a11, boolean a12, boolean a13, boolean a14){
        btnModificar.setEnabled(a1);
        btnHabilitar.setEnabled(a2);
        txtRut.setEnabled(a3);
        txtHabilitado.setEnabled(a4);
        txtMaterno.setEnabled(a5);
        txtPaterno.setEnabled(a6);
        jComboBox1.setEnabled(a7);
        jComboBox2.setEnabled(a8);
        dtFecha.setEnabled(a9);
        txtNombre.setEnabled(a10);
        txtNacionalidad.setEnabled(a11);
        txtCorreo.setEnabled(a12);
        txtPass.setEnabled(a13);
        txtFono.setEnabled(a14);
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
        jLabel10 = new javax.swing.JLabel();
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
        jLabel11 = new javax.swing.JLabel();

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
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel.add(lbl_rutRetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(1148, 154, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Habilitado");
        jLabel6.setAlignmentX(0.5F);
        panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 263, 80, 30));
        panel.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 108, 164, 30));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        panel.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 150, 30));
        panel.add(txtPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 139, 164, 30));
        panel.add(txtMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 164, 30));
        panel.add(txtNacionalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 232, 164, 30));
        panel.add(txtHabilitado, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 263, 164, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Fono");
        jLabel7.setAlignmentX(0.5F);
        panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 45, 100, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Correo");
        jLabel8.setAlignmentX(0.5F);
        panel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 76, 100, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Unidad");
        jLabel9.setAlignmentX(0.5F);
        panel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 108, 100, 30));
        panel.add(txtFono, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 45, 164, 30));
        panel.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 76, 164, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tipo Usuario");
        jLabel10.setAlignmentX(0.5F);
        panel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 139, 100, 30));

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
        panel.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 77, 164, 30));

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
        panel.add(txtRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 45, 164, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Nombre");
        jLabel1.setAlignmentX(0.5F);
        panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 108, 70, 30));

        lbl_rut.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_rut.setText("RUT");
        lbl_rut.setAlignmentX(0.5F);
        lbl_rut.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lbl_rutPropertyChange(evt);
            }
        });
        panel.add(lbl_rut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 70, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Paterno");
        jLabel2.setAlignmentX(0.5F);
        panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 70, 30));

        lbl_razonSocial.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_razonSocial.setText("Clave");
        lbl_razonSocial.setAlignmentX(0.5F);
        panel.add(lbl_razonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 77, 70, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Materno");
        jLabel3.setAlignmentX(0.5F);
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 80, 30));

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        panel.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 150, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Fecha Ingreso");
        jLabel4.setAlignmentX(0.5F);
        panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 201, 90, 30));

        btnHabilitar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHabilitar.setText("Habilitar/Bloquear");
        btnHabilitar.setActionCommand("Habilitar");
        btnHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarActionPerformed(evt);
            }
        });
        panel.add(btnHabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 150, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nacionalidad");
        jLabel5.setAlignmentX(0.5F);
        panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 232, 80, 30));

        btn_limpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });
        panel.add(btn_limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 150, 30));

        dtFecha.setDateFormatString("yyyy-MM-dd");
        dtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dtFechaFocusLost(evt);
            }
        });
        panel.add(dtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 201, 164, 30));

        panel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 139, 164, 30));

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        panel.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 108, 164, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registro.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -256, 1180, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        String habi="";
        
        if(hab.equals("1")){
            habi="Si";
        }else{
            habi="No";
        }
        txtRut.setText(rut);
        txtPass.setText(pass);
        txtPaterno.setText(paterno);
        txtFono.setText(fono);
        txtMaterno.setText(materno);
        dtFecha.setDate(fechaIngreso);
        txtHabilitado.setText(habi);
        txtNacionalidad.setText(nacionalidad);
        txtNombre.setText(nombre);
        txtCorreo.setText(mail);
        //txtUnidad.setText(unidad);
        //txtTipoUsuario.setText(tipousuario);
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
                    activa_boton(true, false, false,false, true, true, true,true,false,true,true,true,false,true);
                }
                if (boton.getName().equals("hab")) {
                    //JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("Click en el boton habilitar/bloquear");
                    //EVENTOS ELIMINAR
                    activa_boton(false, true, false, false, false, false, false, false, false, false, false, false, false, false);
                }
            }

        }
    }//GEN-LAST:event_tab_UsuarioMouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void dtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dtFechaFocusLost

    }//GEN-LAST:event_dtFechaFocusLost

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        lim.limpiar_texto(panel);
        activa_boton(false, false, false, false, false, false, false, false, false,false,false,false,false,false);
    }//GEN-LAST:event_btn_limpiarActionPerformed

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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
        usu.visualizar_Usuario(tab_Usuario);
        activa_boton(false, false, true, true, true, true, true, true, true, true, true, true, true, true);
        lim.limpiar_texto(panel);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void lbl_rutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lbl_rutPropertyChange
        lbl_rut.setHorizontalAlignment(4);     // TODO add your handling code here:
    }//GEN-LAST:event_lbl_rutPropertyChange

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

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        int maximoCaracter = 29;
        char validarCaracter = evt.getKeyChar();
        if (txtPass.getText().length() >= maximoCaracter) {
            evt.consume();

        }
    }//GEN-LAST:event_txtPassKeyTyped

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        new AgregarUsuario().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

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
    private javax.swing.JLabel jLabel11;
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
