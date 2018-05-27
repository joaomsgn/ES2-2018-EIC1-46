package jMetalES2Example;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.JMetalException;

import Engine.Problem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Implementação de um problema do tipo Integer que executa o .jar externo
   NMMin.jar e pode ser usado como um dos problema de teste indicados 
   no encunciado do trabalho */

@SuppressWarnings("serial")
public class MyProblemIntegerExternalViaJAR extends AbstractIntegerProblem {
	Problem prob;

	
	  public MyProblemIntegerExternalViaJAR(Problem p) throws JMetalException {
		  prob=p;
	    setNumberOfVariables(prob.getVariables().size());
	    setNumberOfObjectives(prob.getObjectives().size());
	    setName("MyProblemIntegerExternalViaJAR");

	    List<Integer> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Integer> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(Integer.parseInt(prob.getVariables().get(i).getMin()));
	      upperLimit.add(Integer.parseInt(prob.getVariables().get(i).getMax()));
	    }  

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);

	  }

	  public void evaluate(IntegerSolution solution){
	    String solutionString ="";
	    String evaluationResultString ="";
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      solutionString = solutionString + " " + solution.getVariableValue(i);  
	    }
	    try {
			String line;
	    	Process p = Runtime.getRuntime().exec("java -jar \"" +prob.getJarPath() +"\"   " + solutionString);
	    	BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    	while ((line = brinput.readLine()) != null) 
	    		{evaluationResultString+=line;}
	    	brinput.close();
	        p.waitFor();
	      }
	      catch (Exception err) {
	    	  System.out.println("Rebentando aqui");
	    	  err.printStackTrace(); }
	    
   		String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
	    // It is assumed that all evaluated criteria are returned in the same result string
	    for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
		    solution.setObjective(i, Integer.parseInt(individualEvaluationCriteria[i]));    
	    }	    
	  }	  
	}
