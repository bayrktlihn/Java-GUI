package com.bayrktlihn;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bayrktlihn.entity.EmploymentCategory;
import com.bayrktlihn.entity.Person;

import lombok.Setter;

public class TablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5479401603804239333L;

	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popupMenu;
	@Setter
	private PersonTableListener personTableListener;

	public TablePanel() {

		this.tableModel = new PersonTableModel();
		this.table = new JTable(tableModel);
		this.popupMenu = new JPopupMenu();
		
		table.setDefaultRenderer(EmploymentCategory.class, new EmploymentCategoryRenderer());
		table.setDefaultEditor(EmploymentCategory.class, new EmploymentCategoryEditor());
		table.setRowHeight(25);

		setPopupMenuItems();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());

				table.getSelectionModel().setSelectionInterval(row, row);

				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(table, e.getX(), e.getY());
				}
			}
		});

		setLayout(new BorderLayout());

		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	private void setPopupMenuItems() {
		JMenuItem removeMenuItem = new JMenuItem("Delete Row");

//		Menu Events
		removeMenuItem.addActionListener(x -> {
			int row = table.getSelectedRow();

			if (personTableListener != null) {
				personTableListener.rowDeleted(tableModel.getPersons().get(row).getId());
				tableModel.getPersons().remove(row);
				tableModel.fireTableRowsDeleted(row, row);
			}

		});

		this.popupMenu.add(removeMenuItem);
	}

	public void setData(List<Person> persons) {
		this.tableModel.setPersons(persons);
	}
	
	public List<Person> getData(){
		return this.tableModel.getPersons();
	}
	

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

}
