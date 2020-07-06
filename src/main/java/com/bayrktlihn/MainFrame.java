package com.bayrktlihn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.bayrktlihn.controller.MainController;
import com.bayrktlihn.entity.Person;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9043666128888207069L;

	private FormPanel formPanel;

	private MainController mainController = MyContext.getBean(MainController.class);
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	private Toolbar toolbar;

	private Preferences preferences;

	private JSplitPane splitPane;
	private JTabbedPane tabPane;
	private TextPanel textPanel;
	private MessagePanel messagePanel;

	public MainFrame() {
		super("Hello World");


		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}

		setLayout(new BorderLayout());

		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		prefsDialog = new PrefsDialog(this);
		toolbar = new Toolbar();
		tabPane = new JTabbedPane();
		textPanel = new TextPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabPane);
		messagePanel = new MessagePanel(this);

		splitPane.setOneTouchExpandable(true);

		tabPane.addTab("Person Database", tablePanel);
		tabPane.addTab("Messages", messagePanel);

		preferences = Preferences.userRoot().node("db");

		prefsDialog.setPrefsListener(new PrefsListener() {

			@Override
			public void preferencesSet(String username, String password, int portNumber) {
				preferences.put("username", username);
				preferences.put("password", password);
				preferences.putInt("portNumber", portNumber);
			}
		});

		String username = preferences.get("username", "");
		String password = preferences.get("password", "");
		int portNumber = preferences.getInt("portNumber", 3306);
		prefsDialog.setDefaults(username, password, portNumber);

		tablePanel.setData(mainController.findAll());

		tablePanel.setPersonTableListener(new PersonTableListener() {

			@Override
			public void rowDeleted(int id) {
				mainController.deleteById(id);
			}
		});

		tabPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int tabIndex = tabPane.getSelectedIndex();

				if (tabIndex == 1) {
					messagePanel.refresh();
				}
			}
		});

		setJMenuBar(createMenuBar());

		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				Person person = mainController.savePerson(e);
				tablePanel.getData().add(person);
				tablePanel.refresh();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MyContext.close();
				dispose();
				System.gc();
				System.exit(0);
			}
		});

		add(toolbar, BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);

		setMinimumSize(new Dimension(500, 500));
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {

//		File Menu Section
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataMenuItem = new JMenuItem("Export Data");
		JMenuItem importDataMenuItem = new JMenuItem("Import Data");
		JMenuItem exitMenuItem = new JMenuItem("Exit");

		fileMenu.add(exportDataMenuItem);
		fileMenu.add(importDataMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

//		Window Menu Section
		JMenu windowMenu = new JMenu("Window");

		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem personFormMenuItem = new JCheckBoxMenuItem("Person Form");
		personFormMenuItem.setSelected(true);

		showMenu.add(personFormMenuItem);

		JMenuItem prefsMenuItem = new JMenuItem("Preferences");

		windowMenu.add(showMenu);
		windowMenu.add(prefsMenuItem);

//		Mnemonic Section
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitMenuItem.setMnemonic(KeyEvent.VK_X);

//		Accelerator Section
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		importDataMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		prefsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

//		MenuItems Events Section
		personFormMenuItem.addActionListener(event -> {

			if (personFormMenuItem.isSelected()) {
				splitPane.setDividerLocation((int) formPanel.getMinimumSize().getWidth());

			}

			formPanel.setVisible(personFormMenuItem.isSelected());
		});

		exitMenuItem.addActionListener(x -> {

			int action = JOptionPane.showConfirmDialog(this, "Do you really want to exit the application?",
					"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

			if (action == JOptionPane.OK_OPTION) {
				WindowListener[] windowListeners = getWindowListeners();

				for (WindowListener windowListener : windowListeners) {
					windowListener.windowClosing(new WindowEvent(this, 0));
				}
			}

		});

		importDataMenuItem.addActionListener(event -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(new PersonFileFilter());
//			fileChooser.setMultiSelectionEnabled(true);

			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				mainController.loadPersonsToFile(fileChooser.getSelectedFile());
				tablePanel.refresh();
			}
		});

		exportDataMenuItem.addActionListener(event -> {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(new PersonFileFilter());

			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				mainController.savePersonsToFile(fileChooser.getSelectedFile());
			}
		});

		prefsMenuItem.addActionListener(x -> {
			prefsDialog.setVisible(true);
		});

//		MenuBar Section
		JMenuBar menuBar = new JMenuBar();

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		return menuBar;
	}

}
