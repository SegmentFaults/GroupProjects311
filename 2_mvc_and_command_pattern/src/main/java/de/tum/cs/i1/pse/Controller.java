package de.tum.cs.i1.pse;

import de.tum.cs.i1.pse.command.LowerCCommand;
import de.tum.cs.i1.pse.command.LowerFCommand;
import de.tum.cs.i1.pse.command.LowerKCommand;
import de.tum.cs.i1.pse.command.RaiseCCommand;
import de.tum.cs.i1.pse.command.RaiseFCommand;
import de.tum.cs.i1.pse.command.RaiseKCommand;
import de.tum.cs.i1.pse.model.TemperatureModel;
// import IllegalTemperatureException;

public class Controller {

	Invoker manageInvoker = new Invoker();
	public TemperatureModel model;

	public Controller(TemperatureModel model) {
		this.model = model;
	}

	public void setC(double val) throws IllegalTemperatureException {
		model.setC(val);
	}

	public void setF(double val) throws IllegalTemperatureException {
		model.setF(val);
	}

	public void setK(double val) throws IllegalTemperatureException {
		if(val < 0) {
			throw new IllegalTemperatureException(null);
		} else {
			model.setK(val);
		}
	}

	public void undo() throws IllegalTemperatureException {
		manageInvoker.undo();
	}

	public void redo() throws IllegalTemperatureException {
		manageInvoker.redo();
	}

	public void increaseC() throws IllegalTemperatureException {
		// setC(model.getC()+1);
		manageInvoker.execute(new RaiseCCommand(model));

	}

	public void increaseF() throws IllegalTemperatureException {
		// setF(model.getF()+1);
		manageInvoker.execute(new RaiseFCommand(model));

	}

	public void increaseK() throws IllegalTemperatureException {
		// setK(model.getK()+1);
		manageInvoker.execute(new RaiseKCommand(model));

	}

	public void decreaseC() throws IllegalTemperatureException {
		// setC(model.getC()-1);
		manageInvoker.execute(new LowerCCommand(model));

	}

	public void decreaseF() throws IllegalTemperatureException {
		// setF(model.getF()-1);
		manageInvoker.execute(new LowerFCommand(model));

	}

	public void decreaseK() throws IllegalTemperatureException {
		// setK(model.getK()-1);
		manageInvoker.execute(new LowerKCommand(model));

	}

	public double getC() {
		return model.getC();
	}
	
	public double getK() {
		return model.getK();
	}
	
	public double getF() {
		return model.getF();
	}

}
