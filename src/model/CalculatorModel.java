package model;

public class CalculatorModel {
	public static final int BTN_0				= 0,
							BTN_1				= 1,
							BTN_2				= 3,
							BTN_3				= 4,
							BTN_4				= 5,
							BTN_5				= 6,
							BTN_6				= 7,
							BTN_7				= 8,
							BTN_8				= 9,
							BTN_9				= 10,
							BTN_ADD				= 11,
							BTN_SUBTRACT		= 12,
							BTN_MULTIPLY		= 13,
							BTN_DIVIDE			= 14,
							BTN_EQUALS			= 15,
							BTN_CLEAR			= 16;
	
	public static final int OPERATION_ADD		= 0,
							OPERATION_SUBTRACT	= 1,
							OPERATION_MULTIPLY	= 2,
							OPERATION_DIVIDE	= 3,
							OPERATION_NONE		= 4;
	
	private int total;
	private int next;
	private int operation;
	
	public CalculatorModel() {
		total = 0;
		next = 0;
		operation = OPERATION_NONE;
	}
	
	public void setNext(int next) {
		
	}
	
	public int getNext() {
		return next;
	}
	
	public void setOperation() {
		
	}
	
	public int getOperation() {
		return operation;
	}
}