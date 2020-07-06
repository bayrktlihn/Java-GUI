package com.bayrktlihn;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.bayrktlihn.controller.MainController;
import com.bayrktlihn.entity.EmploymentCategory;
import com.bayrktlihn.entity.Person;

import lombok.Getter;
import lombok.Setter;

public class PersonTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3119336498180027918L;

	private String[] colNames = { "Id", "Name", "Occupation", "Age Category", "Employment Category", "Us Citizen",
			"Tax Id" };
	@Getter
	@Setter
	private List<Person> persons;

	@Override
	public int getRowCount() {
		return persons.size();
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
		case 4:
		case 5:
			return true;

		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		if (persons == null)
			return;

		Person person = persons.get(rowIndex);

		switch (columnIndex) {
		case 1:
			person.setName((String) aValue);
			break;
		case 4:
			person.setEmploymentCategory((EmploymentCategory) aValue);
			break;
		case 5:
			person.setUsCitizen((boolean) aValue);
			break;
		default:
			break;
		}

		getMainController().savePerson(person);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Person person = persons.get(rowIndex);

		if (columnIndex == 0) {
			return person.getId();
		} else if (columnIndex == 1) {
			return person.getName();
		} else if (columnIndex == 2) {
			return person.getOccupation();
		} else if (columnIndex == 3) {
			return person.getAgeCategory();
		} else if (columnIndex == 4) {
			return person.getEmploymentCategory();
		} else if (columnIndex == 5) {
			return person.isUsCitizen();
		} else if (columnIndex == 6) {
			return person.getTaxId();
		}

		return null;
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return EmploymentCategory.class;
		case 5:
			return Boolean.class;
		case 6:
			return String.class;

		default:
			return null;
		}
	}

	private MainController getMainController() {
		return MyContext.getBean(MainController.class);
	}

}
