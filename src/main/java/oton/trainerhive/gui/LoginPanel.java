package oton.trainerhive.gui;

/**
 *
 * @author Kailuss
 */
public class LoginPanel extends javax.swing.JPanel {

    private final MainFrame principal;

    public LoginPanel(MainFrame principal) {
	this.principal = principal;
	initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogoImg = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        buttonLogin = new javax.swing.JButton();
        buttonWebsite = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(960, 597));

        labelLogoImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoTHanimado.gif"))); // NOI18N
        labelLogoImg.setAlignmentX(0.5F);

        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoTHtitle.png"))); // NOI18N
        labelLogo.setAlignmentX(0.5F);

        buttonLogin.setBackground(new java.awt.Color(99, 101, 105));
        buttonLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonLogin.setForeground(new java.awt.Color(255, 204, 51));
        buttonLogin.setText("Iniciar sesión");
        buttonLogin.setFocusPainted(false);
        buttonLogin.setFocusable(false);
        buttonLogin.setMaximumSize(new java.awt.Dimension(150, 48));
        buttonLogin.setMinimumSize(new java.awt.Dimension(150, 48));
        buttonLogin.setPreferredSize(new java.awt.Dimension(150, 48));
        buttonLogin.setRequestFocusEnabled(false);
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelLogoImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonWebsite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(319, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(labelLogoImg)
                .addGap(0, 0, 0)
                .addComponent(labelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(buttonWebsite)
                .addGap(64, 64, 64))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Abre el diálogo de LogIn
    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
	LoginDialog jDialogLogin = new LoginDialog(principal, true);
	jDialogLogin.setVisible(true);
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void buttonWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWebsiteActionPerformed
    }//GEN-LAST:event_buttonWebsiteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JButton buttonWebsite;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelLogoImg;
    // End of variables declaration//GEN-END:variables
}
