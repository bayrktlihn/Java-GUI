package com.bayrktlihn;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import lombok.Setter;

public class ProgressDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1746373402048565344L;

	private JButton cancelButton;
	private JProgressBar progressBar;
	@Setter
	private ProgressDialogListener progressDialogListener;

	public ProgressDialog(Window parent, String title) {
		super(parent, title, ModalityType.APPLICATION_MODAL);

		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);

		progressBar.setMaximum(10);

		progressBar.setString("Retrieving messages...");

//		progressBar.setIndeterminate(true);

		setLayout(new FlowLayout());

		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressBar.setPreferredSize(size);

		add(progressBar);
		add(cancelButton);

		cancelButton.addActionListener(event -> {
			if (progressDialogListener != null) {
				progressDialogListener.progressDialogCancelled();
			}
		});

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (progressDialogListener != null) {
					progressDialogListener.progressDialogCancelled();
				}
			}
		});

		pack();

		setLocationRelativeTo(parent);
	}

	public void setMaximum(int value) {
		progressBar.setMaximum(value);
	}

	public void setValue(int value) {
		int progress = 100 * value / progressBar.getMaximum();

		progressBar.setString(String.format("%d%% complete", progress));

		progressBar.setValue(value);
	}

	@Override
	public void setVisible(boolean visible) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				if (visible == false) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					progressBar.setValue(0);
				}

				if (visible) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				} else {
					setCursor(Cursor.getDefaultCursor());
				}

				ProgressDialog.super.setVisible(visible);
			}
		});
	}

}
