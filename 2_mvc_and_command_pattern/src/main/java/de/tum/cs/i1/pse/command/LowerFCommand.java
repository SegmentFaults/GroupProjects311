package de.tum.cs.i1.pse.command;

import de.tum.cs.i1.pse.IllegalTemperatureException;
import de.tum.cs.i1.pse.model.TemperatureModel;

public class LowerFCommand extends Command {

	public LowerFCommand(TemperatureModel model) {
		this.model = model;
	}
	
	@Override
	public void execute() throws IllegalTemperatureException {
		model.setF(model.getF()-1);

	}

	@Override
	public void undo() throws IllegalTemperatureException {
		model.setF(model.getF()+1);

	}

	@Override
	public void redo() throws IllegalTemperatureException {
		model.setF(model.getF()-1);

	}

}
