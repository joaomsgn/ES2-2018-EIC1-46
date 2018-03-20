package Engine;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.SpinnerListModel;
import javax.swing.JProgressBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextPane;

public class GUI extends JFrame {
	
	private String problem="";
	private String description="";
	private String email="";
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JSpinner spinner_6;
	private JPanel panel_5 ;
	private Worker worker;
	private JSpinner date ;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI frame = new GUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GUI(Worker w) {
		worker =w;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 758);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Problema", null, panel, null);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(12, 5, 717, 653);
		panel.add(tabbedPane_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_2.addTab("Descrever Problema", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Problema:");
		label.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label.setBounds(12, 28, 69, 19);
		panel_2.add(label);
		
		 textArea = new JTextArea();
		textArea.setBounds(86, 27, 193, 18);
		panel_2.add(textArea);
		
		
		
		JLabel label_1 = new JLabel("Email:");
		label_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_1.setBounds(12, 378, 41, 19);
		panel_2.add(label_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(65, 377, 275, 16);
//		textArea_1.setText(worker.getUser().getMail());
		panel_2.add(textArea_1);
		
		
		JLabel label_2 = new JLabel("Descri\u00E7\u00E3o do Problema:");
		label_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_2.setBounds(12, 60, 155, 19);
		panel_2.add(label_2);
		
		 textArea_2 = new JTextArea();
		textArea_2.setBounds(12, 104, 635, 230);
		panel_2.add(textArea_2);
		
		
		JLabel label_3 = new JLabel("Resposta at\u00E9:");
		label_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_3.setBounds(12, 440, 89, 19);
		panel_2.add(label_3);
		
		JButton btnSubmeter = new JButton("Submeter");
		btnSubmeter.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnSubmeter.setBounds(539, 566, 108, 25);
		btnSubmeter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				problem = textArea.getText();
	        	email = textArea_1.getText();
	        	description = textArea_2.getText();
				worker.sendMail("isctee@gmail.com",email, problem, description);
//				ema
			}
		});
		panel_2.add(btnSubmeter);
		
		
		 date = new JSpinner();
		date.setModel(new SpinnerDateModel(new Date(1520899200000L), null, null, Calendar.DAY_OF_YEAR));
		date.setBounds(113, 439, 119, 22);
		panel_2.add(date);
		
		JButton btnNewButton_3 = new JButton("Guardar");
		btnNewButton_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnNewButton_3.setBounds(422, 566, 97, 25);
		panel_2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ButtonListener());
		
		 panel_5 = new JPanel();
		tabbedPane_2.addTab("Vari\u00E1veis de Decis\u00E3o do Problema", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel label_5 = new JLabel("N\u00FAmero de Vari\u00E1veis de decis\u00E3o:");
		label_5.setBounds(12, 33, 214, 19);
		panel_5.add(label_5);
		label_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		
		spinner_6 = new JSpinner();
		spinner_6.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner_6.setBounds(238, 32, 30, 22);
		panel_5.add(spinner_6);
		JLabel label_6 = new JLabel("Nome Vari\u00E1veis de Decis\u00E3o:");
		label_6.setBounds(12, 87, 191, 16);
		panel_5.add(label_6);
		label_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		
		JTextArea textArea_8 = new JTextArea();
		textArea_8.setBounds(198, 85, 207, 22);
		panel_5.add(textArea_8);
//-------------------------------------------------------------------------------------------------------------------------------------
		JLabel lblIntervaloDeValores = new JLabel("Intervalo de Valores");
		lblIntervaloDeValores.setBounds(450, 200, 144, 16);
		panel_5.add(lblIntervaloDeValores);
		lblIntervaloDeValores.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
	 
		JLabel lblNewLabel_10 = new JLabel("Tipo de Vari\u00E1vel");
		lblNewLabel_10.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(295, 200, 120, 16);
		panel_5.add(lblNewLabel_10);
		
//------------------------------------------------------------------------------------------
		JButton btnNewButton_1 = new JButton("Continuar");
		btnNewButton_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnNewButton_1.setBounds(300, 30, 97, 25);
		panel_5.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("Guardar");
		btnNewButton_4.setBounds(497, 496, 97, 25);
		panel_5.add(btnNewButton_4);
		btnNewButton_1.addActionListener(new ButtonListener2());
		
		JPanel panel_4 = new JPanel();
		tabbedPane_2.addTab("Restri\u00E7\u00F5es", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblRestriesParaO = new JLabel("Restri\u00E7\u00F5es para o problema de otimiza\u00E7\u00E3o");
		lblRestriesParaO.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		lblRestriesParaO.setBounds(12, 13, 430, 22);
		panel_4.add(lblRestriesParaO);
		
		JLabel lblNomeDaVarivel = new JLabel("Nome da vari\u00E1vel de decis\u00E3o:");
		lblNomeDaVarivel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		lblNomeDaVarivel.setBounds(12, 79, 256, 16);
		panel_4.add(lblNomeDaVarivel);
		
		JTextArea textArea_7 = new JTextArea();
		textArea_7.setBounds(246, 78, 305, 22);
		panel_4.add(textArea_7);
		
		JLabel lblNewLabel_8 = new JLabel("Crit\u00E9rios que pretende ver otimizados:");
		lblNewLabel_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(12, 238, 305, 22);
		panel_4.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Crit\u00E9rio:");
		lblNewLabel_9.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(12, 282, 71, 16);
		panel_4.add(lblNewLabel_9);
		
		JLabel lblNewLabel_11 = new JLabel("Valores inv\u00E1lidos:");
		lblNewLabel_11.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(12, 127, 122, 16);
		panel_4.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Condi\u00E7\u00F5es");
		lblNewLabel_12.setBounds(12, 169, 71, 16);
		panel_4.add(lblNewLabel_12);
		
		JLabel label_4 = new JLabel("Crit\u00E9rio:");
		label_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_4.setBounds(12, 319, 71, 16);
		panel_4.add(label_4);
		
		JLabel label_21 = new JLabel("Crit\u00E9rio:");
		label_21.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_21.setBounds(12, 356, 71, 16);
		panel_4.add(label_21);
		
		JLabel label_22 = new JLabel("Crit\u00E9rio:");
		label_22.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_22.setBounds(12, 396, 71, 16);
		panel_4.add(label_22);
		
		JLabel label_23 = new JLabel("Crit\u00E9rio:");
		label_23.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_23.setBounds(12, 436, 71, 16);
		panel_4.add(label_23);
		
		JTextArea textArea_6 = new JTextArea();
		textArea_6.setBounds(71, 280, 167, 18);
		panel_4.add(textArea_6);
		
		JTextArea textArea_14 = new JTextArea();
		textArea_14.setBounds(71, 317, 167, 18);
		panel_4.add(textArea_14);
		
		JTextArea textArea_15 = new JTextArea();
		textArea_15.setBounds(71, 354, 167, 18);
		panel_4.add(textArea_15);
		
		JTextArea textArea_16 = new JTextArea();
		textArea_16.setBounds(71, 394, 167, 18);
		panel_4.add(textArea_16);
		
		JTextArea textArea_17 = new JTextArea();
		textArea_17.setBounds(71, 434, 167, 18);
		panel_4.add(textArea_17);
		
		JButton btnNewButton_5 = new JButton("Guardar");
		btnNewButton_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnNewButton_5.setBounds(563, 564, 97, 25);
		panel_4.add(btnNewButton_5);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Problemas Existentes", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do Problema:");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(12, 38, 151, 16);
		panel_1.add(lblNewLabel_1);
		
		JTextArea textArea_18 = new JTextArea();
		textArea_18.setBounds(161, 36, 223, 18);
		panel_1.add(textArea_18);
		
		JButton btnNewButton_2 = new JButton("Carregar");
		btnNewButton_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnNewButton_2.setBounds(441, 29, 110, 25);
		panel_1.add(btnNewButton_2);
		
		JLabel lblNewLabel_4 = new JLabel("Caracteriza\u00E7\u00E3o do Problema:");
		lblNewLabel_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(12, 106, 210, 16);
		panel_1.add(lblNewLabel_4);
		
		JTextArea textArea_19 = new JTextArea();
		textArea_19.setBounds(12, 135, 664, 256);
		panel_1.add(textArea_19);
		
		JPanel EmailPanel = new JPanel();
		tabbedPane.addTab("E-mail", null, EmailPanel, null);
		EmailPanel.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(12, 13, 664, 571);
		EmailPanel.add(tabbedPane_1);
		
		JPanel newEmailPanel = new JPanel();
		tabbedPane_1.addTab("Novo", null, newEmailPanel, null);
		newEmailPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Para:");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(12, 13, 56, 16);
		newEmailPanel.add(lblNewLabel_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(59, 11, 355, 22);
		newEmailPanel.add(textArea_3);
		
		JLabel lblNewLabel_3 = new JLabel("Assunto:");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(12, 50, 56, 16);
		newEmailPanel.add(lblNewLabel_3);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(79, 46, 346, 22);
		newEmailPanel.add(textArea_4);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(12, 96, 635, 224);
		newEmailPanel.add(textArea_5);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnNewButton.setBounds(12, 347, 97, 25);
		newEmailPanel.add(btnNewButton);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("Caixa de Entrada", null, panel_6, null);
		panel_6.setLayout(null);
		
		JList list = new JList();
		list.setBounds(12, 13, 190, 515);
		panel_6.add(list);
		
		JPanel helpPanel = new JPanel();
		tabbedPane.addTab("Ajuda", null, helpPanel, null);
		helpPanel.setLayout(null);
		
		JLabel label_24 = new JLabel("Perguntas Frequentes:");
		label_24.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_24.setBounds(12, 28, 156, 16);
		helpPanel.add(label_24);
	}
	class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
        	problem = textArea.getText();
        	System.out.println(problem);
        	email = textArea_1.getText();
        	System.out.println(email);
        	description = textArea_2.getText();
        	System.out.println(description);
        	//DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        	//Date today = Calendar.getInstance().getTime();
        	//String date = df.format(today);
        	 worker.saveProblem();
    }
}
	class ButtonListener2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int nroVar = (int) spinner_6.getValue();
			for(int c = 0; c<nroVar; c++){
				int aux =c*45;
				helper(aux);
			}
		}
		
public void helper(int aux){
	JSpinner spinner_7 = new JSpinner();
	spinner_7.setModel(new SpinnerListModel(new String[] {"Integer", "Float", "Byte", "Short", "Decimal", "Boolean", "Long", "Double"}));
	spinner_7.setBounds(300, 245 + aux, 89, 22);
	panel_5.add(spinner_7);
	
	JLabel label_9 = new JLabel("Min:");
	label_9.setBounds(434, 245 +aux, 30, 16);
	panel_5.add(label_9);
	label_9.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
	
	JSpinner spinner_8 = new JSpinner();
	spinner_8.setBounds(564, 245 +aux, 30, 22);
	panel_5.add(spinner_8);
	
	JLabel label_10 = new JLabel("Max:");
	label_10.setBounds(518, 245 +aux, 56, 16);
	panel_5.add(label_10);
	label_10.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
	
	JSpinner spinner_9 = new JSpinner();
	spinner_9.setBounds(476, 245 +aux, 30, 22);
	panel_5.add(spinner_9);
	
	JLabel label_12 = new JLabel("Nome da Vari\u00E1vel:");
	label_12.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
	label_12.setBounds(12, 245 +aux, 150, 16);
	panel_5.add(label_12);
	
	JTextArea textArea_9 = new JTextArea();
	textArea_9.setBounds(143, 245 +aux, 145, 22);
	panel_5.add(textArea_9); 
	repaint();
	
	// worker.addVariable(new Variavel(spinner_7, spinner_8, spinner_9));
}
	
}
	/**
	 * @return the problem
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	 public String getDate(){
		 return date.getToolTipText();
	 }
	 
	 public void setMail(String s){
		 textArea_1.setText(s);
	 }
}





//    for matches1 as curs1 Cursor for select idLog as a1, dataLog as a2,tipoAccao as a3,idUtilizadorBD as a4,informacao as a5
//    ,emailUtilizador_pkTUtilizador as a6,senhaUtilizador as a7,nomeUtilizador as a8,numeroCliente_pkTCliente as a9 
//    ,morada as a10,contribuinte as a11 from LogUtilizadores where idLog > @ultimoMigrado order by idLog ASC DO 
//    call SP_InsertLogUtilizador(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,@numeroMigração);
//
