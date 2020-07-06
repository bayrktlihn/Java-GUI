package com.bayrktlihn;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.bayrktlihn.entity.EmploymentCategory;

public class EmploymentCategoryEditor extends AbstractCellEditor implements TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3906042284756297028L;
	private JComboBox<EmploymentCategory> comboBox;
	
	public EmploymentCategoryEditor() {
		this.comboBox = new JComboBox<EmploymentCategory>(EmploymentCategory.values());
	}

	@Override
	public Object getCellEditorValue() {
		return comboBox.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		comboBox.setSelectedItem(value);
		
		comboBox.addActionListener(event -> {
			fireEditingStopped();
		});
		
		return comboBox;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		return true;
	}

}
