package Engine;

import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Variavel {
	
	private String name;
	private Object min;
	private Object max;
	
	private JSpinner type;
	private JTextField s_min;
	private JTextField s_max;
	private JTextArea s_name;
	
	public Variavel(JTextField spinner_9, JTextField spinner_8, JTextArea n ){
		s_name =n;
		s_min = spinner_9;
		s_max = spinner_8;
	}
	
	public void setValues(){
		min = s_min.getText();
		max = s_max.getText();
		name = s_name.getText();
		System.out.println( name +" - " +min +" - " +max);
	}
//public static void main(String[] args) {
//	new Variavel(3,6);
//}

	public String getMin() {
		return (String)min;
	}

	public String getMax() {
		return (String)max;
	}

	public String getName() {
		return name;
	}
}
