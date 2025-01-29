package oton.trainerhive.gui;

import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;

import oton.trainerhive.dataaccess.DataAccess;
import oton.trainerhive.dto.Exercise;
import oton.trainerhive.dto.Workout;
import oton.trainerhive.dto.User;
import oton.trainerhive.gui.tablemodels.UsersTableModel;
import oton.trainerhive.gui.tablemodels.ExercisesTableModel;
import oton.trainerhive.gui.tablemodels.WorkoutsTableModel;
import oton.trainerhive.gui.util.UIConstants;

/**
 *
 * @author Alfonso Otón
 */
public class TablesPanel extends javax.swing.JPanel {

    private final MainFrame principal;
    private User selectedUser;

    // Constructor.
    public TablesPanel(MainFrame principal) {
	this.principal = principal;

	initComponents();
	setUpPanelTables();
	updateUserInfo();
    }

    // Configuración de los paneles y las tablas.
    private void setUpPanelTables() {
	configureTablePanelBorders();
	configureTableListeners();
	loadAlumnosTable();
	formatTableColumns();
    }

    // Bordes de los paneles.
    private void configureTablePanelBorders() {
	scrollPaneAlumnos.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, UIConstants.GRIS_OSCURO));
	scrollPaneEntrenamientos.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, UIConstants.GRIS_MEDIO));
	scrollPaneEjercicios.setBorder(BorderFactory.createEmptyBorder());
    }

    // Ancho de columnas y márgenes de celda.
    private void formatTableColumns() {
	formatTable(tableAlumnos, UIConstants.COLUMN_WIDTH_ALUMNOS);
	formatTable(tableEntrenamientos, UIConstants.COLUMN_WIDTH_ENTRENAMIENTOS_1, UIConstants.COLUMN_WIDTH_ENTRENAMIENTOS_2);
	formatTable(tableEjercicios, UIConstants.COLUMN_WIDTH_EJERCICIOS);
    }

    // Carga los alumnos y selecciona el primero.
    private void loadAlumnosTable() {
	tableAlumnos.setModel(new UsersTableModel(DataAccess.getAllUsersByInstructor(principal.getActiveUser().getId())));
	if (tableAlumnos.getRowCount() > 0) {
	    tableAlumnos.getSelectionModel().setSelectionInterval(0, 0);
	    tableAlumnos.requestFocus();
	}
	tableAlumnos.revalidate();
	tableAlumnos.repaint();
    }

    // Configura los listeners de las tablas.
    private void configureTableListeners() {
	setupAlumnosListener(tableAlumnos, tableEntrenamientos, tableEjercicios);
	setupEntrenamientosListener(tableEntrenamientos, tableEjercicios);
    }

    // ListSelectionListener de la tabla alumnos.
    private void setupAlumnosListener(JTable alumnosTabla, JTable entrenamientosTabla, JTable ejerciciosTabla) {
	alumnosTabla.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
	    // Si la selección es válida, obtiene el alumno seleccionado,
	    // actualiza la tabla de entrenamientos y limpia la tabla de ejercicios.
	    if (isValidSelection(event, alumnosTabla)) {
		selectedUser = getSelectedAlumno(alumnosTabla);
		updateEntrenamientosTable(entrenamientosTabla, selectedUser);
		clearEjerciciosTable(ejerciciosTabla);
	    }
	});
    }

    public void updateEntrenamientosTable() {
	updateEntrenamientosTable(tableEntrenamientos, selectedUser);
	clearEjerciciosTable(tableEjercicios);
    }

    // ListSelectionListener de la tabla entrenamientos.
    private void setupEntrenamientosListener(JTable entrenamientosTabla, JTable ejerciciosTabla) {
	entrenamientosTabla.getSelectionModel().addListSelectionListener(event -> {
	    // Si la selección es válida, obtiene el entrenamiento seleccionado
	    // y muestra los ejercicios correspondientes.
	    if (isValidSelection(event, entrenamientosTabla)) {
		Workout entrenamiento = getSelectedEntrenamiento(entrenamientosTabla);
		mostrarEjerciciosDeEntrenamiento(ejerciciosTabla, entrenamiento);
	    }
	});
    }

    // Verifica que cambia la selección y que la fila seleccionada sea válida.
    private boolean isValidSelection(ListSelectionEvent event, JTable table) {
	return !event.getValueIsAdjusting() && table.getSelectedRow() >= 0;
    }

    // Obtiene el modelo de la tabla de alumnos y devuelve el alumno de la fila seleccionada.
    private User getSelectedAlumno(JTable alumnosTabla) {
	UsersTableModel model = (UsersTableModel) alumnosTabla.getModel();
	return model.getUserAt(alumnosTabla.getSelectedRow());
    }

    // Obtiene el modelo de la tabla de entrenamientos y devuelve el entrenamiento en la fila seleccionada.
    private Workout getSelectedEntrenamiento(JTable entrenamientosTabla) {
	WorkoutsTableModel model = (WorkoutsTableModel) entrenamientosTabla.getModel();
	return model.getEntrenamientoAt(entrenamientosTabla.getSelectedRow());
    }

    // Limpia la tabla de ejercicios actualizándola con una lista vacía.
    private void clearEjerciciosTable(JTable ejerciciosTabla) {
	updateEjerciciosTable(ejerciciosTabla, new ArrayList<>());
    }

    // Muestra los ejercicios asociados al entrenamiento seleccionado en la tabla de ejercicios.
    private void mostrarEjerciciosDeEntrenamiento(JTable ejerciciosTabla, Workout entrenamiento) {
	updateEjerciciosTable(ejerciciosTabla, DataAccess.getExercicisPerWorkout(entrenamiento));
    }

    // Actualiza el contenido de la tabla de entrenamientos.
    private void updateEntrenamientosTable(JTable entrenamientosTabla, User alumnoSeleccionado) {
	ArrayList<Workout> entrenamientos = DataAccess.getWorkoutsPerUser(alumnoSeleccionado);
	entrenamientosTabla.setModel(new WorkoutsTableModel(entrenamientos));
	formatTable(entrenamientosTabla, UIConstants.COLUMN_WIDTH_ENTRENAMIENTOS_1, UIConstants.COLUMN_WIDTH_ENTRENAMIENTOS_2);
    }

    // Actualiza el contenido de la tabla de ejercicios.
    private void updateEjerciciosTable(JTable ejerciciosTabla, ArrayList<Exercise> ejercicios) {
	ejerciciosTabla.setModel(new ExercisesTableModel(ejercicios));
	formatTable(ejerciciosTabla, UIConstants.COLUMN_WIDTH_EJERCICIOS);
    }

    // Configura las columnas de una tabla según el ancho especificado
    private void formatTable(JTable table, int... anchos) {
	if (anchos.length != table.getColumnCount()) {
	    System.err.println("Error: el número de anchos no coincide con el número de columnas de la tabla.");
	    return;
	}
	for (int i = 0; i < anchos.length; i++) {
	    table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
	    table.getColumnModel().getColumn(i).setCellRenderer(principal.getCellRenderer());
	}
    }

    private void updateUserInfo() {
	// Muestra el número de alumnos que tiene este instructor
	labelUsersCount.setText(tableAlumnos.getRowCount() + " alumnos registrados.");
	// Muestra el nombre e Email del instructor
	labelActiveUser.setText(principal.getActiveUser().getName() + " (" + principal.getActiveUser().getEmail() + ")");
    }

    public User getSelectedUser() {
	return selectedUser;
    }

    private void logOut() {
	principal.logOut();
    }

    // WARNING: Do NOT modify this code.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scrollPaneAlumnos = new javax.swing.JScrollPane();
        tableAlumnos = new javax.swing.JTable();
        toolBarUsers = new javax.swing.JToolBar();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(5, 32767));
        labelUsersCount = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        labelActiveUser = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        buttonLogOut = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(4, 0), new java.awt.Dimension(4, 0), new java.awt.Dimension(4, 32767));
        panelEntramientos = new javax.swing.JPanel();
        scrollPaneEntrenamientos = new javax.swing.JScrollPane();
        tableEntrenamientos = new javax.swing.JTable();
        scrollPaneEjercicios = new javax.swing.JScrollPane();
        tableEjercicios = new javax.swing.JTable();
        toolBarEntrenamientos = new javax.swing.JToolBar();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(16, 0), new java.awt.Dimension(16, 0), new java.awt.Dimension(16, 30));
        buttonNewTask = new javax.swing.JButton();
        buttonEditTask = new javax.swing.JButton();
        buttonRemoveTask = new javax.swing.JButton();
        buttonWatchTask = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        buttonNewWorkout = new javax.swing.JButton();
        buttonEditWorkout = new javax.swing.JButton();
        buttonEditWorkout1 = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 10));

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        scrollPaneAlumnos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneAlumnos.setAutoscrolls(true);
        scrollPaneAlumnos.setPreferredSize(new java.awt.Dimension(240, 402));

        tableAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alumnos"
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
        tableAlumnos.setFillsViewportHeight(true);
        tableAlumnos.setNextFocusableComponent(tableEntrenamientos);
        tableAlumnos.setRowHeight(48);
        tableAlumnos.setSelectionBackground(new java.awt.Color(251, 232, 96));
        tableAlumnos.setSelectionForeground(new java.awt.Color(38, 40, 41));
        tableAlumnos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableAlumnos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableAlumnos.setShowGrid(false);
        tableAlumnos.setShowHorizontalLines(true);
        tableAlumnos.getTableHeader().setReorderingAllowed(false);
        scrollPaneAlumnos.setViewportView(tableAlumnos);
        if (tableAlumnos.getColumnModel().getColumnCount() > 0) {
            tableAlumnos.getColumnModel().getColumn(0).setResizable(false);
            tableAlumnos.getColumnModel().getColumn(0).setPreferredWidth(239);
        }

        jPanel1.add(scrollPaneAlumnos, java.awt.BorderLayout.WEST);

        add(jPanel1, java.awt.BorderLayout.LINE_START);

        toolBarUsers.setBackground(new java.awt.Color(99, 101, 105));
        toolBarUsers.setRollover(true);
        toolBarUsers.setMaximumSize(new java.awt.Dimension(960, 26));
        toolBarUsers.setMinimumSize(new java.awt.Dimension(960, 26));
        toolBarUsers.setPreferredSize(new java.awt.Dimension(960, 26));
        toolBarUsers.add(filler1);

        labelUsersCount.setForeground(new java.awt.Color(60, 63, 65));
        labelUsersCount.setText("Número de alumnos");
        labelUsersCount.setMaximumSize(new java.awt.Dimension(200, 16));
        labelUsersCount.setMinimumSize(new java.awt.Dimension(200, 16));
        labelUsersCount.setPreferredSize(new java.awt.Dimension(200, 16));
        toolBarUsers.add(labelUsersCount);
        toolBarUsers.add(filler2);

        labelActiveUser.setFont(labelActiveUser.getFont().deriveFont(labelActiveUser.getFont().getStyle() | java.awt.Font.BOLD));
        labelActiveUser.setForeground(new java.awt.Color(60, 63, 65));
        labelActiveUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelActiveUser.setText("Usuario activo");
        labelActiveUser.setFocusable(false);
        labelActiveUser.setMaximumSize(new java.awt.Dimension(400, 16));
        labelActiveUser.setMinimumSize(new java.awt.Dimension(400, 16));
        labelActiveUser.setPreferredSize(new java.awt.Dimension(400, 16));
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

        add(toolBarUsers, java.awt.BorderLayout.PAGE_END);

        panelEntramientos.setFocusable(false);
        panelEntramientos.setLayout(new java.awt.BorderLayout());

        scrollPaneEntrenamientos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneEntrenamientos.setPreferredSize(new java.awt.Dimension(480, 402));

        tableEntrenamientos.setBackground(new java.awt.Color(60, 63, 65));
        tableEntrenamientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entrenamiento", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableEntrenamientos.setFillsViewportHeight(true);
        tableEntrenamientos.setFocusable(false);
        tableEntrenamientos.setMaximumSize(new java.awt.Dimension(480, 192));
        tableEntrenamientos.setMinimumSize(new java.awt.Dimension(480, 192));
        tableEntrenamientos.setRequestFocusEnabled(false);
        tableEntrenamientos.setRowHeight(48);
        tableEntrenamientos.setSelectionBackground(new java.awt.Color(251, 232, 96));
        tableEntrenamientos.setSelectionForeground(new java.awt.Color(38, 40, 41));
        tableEntrenamientos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableEntrenamientos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableEntrenamientos.setShowGrid(false);
        tableEntrenamientos.setShowHorizontalLines(true);
        scrollPaneEntrenamientos.setViewportView(tableEntrenamientos);
        if (tableEntrenamientos.getColumnModel().getColumnCount() > 0) {
            tableEntrenamientos.getColumnModel().getColumn(0).setResizable(false);
            tableEntrenamientos.getColumnModel().getColumn(0).setPreferredWidth(240);
            tableEntrenamientos.getColumnModel().getColumn(1).setResizable(false);
            tableEntrenamientos.getColumnModel().getColumn(1).setPreferredWidth(240);
        }

        panelEntramientos.add(scrollPaneEntrenamientos, java.awt.BorderLayout.CENTER);

        scrollPaneEjercicios.setBorder(null);
        scrollPaneEjercicios.setPreferredSize(new java.awt.Dimension(240, 402));

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
        tableEjercicios.setFillsViewportHeight(true);
        tableEjercicios.setFocusable(false);
        tableEjercicios.setRequestFocusEnabled(false);
        tableEjercicios.setRowHeight(48);
        tableEjercicios.setSelectionBackground(new java.awt.Color(251, 232, 96));
        tableEjercicios.setSelectionForeground(new java.awt.Color(38, 40, 41));
        tableEjercicios.getTableHeader().setReorderingAllowed(false);
        scrollPaneEjercicios.setViewportView(tableEjercicios);
        if (tableEjercicios.getColumnModel().getColumnCount() > 0) {
            tableEjercicios.getColumnModel().getColumn(0).setResizable(false);
            tableEjercicios.getColumnModel().getColumn(0).setPreferredWidth(240);
        }

        panelEntramientos.add(scrollPaneEjercicios, java.awt.BorderLayout.LINE_END);

        toolBarEntrenamientos.setBackground(new java.awt.Color(38, 40, 41));
        toolBarEntrenamientos.setRollover(true);
        toolBarEntrenamientos.setFocusable(false);
        toolBarEntrenamientos.setPreferredSize(new java.awt.Dimension(100, 64));
        toolBarEntrenamientos.add(filler7);

        buttonNewTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/NewTask.png"))); // NOI18N
        buttonNewTask.setText("Asignar");
        buttonNewTask.setFocusable(false);
        buttonNewTask.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNewTask.setPreferredSize(new java.awt.Dimension(72, 24));
        buttonNewTask.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNewTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewTaskActionPerformed(evt);
            }
        });
        toolBarEntrenamientos.add(buttonNewTask);

        buttonEditTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/EditTask.png"))); // NOI18N
        buttonEditTask.setText("Modificar");
        buttonEditTask.setEnabled(false);
        buttonEditTask.setFocusable(false);
        buttonEditTask.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEditTask.setPreferredSize(new java.awt.Dimension(72, 24));
        buttonEditTask.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarEntrenamientos.add(buttonEditTask);

        buttonRemoveTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/DeleteTask.png"))); // NOI18N
        buttonRemoveTask.setText("Quitar");
        buttonRemoveTask.setFocusable(false);
        buttonRemoveTask.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonRemoveTask.setPreferredSize(new java.awt.Dimension(72, 24));
        buttonRemoveTask.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonRemoveTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveTaskActionPerformed(evt);
            }
        });
        toolBarEntrenamientos.add(buttonRemoveTask);

        buttonWatchTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/WatchWorkout.png"))); // NOI18N
        buttonWatchTask.setText("Corregir");
        buttonWatchTask.setEnabled(false);
        buttonWatchTask.setFocusable(false);
        buttonWatchTask.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonWatchTask.setPreferredSize(new java.awt.Dimension(72, 24));
        buttonWatchTask.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarEntrenamientos.add(buttonWatchTask);
        toolBarEntrenamientos.add(filler4);

        buttonNewWorkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/NewWorkout.png"))); // NOI18N
        buttonNewWorkout.setText("Crear");
        buttonNewWorkout.setEnabled(false);
        buttonNewWorkout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNewWorkout.setPreferredSize(new java.awt.Dimension(72, 24));
        buttonNewWorkout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNewWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewWorkoutActionPerformed(evt);
            }
        });
        toolBarEntrenamientos.add(buttonNewWorkout);

        buttonEditWorkout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/EditWorkout.png"))); // NOI18N
        buttonEditWorkout.setText("Editar");
        buttonEditWorkout.setEnabled(false);
        buttonEditWorkout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEditWorkout.setPreferredSize(new java.awt.Dimension(72, 24));
        buttonEditWorkout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarEntrenamientos.add(buttonEditWorkout);

        buttonEditWorkout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/RemoveWorkout.png"))); // NOI18N
        buttonEditWorkout1.setText("Borrar");
        buttonEditWorkout1.setEnabled(false);
        buttonEditWorkout1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEditWorkout1.setPreferredSize(new java.awt.Dimension(72, 24));
        buttonEditWorkout1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarEntrenamientos.add(buttonEditWorkout1);
        toolBarEntrenamientos.add(filler5);

        panelEntramientos.add(toolBarEntrenamientos, java.awt.BorderLayout.PAGE_END);

        add(panelEntramientos, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
	logOut();
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void buttonNewWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewWorkoutActionPerformed
    }//GEN-LAST:event_buttonNewWorkoutActionPerformed

    private void buttonNewTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewTaskActionPerformed
	WorkoutCreationDialog dialogCreateWorkout = new WorkoutCreationDialog(principal, true, selectedUser);
	dialogCreateWorkout.setVisible(true);
    }//GEN-LAST:event_buttonNewTaskActionPerformed

    private void buttonRemoveTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveTaskActionPerformed
	Workout selectedEntrenamiento = getSelectedEntrenamiento(tableEntrenamientos);

	if (selectedEntrenamiento == null) {
	    return;
	}

	boolean deleted = DataAccess.deleteWorkout(selectedEntrenamiento.getId());

	if (deleted) {
	    updateEntrenamientosTable();
	}
    }//GEN-LAST:event_buttonRemoveTaskActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEditTask;
    private javax.swing.JButton buttonEditWorkout;
    private javax.swing.JButton buttonEditWorkout1;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonNewTask;
    private javax.swing.JButton buttonNewWorkout;
    private javax.swing.JButton buttonRemoveTask;
    private javax.swing.JButton buttonWatchTask;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelActiveUser;
    private javax.swing.JLabel labelUsersCount;
    private javax.swing.JPanel panelEntramientos;
    private javax.swing.JScrollPane scrollPaneAlumnos;
    private javax.swing.JScrollPane scrollPaneEjercicios;
    private javax.swing.JScrollPane scrollPaneEntrenamientos;
    private javax.swing.JTable tableAlumnos;
    private javax.swing.JTable tableEjercicios;
    private javax.swing.JTable tableEntrenamientos;
    private javax.swing.JToolBar toolBarEntrenamientos;
    private javax.swing.JToolBar toolBarUsers;
    // End of variables declaration//GEN-END:variables
}
