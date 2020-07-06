package com.bayrktlihn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import lombok.Setter;

public class PrefsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2541992657720739284L;

	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerNumberModel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	@Setter
	private PrefsListener prefsListener;

	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		

		spinnerNumberModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerNumberModel);
		
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		
		passwordField.setEchoChar('*');

		layoutControls();

		setSize(320, 230);
		setLocationRelativeTo(parent);

	}

	public void setDefaults(String username, String password, int port) {
		usernameField.setText(username);
		passwordField.setText(password);
		portSpinner.setValue(port);
	}

	private void layoutControls() {

		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();

		int space = 15;
		Border outsideBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border insideBorder = BorderFactory.createTitledBorder("Database Preferences");
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		controlsPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		
//		ControlsPanel Section
//		First Row
		gc.gridy = 0;
		
//		First Column
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Port"), gc);

//		Next Column
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(portSpinner, gc);

//		Next Row
		gc.gridy++;
		
//		First Column
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Username"), gc);
		
//		Next Column
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		gc.fill = GridBagConstraints.HORIZONTAL;
		controlsPanel.add(usernameField, gc);

//		Last Row
		gc.gridy++;
		
//		First Column
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Password"), gc);

//		Next Column
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		gc.fill = GridBagConstraints.HORIZONTAL;
		controlsPanel.add(passwordField, gc);


//		ButtonsPanel Section
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		setLayout(new BorderLayout());
		add(controlsPanel,BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);

//		Events
		okButton.addActionListener(x -> {
			int portNumber = (Integer) portSpinner.getValue();

			String username = usernameField.getText();

			String password = new String(passwordField.getPassword());

			if (prefsListener != null) {
				prefsListener.preferencesSet(username, password, portNumber);
			}

			setVisible(false);
		});

		cancelButton.addActionListener(x -> {
			setVisible(false);
		});
	}

}
