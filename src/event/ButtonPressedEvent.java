package event;

public class ButtonPressedEvent implements CalculatorEvent {
	private int btn;
	
	public ButtonPressedEvent(int btn) {
		this.btn = btn;
	}
	
	public int getBtn() {
		return btn;
	}
}