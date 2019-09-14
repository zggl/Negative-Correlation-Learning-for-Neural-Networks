package testbed;

import java.util.ArrayList;

import dataprocessing.*;
import predictors.*;


public class NCRegression {


	public static void main(String[] args) {

		System.out.println("Negative Correlation Learning Demo");
		System.out.println("(c) Gavin Brown 2007");
		System.out.println("Regression : Friedman's function\n");
		
		DataReader reader = new DataReader("friedman.csv");
		reader.shuffle();
		//reader.printData();

		Ensemble ens = new Ensemble();
		for (int i=0; i<5; i++)
			ens.add( new MLP(reader.numInputs, 5, MLP.LINEAR));

		////////
		//THIS IS THE LAMBDA PARAMETER IN NC LEARNING.
		//SETTING IT TO 0.0 IS EQUIVALENT TO A SIMPLE ENSEMBLE
		//SETTING IT TO 1.0 IS EQUIVALENT TO A SINGLE LARGE NETWORK
		//
		ens.lambda = 0.0;
		////////
		
		ArrayList trainingData = reader.getTrainingData();
		ArrayList testingData = reader.getTestingData();
		for (int i=0; i<10; i++)
		{
			System.out.println( (i*50)+" : mse = "+ens.test(trainingData) );
			ens.train(trainingData, 50);
		}
		System.out.println( "Testing : mse = "+ens.test(testingData));
		
	}

}