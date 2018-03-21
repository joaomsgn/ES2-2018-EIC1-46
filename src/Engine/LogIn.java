package Engine;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.RepaintManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LogIn {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextArea textArea;
	private JLabel icon;
	private ArrayList<User> users = new ArrayList<User>();
	private Worker worker;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LogIn window = new LogIn();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public LogIn(Worker w) {
		worker =w;
		read_BD();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 448, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 24, 56, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(12, 73, 84, 16);
		frame.getContentPane().add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(108, 72, 293, 19);
		frame.getContentPane().add(passwordField);

		textArea = new JTextArea();
		textArea.setBounds(108, 22, 293, 19);
		frame.getContentPane().add(textArea);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (User user : users) {
					System.out.println(user.getMail() +" - " +user.getPassword());
					System.out.println(user.getMail().equals(textArea.getText()));
					System.out.println(user.getPassword()+" - " +passwordField.getText());
					if (user.getMail().equals(textArea.getText())
							&& user.getPassword().equals(passwordField.getText())) {
						frame.setVisible(false);
						worker.setUser(user);
						worker.Lauch();
					}
					else{frame.repaint();
						textArea.setBackground(Color.RED);
						passwordField.setBackground(Color.RED);
						frame.repaint();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnNewButton.setBounds(12, 164, 126, 43);
		frame.getContentPane().add(btnNewButton);

		icon = new JLabel("");
		icon.setBounds(197, 104, 170, 170);
		Image img = new ImageIcon(/*this.getClass().getResource("iscte2.png")).getImage()*/ "images/iscte2.png");

		Image img2 = img.getScaledInstance(icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH);
		icon.setIcon(new ImageIcon(img2));

		frame.getContentPane().add(icon);

		}

	private void read_BD() {
		File f = new File("BD");
		try {
			Scanner r = new Scanner(f);
			while (r.hasNextLine()) {
				String[] a = r.nextLine().split("\\t");
				users.add(new User(a[0], a[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public String toString(){
		return textArea.getText();
		
	}
}
