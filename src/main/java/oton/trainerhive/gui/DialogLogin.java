package oton.trainerhive.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import oton.trainerhive.dataaccess.DataAccess;
import oton.trainerhive.dto.Usuario;

/**
 *
 * @author Kailuss
 */
public class DialogLogin extends javax.swing.JDialog {

    private final Main principal;

    // Creates new form JDialogLogin
    public DialogLogin(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	principal = (Main) parent;
	initComponents();
	getRootPane().setDefaultButton(buttonTryLogin);
	this.setLocationRelativeTo(null);

	// Agrega un FocusListener para seleccionar el texto al obtener el foco
	passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
	    @Override
	    public void focusGained(java.awt.event.FocusEvent evt) {
		passwordField.selectAll(); // Selecciona todo el contenido
	    }
	});
    }

    // WARNING: Do NOT modify this code. This method is called from within the constructor to initialize the form. The content of this method is always regenerated by the Form Editor.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogo64 = new javax.swing.JLabel();
        textFieldUserName = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        buttonTryLogin = new javax.swing.JButton();
        labelLoginError = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio de sesión");
        setMinimumSize(new java.awt.Dimension(300, 360));
        setResizable(false);
        setSize(new java.awt.Dimension(300, 360));

        labelLogo64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TrainerHiveLogo64.png"))); // NOI18N
        labelLogo64.setAlignmentX(0.5F);
        labelLogo64.setFocusable(false);
        labelLogo64.setMaximumSize(new java.awt.Dimension(300, 64));
        labelLogo64.setMinimumSize(new java.awt.Dimension(300, 64));
        labelLogo64.setPreferredSize(new java.awt.Dimension(300, 64));

        textFieldUserName.setText("Usuario");
        textFieldUserName.setMaximumSize(new java.awt.Dimension(200, 36));
        textFieldUserName.setMinimumSize(new java.awt.Dimension(200, 36));
        textFieldUserName.setPreferredSize(new java.awt.Dimension(200, 36));
        textFieldUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUserNameActionPerformed(evt);
            }
        });

        passwordField.setText("contrasena");
        passwordField.setAutoscrolls(false);
        passwordField.setMaximumSize(new java.awt.Dimension(200, 36));
        passwordField.setMinimumSize(new java.awt.Dimension(200, 36));
        passwordField.setPreferredSize(new java.awt.Dimension(200, 36));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        buttonTryLogin.setBackground(new java.awt.Color(99, 101, 105));
        buttonTryLogin.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        buttonTryLogin.setForeground(new java.awt.Color(255, 204, 51));
        buttonTryLogin.setText("Iniciar sesión");
        buttonTryLogin.setFocusPainted(false);
        buttonTryLogin.setFocusable(false);
        buttonTryLogin.setMaximumSize(new java.awt.Dimension(200, 36));
        buttonTryLogin.setMinimumSize(new java.awt.Dimension(200, 36));
        buttonTryLogin.setPreferredSize(new java.awt.Dimension(200, 36));
        buttonTryLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTryLoginActionPerformed(evt);
            }
        });

        labelLoginError.setForeground(new java.awt.Color(255, 204, 51));
        labelLoginError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLoginError.setMaximumSize(new java.awt.Dimension(200, 16));
        labelLoginError.setMinimumSize(new java.awt.Dimension(200, 16));
        labelLoginError.setPreferredSize(new java.awt.Dimension(200, 16));

        jCheckBox1.setText("Recuérdame");
        jCheckBox1.setEnabled(false);
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLogo64, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(buttonTryLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLoginError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelLogo64, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(textFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(labelLoginError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonTryLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void buttonTryLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTryLoginActionPerformed
	// Obtiene los valores ingresados en los campos de texto y contraseña
	String userName = textFieldUserName.getText();
	String password = new String(passwordField.getPassword());

	// Instancia de DataAccess
	DataAccess da = new DataAccess();

	// Limpia el mensaje de error
	labelLoginError.setText("");

	try {
	    // Obtiene la lista de usuarios que coinciden con el nombre de usuario
	    ArrayList<Usuario> usuaris = da.getInstructor(userName);
	    if (usuaris.isEmpty()) {
		labelLoginError.setText("El usuario no existe.");
		return;
	    }
	    for (Usuario u : usuaris) {
		if (da.decryptPassword(password, u.getPasswordHash())) {
		    this.dispose();
		    principal.activeUser = u;
		    principal.swapPanel(principal.panel_Tables); 
		} else {
		    labelLoginError.setText("La contraseña es incorrecta.");
		}
		// txaShowInfoUsuaris.append(u.getId() + " " + u.getNom() + " " + u.getEmail() + " " + u.getPasswordHash().substring(0, 5) + " " + u.isInstructor() + "\n");
	    }
	} catch (SQLException e) {
	    // Manejo de excepciones en caso de error con la base de datos
	    labelLoginError.setText("Error de conexión.");
	    System.out.println("Error al intentar iniciar sesión: " + e.getMessage() + "\n");
	}
    }//GEN-LAST:event_buttonTryLoginActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void textFieldUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUserNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonTryLogin;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel labelLoginError;
    private javax.swing.JLabel labelLogo64;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField textFieldUserName;
    // End of variables declaration//GEN-END:variables
}
