package Engine;

import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

import org.w3c.dom.Node;

import jMetalES2Example.ExperimentsBinary;
import jMetalES2Example.ExperimentsBinaryExternalViaJAR;
import jMetalES2Example.ExperimentsDouble;
import jMetalES2Example.ExperimentsDoubleExternalViaJAR;
import jMetalES2Example.ExperimentsInteger;
import jMetalES2Example.ExperimentsIntegerExternalViaJAR;

public class Worker {

	private Mail mail_sender;
	private GUI frame;
	// private ArrayList<Variavel> variables = new ArrayList<Variavel>();
	private Xml file = new Xml();
	private User user = new User("Neto", "olaadeus");
	private String jarPath;
	private Problem problem = new Problem();

	public Worker() {
//		LogIn login = new LogIn(this);
		 Lauch();
	}

	public void Lauch() {
		frame = new GUI(this);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new Worker();
	}

	public void setUser(User u) {
		user = u;
	}

	public void sendMail(String from, String to, String subject, String body) {
		mail_sender = new Mail(user.getMail(), user.getPassword());
		System.out.println(user.getMail() + " - " + user.getPassword());
		mail_sender.setValues(from, to, subject, body);
		mail_sender.start();
	}

	public void addVariable(Variavel v) {
		System.out.println("-----------------------------------------------------");
		problem.addVariables(v);
	}

	public void saveProblem() {
		problem.setDate(frame.getDate());
		problem.setProblem(frame.getProblem());
		problem.setUser(user);
		problem.setTitle(frame.getTextArea().getText());
		problem.setDescription(frame.getDescription());
		problem.setEmail(frame.getEmail());
		problem.setType(frame.getTypeVar());
		problem.setJarPath(frame.getJarPathField().getText());
		String[] result = new String[frame.getSelectedAlgs().size()];
		for (int i = 0; i < result.length; i++)
			result[i] = frame.getSelectedAlgs().getElementAt(i);
		for(Variavel var : problem.getVariables())
			var.setValues();
		for (JTextField name : frame.getObjectives())
			addObjective(name.getText());
		System.out.println(" --------------------------------------------------- " +frame.getTextArea_8().getText());
		problem.setVarName(frame.getTextArea_8().getText());
		problem.setAlgType(frame.getAlgorithType());
		problem.setAlgorithms(result);
		file.write(problem);
	}

	public User getUser() {
		return user;
	}

	public void setJar(String absolutePath) {
		jarPath = absolutePath;
		System.out.println(jarPath);
	}

	public void load(String filePath) {
		Node master = file.read(filePath);
		Node probItem = master.getChildNodes().item(3);
		System.out.println("user - " + user.getMail() + " - " + filePath);
		frame.setDate(probItem.getAttributes().item(0));
		frame.setDescription(probItem.getAttributes().item(1));
		frame.setProblem(probItem.getAttributes().item(2));
		frame.setUserMail(probItem.getAttributes().item(3));
		Node varItem = master.getChildNodes().item(5);
		// System.out.println(varItem.getAttributes().item(0).getNodeName());
		String type = varItem.getAttributes().item(0).getNodeValue();
		frame.setTextArea_8(varItem.getNodeName());
		for (int i = 1; i < varItem.getAttributes().getLength(); i++)
			frame.creatVariableField(((i - 1) * 45), varItem.getAttributes().item(i).getNodeName(),
					varItem.getAttributes().item(i).getNodeValue().split(" - ")[0],
					varItem.getAttributes().item(i).getNodeValue().split(" - ")[1]);
		// String name = varItem.getAttributes().item(1).getNodeValue();
		// String min = varItem.getAttributes().item(2).getNodeValue();
		// String max = varItem.getAttributes().item(3).getNodeValue();
		// creatFields(varItem.getAttributes().getLength(), name, min, max);
		frame.setTypeVar(type);
		
		Node varItem2 = master.getChildNodes().item(7);
		for (int i = 0; i < varItem2.getAttributes().getLength(); i++) {
			frame.creatObjectiveField(((i - 1) * 45), varItem2.getAttributes().item(i).getNodeValue());
		}
		
		Node varItem3 = master.getChildNodes().item(9);
		String algotype = varItem3.getAttributes().item(varItem3.getAttributes().getLength()-1).getNodeValue();
		System.out.println(algotype);
		if(algotype.compareTo("Manual")==0)
			frame.setManualBox();
		if(algotype.compareTo("Semi Automatic")==0)
			frame.setSemiBox();
		if(algotype.compareTo("Automatic")==0)
			frame.setAutomaticBox();
		frame.setJarPathField(varItem3.getAttributes().item(varItem3.getAttributes().getLength()-2).getNodeValue());
		for (int i = 0; i < varItem3.getAttributes().getLength()-2; i++) {
			frame.setSelectedAlgs(varItem3.getAttributes().item(i).getNodeValue());
		}
//		for (int i = 1; i < varItem.getAttributes().getLength(); i++)
			
		

	}

	public void creatFields(int nroVar, String name, String min, String max) {
		System.out.println(name + " - " + min + " - " + max);
		for (int c = 0; c < nroVar; c++) {
			int aux = c * 45;
			frame.creatVariableField(aux, name, min, max);
		}
	}

	public void setProblem(Node item) {
		frame.setDate(item.getAttributes().item(0));
		frame.setDescription(item.getAttributes().item(1));
		frame.setProblem(item.getAttributes().item(2));
		frame.setUserMail(item.getAttributes().item(3));
	}

	public void run() {
		String type = frame.getTypeVar();
		String jarPath = frame.getJarPathField().getText();
		switch (type) {
		case "Binary":

			if (jarPath.endsWith(".jar")) {
				ExperimentsBinaryExternalViaJAR b = new ExperimentsBinaryExternalViaJAR(problem);
				try {
					b.start();
					Process process = new ProcessBuilder("D:\\Programas\\R-3.4.3\\bin\\RScript.exe", "HV.Boxplot.R")
							.directory(new File("experimentBaseDirectory\\ExperimentsBinaryExternalViaJAR\\R")).start();
					Process process2 = new ProcessBuilder(
							"D:\\Programas\\Nova Pasta(2)\\miktex\\bin\\x64\\miktex-pdflatex.exe",
							"ExperimentsBinaryExternalViaJAR.tex").directory(
									new File("experimentBaseDirectory\\ExperimentsBinaryExternalViaJAR\\latex\\"))
									.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				ExperimentsBinary b = new ExperimentsBinary(problem);
				try {
					b.start();
					Process process = new ProcessBuilder("D:\\Programas\\R-3.4.3\\bin\\RScript.exe", "HV.Boxplot.R")
							.directory(new File("experimentBaseDirectory\\ExperimentsBinary\\R")).start();
					Process process2 = new ProcessBuilder(
							"D:\\Programas\\Nova Pasta(2)\\miktex\\bin\\x64\\miktex-pdflatex.exe",
							"ExperimentsBinary.tex")
									.directory(new File("experimentBaseDirectory\\ExperimentsBinary\\latex\\")).start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case "Double":
			if (jarPath.endsWith(".jar")) {
				ExperimentsDoubleExternalViaJAR b = new ExperimentsDoubleExternalViaJAR(problem);
				try {
					b.start();
					Process process = new ProcessBuilder("D:\\Programas\\R-3.4.3\\bin\\RScript.exe", "HV.Boxplot.R")
							.directory(new File("experimentBaseDirectory\\ExperimentsDoubleExternalViaJAR\\R")).start();
					Process process2 = new ProcessBuilder(
							"D:\\Programas\\Nova Pasta(2)\\miktex\\bin\\x64\\miktex-pdflatex.exe",
							"ExperimentsDouble.tex").directory(
									new File("experimentBaseDirectory\\ExperimentsDoubleExternalViaJAR\\latex\\"))
									.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} else {
				ExperimentsDouble b = new ExperimentsDouble(problem);
				try {
					b.start();
					Process process = new ProcessBuilder("D:\\Programas\\R-3.4.3\\bin\\RScript.exe", "HV.Boxplot.R")
							.directory(new File("experimentBaseDirectory\\ExperimentsDouble\\R")).start();
					Process process2 = new ProcessBuilder(
							"D:\\Programas\\Nova Pasta(2)\\miktex\\bin\\x64\\miktex-pdflatex.exe",
							"ExperimentsDouble.tex")
									.directory(new File("experimentBaseDirectory\\ExperimentsDouble\\latex\\")).start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case "Integer":
			if (jarPath.endsWith(".jar")) {
				ExperimentsIntegerExternalViaJAR b = new ExperimentsIntegerExternalViaJAR(problem);
				try {
					b.start();
					Process process = new ProcessBuilder("D:\\Programas\\R-3.4.3\\bin\\RScript.exe", "HV.Boxplot.R")
							.directory(new File("experimentBaseDirectory\\ExperimentsIntegerExternalViaJAR\\R"))
							.start();
					Process process2 = new ProcessBuilder(
							"D:\\Programas\\Nova Pasta(2)\\miktex\\bin\\x64\\miktex-pdflatex.exe",
							"ExperimentsIntegerExternalViaJAR.tex").directory(
									new File("experimentBaseDirectory\\ExperimentsIntegerExternalViaJAR\\latex\\"))
									.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				ExperimentsInteger b = new ExperimentsInteger(problem);
				try {
					b.start();
					Process process = new ProcessBuilder("D:\\Programas\\R-3.4.3\\bin\\RScript.exe", "HV.Boxplot.R")
							.directory(new File("experimentBaseDirectory\\ExperimentsInteger\\R")).start();
					Process process2 = new ProcessBuilder(
							"D:\\Programas\\Nova Pasta(2)\\miktex\\bin\\x64\\miktex-pdflatex.exe",
							"ExperimentsInteger.tex")
									.directory(new File("experimentBaseDirectory\\ExperimentsInteger\\latex\\"))
									.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
	}

	public void addObjective(String name) {
		// TODO Auto-generated method stub
		problem.addObjectives(name);
	}

}
