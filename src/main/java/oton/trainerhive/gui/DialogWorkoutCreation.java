package oton.trainerhive.gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import oton.trainerhive.dataaccess.DataAccess;
import oton.trainerhive.dto.Ejercicio;
import oton.trainerhive.dto.Entrenamiento;
import oton.trainerhive.dto.Usuario;
import oton.trainerhive.gui.tablemodels.EjerciciosSimpleTableModel;

/**
 *
 * @author Alfonso Otón
 */
public final class DialogWorkoutCreation extends javax.swing.JDialog {

    private static final String DEFAULT_COMBOBOX_TEXT = "Añade un ejercicio";
    private final FramePrincipal principal;
    private final PanelTables panelTables;
    private final ArrayList<Ejercicio> addedExercises;
    private final ArrayList<Ejercicio> allExercises;
    private Ejercicio selectedExercise;
    private final Usuario selectedUser;
    private final Entrenamiento newWorkout;

    public DialogWorkoutCreation(FramePrincipal parent, boolean modal, Usuario selectedUser) {
	super(parent, modal);
	this.principal = parent;
	this.panelTables = (PanelTables) principal.getContentPane().getComponent(0);
	this.selectedUser = selectedUser;
	this.newWorkout = new Entrenamiento();
	this.addedExercises = new ArrayList<>();
	this.allExercises = DataAccess.getAllExercicis();

	initComponents();
	setTitle("Nueva actividad para " + this.selectedUser.getNom());
	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/btn_icons/NewWorkout16.png")));
	initializeDialog();
    }

    private void initializeDialog() {
	setLocationRelativeTo(principal);
	initComboBox();
	initTable();
    }

    // Configuración inicial del ComboBox
    private void initComboBox() {
	setComboBoxRenderer();
	setComboBoxListener();
	updateAvailableExercises();
    }

    // Configuración inicial de la Tabla
    private void initTable() {
	configureTableAppearance(tableExercises, scrollPaneExercises);
	setTableListener(tableExercises);
    }

    // Configura el renderizador para mostrar el texto por defecto en el ComboBox
    private void setComboBoxRenderer() {
	comboBoxExercises.setRenderer(new DefaultListCellRenderer() {
	    @Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		setText(value == null ? DEFAULT_COMBOBOX_TEXT : value.toString());
		return c;
	    }
	});
    }

    // Listener de selección en el ComboBox
    private void setComboBoxListener() {
	comboBoxExercises.addActionListener(e -> {
	    if (comboBoxExercises.getSelectedIndex() > 0) {
		addExerciseToTable((Ejercicio) comboBoxExercises.getSelectedItem());
		resetComboBox();
		refreshUI();
	    }
	});
    }

    // Listener de selección de filas en la tabla
    private void setTableListener(JTable exercisesTable) {
	exercisesTable.getSelectionModel().addListSelectionListener(event -> {
	    if (isValidSelection(event, exercisesTable)) {
		selectedExercise = getSelectedExercise(exercisesTable);
		enableExerciseButtons(true);
		updateMoveButtonState();
	    }
	});
    }

    // Actualiza los ejercicios disponibles en el ComboBox
    private void updateAvailableExercises() {
	comboBoxExercises.removeAllItems();
	comboBoxExercises.addItem(null);
	for (Ejercicio ejercicio : allExercises) {
	    if (!addedExercises.contains(ejercicio)) {
		comboBoxExercises.addItem(ejercicio);
	    }
	}
	comboBoxExercises.setEnabled(comboBoxExercises.getItemCount() > 1);
    }

    // Agrega un ejercicio a la tabla
    private void addExerciseToTable(Ejercicio exercise) {
	addedExercises.add(exercise);
    }

    // Elimina el ejercicio seleccionado de la tabla
    private void removeSelectedExercise() {
	if (selectedExercise != null) {
	    addedExercises.remove(selectedExercise);
	    refreshUI();
	}
    }

    // Mueve el ejercicio seleccionado hacia arriba en la lista
    private void moveUpSelectedExercise() {
	int index = addedExercises.indexOf(selectedExercise);
	if (canMoveUp(index)) {
	    Collections.swap(addedExercises, index, index - 1);
	    selectTableRow(index - 1);
	    updateMoveButtonState();
	}
    }

    // Mueve el ejercicio seleccionado hacia abajo en la lista
    private void moveDownSelectedExercise() {
	int index = addedExercises.indexOf(selectedExercise);
	if (canMoveDown(index)) {
	    Collections.swap(addedExercises, index, index + 1);
	    selectTableRow(index + 1);
	    updateMoveButtonState();
	}
    }

    // Actualiza la UI
    private void refreshUI() {
	updateExercisesTable();
	updateAvailableExercises();
	configureTableAppearance(tableExercises, scrollPaneExercises);
	enableExerciseButtons(isRowSelected());
    }

    private void resetComboBox() {
	comboBoxExercises.setSelectedIndex(0);
	comboBoxExercises.hidePopup();
    }

    private void updateExercisesTable() {
	tableExercises.setModel(new EjerciciosSimpleTableModel(addedExercises));
	tableExercises.revalidate();
	tableExercises.repaint();
    }

    private void configureTableAppearance(JTable table, JScrollPane pane) {
	pane.setColumnHeaderView(null);
	table.getColumnModel().getColumn(0).setCellRenderer(principal.getCellRenderer());
    }

    private boolean isValidSelection(ListSelectionEvent event, JTable table) {
	return !event.getValueIsAdjusting() && table.getSelectedRow() >= 0;
    }

    private Ejercicio getSelectedExercise(JTable table) {
	return ((EjerciciosSimpleTableModel) table.getModel()).getExerciseAt(table.getSelectedRow());
    }

    private boolean isRowSelected() {
	return tableExercises.getSelectedRow() != -1;
    }

    private void enableExerciseButtons(boolean enable) {
	buttonRemoveExercise.setEnabled(enable);
	buttonMoveUp.setEnabled(enable);
	buttonMoveDown.setEnabled(enable);
    }

    private void updateMoveButtonState() {
	int index = addedExercises.indexOf(selectedExercise);
	buttonMoveUp.setEnabled(canMoveUp(index));
	buttonMoveDown.setEnabled(canMoveDown(index));
    }

    private boolean canMoveUp(int index) {
	return index > 0;
    }

    private boolean canMoveDown(int index) {
	return index < addedExercises.size() - 1;
    }

    private void selectTableRow(int index) {
	tableExercises.getSelectionModel().setSelectionInterval(index, index);
    }

    private boolean checkForm() {
	return !(!isValidDateFormat()
		|| textFieldWorkoutComments.getText().trim().isEmpty()
		|| selectedUser == null
		|| addedExercises.isEmpty());
    }

    private boolean isValidDateFormat() {
	try {
	    AbstractFormatter formatter = formattedTextFieldDate.getFormatter();
	    String dateText = formattedTextFieldDate.getText();
	    formatter.stringToValue(dateText);
	    return true;
	} catch (ParseException e) {
	    return false;
	}
    }

    private Entrenamiento getNewWorkout() {
	newWorkout.setComments(textFieldWorkoutComments.getText());
	newWorkout.setForDate(formattedTextFieldDate.getText());
	newWorkout.setUserId(selectedUser.getId());
	return newWorkout;
    }

    // Muestra un cuadro de mensaje indicando que el formulario no se ha completado correctamente
    public void showFormError() {
	JOptionPane.showMessageDialog(
		this,
		"Por favor, rellene todos los campos correctamente.", // Mensaje
		"Error en el formulario", // Título
		JOptionPane.WARNING_MESSAGE // Tipo de mensaje
	);
    }

    // WARNING: Do NOT modify this code.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 32767));
        scrollPaneExercises = new javax.swing.JScrollPane();
        tableExercises = new javax.swing.JTable();
        buttonMoveUp = new javax.swing.JButton();
        buttonMoveDown = new javax.swing.JButton();
        labelExerciseName = new javax.swing.JLabel();
        textFieldWorkoutComments = new javax.swing.JTextField();
        toolBar = new javax.swing.JToolBar();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 32767));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        buttonCancel = new javax.swing.JButton();
        buttonSaveWorkout = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 0), new java.awt.Dimension(2, 32767));
        comboBoxExercises = new javax.swing.JComboBox<>();
        buttonRemoveExercise = new javax.swing.JButton();
        labelExerciseName1 = new javax.swing.JLabel();
        formattedTextFieldDate = new javax.swing.JFormattedTextField();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva actividad para Bart");
        setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(400, 400));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 400));

        scrollPaneExercises.setBorder(null);
        scrollPaneExercises.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneExercises.setPreferredSize(new java.awt.Dimension(180, 400));

        tableExercises.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
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
        tableExercises.setFillsViewportHeight(true);
        tableExercises.setFocusable(false);
        tableExercises.setPreferredSize(new java.awt.Dimension(348, 100));
        tableExercises.setRowHeight(32);
        tableExercises.setSelectionBackground(new java.awt.Color(251, 232, 96));
        tableExercises.setSelectionForeground(new java.awt.Color(38, 40, 41));
        tableExercises.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableExercises.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableExercises.setShowHorizontalLines(true);
        tableExercises.getTableHeader().setResizingAllowed(false);
        tableExercises.getTableHeader().setReorderingAllowed(false);
        scrollPaneExercises.setViewportView(tableExercises);
        tableExercises.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableExercises.getColumnModel().getColumnCount() > 0) {
            tableExercises.getColumnModel().getColumn(0).setResizable(false);
        }

        buttonMoveUp.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonMoveUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/MoveUp.png"))); // NOI18N
        buttonMoveUp.setEnabled(false);
        buttonMoveUp.setFocusable(false);
        buttonMoveUp.setMaximumSize(new java.awt.Dimension(22, 22));
        buttonMoveUp.setMinimumSize(new java.awt.Dimension(22, 22));
        buttonMoveUp.setPreferredSize(new java.awt.Dimension(22, 22));
        buttonMoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMoveUpActionPerformed(evt);
            }
        });

        buttonMoveDown.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonMoveDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/MoveDown.png"))); // NOI18N
        buttonMoveDown.setEnabled(false);
        buttonMoveDown.setFocusable(false);
        buttonMoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMoveDownActionPerformed(evt);
            }
        });

        labelExerciseName.setText("Nombre");
        labelExerciseName.setMaximumSize(new java.awt.Dimension(175, 16));
        labelExerciseName.setMinimumSize(new java.awt.Dimension(175, 16));
        labelExerciseName.setPreferredSize(new java.awt.Dimension(175, 16));

        textFieldWorkoutComments.setMaximumSize(new java.awt.Dimension(175, 36));
        textFieldWorkoutComments.setMinimumSize(new java.awt.Dimension(175, 36));
        textFieldWorkoutComments.setPreferredSize(new java.awt.Dimension(175, 36));
        textFieldWorkoutComments.setSelectedTextColor(new java.awt.Color(38, 40, 41));
        textFieldWorkoutComments.setSelectionColor(new java.awt.Color(251, 232, 96));

        toolBar.setBackground(new java.awt.Color(38, 40, 41));
        toolBar.setRollover(true);
        toolBar.setFocusable(false);
        toolBar.setMaximumSize(new java.awt.Dimension(32883, 35));
        toolBar.setMinimumSize(new java.awt.Dimension(116, 35));
        toolBar.setPreferredSize(new java.awt.Dimension(360, 35));
        toolBar.add(filler4);
        toolBar.add(filler1);

        buttonCancel.setText("Cancelar");
        buttonCancel.setFocusable(false);
        buttonCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonCancel.setPreferredSize(new java.awt.Dimension(75, 24));
        buttonCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });
        toolBar.add(buttonCancel);

        buttonSaveWorkout.setText("Asignar");
        buttonSaveWorkout.setFocusable(false);
        buttonSaveWorkout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSaveWorkout.setPreferredSize(new java.awt.Dimension(75, 24));
        buttonSaveWorkout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonSaveWorkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveWorkoutActionPerformed(evt);
            }
        });
        toolBar.add(buttonSaveWorkout);
        toolBar.add(filler2);

        comboBoxExercises.setFocusable(false);
        comboBoxExercises.setLightWeightPopupEnabled(false);
        comboBoxExercises.setMaximumSize(new java.awt.Dimension(188, 27));
        comboBoxExercises.setMinimumSize(new java.awt.Dimension(188, 27));
        comboBoxExercises.setPreferredSize(new java.awt.Dimension(188, 27));

        buttonRemoveExercise.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonRemoveExercise.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_icons/RemoveExerciseFromList.png"))); // NOI18N
        buttonRemoveExercise.setEnabled(false);
        buttonRemoveExercise.setFocusable(false);
        buttonRemoveExercise.setMaximumSize(new java.awt.Dimension(22, 22));
        buttonRemoveExercise.setMinimumSize(new java.awt.Dimension(22, 22));
        buttonRemoveExercise.setPreferredSize(new java.awt.Dimension(22, 22));
        buttonRemoveExercise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveExerciseActionPerformed(evt);
            }
        });

        labelExerciseName1.setText("Fecha (dd/mm/aaaa)");
        labelExerciseName1.setMaximumSize(new java.awt.Dimension(175, 16));
        labelExerciseName1.setMinimumSize(new java.awt.Dimension(175, 16));
        labelExerciseName1.setPreferredSize(new java.awt.Dimension(175, 16));

        formattedTextFieldDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        formattedTextFieldDate.setActionCommand("<Not Set>");
        formattedTextFieldDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formattedTextFieldDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(textFieldWorkoutComments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelExerciseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelExerciseName1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(formattedTextFieldDate)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(comboBoxExercises, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonRemoveExercise, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonMoveUp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonMoveDown, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(scrollPaneExercises, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelExerciseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelExerciseName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldWorkoutComments, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formattedTextFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonMoveDown, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonMoveUp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonRemoveExercise, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboBoxExercises, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneExercises, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMoveUpActionPerformed
	moveUpSelectedExercise();
    }//GEN-LAST:event_buttonMoveUpActionPerformed

    private void buttonMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMoveDownActionPerformed
	moveDownSelectedExercise();
    }//GEN-LAST:event_buttonMoveDownActionPerformed

    private void buttonSaveWorkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveWorkoutActionPerformed
	if (checkForm()) {
	    DataAccess.insertWorkout(getNewWorkout(), addedExercises);
	    panelTables.updateEntrenamientosTable();
	    dispose();
	} else {
	    showFormError();
	}
    }//GEN-LAST:event_buttonSaveWorkoutActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
	dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonRemoveExerciseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveExerciseActionPerformed
	removeSelectedExercise();
    }//GEN-LAST:event_buttonRemoveExerciseActionPerformed

    private void formattedTextFieldDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formattedTextFieldDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formattedTextFieldDateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonMoveDown;
    private javax.swing.JButton buttonMoveUp;
    private javax.swing.JButton buttonRemoveExercise;
    private javax.swing.JButton buttonSaveWorkout;
    private javax.swing.JComboBox<Ejercicio> comboBoxExercises;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JFormattedTextField formattedTextFieldDate;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel labelExerciseName;
    private javax.swing.JLabel labelExerciseName1;
    private javax.swing.JScrollPane scrollPaneExercises;
    private javax.swing.JTable tableExercises;
    private javax.swing.JTextField textFieldWorkoutComments;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
