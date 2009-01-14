/*
 * Copyright 2008, David Karnok 
 * The file is part of the Open Imperium Galactica project.
 * 
 * The code should be distributed under the LGPL license.
 * See http://www.gnu.org/licenses/lgpl.html for details.
 */

package hu.openig;

import hu.openig.gfx.GFXCursors;
import hu.openig.gfx.StarmapBar;
import hu.openig.gfx.StarmapContents;
import hu.openig.gfx.StarmapGFX;
import hu.openig.gfx.StarmapRenderer;
import hu.openig.utils.PCXImage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.JFrame;


/**
 * @author karnokd, 2009.01.05.
 * @version $Revision 1.0$
 */
public class Starmap {
	private static StarmapGFX loadImages(String root) {
		StarmapGFX gfx = new StarmapGFX();
		
		BufferedImage alap = PCXImage.from(root + "\\GFX\\ALAP.PCX", -1);
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		// TOP BAR
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		// get the colors from the joining point
		gfx.top = new StarmapBar();
		gfx.top.left = alap.getSubimage(0, 0, 400, 20);
		gfx.top.right = alap.getSubimage(400, 0, 240, 20);
		gfx.top.link = alap.getSubimage(399, 0, 1, 20);
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		// BOTTOM BAR
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		gfx.bottom = new StarmapBar();
		// get the colors from the joining point
		gfx.bottom = new StarmapBar();
		gfx.bottom.left = alap.getSubimage(0, 20, 400, 18);
		gfx.bottom.right = alap.getSubimage(400, 20, 240, 18);
		gfx.bottom.link = alap.getSubimage(399, 20, 1, 18);
		
		BufferedImage cursors = PCXImage.from(root + "\\GFX\\ICONMAIN.PCX", 0);
		gfx.cursors = new GFXCursors();
		int idx = 0;

		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		// CURSORS
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		gfx.cursors.pointer = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(0, 0), "Pointer");
		gfx.cursors.hand = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(0, 0), "Hand");
		gfx.cursors.target = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(10, 10), "Target");
		gfx.cursors.move = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(10, 10), "Move");
		gfx.cursors.select = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(10, 10), "Select");
		gfx.cursors.northwest = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(0, 0), "NorthWest");
		gfx.cursors.north = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(10, 0), "North");
		gfx.cursors.northeast = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(19, 0), "NorthEast");
		gfx.cursors.east = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(19, 8), "East");
		gfx.cursors.west = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(0, 8), "West");
		gfx.cursors.back = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(9, 10), "Back");
		gfx.cursors.southwest = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(0, 19), "SouthWest");
		gfx.cursors.south = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(10, 19), "South");
		gfx.cursors.southeast = toolkit.createCustomCursor(cursors.getSubimage(idx++ * 32, 1, 32, 31), 
				new Point(19, 19), "SouthWest");
	
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		// STARMAP BODY
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		BufferedImage body = PCXImage.from(root + "\\SCREENS\\STARMAP.PCX", -1);
		
		gfx.contents = new StarmapContents();
		
		gfx.contents.bottomLeft = body.getSubimage(0, 331, 33, 111);
		gfx.contents.bottomFiller = body.getSubimage(33, 331, 2, 111);
		gfx.contents.bottomRight = body.getSubimage(33, 331, 607, 111);
		gfx.contents.shipControls = body.getSubimage(285, 359, 106, 83);
		gfx.contents.rightTop = body.getSubimage(505, 0, 135, 42);
		gfx.contents.rightFiller = body.getSubimage(505, 42, 135, 2);
		gfx.contents.rightBottom = body.getSubimage(505, 42, 135, 289);
		
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		// SCROLLBAR SEGMENTS
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		gfx.contents.hscrollLeft = body.getSubimage(37, 334, 18, 18);
		gfx.contents.hscrollFiller = body.getSubimage(55, 334, 2, 18);
		gfx.contents.hscrollRight = body.getSubimage(55, 334, 17, 18);
		
		gfx.contents.vscrollTop = body.getSubimage(508, 291, 18, 18);
		gfx.contents.vscrollFiller = body.getSubimage(508, 309, 18, 2);
		gfx.contents.vscrollBottom = body.getSubimage(508, 309, 18, 17);
		
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		// BIGMAP AND MINIMAP
		// oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
		gfx.contents.minimap = PCXImage.from(root + "\\GFX\\ZOOM2.PCX", -1);
		// fix image
		gfx.contents.minimap = gfx.contents.minimap.getSubimage(0, 0, gfx.contents.minimap.getWidth() - 1, gfx.contents.minimap.getHeight());
		gfx.contents.fullMap = PCXImage.from(root + "\\GFX\\ZOOM.PCX", -1);
		// fix image
		gfx.contents.fullMap = gfx.contents.fullMap.getSubimage(0, 0, gfx.contents.fullMap.getWidth(), 662);
		
		gfx.contents.mapBackground = new Color(gfx.contents.fullMap.getRGB(0, 0));
		return gfx;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String igroot = "c:\\games\\ig\\";
		StarmapRenderer smr = new StarmapRenderer(loadImages(igroot));
		JFrame fm = new JFrame("Open-IG: Starmap");
		fm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c = fm.getContentPane();
		GroupLayout gl = new GroupLayout(c);
		c.setLayout(gl);
		gl.setHorizontalGroup(gl.createSequentialGroup()
			.addComponent(smr, 640, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl.setVerticalGroup(
			gl.createSequentialGroup()
			.addComponent(smr, 480, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		fm.pack();
		fm.setLocationRelativeTo(null);
		final int inW = fm.getWidth();
		final int inH = fm.getHeight();
		fm.setMinimumSize(new Dimension(inW, inH));
		/*
		fm.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = e.getComponent();
				c.setSize(Math.max(inW, c.getWidth()), Math.max(inH, c.getHeight()));
			}
		});
		*/
		fm.setVisible(true);
	}

}
