/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.Database;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.sql.*;
import sun.security.util.Password;

/**
 *
 * @author Raul
 */
public class Login extends javax.swing.JFrame {

    static Login home = new Login();
    /**
     * Creates new form login_Admin
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblenunciao = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        lblPass = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        lblRut = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de sesion: ADMINISTRADOR");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblenunciao.setFont(new java.awt.Font("Lucida Sans", 1, 24)); // NOI18N
        lblenunciao.setForeground(new java.awt.Color(255, 255, 255));
        lblenunciao.setText("Inicio de Sesion Vista Hermosa");
        getContentPane().add(lblenunciao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 410, 40));

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 150, 30));

        lblPass.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPass.setForeground(new java.awt.Color(255, 255, 255));
        lblPass.setText("Ingrese Contraseña:");
        getContentPane().add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        txtRut.setName(""); // NOI18N
        txtRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutActionPerformed(evt);
            }
        });
        txtRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutKeyTyped(evt);
            }
        });
        getContentPane().add(txtRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 150, 30));

        lblRut.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblRut.setForeground(new java.awt.Color(255, 255, 255));
        lblRut.setText("Ingrese Rut:");
        getContentPane().add(lblRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, 40));

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 110, -1));

        jButton1.setText("Salir");
        jButton1.setActionCommand("btnSalir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 70, -1));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/home.jpg"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutActionPerformed

    private void txtRutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutKeyTyped
           // TODO add your handling code here:
          /* int maximorut=9;
           int errores=0;
           char validarCaracter=evt.getKeyChar();
           ArrayList<Character> lista=retornarListaCaracteres();
           
           for (int i = 0; i < lista.size(); i++) {
               char caracter = lista.get(i);
               if(validarCaracter==caracter ){
               errores= errores+1;
               }
           }
           
           if((Character.isLetter(validarCaracter)&& validarCaracter != 'k') || txtRut.getText().length()>=maximorut || errores > 0 ){
                    evt.consume();
           }*/
            
    }//GEN-LAST:event_txtRutKeyTyped

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        // TODO add your handling code here:
       int maximorut=20;
        
       // if(txtpass.getText().length()>=maximorut){
       // evt.consume();
    //    }
    }//GEN-LAST:event_txtPassKeyTyped

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

         
        if(txtRut.getText().equals("") && txtPass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese Credenciales correctas", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else if(txtRut.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese Rut porfavor", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else if(txtPass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese Contraseña porfavor", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
 
            String rut = txtRut.getText();
            String clave = txtPass.getText();
            
            
            
            int coincidencia = 0;
            
            ResultSet lista = Database.crearConsulta("SELECT * FROM usuario WHERE rut='" + rut + "' and  pass='" + clave + "'and id_tipousuario='"+"1"+"' and habilitado='"+"1"+"'");

            while (lista.next()) {
                coincidencia = coincidencia + 1;
            }
            if (coincidencia == 0) { //Si no encontró algo
                JOptionPane.showMessageDialog(null, "Usuario no permitido", "Aviso", JOptionPane.ERROR_MESSAGE);
            } else if (coincidencia >= 1) {
                        JOptionPane.showMessageDialog(null, "Login exitoso");
                        home.dispose();
                        new Menus().setVisible(true);
                        this.dispose();
                    }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        }
       
     
    }//GEN-LAST:event_btnLoginActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO  your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

 public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                home.setVisible(true);

            }
        });    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblRut;
    private javax.swing.JLabel lblenunciao;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables

   
    
    private ArrayList<Character> retornarListaCaracteres(){
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
        return  validaciones;
    }
    
    

    
    
}
