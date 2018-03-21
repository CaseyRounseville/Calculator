package event;

public class NextChangedEvent implements CalculatorEvent {
	private int next;
	
	public NextChangedEvent(int next) {
		this.next = next;
	}
	
	public int getNext() {
		return next;
	}
}