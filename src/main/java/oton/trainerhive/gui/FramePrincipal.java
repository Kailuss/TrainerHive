package oton.trainerhive.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import oton.trainerhive.dto.Usuario;
import oton.trainerhive.gui.util.UIConstantes;

/**
 *
 * @author Alfonso Otón
 */
public final class FramePrincipal extends javax.swing.JFrame {

    private JPanel activePanel;					// Panel activo
    private Usuario activeUser;					// Usuario que ha iniciado sesión
    private final DefaultTableCellRenderer cellRenderer;	// Configuración de las celdas de las tablas
    private PanelLogIn panel_LogIn;				// Panel de bienvenida
    private PanelTables panel_Tables;				// Panel de usuarios

    // Constructor
    public FramePrincipal() {
	initComponents();
	this.cellRenderer = createTableCellRenderer();		// Configura el formato que tendran las celdas
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Deja de ejecutar el programa al cerrar la ventana
	this.setLocationRelativeTo(null);			// Centra el jFrame en pantalla		
	swapPanel(PanelType.LOGIN);				// Carga el panel LogIn
    }

    // WARNING: Do NOT modify this code.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBarMain = new javax.swing.JMenuBar();
        menuTH = new javax.swing.JMenu();
        menuTHNewWorkout = new javax.swing.JMenuItem();
        menuTHSession = new javax.swing.JMenuItem();
        separatorTH = new javax.swing.JPopupMenu.Separator();
        menuTHAbout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuTHSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(1280, 900));
        setName("mainFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1280, 900));
        setSize(new java.awt.Dimension(1024, 900));

        menuBarMain.setBorder(null);
        menuBarMain.setBorderPainted(false);
        menuBarMain.setFocusable(false);
        menuBarMain.setMaximumSize(new java.awt.Dimension(164, 30));
        menuBarMain.setMinimumSize(new java.awt.Dimension(164, 30));
        menuBarMain.setPreferredSize(new java.awt.Dimension(164, 30));

        menuTH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TrainerHiveLogo16.png"))); // NOI18N
        menuTH.setText("Trainer Hive");
        menuTH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        menuTHNewWorkout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuTHNewWorkout.setText("Nuevo entrenamiento...");
        menuTHNewWorkout.setEnabled(false);
        menuTHNewWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTHNewWorkoutActionPerformed(evt);
            }
        });
        menuTH.add(menuTHNewWorkout);

        menuTHSession.setText("Iniciar sesión...");
        menuTHSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTHSessionActionPerformed(evt);
            }
        });
        menuTH.add(menuTHSession);
        menuTH.add(separatorTH);

        menuTHAbout.setText("Acerca de...");
        menuTHAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTHAboutActionPerformed(evt);
            }
        });
        menuTH.add(menuTHAbout);
        menuTH.add(jSeparator1);

        menuTHSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuTHSalir.setText("Salir");
        menuTHSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTHSalirActionPerformed(evt);
            }
        });
        menuTH.add(menuTHSalir);

        menuBarMain.add(menuTH);

        setJMenuBar(menuBarMain);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuTHSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTHSalirActionPerformed
	System.exit(0);
    }//GEN-LAST:event_menuTHSalirActionPerformed

    private void menuTHAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTHAboutActionPerformed
    }//GEN-LAST:event_menuTHAboutActionPerformed

    private void menuTHSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTHSessionActionPerformed
	if (activePanel == panel_LogIn) {
	    DialogLogin jDialogLogin = new DialogLogin(this, true);
	    jDialogLogin.setVisible(true);
	} else if (activePanel == panel_Tables)
	    logOut();
    }//GEN-LAST:event_menuTHSessionActionPerformed

    private void menuTHNewWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTHNewWorkoutActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_menuTHNewWorkoutActionPerformed

    private DefaultTableCellRenderer createTableCellRenderer() {
	return new DefaultTableCellRenderer() {
	    @Override
	    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
		    boolean isSelected, boolean hasFocus, int row, int column) {
		javax.swing.JLabel label = (javax.swing.JLabel) super.getTableCellRendererComponent(table, value,
			isSelected, hasFocus, row, column);
		// Configura el cell renderer component para añadir 16 píxeles de margen interno a cada lado del contenido.
		label.setBorder(new EmptyBorder(0, UIConstantes.CELL_PADDING_LEFT, 0, UIConstantes.CELL_PADDING_RIGHT));
		return label;
	    }
	};
    }

    // Tipos de paneles.
    public enum PanelType {
	LOGIN, TABLES
    }

    public void swapPanel(PanelType panelType) {
	// Elimina el panel actual.
	if (activePanel != null) {
	    remove(activePanel);
	}
	// Crea y asigna el nuevo panel.
	activePanel = switch (panelType) {
	    case LOGIN -> {
		panel_LogIn = new PanelLogIn(this);
		yield panel_LogIn;
	    }
	    case TABLES -> {
		panel_Tables = new PanelTables(this);
		yield panel_Tables;
	    }
	};
	// Añade el panel al frame y actualiza.
	add(activePanel);
	revalidate();
	repaint();
    }

    public void logIn() {
	//DialogLogin jDialogLogin = new DialogLogin(this, true);
	//jDialogLogin.setVisible(true);
	menuTHSession.setText("Cerrar sesión");
	menuTHNewWorkout.setEnabled(true);
    }

    public void logOut() {
	setActiveUser(null);
	swapPanel(PanelType.LOGIN);
	menuTHSession.setText("Abrir sesión...");
	menuTHNewWorkout.setEnabled(false);
    }

    public Usuario getActiveUser() {
	return activeUser;
    }

    public void setActiveUser(Usuario activeUser) {
	this.activeUser = activeUser;
    }

    public DefaultTableCellRenderer getCellRenderer() {
	return cellRenderer;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBarMain;
    private javax.swing.JMenu menuTH;
    private javax.swing.JMenuItem menuTHAbout;
    private javax.swing.JMenuItem menuTHNewWorkout;
    private javax.swing.JMenuItem menuTHSalir;
    private javax.swing.JMenuItem menuTHSession;
    private javax.swing.JPopupMenu.Separator separatorTH;
    // End of variables declaration//GEN-END:variables
}
