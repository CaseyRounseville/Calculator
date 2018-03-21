package event;

public abstract class CalculatorEventHandler<T extends CalculatorEvent> {
	private boolean oneShot;
	
	public CalculatorEventHandler() {
		oneShot = false;
	}
	
	public CalculatorEventHandler(boolean oneShot) {
		this.oneShot = oneShot;
	}
	
	public boolean isOneShot() {
		return oneShot;
	}
	
	public abstract void handle(T t);
}