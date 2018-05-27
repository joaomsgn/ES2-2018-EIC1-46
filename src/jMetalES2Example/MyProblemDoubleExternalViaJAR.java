<<<<<<< HEAD
package jMetalES2Example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Engine.Problem;

/* Implementação de um problema do tipo Double que executa o .jar externo
   Kursawe.jar e pode ser usado como um dos problema de teste indicados 
   no encunciado do trabalho */

@SuppressWarnings("serial")
public class MyProblemDoubleExternalViaJAR extends AbstractDoubleProblem {
		
	Problem prob;

	
	  public MyProblemDoubleExternalViaJAR(Problem p) {
		 prob=p;
	    setNumberOfVariables(prob.getVariables().size());
	    setNumberOfObjectives(prob.getObjectives().size());
	    setName("MyProblemDoubleExternalViaJAR");

	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) { 
	    System.out.println(prob);
	    System.out.println(prob.getVariables().size());
	    System.out.println(prob.getVariables().get(i));
	    System.out.println(prob.getVariables().get(i).getMin());
	      lowerLimit.add(Double.parseDouble(prob.getVariables().get(i).getMin()));
	      upperLimit.add(Double.parseDouble(prob.getVariables().get(i).getMax()));
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);	    	    
	  }

	  public void evaluate(DoubleSolution solution){
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
	      catch (Exception err) { err.printStackTrace(); }
   		String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
	    // It is assumed that all evaluated criteria are returned in the same result string
	    for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
	    	solution.setObjective(i, Double.parseDouble(individualEvaluationCriteria[i]));
	    }	    
	  }
	}
=======
package jMetalES2Example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Engine.Problem;

/* Implementação de um problema do tipo Double que executa o .jar externo
   Kursawe.jar e pode ser usado como um dos problema de teste indicados 
   no encunciado do trabalho */

@SuppressWarnings("serial")
public class MyProblemDoubleExternalViaJAR extends AbstractDoubleProblem {
		
	Problem prob;

	
	  public MyProblemDoubleExternalViaJAR(Problem p) {
		 prob=p;
	    setNumberOfVariables(prob.getVariables().size());
	    setNumberOfObjectives(prob.getObjectives().size());
	    setName("MyProblemDoubleExternalViaJAR");

	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) { 
	    System.out.println(prob);
	    System.out.println(prob.getVariables().size());
	    System.out.println(prob.getVariables().get(i));
	    System.out.println(prob.getVariables().get(i).getMin());
	      lowerLimit.add(Double.parseDouble(prob.getVariables().get(i).getMin()));
	      upperLimit.add(Double.parseDouble(prob.getVariables().get(i).getMax()));
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);	    	    
	  }

	  public void evaluate(DoubleSolution solution){
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
	      catch (Exception err) { err.printStackTrace(); }
   		String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
	    // It is assumed that all evaluated criteria are returned in the same result string
	    for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
	    	solution.setObjective(i, Double.parseDouble(individualEvaluationCriteria[i]));
	    }	    
	  }
	}
>>>>>>> 7c27176b63ec5090a680220f52788d19d57889df
