<<<<<<< HEAD
package jMetalES2Example;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Engine.Problem;

public class MyProblemDouble extends AbstractDoubleProblem {

	Problem prob;

	public MyProblemDouble(Problem p) {
		prob=p;
		setNumberOfVariables(p.getVariables().size());
		setNumberOfObjectives(p.getObjectives().size());
		setName("MyProblemDouble");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());
		
		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(Double.parseDouble(prob.getVariables().get(i).getMin()));
			upperLimit.add(Double.parseDouble(prob.getVariables().get(i).getMax()));
		}

		setLowerLimit(lowerLimit); 
		setUpperLimit(upperLimit);
	}

	public void evaluate(DoubleSolution solution) {
		
		double[] fx = new double[getNumberOfObjectives()];
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			x[i] = solution.getVariableValue(i);
		}
		
		double FN = 0;
		double FP = 0;
		for (int i =0; i<getNumberOfVariables();i++) {
			double avg = (getLowerBound(i)+getUpperBound(i))/2;
			if (x[i] >= avg)
				FP+=x[i];
			if (x[i] < avg)
				FN+=x[i];
		}
		
		fx[0] = FP;
//		for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
//			fx[0] += Math.abs(x[0]); // Example for testing
//		}

		fx[1] = FN;
//		for (int var = 0; var < solution.getNumberOfVariables(); var++) {
//			fx[1] += Math.abs(x[1]); // Example for testing
//		}
// Será isto?
		
		for (int i = 0; i < getNumberOfVariables(); i++) {
			solution.setObjective(i, fx[i]);
		}
		
		for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
//	    	solution.setObjective(i,f[i] );
	    }	 
//		solution.setObjective(1, fx[1]);
	}

}
=======
package jMetalES2Example;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Engine.Problem;

public class MyProblemDouble extends AbstractDoubleProblem {

	Problem prob;

	public MyProblemDouble(Problem p) {
		prob=p;
		setNumberOfVariables(p.getVariables().size());
		setNumberOfObjectives(p.getObjectives().size());
		setName("MyProblemDouble");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());
		
		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(Double.parseDouble(prob.getVariables().get(i).getMin()));
			upperLimit.add(Double.parseDouble(prob.getVariables().get(i).getMax()));
		}

		setLowerLimit(lowerLimit); 
		setUpperLimit(upperLimit);
	}

	public void evaluate(DoubleSolution solution) {
		
		double[] fx = new double[getNumberOfObjectives()];
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			x[i] = solution.getVariableValue(i);
		}
		
		double FN = 0;
		double FP = 0;
		for (int i =0; i<getNumberOfVariables();i++) {
			double avg = (getLowerBound(i)+getUpperBound(i))/2;
			if (x[i] >= avg)
				FP+=x[i];
			if (x[i] < avg)
				FN+=x[i];
		}
		
		fx[0] = FP;
//		for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
//			fx[0] += Math.abs(x[0]); // Example for testing
//		}

		fx[1] = FN;
//		for (int var = 0; var < solution.getNumberOfVariables(); var++) {
//			fx[1] += Math.abs(x[1]); // Example for testing
//		}
// Será isto?
		
		for (int i = 0; i < getNumberOfVariables(); i++) {
			solution.setObjective(i, fx[i]);
		}
		
		for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
//	    	solution.setObjective(i,f[i] );
	    }	 
//		solution.setObjective(1, fx[1]);
	}

}
>>>>>>> 7c27176b63ec5090a680220f52788d19d57889df
