package Engine;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.Node;
import java.awt.GridLayout;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class GUI extends JFrame {

	private String problem = "";
	private String description = "";
	private String email = "";
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JSpinner spinner_6;
	private JPanel panel_5;
	private JPanel panel_7;
	private Worker worker;
	private JSpinner date;
	private File file;
	private JSpinner varType;
	public String type;
	private JCheckBox semiBox;
	private JCheckBox manualBox;
	private JCheckBox automaticBox;
	private DefaultListModel<String> selectedAlgs = new DefaultListModel<>();
	private ArrayList<JTextField> objectives = new ArrayList<>();
	private Random rand = new Random();
	private final String[] algorth = new String[] { "NSGAII", "SMSEMOA", "MOCell", "PAES", "RandomSearch" };
	private JButton rmvAlg;
	private JTextField jarPathField;
	private JTextArea textArea_8;
	private String algorithType;

	public void setAlgorithType(String algorithType) {
		this.algorithType = algorithType;
	}
	public JTextArea getTextArea() {
		return textArea;
	}

	private JTextField textField;

	public ArrayList<JTextField> getObjectives(){
		return objectives;
	}
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// GUI frame = new GUI();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public GUI(Worker w) {
		worker = w;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 758);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Problem", null, panel, null);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane_2);
		JPanel panel_2 = new JPanel();
		tabbedPane_2.addTab("Describe Problem", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel label = new JLabel("Problem:");
		label.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label.setBounds(12, 28, 69, 19);
		panel_2.add(label);

		JButton load = new JButton("Load");
		load.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		load.setBounds(500, 28, 69, 19);  
		panel_2.add(load);
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				worker.load(textArea.getText());

			}
		});

		textArea = new JTextArea();
		textArea.setBounds(86, 27, 193, 18);
		panel_2.add(textArea);

		JLabel label_1 = new JLabel("Email:");
		label_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_1.setBounds(12, 378, 41, 19);
		panel_2.add(label_1);

		textArea_1 = new JTextArea();
		textArea_1.setBounds(65, 377, 275, 16);
		// textArea_1.setText(worker.getUser().getMail());
		textArea.setText("Teste");
		panel_2.add(textArea_1);

		JLabel label_2 = new JLabel("Problem's Description:");
		label_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_2.setBounds(12, 60, 155, 19);
		panel_2.add(label_2);

		textArea_2 = new JTextArea();
		textArea_2.setBounds(12, 104, 635, 230);
		panel_2.add(textArea_2);

		JLabel label_3 = new JLabel("Answer untill:");
		label_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_3.setBounds(12, 440, 89, 19);
		panel_2.add(label_3);

		JButton btnSubmeter = new JButton("Submit");
		btnSubmeter.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnSubmeter.setBounds(539, 566, 108, 25);
		btnSubmeter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				problem = textArea.getText();
				email = textArea_1.getText();
				description = textArea_2.getText();
				worker.sendMail("isctee@gmail.com", email, problem, description);
				// ema
			}
		});
		panel_2.add(btnSubmeter);

		date = new JSpinner();
		date.setModel(new SpinnerDateModel(new Date(1520899200000L), null, null, Calendar.DAY_OF_YEAR));
		date.setBounds(113, 439, 119, 22);
		panel_2.add(date);
		setDate(null);

		JButton btnNewButton_3 = new JButton("Save");
		btnNewButton_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnNewButton_3.setBounds(422, 566, 97, 25);
		panel_2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ButtonListener());

		panel_5 = new JPanel();
		tabbedPane_2.addTab("Problem's decision variables", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel label_5 = new JLabel("Number of decision variables:");
		label_5.setBounds(12, 33, 214, 19);
		panel_5.add(label_5);
		label_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));

		JLabel label_10 = new JLabel("Type of decision variables:");
		label_10.setBounds(12, 60, 214, 19);
		panel_5.add(label_10);
		label_10.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));

		spinner_6 = new JSpinner();
		spinner_6.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinner_6.setBounds(238, 32, 30, 22);
		panel_5.add(spinner_6);
		JLabel label_6 = new JLabel("Decision variables' name:");
		label_6.setBounds(12, 100, 191, 16);
		panel_5.add(label_6);
		label_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));

		varType = new JSpinner();
		varType.setModel(new SpinnerListModel(new String[] { "Integer", "Double", "Binary" }));
		varType.setBounds(238, 60, 89, 22);
		panel_5.add(varType);

		textArea_8 = new JTextArea();
		textArea_8.setBounds(198, 98, 207, 22);
		panel_5.add(textArea_8);
		// -------------------------------------------------------------------------------------------------------------------------------------
		JLabel lblIntervaloDeValores = new JLabel("Range of values");
		lblIntervaloDeValores.setBounds(400, 133, 144, 16);
		panel_5.add(lblIntervaloDeValores);
		lblIntervaloDeValores.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));

		// JLabel lblNewLabel_10 = new JLabel("Tipo de Vari\u00E1vel");
		// lblNewLabel_10.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		// lblNewLabel_10.setBounds(295, 200, 120, 16);
		// panel_5.add(lblNewLabel_10);

		// ------------------------------------------------------------------------------------------
		JButton btnNewButton_1 = new JButton("Continue");
		btnNewButton_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnNewButton_1.setBounds(400, 40, 97, 25);
		panel_5.add(btnNewButton_1);

		JButton btnNewButton_4 = new JButton("Save");
		btnNewButton_4.addActionListener(new ButtonListener());
		btnNewButton_4.setBounds(497, 561, 97, 25);
		panel_5.add(btnNewButton_4);
		btnNewButton_1.addActionListener(new ButtonListener2());

		panel_7 = new JPanel();
		panel_7.setLayout(null);
		tabbedPane_2.addTab("Objectives", null, panel_7, null);

		JLabel lblNumberOfObjectives = new JLabel("Number of Objectives");
		lblNumberOfObjectives.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNumberOfObjectives.setBounds(12, 41, 214, 19);
		panel_7.add(lblNumberOfObjectives);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(238, 40, 30, 22);
		panel_7.add(spinner);

		JLabel lblObjectives = new JLabel("Objectives:");
		lblObjectives.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblObjectives.setBounds(12, 102, 144, 16);
		panel_7.add(lblObjectives);

		JButton button = new JButton("Continue");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int nroVar = Integer.parseInt(spinner.getValue().toString());
				for (int c = 0; c < nroVar; c++) {
					int aux = c * 45;
					creatObjectiveField(aux);
				}
			}
		});
		button.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		button.setBounds(311, 38, 97, 25);
		panel_7.add(button);

		JButton button_1 = new JButton("Save");
		button_1.setBounds(497, 496, 97, 25);
		panel_7.add(button_1);

		JPanel panel_4 = new JPanel();
		tabbedPane_2.addTab("Algorithms", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblRestriesParaO = new JLabel("Type of use:");
		lblRestriesParaO.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		lblRestriesParaO.setBounds(12, 13, 430, 22);
		panel_4.add(lblRestriesParaO);

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ButtonListener());
		saveBtn.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		saveBtn.setBounds(464, 563, 97, 25);
		panel_4.add(saveBtn);

		JSeparator separator = new JSeparator();
		separator.setBounds(210, 65, 1, 324);
		panel_4.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(500, 65, 1, 324);
		panel_4.add(separator_1);

		JComboBox manualAlgorithms = new JComboBox();

		manualAlgorithms.setBounds(10, 120, 140, 25);
		panel_4.add(manualAlgorithms);

		JButton manualAdd = new JButton("Add");
		manualAdd.setEnabled(false);

		manualAdd.setBounds(160, 121, 51, 23);
		panel_4.add(manualAdd);

		JComboBox demiAlgorithms = new JComboBox();
		demiAlgorithms.setBounds(276, 120, 140, 25);
		panel_4.add(demiAlgorithms);

		JButton semiAdd = new JButton("Add");
		semiAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String element = demiAlgorithms.getModel().getSelectedItem().toString();
				if (!selectedAlgs.contains(element))
					selectedAlgs.addElement(element);

			}

		});
		semiAdd.setEnabled(false);
		semiAdd.setBounds(426, 121, 51, 23);
		panel_4.add(semiAdd);

		JList algs = new JList(selectedAlgs);
		// algs.addListSelectionListener(new ListSelectionListener() {
		//
		// @Override
		// public void valueChanged(ListSelectionEvent e) {
		//
		// }
		// });
		algs.setBounds(131, 303, 430, 115);
		panel_4.add(algs);

		JButton automaticAdd = new JButton("Generate Automatic");
		automaticAdd.setEnabled(false);
		automaticAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedAlgs.clear();
				int val = rand.nextInt(algorth.length);
				for (int i = 0; i < val; i++) {
					int rando = rand.nextInt(algorth.length);
					if (!selectedAlgs.contains(algorth[rando]))
						selectedAlgs.addElement(algorth[rando]);
				}

			}
		});
		automaticAdd.setBounds(563, 121, 139, 23);
		panel_4.add(automaticAdd);

		manualBox = new JCheckBox("Manual");
		manualBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!manualBox.isSelected()) {
					// semiAdd.setEnabled(false);
					// automaticAdd.setEnabled(false);
					manualAdd.setEnabled(false);
					semiBox.setEnabled(true);
					automaticBox.setEnabled(true);
				} else {
					// semiAdd.setEnabled(true);
					// automaticAdd.setEnabled(true
					manualAdd.setEnabled(true);
					semiBox.setEnabled(false);
					automaticBox.setEnabled(false);
				}

			}
		});
		manualBox.setBounds(50, 74, 97, 23);
		panel_4.add(manualBox);

		semiBox = new JCheckBox("Semi Automatic");
		semiBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!semiBox.isSelected()) {
					// manualAdd.setEnabled(false);
					// automaticAdd.setEnabled(false);
					semiAdd.setEnabled(false);
					manualBox.setEnabled(true);
					automaticBox.setEnabled(true);
					selectedAlgs.clear();
				} else {
					// manualAdd.setEnabled(true);
					// automaticAdd.setEnabled(true);
					semiAdd.setEnabled(true);
					manualBox.setEnabled(false);
					automaticBox.setEnabled(false);
					for (int i = 0; i < 2; i++) {
						int rando = rand.nextInt(algorth.length);
						if (!selectedAlgs.contains(algorth[rando]))
							selectedAlgs.addElement(algorth[rando]);
					}
				}

			}
		});
		semiBox.setBounds(319, 74, 111, 23);
		panel_4.add(semiBox);

		automaticBox = new JCheckBox("Automatic");
		automaticBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!automaticBox.isSelected()) {
					// manualAdd.setEnabled(false);
					// semiAdd.setEnabled(false);
					automaticAdd.setEnabled(false);
					semiBox.setEnabled(true);
					manualBox.setEnabled(true);
					rmvAlg.setEnabled(true);
				} else {
					// manualAdd.setEnabled(true);
					// semiAdd.setEnabled(true);
					automaticAdd.setEnabled(true);
					semiBox.setEnabled(false);
					manualBox.setEnabled(false);
					rmvAlg.setEnabled(false);
				}

			}
		});
		automaticBox.setBounds(591, 74, 111, 23);
		panel_4.add(automaticBox);

		rmvAlg = new JButton("Remove");
		rmvAlg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!selectedAlgs.isEmpty())
					selectedAlgs.remove(algs.getSelectedIndex());
			}
		});
		rmvAlg.setBounds(472, 429, 89, 23);
		panel_4.add(rmvAlg);

		JLabel lblJarPath = new JLabel("Jar Path:");
		lblJarPath.setBounds(50, 496, 46, 14);
		panel_4.add(lblJarPath);

		jarPathField = new JTextField();
		jarPathField.setBounds(106, 493, 268, 20);
		panel_4.add(jarPathField);
		jarPathField.setColumns(10);

		JButton browseBtn = new JButton("Browse");
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				JFrame fileframe = new JFrame("FrameDemo");
				fileframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				int returnVal = fileChooser.showSaveDialog(fileframe);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (fileChooser.getSelectedFile().getAbsolutePath().endsWith(".jar"))
						jarPathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
					else
						JOptionPane.showMessageDialog(null, "Error: File is not a JAR file");
				}
			}
		});
		browseBtn.setBounds(396, 492, 89, 23);
		panel_4.add(browseBtn);
		JButton runBtn = new JButton("Run");
		runBtn.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		runBtn.setBounds(574, 563, 97, 25);
		runBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				worker.saveProblem();
				worker.run();
			}
		});
		panel_4.add(runBtn);

		for (String s : algorth) {
			manualAlgorithms.addItem(s);
			demiAlgorithms.addItem(s);
		}

		JPanel panel_3 = new JPanel();
		tabbedPane_2.addTab("Problem's File", null, panel_3, null);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblAdicionaAquiO = new JLabel("Adiciona aqui o ficheiro .jar do teu problema");
		panel_3.add(lblAdicionaAquiO);

		JFileChooser fileChooser = new JFileChooser();
		panel_3.add(fileChooser);
		file = new File(fileChooser.getSelectedFile(), "Problem");
		fileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				worker.setJar(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Existing problem", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Problem's name:");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(12, 38, 151, 16);
		panel_1.add(lblNewLabel_1);

		JTextArea textArea_18 = new JTextArea();
		textArea_18.setBounds(161, 36, 223, 18);
		panel_1.add(textArea_18);

		JButton btnNewButton_2 = new JButton("Load");
		btnNewButton_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		btnNewButton_2.setBounds(441, 29, 110, 25);
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// worker.setJar(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});
		panel_1.add(btnNewButton_2);

		JLabel lblNewLabel_4 = new JLabel("Problem's definition:");
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
		tabbedPane_1.addTab("New", null, newEmailPanel, null);
		newEmailPanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("To:");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(12, 13, 56, 16);
		newEmailPanel.add(lblNewLabel_2);

		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(59, 11, 355, 22);
		newEmailPanel.add(textArea_3);

		JLabel lblNewLabel_3 = new JLabel("Subject:");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(12, 50, 56, 16);
		newEmailPanel.add(lblNewLabel_3);

		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(79, 46, 346, 22);
		newEmailPanel.add(textArea_4);

		JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(12, 96, 635, 224);
		newEmailPanel.add(textArea_5);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnNewButton.setBounds(12, 347, 97, 25);
		newEmailPanel.add(btnNewButton);

		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("Inbox", null, panel_6, null);
		panel_6.setLayout(null);

		JList list = new JList();
		list.setBounds(12, 13, 190, 515);
		panel_6.add(list);

		JPanel helpPanel = new JPanel();
		tabbedPane.addTab("Help", null, helpPanel, null);
		helpPanel.setLayout(null);

		JLabel label_24 = new JLabel("Frequently asked questions:");
		label_24.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_24.setBounds(12, 28, 156, 16);
		helpPanel.add(label_24);
		// helper(0, null, null, null);
		manualAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String element = manualAlgorithms.getModel().getSelectedItem().toString();
				if (!selectedAlgs.contains(element))
					selectedAlgs.addElement(element);
			}
		});
//		creatVariableField(0,null,null,null);
	}

	public JTextArea getTextArea_8() {
		return textArea_8;
	}

	public void setTextArea_8(String string) {
		this.textArea_8.setText(string);
	}

	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			problem = textArea.getText();
			System.out.println(problem);
			email = textArea_1.getText();
			System.out.println(email);
			description = textArea_2.getText();
			System.out.println(description);
			type = varType.getModel().getValue().toString();
			for (JTextField name : objectives)
				worker.addObjective(name.getText());
			// DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			// Date today = Calendar.getInstance().getTime();
			// String date = df.format(today);
			worker.saveProblem();
		}
	}

	class ButtonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			type = varType.getModel().getValue().toString();
			int nroVar = Integer.parseInt(spinner_6.getValue().toString());
			for (int c = 0; c < nroVar; c++) {
				int aux = c * 35;
				creatVariableField(aux, null, null, null);
			}
		}
	}

	public void creatVariableField(int aux, String name, String min, String max) {

		KeyAdapter restriction = new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				System.out.println(c);
				if (type == "Binary") {
					if (!(((c >= '0') && (c <= '1') || c == '-') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				} else if (type == "Double") {
					if ((((JTextField) e.getSource()).getText().contains(".") && c == '.')
							|| (((JTextField) e.getSource()).getText().contains("-") && c == '-')) {
						getToolkit().beep();
						e.consume();
					} else if (!(((c >= '0') && (c <= '9')
							|| ((c == '.' || c == '-')/* ||!() */)) || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						getToolkit().beep();
						e.consume();
					}
				} else if (!(((c >= '0') && (c <= '9') || c == '-') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		};
		JTextField spinner_8 = new JTextField();
		spinner_8.setBounds(500, 170 + aux, 100, 22);
		spinner_8.addKeyListener(restriction);
		panel_5.add(spinner_8);
		System.out.println("GUI - " + max);
		if (max != null)
			spinner_8.setText(max);

		JLabel label_10 = new JLabel("Max:");
		label_10.setBounds(458, 170+ aux, 56, 16);
		panel_5.add(label_10);
		label_10.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));

		JTextField spinner_9 = new JTextField();
		spinner_9.addKeyListener(restriction);
		spinner_9.setBounds(340, 170+ aux, 100, 22);
		panel_5.add(spinner_9);
		if (min != null)
			spinner_9.setText(min);
		JLabel label_12 = new JLabel("Variable's name:");
		label_12.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		label_12.setBounds(12, 170+ aux, 150, 16);
		panel_5.add(label_12);
		JTextArea textArea_9 = new JTextArea();
		textArea_9.setBounds(143, 170+ aux, 145, 22);
		panel_5.add(textArea_9);
		if (name != null)
			textArea_9.setText(name);
		repaint();
		worker.addVariable(new Variavel(spinner_9, spinner_8, textArea_9));
	}

	public void creatObjectiveField(int aux) {

		textField = new JTextField();
		textField.setBounds(102, 146 + aux, 245, 20);
		panel_7.add(textField);
		textField.setColumns(10);

		JLabel lblObjective = new JLabel("Objective " + (aux / 45) + ":");
		lblObjective.setBounds(41, 149, 64 + aux, 14);
		panel_7.add(lblObjective);
		objectives.add(textField);
		repaint();
		// worker.addObjective(name);
	}

	public String getProblem() {
		return problem;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getDate() {
		System.out.println(date.getModel().getValue());
		return date.getModel().getValue().toString();
	}

	public void setMail(String s) {
		textArea_1.setText(s);
	}

	public File getJarFile() {
		return file;
	}

	public void setDate(Node item) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		try {
			if (item != null)
				cal.setTime(sdf.parse(item.getNodeValue()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		date.setValue(cal.getTime());
	}

	public void setDescription(Node item) {
		System.out.println(item.getNodeValue());
		textArea_2.setText(item.getNodeValue());
	}
	public void creatObjectiveField(int aux, String string) {

		textField = new JTextField();
		textField.setBounds(102, 146 + aux, 245, 20);
		panel_7.add(textField);
		textField.setColumns(10);

		JLabel lblObjective = new JLabel("Objective " + (aux / 45) + ":");
		lblObjective.setBounds(41, 149, 64 + aux, 14);
		panel_7.add(lblObjective);
		objectives.add(textField);
		repaint();
		if(string!=null)
			textField.setText(string);
		// worker.addObjective(name);
	}

	public void setProblem(Node item) {
		System.out.println(item.getNodeValue());
		textArea.setText(item.getNodeValue());
	}

	public void setUserMail(Node item) {
		System.out.println(item.getNodeValue());
		textArea_1.setText(item.getNodeValue());
	}

	public String getTypeVar() {
		return type;
	}
	public void setTypeVar(String type2) {
		varType.setValue(type2);
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	
	public JTextField getJarPathField() {
		return jarPathField;
	}

	public DefaultListModel<String> getSelectedAlgs() {
		return selectedAlgs;
	}
	public String getAlgorithType() {
		return algorithType;
	}public void setSelectedAlgs(String s) {
		this.selectedAlgs.addElement(s);;
	}

	public JCheckBox getSemiBox() {
		return semiBox;
	}

	public void setSemiBox() {
		this.semiBox.doClick();
	}

	public JCheckBox getManualBox() {
		return manualBox;
	}

	public void setManualBox() {
		this.manualBox.doClick();
	}

	public JCheckBox getAutomaticBox() {
		return automaticBox;
	}

	public void setAutomaticBox() {
		this.automaticBox.doClick();
	}

	public void setJarPathField(String jarPathField) {
		this.jarPathField.setText(jarPathField);
	}


}
