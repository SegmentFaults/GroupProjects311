package de.tum.cs.i1.pse.command;

import de.tum.cs.i1.pse.IllegalTemperatureException;
import de.tum.cs.i1.pse.model.TemperatureModel;

public class LowerCCommand extends Command {

	
	public LowerCCommand(TemperatureModel model) {
		this.model = model;
	}
	
	@Override
	public void execute() throws IllegalTemperatureException {
		model.setC(model.getC()-1);

	}

	@Override
	public void undo() throws IllegalTemperatureException {
		model.setC(model.getC()+1);

	}

	@Override
	public void redo() throws IllegalTemperatureException {
		model.setC(model.getC()-1);

	}

}
