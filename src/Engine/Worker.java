package Engine;

import java.util.ArrayList;

public class Worker {

	private Mail mail_sender;
	private GUI frame;
	private ArrayList<Variavel> variables = new ArrayList<Variavel>();
	private Xml file = new Xml();
	private User user;
	public Worker(){
		LogIn login = new LogIn(this);
	}
	
	public void Lauch(){
		frame = new GUI(this);
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		new Worker();
	}
	
	public void setUser(User u){
		user=u;
	}
	
	public void sendMail(String from, String to, String subject, String body){
		mail_sender = new Mail();
		mail_sender.setValues(from, to, subject, body);
		mail_sender.start();
	}
	
	public void addVariable(Variavel v){
		variables.add(v);
}
	
	public void saveProblem(){
		file.initiate(frame.getEmail(), frame.getProblem(), frame.getDescription(), frame.getDate());
		for(Variavel var: variables){
		}
	}

	public User getUser() {
		return user;
	}
}
