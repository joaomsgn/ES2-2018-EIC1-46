package jMetalES2Example;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.JMetalException;

import Engine.Problem;

import java.util.ArrayList;
import java.util.List;

public class MyProblemInteger extends AbstractIntegerProblem {
	
	  public MyProblemInteger(Problem prob) throws JMetalException {
	    setNumberOfVariables(prob.getVariables().size());
	    setNumberOfObjectives(prob.getObjectives().size());
	    setName("MyProblemInteger");

	    List<Integer> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Integer> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(Integer.parseInt(prob.getVariables().get(i).getMin()));
	      upperLimit.add(Integer.parseInt(prob.getVariables().get(i).getMin()));
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);

	  }

	  public void evaluate(IntegerSolution solution){
	    double[] fx = new double[getNumberOfObjectives()];
	    int[] x = new int[getNumberOfVariables()];
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      x[i] = solution.getVariableValue(i) ;
	    }

	    int FN = 0;
		int FP = 0;
		for (int i =0; i<getNumberOfVariables();i++) {
			double avg = (getLowerBound(i)+getUpperBound(i))/2;
			if (x[i] >= avg)
				FP+=x[i];
			if (x[i] < avg)
				FN+=x[i];
		}
		fx[0] = FP;
		fx[1] = FN;
		for (int i = 0; i < getNumberOfObjectives(); i++) {
			solution.setObjective(i, fx[i]);
		}
	  }
	  	  
	}
