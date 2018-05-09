package de.tum.cs.i1.pse.command;

import de.tum.cs.i1.pse.IllegalTemperatureException;
import de.tum.cs.i1.pse.model.TemperatureModel;

/**
 * Represents an abstract TemperatureConverter command.
 * All commands shall extend this class.
 */
public abstract class Command {

	/**
	 * Reference to the model.
	 */
	protected TemperatureModel model;

	/**
	 * Execute the command.
	 * @throws IllegalTemperatureException 
	 */
	public abstract void execute() throws IllegalTemperatureException;

	/**
	 * Undo the command.
	 * @throws IllegalTemperatureException 
	 */
	public abstract void undo() throws IllegalTemperatureException;

	/**
	 * Redo the command.
	 * @throws IllegalTemperatureException 
	 */
	public abstract void redo() throws IllegalTemperatureException;
}

