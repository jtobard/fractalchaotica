package cl.basilisco.core;

public class BadFormulaException extends Exception {

	private static final long serialVersionUID = -5689328064391085383L;

	@Override
	public String getMessage() {
		return "Bad Formula.";
	}
}
