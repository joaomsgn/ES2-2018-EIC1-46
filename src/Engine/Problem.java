package Engine;

import java.util.ArrayList;

public class Problem {

	private String type;
	private String jarPath;
	private String description;
	private String title;
	private String date;
	private String email;
	private String problem;
	private User user;
	private ArrayList<Variavel> variables = new ArrayList<Variavel>();
	private ArrayList<String> objectives = new ArrayList<String>();
	private String[] algorithms;
	private String varName;
	private String algType;

	public String getAlgType() {
		return algType;
	}

	public void setAlgType(String algType) {
		this.algType = algType;
	}

	public String getVarName() {
		return varName;
	}
	

	public void setObjectives(ArrayList<String> objectives) {
		this.objectives = objectives;
	}

	public Problem() {

	}

	public String[] getAlgorithms() {
		return algorithms;
	}

	public void setAlgorithms(String[] algorithms) {
		this.algorithms = algorithms;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setVariables(ArrayList<Variavel> variables) {
		this.variables = variables;
	}

	public void addObjectives(String s) {
		this.objectives.add(s);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJarPath() {
		return jarPath;
	}

	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}

	public ArrayList<Variavel> getVariables() {
		return variables;
	}

	public void addVariables(Variavel variables) {
		this.variables.add(variables);
	}

	public ArrayList<String> getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives.add(objectives);
	}

	public void setVarName(String text) {
		// TODO Auto-generated method stub
		varName=text;
	}

}
