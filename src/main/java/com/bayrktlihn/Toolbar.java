package com.bayrktlihn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

import lombok.Setter;

public class Toolbar extends JToolBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5415725698143812760L;

	private JButton saveButton;
	private JButton refreshButton;
	@Setter
	private StringListener stringListener;

	public Toolbar() {

		setBorder(BorderFactory.createEtchedBorder());

//		setFloatable(false);

		saveButton = new JButton();
		refreshButton = new JButton();

		saveButton.setToolTipText("Save");
		refreshButton.setToolTipText("Refresh");

		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);

		saveButton.setIcon(Utils.createIcon("/toolbarButtonGraphics/general/Save16.gif"));
		refreshButton.setIcon(Utils.createIcon("/toolbarButtonGraphics/general/Refresh16.gif"));

		add(saveButton);
		addSeparator();
		add(refreshButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			if (stringListener != null) {
				stringListener.textEmitted("Hello\n");
			}
		} else if (e.getSource() == refreshButton) {
			if (stringListener != null) {
				stringListener.textEmitted("Goodbye\n");
			}

		}
	}

}
