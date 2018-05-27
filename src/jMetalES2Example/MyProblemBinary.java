package jMetalES2Example;

import java.util.BitSet;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.util.JMetalException;

import Engine.Problem;

public class MyProblemBinary extends AbstractBinaryProblem {
	  private int bits ;
	  private Problem prob;

	  public MyProblemBinary(Problem pr) throws JMetalException {
		  prob=pr;
		setNumberOfVariables(prob.getVariables().size());
	    setNumberOfObjectives(prob.getObjectives().size());
	    setName("MyProblemBinary");
	    bits = ((String)prob.getVariables().get(0).getMin()).length();

	  } 
	  
	  @Override
	  protected int getBitsPerVariable(int index) {
//	  	if (index != 0) {
//	  		throw new JMetalException("Problem MyBinaryProblem has only a variable. Index = " + index) ;
//	  	}
	  	return bits ;
	  }

	  @Override
	  public BinarySolution createSolution() {
	    return new DefaultBinarySolution(this) ;
	  }

	  @Override
	  public void evaluate(BinarySolution solution){
		  System.out.println("number of objectives - " +getNumberOfObjectives());
	    int counterOnes;
	    int counterZeroes;
	    counterOnes = 0;
	    counterZeroes = 0;

	    BitSet bitset = solution.getVariableValue(0) ;
	    for (int i = 0; i < bitset.length(); i++) {
	      if (bitset.get(i)) {
	        counterOnes++;
	      } else {
	        counterZeroes++;
	      }
	    }
	    // OneZeroMax is a maximization problem: multiply by -1 to minimize
	    solution.setObjective(0, -1.0 * counterOnes);
	    solution.setObjective(1, -1.0 * counterZeroes);		  
	  }
  
	}
