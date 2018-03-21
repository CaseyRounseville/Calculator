package controller;

import model.CalculatorModel;
import static model.CalculatorModel.*;

import event.CalculatorEventSystem;
import event.CalculatorEventHandler;
import event.CalculatorEvent;
import event.ButtonPressedEvent;

public class CalculatorController {
	private CalculatorModel calculatorModel;
	
	public CalculatorController(CalculatorModel calculatorModel) {
		this.calculatorModel = calculatorModel;
		
		CalculatorEventSystem ces = CalculatorEventSystem.getInstance();
		
		ces.registerEventHandler(ButtonPressedEvent.class, new CalculatorEventHandler<ButtonPressedEvent>() {
			@Override
			public void handle(ButtonPressedEvent event) {				
				switch (event.getBtn()) {
				case BTN_0:				keepTypingNext(0);			break;
				case BTN_1:				keepTypingNext(1);			break;
				case BTN_2:				keepTypingNext(2);			break;
				case BTN_3:				keepTypingNext(3);			break;
				case BTN_4:				keepTypingNext(4);			break;
				case BTN_5:				keepTypingNext(5);			break;
				case BTN_6:				keepTypingNext(6);			break;
				case BTN_7:				keepTypingNext(7);			break;
				case BTN_8:				keepTypingNext(8);			break;
				case BTN_9:				keepTypingNext(9);			break;
				case BTN_ADD:			break;
				case BTN_SUBTRACT:		break;
				case BTN_MULTIPLY:		break;
				case BTN_DIVIDE:		break;
				case BTN_EQUALS:		break;
				case BTN_CLEAR:			clear();					break;
				}
			}
		});
	}
	
	private void keepTypingNext(int digit) {
		int oldNext = calculatorModel.getNext();
		calculatorModel.setNext(oldNext * 10 + digit);
	}
	
	private void performOperation(int operation) {
		
	}
	
	private void clear() {
		calculatorModel.setTotal(0);
		calculatorModel.setNext(0);
		calculatorModel.setOperation(OPERATION_NONE);
	}
}