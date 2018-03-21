package event;

public class OperationChangedEvent implements CalculatorEvent {
	private int operation;
	
	public OperationChangedEvent(int operation) {
		this.operation = operation;
	}
	
	public int getOperation() {
		return operation;
	}
}