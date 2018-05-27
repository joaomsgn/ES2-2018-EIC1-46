package jMetalES2Example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.solution.impl.DefaultBinarySolution;
import org.uma.jmetal.util.JMetalException;

import Engine.Problem;

/* Implementação de um problema do tipo Binary que executa o .jar externo
   OneZeroMax.jar e pode ser usado como um dos problema de teste indicados 
   no encunciado do trabalho */

@SuppressWarnings("serial")
public class MyProblemBinaryExternalViaJAR extends AbstractBinaryProblem {

	// public static void main(String[] args) {
	// new MyProblemBinaryExternalViaJAR("",100);
	// }
	private int bits;
	Problem prob;
 
	public MyProblemBinaryExternalViaJAR(Problem p) throws JMetalException {
		prob = p;
		setNumberOfVariables(prob.getVariables().size());
		setNumberOfObjectives(prob.getObjectives().size());
		setName("MyProblemBinaryExternalViaJAR");
		bits = ((String) prob.getVariables().get(0).getMin()).length();
		createSolution();

	}

	@Override
	protected int getBitsPerVariable(int index) {
//		if (index != 0) {
//			throw new JMetalException("Problem MyBinaryProblem has only a variable. Index = " + index);
//		}
		return bits;
	}

	@Override
	public BinarySolution createSolution() {
		return new DefaultBinarySolution(this);
	}

	@Override
	public void evaluate(BinarySolution solution) {

		String solutionString = "";
		String evaluationResultString = "";
		BitSet bitset = solution.getVariableValue(0);
		solutionString = bitset.toString();
		try {
			String line;
			Process p = Runtime.getRuntime().exec("java -jar \"" + prob.getJarPath() + "\"   " + solutionString);
			BufferedReader brinput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = brinput.readLine()) != null) {
				evaluationResultString += line;
			}
			brinput.close();
			p.waitFor();
		} catch (Exception err) {
			err.printStackTrace();
		}
		String[] individualEvaluationCriteria = evaluationResultString.split("\\s+");
		// It is assumed that all evaluated criteria are returned in the same
		// result string
		for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
			solution.setObjective(i, Double.parseDouble(individualEvaluationCriteria[i]));
		}

	}
}
