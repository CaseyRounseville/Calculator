package event;

public class TotalChangedEvent implements CalculatorEvent {
	private int total;
	
	public TotalChangedEvent(int total) {
		this.total = total;
	}
	
	public int getTotal() {
		return total;
	}
}