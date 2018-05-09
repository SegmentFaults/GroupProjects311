package de.tum.cs.i1.pse.command;

import de.tum.cs.i1.pse.IllegalTemperatureException;
import de.tum.cs.i1.pse.model.TemperatureModel;

public class RaiseKCommand extends Command {

	public RaiseKCommand(TemperatureModel model) {
		this.model = model;
	}
	
	@Override
	public void execute() throws IllegalTemperatureException {
		model.setK(model.getK()+1);
		
	}

	@Override
	public void undo() throws IllegalTemperatureException {
		model.setK(model.getK()-1);
		
	}

	@Override
	public void redo() throws IllegalTemperatureException {
		model.setK(model.getK()+1);
		
	}

}
