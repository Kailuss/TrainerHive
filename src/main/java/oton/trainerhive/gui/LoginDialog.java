package oton.trainerhive.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import oton.trainerhive.dataaccess.DataAccess;
import oton.trainerhive.dto.User;
import oton.trainerhive.gui.MainFrame.PanelType;
import oton.trainerhive.gui.util.FontManager;

/**
 *
 * @author Alfonso Otón
 */
public class LoginDialog extends javax.swing.JDialog {

    private final MainFrame principal;

    // Constructor del diálogo de inicio de sesión
    public LoginDialog(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	principal = (MainFrame) parent;
	initComponents();
	setupUI();
    }

    // Configuración inicial de la interfaz
    private void setupUI() {
	FontManager.applyFontToContainer(this, 13f, 13f);
	FontManager.applyFont(loginButton, "bold", 13);
	getRootPane().setDefaultButton(loginButton);	// Botón predeterminado al presionar Enter
	setLocationRelativeTo(principal);		// Centra el diálogo respecto al Frame principal

	// Listener para seleccionar todo el texto del campo de contraseña al obtener el foco
	passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
	    @Override
	    public void focusGained(java.awt.event.FocusEvent evt) {
		passwordField.selectAll();
	    }
	});

	// Listener para mostrar u ocultar la contraseña
	showPasswordCheckBox.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
		    passwordField.setEchoChar((char) 0);    // Muestra contraseña
		} else {
		    passwordField.setEchoChar('•');	    // Oculta contraseña
		}
	    }
	});

	 // Simula un clic en el botón 'Iniciar sesión' al pulsar la tecla Intro
	userField.addKeyListener(new java.awt.event.KeyAdapter() {
	    @Override
	    public void keyPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
		    loginButton.doClick();
		}
	    }
	});

	passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
	    @Override
	    public void keyPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
		    loginButton.doClick();
		}
	    }
	});
    }

    // Método para gestionar el inicio de sesión
    private void successfulLogin(User user) {
	principal.setActiveUser(user);
	principal.swapPanel(PanelType.TABLES);
	principal.logIn();
	dispose();
    }

    // WARNING: Do NOT modify this code.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoLabel = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        loginErrorLabel = new javax.swing.JLabel();
        showPasswordCheckBox = new javax.swing.JCheckBox();
        resetPasswordLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio de sesión");
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(300, 360));
        setResizable(false);
        setSize(new java.awt.Dimension(300, 360));

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new oton.trainerhive.gui.util.SVGRenderer().getSVGIcon("/images/logo", 40, 40));
        logoLabel.setAlignmentX(0.5F);
        logoLabel.setFocusable(false);
        logoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logoLabel.setMaximumSize(new java.awt.Dimension(200, 64));
        logoLabel.setMinimumSize(new java.awt.Dimension(200, 64));
        logoLabel.setPreferredSize(new java.awt.Dimension(200, 64));

        userField.setText("a@b.c");
        userField.setMaximumSize(new java.awt.Dimension(200, 36));
        userField.setMinimumSize(new java.awt.Dimension(200, 36));
        userField.setNextFocusableComponent(passwordField);
        userField.setPreferredSize(new java.awt.Dimension(200, 36));
        userField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFieldActionPerformed(evt);
            }
        });

        passwordField.setText("string");
        passwordField.setAutoscrolls(false);
        passwordField.setMaximumSize(new java.awt.Dimension(200, 36));
        passwordField.setMinimumSize(new java.awt.Dimension(200, 36));
        passwordField.setNextFocusableComponent(showPasswordCheckBox);
        passwordField.setPreferredSize(new java.awt.Dimension(200, 36));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(99, 101, 105));
        loginButton.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 204, 51));
        loginButton.setText("Iniciar sesión");
        loginButton.setToolTipText("");
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.setMaximumSize(new java.awt.Dimension(200, 36));
        loginButton.setMinimumSize(new java.awt.Dimension(200, 36));
        loginButton.setNextFocusableComponent(userField);
        loginButton.setPreferredSize(new java.awt.Dimension(200, 36));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        loginErrorLabel.setForeground(new java.awt.Color(245, 215, 96));
        loginErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginErrorLabel.setMaximumSize(new java.awt.Dimension(200, 16));
        loginErrorLabel.setMinimumSize(new java.awt.Dimension(200, 16));
        loginErrorLabel.setPreferredSize(new java.awt.Dimension(200, 16));

        showPasswordCheckBox.setText("Mostrar contraseña");
        showPasswordCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        showPasswordCheckBox.setNextFocusableComponent(userField);
        showPasswordCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordCheckBoxActionPerformed(evt);
            }
        });

        resetPasswordLabel.setForeground(new java.awt.Color(128, 128, 128));
        resetPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resetPasswordLabel.setText("(He olvidado mi contraseña)");
        resetPasswordLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetPasswordLabel.setMaximumSize(new java.awt.Dimension(200, 16));
        resetPasswordLabel.setMinimumSize(new java.awt.Dimension(200, 16));
        resetPasswordLabel.setPreferredSize(new java.awt.Dimension(200, 16));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(showPasswordCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginErrorLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetPasswordLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showPasswordCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(loginErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        userField.getAccessibleContext().setAccessibleDescription("");
        loginButton.getAccessibleContext().setAccessibleDescription("Botón para iniciar sesión");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
	String userMail = userField.getText();
	String password = new String(passwordField.getPassword());
	loginErrorLabel.setText(""); // Limpia el mensaje de error

	// Intenta iniciar sesión
	try {
	    ArrayList<User> instructors = DataAccess.getInstructor(userMail);

	    if (instructors.isEmpty()) {
		loginErrorLabel.setText("El usuario no existe.");
		return;
	    }

	    // Comprueba cada usuario devuelto (se espera solo uno)
	    User user = instructors.get(0);
	    if (DataAccess.decryptPassword(password, user.getPasswordHash())) {
		successfulLogin(user);
	    } else {
		loginErrorLabel.setText("La contraseña es incorrecta.");
	    }
	} catch (SQLException e) {
	    loginErrorLabel.setText("Error de conexión.");
	}
    }//GEN-LAST:event_loginButtonActionPerformed

    private void showPasswordCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordCheckBoxActionPerformed
    }//GEN-LAST:event_showPasswordCheckBoxActionPerformed

    private void userFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userFieldActionPerformed
    }//GEN-LAST:event_userFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginErrorLabel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel resetPasswordLabel;
    private javax.swing.JCheckBox showPasswordCheckBox;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
