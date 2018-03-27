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
				case BTN_ADD:
					enter();
					calculatorModel.setJustHitEnter(false);
					calculatorModel.setOperation(OPERATION_ADD);
					break;
				case BTN_SUBTRACT:
					enter();
					calculatorModel.setJustHitEnter(false);
					calculatorModel.setOperation(OPERATION_SUBTRACT);
					break;
				case BTN_MULTIPLY:
					enter();
					calculatorModel.setJustHitEnter(false);
					calculatorModel.setOperation(OPERATION_MULTIPLY);
					break;
				case BTN_DIVIDE:
					enter();
					calculatorModel.setJustHitEnter(false);
					calculatorModel.setOperation(OPERATION_DIVIDE);
					break;
				case BTN_SIGN:
					changeSign();
					break;
				case BTN_EQUALS:
					enter();
					calculatorModel.setJustHitEnter(true);
					break;
				case BTN_CLEAR:
					clear();
					break;
				}
			}
		});
	}
	
	private void keepTypingNext(int digit) {
		if (calculatorModel.hasJustHitEnter()) {
			clear();
		}
		int oldNext = calculatorModel.getNext();
		calculatorModel.setNext(oldNext * 10 + digit);
		calculatorModel.setJustHitEnter(false);
	}
	
	private void changeSign() {
		calculatorModel.setNext(calculatorModel.getNext() * -1);
	}
	
	private void performAddition() {
		int oldTotal = calculatorModel.getTotal();
		int oldNext = calculatorModel.getNext();
		
		calculatorModel.setNext(0);
		calculatorModel.setTotal(oldTotal + oldNext);
	}
	
	private void performSubtraction() {
		int oldTotal = calculatorModel.getTotal();
		int oldNext = calculatorModel.getNext();
		
		calculatorModel.setNext(0);
		calculatorModel.setTotal(oldTotal - oldNext);
	}
	
	private void performMultiplication() {
		int oldTotal = calculatorModel.getTotal();
		int oldNext = calculatorModel.getNext();
		
		calculatorModel.setNext(0);
		calculatorModel.setTotal(oldTotal * oldNext);
	}
	
	private void performDivision() {
		int oldTotal = calculatorModel.getTotal();
		int oldNext = calculatorModel.getNext();
		
		if (oldNext == 0) {
			return;
		}
		
		calculatorModel.setNext(0);
		calculatorModel.setTotal(oldTotal / oldNext);
	}
	
	private void clear() {
		calculatorModel.setTotal(0);
		calculatorModel.setNext(0);
		calculatorModel.setOperation(OPERATION_ADD);
		calculatorModel.setJustHitEnter(true);
	}
	
	private void enter() {
		if (calculatorModel.hasJustHitEnter()) {
			return;
		}
		switch (calculatorModel.getOperation()) {
		case OPERATION_ADD:
			performAddition();
			break;
		case OPERATION_SUBTRACT:
			performSubtraction();
			break;
		case OPERATION_MULTIPLY:
			performMultiplication();
			break;
		case OPERATION_DIVIDE:
			performDivision();
			break;
		}
	}
}