package main;

import javafx.application.Application;

import javafx.stage.Stage;

import javafx.scene.Scene;

import model.CalculatorModel;

import view.CalculatorView;

import controller.CalculatorController;

public class Calculator extends Application {
	@Override
	public void start(Stage stage) {
		CalculatorModel calculatorModel = new CalculatorModel();
		CalculatorView calculatorView = new CalculatorView(calculatorModel);
		CalculatorController calculatorController = new CalculatorController(calculatorModel);
		Scene scene = new Scene(calculatorView);
		stage.setScene(scene);
		stage.setWidth(400);
		stage.setHeight(400);
		stage.setTitle("Calculator");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(Calculator.class, args);
	}
}