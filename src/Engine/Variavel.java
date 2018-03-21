package Engine;

import javax.swing.JSpinner;

public class Variavel {
	
	private String name;
	private Object min;
	private Object max;
	
	private JSpinner type;
	private JSpinner s_min;
	private JSpinner s_max;
	
	public Variavel(JSpinner s, JSpinner mn, JSpinner mx ){
		type =s;
		min = mn;
		max = mx;
	}
	
	public void setValues(){
		name = type.getToolTipText();
		min = s_min.getValue();
		max = s_max.getValue();
		System.out.println( name +" - " +min +" - " +max);
	}
//public static void main(String[] args) {
//	new Variavel(3,6);
//}
}
