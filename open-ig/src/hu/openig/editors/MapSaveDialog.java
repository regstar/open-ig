/*
 * Copyright 2008-2011, David Karnok 
 * The file is part of the Open Imperium Galactica project.
 * 
 * The code should be distributed under the LGPL license.
 * See http://www.gnu.org/licenses/lgpl.html for details.
 */
package hu.openig.editors;

import hu.openig.core.Act;

import java.awt.Container;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;

/**
 * The map save dialog.
 * @author karnokd
 *
 */
public class MapSaveDialog extends JDialog {
	/** */
	private static final long serialVersionUID = -1815299252700684422L;
	/** The map save settings. */
	public MapSaveSettings saveSettings;
	/** Save mode? */
	private final boolean save;
	/** The chosen filename. */
	private JTextField fileName;
	/** Surface included? */
	private JCheckBox surface;
	/** Building included. */
	private JCheckBox buildings;
	/** 
	 * Create GUI. 
	 * @param save display a save dialog? 
	 * @param initialSettings the optional initial settings
	 */
	public MapSaveDialog(boolean save, MapSaveSettings initialSettings) {
		this.save = save;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle(save ? "Save map" : "Load map");
		Container c = getContentPane();
		GroupLayout gl = new GroupLayout(c);
		c.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		
		JLabel fileNameLbl = new JLabel("File name:");
		fileName = new JTextField(40);
		surface = new JCheckBox(save ? "Save surface features" : "Load surface features", true);
		buildings = new JCheckBox(save ? "Save buildings" : "Load buildings", true);
		JButton browse = new JButton("Browse...");
		browse.addActionListener(new Act() {
			@Override
			public void act() {
				doBrowse();
			}
		});
		
		if (initialSettings != null) {
			fileName.setText(initialSettings.fileName.getAbsolutePath());
			surface.setSelected(initialSettings.surface);
			buildings.setSelected(initialSettings.buildings);
		}
		
		JButton ok = new JButton(save ? "Save" : "Open");
		ok.addActionListener(new Act() {
			@Override
			public void act() {
				saveSettings = new MapSaveSettings();
				saveSettings.fileName = new File(fileName.getText());
				saveSettings.surface = surface.isSelected();
				saveSettings.buildings = buildings.isSelected();
				dispose();
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new Act() { 
			@Override
			public void act() {
				dispose();
			}
		});
		
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.CENTER)
			.addGroup(
				gl.createSequentialGroup()
				.addComponent(fileNameLbl)
				.addComponent(fileName)
				.addComponent(browse)
			)
			.addComponent(surface)
			.addComponent(buildings)
			.addGroup(
				gl.createSequentialGroup()
				.addComponent(ok)
				.addComponent(cancel)
			)
		);
		
		gl.setVerticalGroup(
			gl.createSequentialGroup()
			.addGroup(
				gl.createParallelGroup(Alignment.BASELINE)
				.addComponent(fileNameLbl)
				.addComponent(fileName)
				.addComponent(browse)
			)
			.addComponent(surface)
			.addComponent(buildings)
			.addGroup(
				gl.createParallelGroup(Alignment.BASELINE)
				.addComponent(ok)
				.addComponent(cancel)
			)
		);
		gl.linkSize(SwingConstants.HORIZONTAL, ok, cancel);
		
		pack();
	}
	/** Browse for file. */
	void doBrowse() {
		String fn = fileName.getText();
		File path = null;
		if (fn.length() > 0) {
			path = new File(fn).getParentFile();
		}
		if (path == null) {
			path = new File(".");
		}
		JFileChooser fc = new JFileChooser(path);
		int result = -1;
		if (save) {
			result = fc.showSaveDialog(this);
		} else {
			result = fc.showOpenDialog(this);
		}
		if (result == JFileChooser.APPROVE_OPTION) {
			fileName.setText(fc.getSelectedFile().getAbsolutePath());
		}
	}
}
