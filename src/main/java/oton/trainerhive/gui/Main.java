package oton.trainerhive.gui;

// Librerías externas
import com.formdev.flatlaf.FlatDarkLaf;

// Librerías estándar de Java
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

// Librerías de Swing
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

// Clases del proyecto
import oton.trainerhive.dataaccess.DataAccess;
import oton.trainerhive.dto.Ejercicio;
import oton.trainerhive.dto.Entrenamiento;
import oton.trainerhive.dto.Usuario;
import oton.trainerhive.gui.tablemodels.AlumnosTableModel;
import oton.trainerhive.gui.tablemodels.EjerciciosTableModel;
import oton.trainerhive.gui.tablemodels.EntrenamientosTableModel;

/**
 *
 * @author Kailuss
 */
public class Main extends javax.swing.JFrame {

    public JPanel panel_LogIn;			// Panel de bienvenida
    public JPanel panel_Tables;			// Panel de usuarios
    private JPanel activePanel;			// Panel activo
    public Usuario activeUser;			// Usuario que ha iniciado sesión
    private DefaultTableCellRenderer renderer;	// Renderizador personalizado para celdas

    public Main() {

	// Componentes y configuraciones
	setCellsRendererFormat();   // Configura el formato del renderizador de celdas
	initComponents();	    // Inicializa los componentes gráficos del JFrame

	// Paneles
	panel_LogIn = panelLogIn;   // Panel de inicio de sesión
	panel_Tables = panelTables; // Panel de tablas
	activePanel = panel_LogIn;  // Panel activo al inicio

	// Comportamiento de la ventana
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Deja de ejecutar el programa al cerrar la ventana
	this.setLocationRelativeTo(null);			// Centra el jFrame en pantalla
    }

    // This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTables = new javax.swing.JPanel();
        toolBarUsers = new javax.swing.JToolBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(5, 32767));
        labelUsersCount = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        labelActiveUser = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        buttonLogOut = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(4, 0), new java.awt.Dimension(4, 0), new java.awt.Dimension(4, 32767));
        scrollPaneAlumnos = new javax.swing.JScrollPane();
        tableAlumnos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        scrollPaneEntrenamientos = new javax.swing.JScrollPane();
        tableEntrenamientos = new javax.swing.JTable();
        scrollPaneEjercicios = new javax.swing.JScrollPane();
        tableEjercicios = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panelLogIn = new javax.swing.JPanel();
        labelLogo = new javax.swing.JLabel();
        buttonLogin = new javax.swing.JButton();
        labelLogo1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        menuBarMain = new javax.swing.JMenuBar();
        menuTH = new javax.swing.JMenu();
        menuTHAcercaDe = new javax.swing.JMenuItem();
        separatorTH = new javax.swing.JPopupMenu.Separator();
        menuTHSalir = new javax.swing.JMenuItem();

        panelTables.setMaximumSize(new java.awt.Dimension(960, 570));
        panelTables.setMinimumSize(new java.awt.Dimension(960, 570));
        panelTables.setPreferredSize(new java.awt.Dimension(960, 570));
        panelTables.setLayout(new java.awt.BorderLayout());

        toolBarUsers.setBackground(new java.awt.Color(99, 101, 105));
        toolBarUsers.setRollover(true);
        toolBarUsers.setMaximumSize(new java.awt.Dimension(960, 26));
        toolBarUsers.setMinimumSize(new java.awt.Dimension(960, 26));
        toolBarUsers.setPreferredSize(new java.awt.Dimension(960, 26));
        toolBarUsers.add(filler1);

        labelUsersCount.setForeground(new java.awt.Color(60, 63, 65));
        labelUsersCount.setText("Número de alumnos");
        toolBarUsers.add(labelUsersCount);
        toolBarUsers.add(filler2);

        labelActiveUser.setFont(labelActiveUser.getFont().deriveFont(labelActiveUser.getFont().getStyle() | java.awt.Font.BOLD));
        labelActiveUser.setForeground(new java.awt.Color(60, 63, 65));
        labelActiveUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelActiveUser.setText("Usuario activo");
        labelActiveUser.setFocusable(false);
        labelActiveUser.setMaximumSize(new java.awt.Dimension(256, 16));
        labelActiveUser.setMinimumSize(new java.awt.Dimension(256, 16));
        labelActiveUser.setPreferredSize(new java.awt.Dimension(256, 16));
        labelActiveUser.setRequestFocusEnabled(false);
        labelActiveUser.setVerifyInputWhenFocusTarget(false);
        toolBarUsers.add(labelActiveUser);
        toolBarUsers.add(filler6);

        buttonLogOut.setBackground(new java.awt.Color(175, 75, 75));
        buttonLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout_icon.png"))); // NOI18N
        buttonLogOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonLogOut.setMaximumSize(new java.awt.Dimension(24, 24));
        buttonLogOut.setMinimumSize(new java.awt.Dimension(24, 24));
        buttonLogOut.setPreferredSize(new java.awt.Dimension(26, 26));
        buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogOutActionPerformed(evt);
            }
        });
        toolBarUsers.add(buttonLogOut);
        toolBarUsers.add(filler3);

        panelTables.add(toolBarUsers, java.awt.BorderLayout.PAGE_END);

        scrollPaneAlumnos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneAlumnos.setPreferredSize(new java.awt.Dimension(240, 402));
        scrollPaneAlumnos.setViewportView(tableAlumnos);

        tableAlumnos.setModel(tableAlumnos.getModel());
        tableAlumnos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableAlumnos.setRowHeight(48);
        tableAlumnos.setSelectionBackground(new java.awt.Color(251, 232, 96));
        tableAlumnos.setSelectionForeground(new java.awt.Color(38, 40, 41));
        tableAlumnos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableAlumnos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableAlumnos.setShowGrid(false);
        tableAlumnos.setShowHorizontalLines(true);
        tableAlumnos.getTableHeader().setReorderingAllowed(false);
        scrollPaneAlumnos.setViewportView(tableAlumnos);

        panelTables.add(scrollPaneAlumnos, java.awt.BorderLayout.WEST);

        jPanel1.setFocusable(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        scrollPaneEntrenamientos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneEntrenamientos.setPreferredSize(new java.awt.Dimension(480, 402));
        scrollPaneEntrenamientos.setViewportView(tableEntrenamientos);

        tableEntrenamientos.setBackground(new java.awt.Color(60, 63, 65));
        tableEntrenamientos.setModel(tableEntrenamientos.getModel());
        tableEntrenamientos.setFocusable(false);
        tableEntrenamientos.setMaximumSize(new java.awt.Dimension(660, 192));
        tableEntrenamientos.setMinimumSize(new java.awt.Dimension(660, 192));
        tableEntrenamientos.setPreferredSize(new java.awt.Dimension(660, 192));
        tableEntrenamientos.setRequestFocusEnabled(false);
        tableEntrenamientos.setRowHeight(48);
        tableEntrenamientos.setSelectionBackground(new java.awt.Color(251, 232, 96));
        tableEntrenamientos.setSelectionForeground(new java.awt.Color(38, 40, 41));
        tableEntrenamientos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableEntrenamientos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableEntrenamientos.setShowGrid(false);
        tableEntrenamientos.setShowHorizontalLines(true);
        tableEntrenamientos.getTableHeader().setResizingAllowed(false);
        tableEntrenamientos.getTableHeader().setReorderingAllowed(false);
        scrollPaneEntrenamientos.setViewportView(tableEntrenamientos);

        jPanel1.add(scrollPaneEntrenamientos, java.awt.BorderLayout.CENTER);

        scrollPaneEjercicios.setPreferredSize(new java.awt.Dimension(240, 402));
        scrollPaneEjercicios.setViewportView(tableEjercicios);

        tableEjercicios.setBackground(new java.awt.Color(60, 63, 65));
        tableEjercicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Ejercicios"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableEjercicios.setEnabled(false);
        tableEjercicios.setFocusable(false);
        tableEjercicios.setRequestFocusEnabled(false);
        tableEjercicios.setRowHeight(48);
        tableEjercicios.setSelectionBackground(new java.awt.Color(251, 232, 96));
        tableEjercicios.setSelectionForeground(new java.awt.Color(38, 40, 41));
        tableEjercicios.getTableHeader().setReorderingAllowed(false);
        scrollPaneEjercicios.setViewportView(tableEjercicios);
        tableEjercicios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableEjercicios.getColumnModel().getColumnCount() > 0) {
            tableEjercicios.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel1.add(scrollPaneEjercicios, java.awt.BorderLayout.LINE_END);

        jToolBar1.setBackground(new java.awt.Color(38, 40, 41));
        jToolBar1.setRollover(true);
        jToolBar1.setPreferredSize(new java.awt.Dimension(100, 32));

        jButton2.setText("Ver");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setPreferredSize(new java.awt.Dimension(56, 24));
        jButton2.setSelected(true);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setText("Editar");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setPreferredSize(new java.awt.Dimension(56, 24));
        jButton3.setSelected(true);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);
        jToolBar1.add(filler4);

        jLabel1.setText("Ejercicios  ");
        jToolBar1.add(jLabel1);

        jButton1.setText("Editar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPreferredSize(new java.awt.Dimension(64, 24));
        jButton1.setSelected(true);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton4.setText("Nuevo");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPreferredSize(new java.awt.Dimension(64, 24));
        jButton4.setSelected(true);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_END);

        panelTables.add(jPanel1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(960, 600));
        setName("frameMain"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(960, 600));

        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoTHanimado.gif"))); // NOI18N
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

        labelLogo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoTHtitle.png"))); // NOI18N
        labelLogo1.setAlignmentX(0.5F);

        jButton5.setBackground(new java.awt.Color(60, 63, 65));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jButton5.setText("www.trainerhive.com");
        jButton5.setBorder(null);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusable(false);
        jButton5.setRequestFocusEnabled(false);
        jButton5.setRolloverEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLogInLayout = new javax.swing.GroupLayout(panelLogIn);
        panelLogIn.setLayout(panelLogInLayout);
        panelLogInLayout.setHorizontalGroup(
            panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogInLayout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLogo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(352, 352, 352))
            .addGroup(panelLogInLayout.createSequentialGroup()
                .addGap(405, 405, 405)
                .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLogInLayout.setVerticalGroup(
            panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogInLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(labelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLogo1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(jButton5)
                .addGap(45, 45, 45))
        );

        getContentPane().add(panelLogIn, java.awt.BorderLayout.CENTER);

        menuBarMain.setBorder(null);
        menuBarMain.setBorderPainted(false);
        menuBarMain.setFocusable(false);
        menuBarMain.setMaximumSize(new java.awt.Dimension(164, 30));
        menuBarMain.setMinimumSize(new java.awt.Dimension(164, 30));
        menuBarMain.setPreferredSize(new java.awt.Dimension(164, 30));

        menuTH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TrainerHiveLogo16.png"))); // NOI18N
        menuTH.setText("Trainer Hive");
        menuTH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        menuTHAcercaDe.setText("Acerca de...");
        menuTHAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTHAcercaDeActionPerformed(evt);
            }
        });
        menuTH.add(menuTHAcercaDe);
        menuTH.add(separatorTH);

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

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
	DialogLogin jDialogLogin = new DialogLogin(this, true);
	jDialogLogin.setVisible(true);
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void menuTHSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTHSalirActionPerformed
	System.exit(0);
    }//GEN-LAST:event_menuTHSalirActionPerformed

    private void menuTHAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTHAcercaDeActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_menuTHAcercaDeActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
	swapPanel(panel_LogIn);
	activeUser = null;
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	// Establece FlatLaf Dark como el tema de la aplicación
	try {
            javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
	// Crea y muestra el form
	java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    public void swapPanel(JPanel newPanel) {

	// Elimina el panel actual si existe
	if (activePanel != null) {
	    this.remove(activePanel);
	}

	// Añade el nuevo panel y lo establece como el activo
	this.add(newPanel);
	activePanel = newPanel;

	// Configura el panel de tablas si es el seleccionado
	if (newPanel == panelTables) {
	    setUpPanelTables();
	    formatAlumnosTable(tableAlumnos);
	    formatEntrenamientosTable(tableEntrenamientos);
	    formatEjerciciosTable(tableEjercicios);
	    setUpToolBar();
	}

	// Revalidación y repintado
	this.revalidate();
	this.repaint();
    }

    private void setUpPanelTables() {

	// Elimina el borde de los paneles
	scrollPaneEntrenamientos.setBorder(BorderFactory.createEmptyBorder());
	scrollPaneAlumnos.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(38, 40, 41)));
	scrollPaneEjercicios.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(90, 94, 96)));

	// Carga los alumnos en la tabla
	tableAlumnos.setModel(new AlumnosTableModel(DataAccess.getAllUsersByInstructor(activeUser.getId())));

	// Añade los listeners
	setUpTableListeners(tableAlumnos, tableEntrenamientos, tableEjercicios);

    // Selecciona la primera fila y aplica el efecto visual
	if (tableAlumnos.getRowCount() > 0) {
	    tableAlumnos.getSelectionModel().setSelectionInterval(0, 0);
	    tableAlumnos.requestFocus();
	}
	//tableAlumnos.revalidate();
	tableAlumnos.repaint();
    }

    public void setUpTableListeners(JTable alumnosTabla, JTable entrenamientosTabla, JTable ejerciciosTabla) {
	// Listener para la tabla Alumnos
	alumnosTabla.getSelectionModel().addListSelectionListener(event -> {
	    if (!event.getValueIsAdjusting() && alumnosTabla.getSelectedRow() >= 0) {
		Usuario alumnoSeleccionado = ((AlumnosTableModel) alumnosTabla.getModel()).getUserAt(alumnosTabla.getSelectedRow());
		updateEntrenamientosTable(entrenamientosTabla, alumnoSeleccionado);
		updateEjerciciosTable(ejerciciosTabla, new ArrayList<>());
	    }
	});
	// Listener para la tabla Entrenamientos
	entrenamientosTabla.getSelectionModel().addListSelectionListener(event -> {
	    if (!event.getValueIsAdjusting() && entrenamientosTabla.getSelectedRow() >= 0) {
		Entrenamiento entrenamientoSeleccionado = ((EntrenamientosTableModel) entrenamientosTabla.getModel()).getEntrenamientoAt(entrenamientosTabla.getSelectedRow());
		updateEjerciciosTable(ejerciciosTabla, DataAccess.getExercicisPerWorkout(entrenamientoSeleccionado));
	    }
	});
    }

    private void updateEntrenamientosTable(JTable entrenamientosTabla, Usuario alumnoSeleccionado) {
	ArrayList<Entrenamiento> entrenamientos = DataAccess.getWorkoutsPerUser(alumnoSeleccionado);
	entrenamientosTabla.setModel(new EntrenamientosTableModel(entrenamientos));
	formatEntrenamientosTable(entrenamientosTabla);
    }

    private void updateEjerciciosTable(JTable ejerciciosTabla, ArrayList<Ejercicio> ejercicios) {
	ejerciciosTabla.setModel(new EjerciciosTableModel(ejercicios));
	formatEjerciciosTable(ejerciciosTabla);
    }

    // Configura las columnas de una tabla según el ancho especificado
    private void formatTable(JTable table, int[] anchos) {
	for (int i = 0; i < anchos.length; i++) {
	    table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
	    table.getColumnModel().getColumn(i).setCellRenderer(renderer);
	}
    }

    // Método para formatear las tablas con configuraciones específicas
    private void formatEntrenamientosTable(JTable table) {
	formatTable(table, new int[]{240, 240});
    }

    private void formatAlumnosTable(JTable table) {
	formatTable(table, new int[]{239});
    }

    private void formatEjerciciosTable(JTable table) {
	formatTable(table, new int[]{239});
    }

    private void setUpToolBar() {
	// Muestra el número de alumnos que tiene este instructor
	labelUsersCount.setText(tableAlumnos.getRowCount() + " alumnos registrados.");
	// Muestra el nombre del instructor
	labelActiveUser.setText(activeUser.getNom());
    }

    private void setCellsRendererFormat() {
	// Aplica márgenes internos al renderer de las celdas
	this.renderer = new DefaultTableCellRenderer() {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (cell instanceof JLabel label) {
		    label.setBorder(new EmptyBorder(0, 16, 0, 16));
		}
		return cell;
	    }
	};
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonLogin;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler6;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelActiveUser;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelLogo1;
    private javax.swing.JLabel labelUsersCount;
    private javax.swing.JMenuBar menuBarMain;
    private javax.swing.JMenu menuTH;
    private javax.swing.JMenuItem menuTHAcercaDe;
    private javax.swing.JMenuItem menuTHSalir;
    private javax.swing.JPanel panelLogIn;
    private javax.swing.JPanel panelTables;
    private javax.swing.JScrollPane scrollPaneAlumnos;
    private javax.swing.JScrollPane scrollPaneEjercicios;
    private javax.swing.JScrollPane scrollPaneEntrenamientos;
    private javax.swing.JPopupMenu.Separator separatorTH;
    private javax.swing.JTable tableAlumnos;
    private javax.swing.JTable tableEjercicios;
    private javax.swing.JTable tableEntrenamientos;
    private javax.swing.JToolBar toolBarUsers;
    // End of variables declaration//GEN-END:variables
}
