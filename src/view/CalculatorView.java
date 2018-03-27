package view;

import javafx.scene.layout.GridPane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static javafx.scene.input.KeyCode.*;

import event.CalculatorEventSystem;
import event.CalculatorEventHandler;
import event.ButtonPressedEvent;
import event.NextChangedEvent;
import event.TotalChangedEvent;
import event.OperationChangedEvent;

import model.CalculatorModel;
import static model.CalculatorModel.*;

public class CalculatorView extends GridPane {
	private CalculatorModel calculatorModel;
	
	private Button btn0;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private Button btn9;
	
	private Button btnAdd;
	private Button btnSubtract;
	private Button btnMultiply;
	private Button btnDivide;
	
	private Button btnSign;
	
	private Button btnEquals;
	
	private Button btnClear;
	
	private Label lblTotal;
	private Label lblNext;
	private Label lblOperation;
	
	public CalculatorView(CalculatorModel calculatorModel) {
		this.calculatorModel = calculatorModel;
	
		btn0			= makeButton("0", BTN_0);
		btn1			= makeButton("1", BTN_1);
		btn2			= makeButton("2", BTN_2);
		btn3			= makeButton("3", BTN_3);
		btn4			= makeButton("4", BTN_4);
		btn5			= makeButton("5", BTN_5);
		btn6			= makeButton("6", BTN_6);
		btn7			= makeButton("7", BTN_7);
		btn8			= makeButton("8", BTN_8);
		btn9			= makeButton("9", BTN_9);
		btnAdd			= makeButton("+", BTN_ADD);
		btnSubtract		= makeButton("-", BTN_SUBTRACT);
		btnMultiply		= makeButton("*", BTN_MULTIPLY);
		btnDivide		= makeButton("/", BTN_DIVIDE);
		btnSign			= makeButton("+/-", BTN_SIGN);
		btnEquals		= makeButton("=", BTN_EQUALS);
		btnClear		= makeButton("CLEAR", BTN_CLEAR);
		
		setOnKeyPressed((event) -> {
			switch (event.getCode()) {
			case DIGIT1:			btn1.fire();			break;
			case DIGIT2:			btn2.fire();			break;
			case DIGIT3:			btn3.fire();			break;
			case DIGIT4:			btn4.fire();			break;
			case DIGIT5:			btn5.fire();			break;
			case DIGIT6:			btn6.fire();			break;
			case DIGIT7:			btn7.fire();			break;
			case DIGIT8:
				if (event.isShiftDown()) {
					btnMultiply.fire();
				} else {
					btn8.fire();
				}
				break;
			case DIGIT9:			btn9.fire();			break;
			case DIGIT0:			btn0.fire();			break;
			case PLUS:				btnAdd.fire();			break;
			case MINUS:				btnSubtract.fire();		break;
			case ASTERISK:			btnMultiply.fire();		break;
			case SLASH:				btnDivide.fire();		break;
			case EQUALS:
				if (event.isShiftDown()) {
					btnAdd.fire();
				} else {
					btnEquals.fire();
				}
				break;
			case BACK_SPACE:		btnClear.fire();		break;
			}
		});
		
		lblTotal = new Label("Total:\t0");
		lblTotal.setStyle("-fx-font-size:32pt");
		CalculatorEventSystem.getInstance().registerEventHandler(TotalChangedEvent.class, new CalculatorEventHandler<TotalChangedEvent>() {
			@Override
			public void handle(TotalChangedEvent event) {
				lblTotal.setText("Total:\t" + event.getTotal());
			}
		});
		
		lblNext = new Label("Next:\t0");
		lblNext.setStyle("-fx-font-size:32pt");
		CalculatorEventSystem.getInstance().registerEventHandler(NextChangedEvent.class, new CalculatorEventHandler<NextChangedEvent>() {
			@Override
			public void handle(NextChangedEvent event) {
				lblNext.setText("Next:\t" + event.getNext());
			}
		});
		
		lblOperation = new Label("Op:\t+");
		lblOperation.setStyle("-fx-font-size:32pt");
		CalculatorEventSystem.getInstance().registerEventHandler(OperationChangedEvent.class, new CalculatorEventHandler<OperationChangedEvent>() {
			@Override
			public void handle(OperationChangedEvent event) {
				int operation = event.getOperation();
				switch (operation) {
				case OPERATION_ADD:
					lblOperation.setText("Op:\t" + "ADD");
					break;
				case OPERATION_SUBTRACT:
					lblOperation.setText("Op:\t" + "SUB");
					break;
				case OPERATION_MULTIPLY:
					lblOperation.setText("Op:\t" + "MUL");
					break;
				case OPERATION_DIVIDE:
					lblOperation.setText("Op:\t" + "DIV");
					break;
				}
			}
		});
		
		arrange();
	}
	
	private Button makeButton(String text, int btnNum) {
		Button btn = new Button(text);
		btn.setPrefWidth(80);
		btn.setPrefHeight(80);
		btn.setOnAction((event) -> {
			CalculatorEventSystem.getInstance().dispatchEvent(new ButtonPressedEvent(btnNum));
		});
		return btn;
	}
	
	private void arrange() {
		// control, col, row
		// control, col, row, colspan, rowspan
		add(lblTotal,			0,			0,			4,			1);
		add(lblOperation,		0,			1,			4,			1);
		add(lblNext,			0,			2,			4,			1);
		
		add(btn1,				0,			3);
		add(btn2,				1,			3);
		add(btn3,				2,			3);
		
		add(btn4,				0,			4);
		add(btn5,				1,			4);
		add(btn6,				2,			4);
		
		add(btn7,				0,			5);
		add(btn8,				1,			5);
		add(btn9,				2,			5);
		
		add(btnSign,			0,			6);
		add(btn0,				1,			6);
		
		add(btnAdd,				3,			3);
		add(btnSubtract,		3,			4);
		add(btnMultiply,		3,			5);
		add(btnDivide,			3,			6);
		
		add(btnEquals,			0,			7);
		add(btnClear,			1,			7);
		
		setStyle("-fx-background-color: #AFAFAFFF");
	}
}