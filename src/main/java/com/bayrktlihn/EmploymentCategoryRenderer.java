package com.bayrktlihn;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.bayrktlihn.entity.EmploymentCategory;

public class EmploymentCategoryRenderer implements TableCellRenderer {
	
	private JComboBox<EmploymentCategory> comboBox;
	
	public EmploymentCategoryRenderer() {
		comboBox = new JComboBox<EmploymentCategory>(EmploymentCategory.values());
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		comboBox.setSelectedItem(value);
		return comboBox;
	}

}
