package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LogInFrame extends JFrame {

	JTextField login;
	JPasswordField passwordField;
	JButton btnLogIn;
	private JPanel contentPane;

	public LogInFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 220, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblLogin = new JLabel("Login: ");
		contentPane.add(lblLogin);

		login = new JTextField();
		lblLogin.setLabelFor(login);
		login.setColumns(10);
		contentPane.add(login);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setLabelFor(passwordField);
		contentPane.add(lblPassword);
		contentPane.add(passwordField);

		btnLogIn = new JButton("Log in");
		btnLogIn.setMnemonic(KeyEvent.VK_ENTER);
		contentPane.add(btnLogIn);

	}
}
