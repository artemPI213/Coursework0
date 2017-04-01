package mainpack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthForm extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField loginField;
	private JLabel labelAuth;
	private JLabel labelLogin;
	private JLabel labelPassword;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthForm frame = new AuthForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AuthForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 260, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		loginField = new JTextField();
		loginField.setBounds(101, 74, 118, 20);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		labelAuth = new JLabel("\u0410\u0432\u0442\u043E\u0440\u0438\u0437\u0430\u0446\u0438\u044F");
		labelAuth.setHorizontalAlignment(SwingConstants.CENTER);
		labelAuth.setBounds(31, 30, 188, 14);
		contentPane.add(labelAuth);
		
		labelLogin = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
		labelLogin.setBounds(31, 77, 60, 14);
		contentPane.add(labelLogin);
		
		labelPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
		labelPassword.setBounds(31, 108, 60, 14);
		contentPane.add(labelPassword);
		
		JLabel labelMessages = new JLabel("");
		labelMessages.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessages.setBounds(31, 55, 188, 14);
		contentPane.add(labelMessages);
		
		JButton buttonAuth = new JButton("\u0412\u0445\u043E\u0434");
		buttonAuth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (loginField.getText().equals("1") && passwordField.getText().equals("1")) 
				{
				FirstForm fr1 = new FirstForm();
				fr1.setVisible(true);	
				setVisible(false);
				}
			else if (loginField.getText().equals("2") && passwordField.getText().equals("2")) 
				{
				SecondForm fr2 = new SecondForm();
				fr2.setVisible(true);	
				setVisible(false);
				}
			else
				{
				labelMessages.setText("Неверные логин или пароль.");
				}
			}
		});
		buttonAuth.setBounds(31, 136, 89, 23);
		contentPane.add(buttonAuth);
		
		JButton buttonExit = new JButton("\u0412\u044B\u0445\u043E\u0434");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonExit.setBounds(130, 136, 89, 23);
		contentPane.add(buttonExit);
		
		passwordField = new JTextField();
		passwordField.setBounds(101, 105, 118, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
	}
}
