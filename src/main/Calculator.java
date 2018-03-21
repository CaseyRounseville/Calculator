package main;

import javafx.application.Application;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.Group;

import view.CalculatorView;

import model.CalculatorModel;

public class Calculator extends Application {
	@Override
	public void start(Stage stage) {
		CalculatorModel calculatorModel = new CalculatorModel();
		Scene scene = new Scene(new Group());
		stage.setScene(scene);
		stage.setWidth(400);
		stage.setHeight(400);
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(Calculator.class, args);
	}
}