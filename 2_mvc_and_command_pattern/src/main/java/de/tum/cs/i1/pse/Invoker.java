package de.tum.cs.i1.pse;

import de.tum.cs.i1.pse.command.Command;
import java.util.Stack;

public class Invoker extends java.util.Observable{
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();

	public void execute(Command cmd) throws IllegalTemperatureException {
		redoStack.clear();
		undoStack.push(cmd);
		cmd.execute();
		
		setChanged();
		notifyObservers();
	}

	public void redo() throws IllegalTemperatureException {
		if(!redoStack.empty()){
			Command cmd = redoStack.pop();
			undoStack.push(cmd);
			
			cmd.redo();
		}
		
		setChanged();
		notifyObservers();
	}

	public void undo() throws IllegalTemperatureException {
		if (!undoStack.empty()) {
			Command cmd = undoStack.pop();
			redoStack.push(cmd);

			cmd.undo();
		}
		
		setChanged();
		notifyObservers();
	}
	
	public boolean isUndoable(){
		return !undoStack.empty();		
	}
	public boolean isRedoable(){
		return !redoStack.empty();		
	}
}
