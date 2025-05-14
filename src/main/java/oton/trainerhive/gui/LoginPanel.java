package oton.trainerhive.gui;

import oton.trainerhive.gui.util.FontManager;

/**
 * Panel de inicio de sesión que se muestra dentro del frame principal. Este panel contiene los componentes visuales básicos para el inicio de sesión, como campos de texto y un botón, y se encarga de configurar su apariencia inicial. La lógica de autenticación reside en el diálogo de inicio de sesión ({@link LoginDialog}).
 *
 * @author Alfonso Otón
 * @version 1.2
 * @since 2025
 */
public class LoginPanel extends javax.swing.JPanel {

    /**
     * Referencia al frame principal de la aplicación. Necesario para interactuar con la ventana principal, aunque la lógica de inicio de sesión reside principalmente en {@link LoginDialog}.
     */
    private final MainFrame principal;

    // La variable loginButton no está declarada explícitamente fuera del bloque initComponents,
    // pero se utiliza en setupUI. Asumo que está declarada dentro de initComponents
    // y es un componente importante de la UI. Se añade Javadoc en la sección de variables.
    /**
     * Constructor del panel de inicio de sesión. Inicializa el panel y configura sus componentes y apariencia básica.
     *
     * @param principal La referencia al {@link MainFrame} principal.
     */
    public LoginPanel(MainFrame principal) {
	this.principal = principal;
	initComponents();
	setupUI();
    }

    /**
     * Configuración inicial de la interfaz de usuario del panel. Aplica fuentes a los componentes del panel.
     */
    private void setupUI() {
	FontManager.applyFontToContainer(this, 13f, 13f);
	FontManager.applyFont(loginButton, "bold", 15);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogoImg = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        buttonWebsite = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(960, 597));

        labelLogoImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogoImg.setIcon(new oton.trainerhive.gui.util.SVGRenderer().getSVGIcon("/images/logo", 72,72));
        labelLogoImg.setAlignmentX(0.5F);
        labelLogoImg.setPreferredSize(new java.awt.Dimension(0, 128));

        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo.setIcon(new oton.trainerhive.gui.util.SVGRenderer().getSVGIcon("/images/trainerhive", 157, 22));
        labelLogo.setAlignmentX(0.5F);
        labelLogo.setPreferredSize(new java.awt.Dimension(240, 35));

        buttonWebsite.setBackground(new java.awt.Color(60, 63, 65));
        buttonWebsite.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        buttonWebsite.setText("www.trainerhive.com");
        buttonWebsite.setBorder(null);
        buttonWebsite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonWebsite.setFocusable(false);
        buttonWebsite.setRequestFocusEnabled(false);
        buttonWebsite.setRolloverEnabled(false);
        buttonWebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWebsiteActionPerformed(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(245, 215, 96));
        loginButton.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        loginButton.setForeground(new java.awt.Color(60, 63, 65));
        loginButton.setText("Entra en la colmena");
        loginButton.setToolTipText("");
        loginButton.setActionCommand("");
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.setMaximumSize(new java.awt.Dimension(200, 36));
        loginButton.setMinimumSize(new java.awt.Dimension(200, 36));
        loginButton.setPreferredSize(new java.awt.Dimension(150, 48));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerButton.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        registerButton.setText("Darse de alta");
        registerButton.setToolTipText("");
        registerButton.setActionCommand("");
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerButton.setMaximumSize(new java.awt.Dimension(200, 36));
        registerButton.setMinimumSize(new java.awt.Dimension(200, 36));
        registerButton.setPreferredSize(new java.awt.Dimension(150, 48));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(360, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelLogoImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonWebsite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                        .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
                .addContainerGap(360, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addComponent(labelLogoImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(buttonWebsite)
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWebsiteActionPerformed
    }//GEN-LAST:event_buttonWebsiteActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
	LoginDialog jDialogLogin = new LoginDialog(principal, true);
	jDialogLogin.setVisible(true);
    }//GEN-LAST:event_loginButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_registerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonWebsite;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelLogoImg;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton registerButton;
    // End of variables declaration//GEN-END:variables
}
