package oton.trainerhive.gui;

/**
 *
 * @author Kailuss
 */
public class PanelLogIn extends javax.swing.JPanel {

    private final FramePrincipal principal;

    public PanelLogIn(FramePrincipal principal) {
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
        setLayout(null);

        labelLogoImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogoImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoTHanimado.gif"))); // NOI18N
        labelLogoImg.setAlignmentX(0.5F);
        add(labelLogoImg);
        labelLogoImg.setBounds(352, 106, 256, 119);

        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoTHtitle.png"))); // NOI18N
        labelLogo.setAlignmentX(0.5F);
        add(labelLogo);
        labelLogo.setBounds(352, 231, 256, 64);

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
        add(buttonLogin);
        buttonLogin.setBounds(405, 346, 150, 48);

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
        add(buttonWebsite);
        buttonWebsite.setBounds(405, 504, 150, 21);
    }// </editor-fold>//GEN-END:initComponents

    // Abre el diálogo de LogIn
    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
	DialogLogin jDialogLogin = new DialogLogin(principal, true);
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
