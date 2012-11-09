package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.security.auth.login.LoginException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
public class Client {
	public static final int PORT = 6680;
	public InetAddress inetAddress;

	Socket clientSocket;
	LogInFrame logInFrame = new LogInFrame();
	Chat chat;
	String login;
	String password;
	DataInputStream in;
	DataOutputStream out;

	public Client() {
		LogInListener loginListener = new LogInListener();
		logInFrame.btnLogIn.addActionListener(loginListener);
		logInFrame.passwordField.addActionListener(loginListener);
	}

	public void showGUI() {
		logInFrame.setVisible(true);
	}

	public void connect() {
		try {
			inetAddress = InetAddress.getByName("172.18.68.151");
			clientSocket = new Socket(inetAddress, PORT);

			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (UnknownHostException e) {
			showErrorMessage("Cannot connect to server");
		} catch (IOException e) {
			showErrorMessage("Cannot connect to server");
		}
	}

	public void login(String login, String password) {
		connect();

		this.login = login;
		this.password = password;

		if (login.equals("")) {
			showErrorMessage("Please enter your login");
		} else if (password.equals("")) {
			showErrorMessage("Please enter your password");
		} else {
			try {
				sendData();
			} catch (LoginException e1) {
				showErrorMessage("Login or password uncorrect");
				return;
			} catch (IOException e1) {
				showErrorMessage("Cannot connect to server");
				return;
			}
			logInFrame.setVisible(false);
			chat();
		}
	}

	void chat() {
		chat = new Chat();
		MessageListener msgListener = this.new MessageListener();
		chat.msgField.addActionListener(msgListener);
		chat.sendButton.addActionListener(msgListener);
	}

	void sendMessage(String message) {
		if (!message.equals("")) {
			try {
				out.writeUTF(login + ": " + message);
				out.flush();

				String answer = in.readUTF();
				String text = chat.chatPane.getText();
				chat.chatPane.setText(text + answer + "\n");
			} catch (SocketException se) {
				showErrorMessage("Connection failed");
			} catch (IOException e) {
				showErrorMessage("Cannot connect to server");
			}
			chat.msgField.setText("");
		}
	}

	void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	void sendData() throws LoginException, IOException {
		out.writeUTF(login);
		out.flush();
		out.writeUTF(password);
		out.flush();

		String serverAnswer = in.readUTF();
		if (!serverAnswer.equals(login)) {
			throw new LoginException();
		}
	}

	class LogInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			login(logInFrame.login.getText(), new String(
					logInFrame.passwordField.getPassword()));
		}
	}

	class MessageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String message = chat.msgField.getText();
			sendMessage(message);
		}
	}
}
