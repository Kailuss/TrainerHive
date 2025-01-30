package oton.trainerhive.gui;

import oton.trainerhive.gui.util.FontManager;

/**
 *
 * @author Kailuss
 */
public class LoginPanel extends javax.swing.JPanel {

    private final MainFrame principal;

    public LoginPanel(MainFrame principal) {
	this.principal = principal;
	initComponents();
	setupUI();
    }

    private void setupUI() {
	FontManager.applyFontToContainer(this, 13f, 13f);
	FontManager.applyFont(loginButton, "bold", 14);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogoImg = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        buttonWebsite = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();

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

        loginButton.setBackground(new java.awt.Color(99, 101, 105));
        loginButton.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 204, 51));
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
                        .addComponent(loginButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
                .addContainerGap(360, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(labelLogoImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(buttonWebsite)
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWebsiteActionPerformed
    }//GEN-LAST:event_buttonWebsiteActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        LoginDialog jDialogLogin = new LoginDialog(principal, true);
        jDialogLogin.setVisible(true);
    }//GEN-LAST:event_loginButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonWebsite;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelLogoImg;
    private javax.swing.JButton loginButton;
    // End of variables declaration//GEN-END:variables
}
