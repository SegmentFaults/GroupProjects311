package de.tum.cs.i1.pse;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;



public class ControlPanel implements java.util.Observer {
	private Controller controller;
	
	private Frame panel= new Frame("Control Panel");
	private MenuBar mb=new MenuBar();  
	private Menu muAction=new Menu("Action");  
	private MenuItem raiseItem=new MenuItem("Raise Temperature");
	private MenuItem lowerItem=new MenuItem("Lower Temperature");
	private MenuItem redoItem=new MenuItem("Redo");
	private MenuItem undoItem=new MenuItem("Undo");
	private Label clickMe = new Label(" Mac users: Please select this control panel for the menu items to show up.");
	
	public ControlPanel(Controller controller,int h, int v) {
		this.controller = controller;
		
		muAction.add(raiseItem);
		muAction.add(lowerItem);
		muAction.add(redoItem);
		muAction.add(undoItem);
		
		mb.add(muAction);
		
		panel.setMenuBar(mb);
		
		panel.addWindowListener(new CloseListener());
		panel.setSize(450, 170);
		panel.setLocation(h, v);
		panel.add(clickMe);
		panel.setVisible(true);
		
		undoItem.setEnabled(false);
		redoItem.setEnabled(false);
		
		raiseItem.addActionListener(new RaiseTempListener());
		lowerItem.addActionListener(new LowerTempListener());
		undoItem.addActionListener(new UndoTempListener());
		redoItem.addActionListener(new RedoTempListener());
		
		panel.repaint();
	}
	class RaiseTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.increaseC();
		}
	}

	class LowerTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.decreaseC();
		}
	}
	class UndoTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.undo();
		}
	}
	class RedoTempListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.redo();
		}
	}

	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Invoker manager = (Invoker)arg0;
		
		undoItem.setEnabled(manager.isUndoable());
		redoItem.setEnabled(manager.isRedoable());
		
		panel.repaint();
	}
}
