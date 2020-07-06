package com.bayrktlihn;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

public class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 810394504490152603L;

	private ServerTreeCellRenderer treeCellRenderer;
	private ServerInfo userObject;
	private JCheckBox checkBox;

	public ServerTreeCellEditor() {
		treeCellRenderer = new ServerTreeCellRenderer();
	}

	@Override
	public Object getCellEditorValue() {
		userObject.setChecked(checkBox.isSelected());
		return userObject;
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {

		Component treeCellRendererComponent = treeCellRenderer.getTreeCellRendererComponent(tree, value, isSelected,
				expanded, leaf, row, true);

		if (leaf) {

			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;

			userObject = (ServerInfo) treeNode.getUserObject();

			checkBox = (JCheckBox) treeCellRendererComponent;

			checkBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					fireEditingStopped();
					checkBox.removeItemListener(this);
				}
			});
		}

		return treeCellRendererComponent;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		if (!(e instanceof MouseEvent))
			return false;

		MouseEvent mouseEvent = (MouseEvent) e;

		JTree tree = (JTree) e.getSource();

		TreePath pathForLocation = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());

		if (pathForLocation == null)
			return false;

		Object lastPathComponent = pathForLocation.getLastPathComponent();

		if (lastPathComponent == null)
			return false;

		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) lastPathComponent;

		return treeNode.isLeaf();
	}

}
