/**
 *  Project:  PDB 2008
 *  Authors:  Ondrej Lengal,   xlenga00@stud.fit.vutbr.cz
 *            Libor Polcak,    xpolca03@stud.fit.vutbr.cz
 *            Boris Prochazka, xproch63@stud.fit.vutbr.cz
 *            Petr Zemek,      xzemek02@stud.fit.vutbr.cz
 *
 * @brief Animals management form.
 *
 */

package zoo;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief Allows user to manage animals in given pavilon/exhibition.
 */
public class AnimalsManagement extends javax.swing.JFrame {

	/// Relation data in the pavilion/exhibition
	AnimalObjectRelData[] _relationData;
	/// Loacation of the pavilion/exhibition
	ObjectData _location;
	/// Position in the _relationData
	int _relationDataPos;
	/// Reference to ZooView
	ZooView _zooView;

	/**
	 * Creates new form AnimalsManagement
	 *
	 * @param location Location where are the animals that should be managed
	 * @param zv Reference to ZooView
	 */
	public AnimalsManagement(ObjectData location, ZooView zv) {
		initComponents();

		_location = location;
		_zooView = zv;
		this.setTitle("Manage animals in " + location.name);
		setUpAnimalsCombo();
		initForm();
	}

	/**
	 * Check if the form contains valid data
	 *
	 * @return True if it is OK.
	 */
	private boolean checkData() {
		Date from = Conversions.stringToDate(txtDateFrom.getText());
		Date to = Conversions.stringToDate(txtDateTo.getText());
		return cmbAnimals.getSelectedItem() != null &&
						from != null && to != null && from.before(to);
	}

	/**
	 * Fills available animals to the combo box
	 */
	private void setUpAnimalsCombo() {
		AnimalData[] animals = AnimalOperations.getAllAnimals();
		//Set up the editor for the animals id cells.
		for (AnimalData animal : animals) {
			cmbAnimals.addItem(animal);
		}
	}

	private void initForm() {
		_relationData = AnimalOperations.getAnimalsFromObject(_location.tableName, _location.id);
		_relationDataPos = 0;
		showRelation(_relationDataPos);
	}

	/**
	 * Fills the form according to current position
	 *
	 * @param position
	 */
	private void showRelation(int position) {
		// Enable / disable buttons according to the current position
		if ((position + 1) < _relationData.length) {
			btnNext.setEnabled(true);
		} else {
			btnNext.setEnabled(false);
		}

		if (position > 0) {
			btnPrev.setEnabled(true);
		} else {
			btnPrev.setEnabled(false);
		}

		if (position < _relationData.length && position >= 0) {
			// Fill the form
			AnimalData animal = Animals.getAnimalData(_relationData[position].animalId);
			txtDateFrom.setText(Conversions.dateToString(_relationData[position].validFrom));
			txtDateTo.setText(Conversions.dateToString(_relationData[position].validTo));
			_relationDataPos = position;
			cmbAnimals.setSelectedItem(animal);
		} else {
			// Clear the form
			cmbAnimals.setSelectedItem(null);
			txtDateFrom.setText("");
			txtDateTo.setText("");
		}
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    txtDateFrom = new javax.swing.JTextField();
    txtDateTo = new javax.swing.JTextField();
    lblAnimalId = new javax.swing.JLabel();
    lblDateFrom = new javax.swing.JLabel();
    lblDateTo = new javax.swing.JLabel();
    cmbAnimals = new javax.swing.JComboBox();
    btnPrev = new javax.swing.JButton();
    btnNext = new javax.swing.JButton();
    btnInsertNew = new javax.swing.JButton();
    btnDelete = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setName("Form"); // NOI18N

    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(zoo.ZooApp.class).getContext().getResourceMap(AnimalsManagement.class);
    txtDateFrom.setText(resourceMap.getString("txtDateFrom.text")); // NOI18N
    txtDateFrom.setName("txtDateFrom"); // NOI18N

    txtDateTo.setText(resourceMap.getString("txtDateTo.text")); // NOI18N
    txtDateTo.setName("txtDateTo"); // NOI18N

    lblAnimalId.setText(resourceMap.getString("lblAnimalId.text")); // NOI18N
    lblAnimalId.setName("lblAnimalId"); // NOI18N

    lblDateFrom.setText(resourceMap.getString("lblDateFrom.text")); // NOI18N
    lblDateFrom.setName("lblDateFrom"); // NOI18N

    lblDateTo.setText(resourceMap.getString("lblDateTo.text")); // NOI18N
    lblDateTo.setName("lblDateTo"); // NOI18N

    cmbAnimals.setName("cmbAnimals"); // NOI18N

    btnPrev.setText(resourceMap.getString("btnPrev.text")); // NOI18N
    btnPrev.setName("btnPrev"); // NOI18N
    btnPrev.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnPrevActionPerformed(evt);
      }
    });

    btnNext.setText(resourceMap.getString("btnNext.text")); // NOI18N
    btnNext.setName("btnNext"); // NOI18N
    btnNext.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNextActionPerformed(evt);
      }
    });

    btnInsertNew.setText(resourceMap.getString("btnInsertNew.text")); // NOI18N
    btnInsertNew.setName("btnInsertNew"); // NOI18N
    btnInsertNew.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnInsertNewActionPerformed(evt);
      }
    });

    btnDelete.setText(resourceMap.getString("btnDelete.text")); // NOI18N
    btnDelete.setName("btnDelete"); // NOI18N
    btnDelete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnDeleteActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(75, 75, 75)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(lblAnimalId)
          .addComponent(lblDateFrom)
          .addComponent(lblDateTo))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(btnDelete)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
            .addComponent(btnInsertNew))
          .addGroup(layout.createSequentialGroup()
            .addComponent(btnPrev)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
            .addComponent(btnNext))
          .addComponent(cmbAnimals, 0, 151, Short.MAX_VALUE)
          .addComponent(txtDateTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
          .addComponent(txtDateFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
        .addGap(112, 112, 112))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(46, 46, 46)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnPrev)
          .addComponent(btnNext))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblAnimalId)
          .addComponent(cmbAnimals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblDateFrom)
          .addComponent(txtDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txtDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lblDateTo))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnDelete)
          .addComponent(btnInsertNew))
        .addContainerGap(87, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents
	private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
		showRelation(_relationDataPos + 1);
	}//GEN-LAST:event_btnNextActionPerformed

	private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
		showRelation(_relationDataPos - 1);
	}//GEN-LAST:event_btnPrevActionPerformed

	private void btnInsertNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertNewActionPerformed
		if (checkData()) {
			try {
				TemporalQuery.addAnimalToObject((AnimalData)
								cmbAnimals.getSelectedItem(), _location.tableName,
								_location.id, Conversions.stringToDate(txtDateFrom.getText()),
								Conversions.stringToDate(txtDateTo.getText()));
				initForm();
				_zooView.redrawPanels();
			} catch (SQLException ex) {
				Logger.getLogger("zoo").log(Level.SEVERE, "Exception caught (" +
								ex.getClass() + "): " + ex.getMessage());
				Dialogs.displayErrorMessage(null,
								"There was a database error during the last operation.",
								"Database error");
			}
		}
		else {
			Dialogs.displayErrorMessage("Form doesn't contain valid data", "Insert failed");
		}
	}//GEN-LAST:event_btnInsertNewActionPerformed

	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
		if (checkData()) {
			try {
				TemporalQuery.removeAnimalFromObject(((AnimalData)
								cmbAnimals.getSelectedItem()).id, _location.tableName,
								_location.id, Conversions.stringToDate(txtDateFrom.getText()),
								Conversions.stringToDate(txtDateTo.getText()));
				initForm();
				_zooView.redrawPanels();
			} catch (SQLException ex) {
				Logger.getLogger("zoo").log(Level.SEVERE, "Exception caught (" +
								ex.getClass() + "): " + ex.getMessage());
				Dialogs.displayErrorMessage(null,
								"There was a database error during the last operation.",
								"Database error");
			}
		}
		else {
			Dialogs.displayErrorMessage("Form doesn't contain valid data", "Delete failed");
		}

	}//GEN-LAST:event_btnDeleteActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnDelete;
  private javax.swing.JButton btnInsertNew;
  private javax.swing.JButton btnNext;
  private javax.swing.JButton btnPrev;
  private javax.swing.JComboBox cmbAnimals;
  private javax.swing.JLabel lblAnimalId;
  private javax.swing.JLabel lblDateFrom;
  private javax.swing.JLabel lblDateTo;
  private javax.swing.JTextField txtDateFrom;
  private javax.swing.JTextField txtDateTo;
  // End of variables declaration//GEN-END:variables
}
