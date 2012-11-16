package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class LogInFrame extends JFrame {
	static final int X1 = 100;
	static final int X2 = 250;
	static final int Y1 = 100;
	static final int Y2 = 200;
	JTextField login;
	JPasswordField passwordField;
	JButton btnLogIn;
	private JPanel contentPane;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;

	public LogInFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(X1, Y1, X2, Y2);
		setMinimumSize(getSize());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 97, 97, 0 };
		gbl_contentPane.rowHeights = new int[] { 30, 0, 30, 30, 0, 30, 30, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblLogin = new JLabel("Login: ");
		lblLogin.setLabelFor(login);
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.EAST;
		gbc_lblLogin.fill = GridBagConstraints.VERTICAL;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 1;
		contentPane.add(lblLogin, gbc_lblLogin);

		login = new JTextField();
		login.setColumns(10);
		GridBagConstraints gbc_login = new GridBagConstraints();
		gbc_login.fill = GridBagConstraints.BOTH;
		gbc_login.insets = new Insets(0, 0, 5, 0);
		gbc_login.gridx = 1;
		gbc_login.gridy = 1;
		contentPane.add(login, gbc_login);

		label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		contentPane.add(label, gbc_label);

		label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		contentPane.add(label_1, gbc_label_1);

		JLabel lblPassword = new JLabel("Password: ");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.fill = GridBagConstraints.VERTICAL;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		contentPane.add(lblPassword, gbc_lblPassword);
		lblPassword.setLabelFor(passwordField);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.NORTH;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 3;
		contentPane.add(passwordField, gbc_passwordField);

		btnLogIn = new JButton("Log in");
		btnLogIn.setMnemonic(KeyEvent.VK_ENTER);
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogIn.fill = GridBagConstraints.BOTH;
		gbc_btnLogIn.gridx = 1;
		gbc_btnLogIn.gridy = 4;
		contentPane.add(btnLogIn, gbc_btnLogIn);

		label_2 = new JLabel("");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 5;
		contentPane.add(label_2, gbc_label_2);

		label_3 = new JLabel("");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 5;
		contentPane.add(label_3, gbc_label_3);

	}
}
