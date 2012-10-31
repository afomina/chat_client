package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.security.auth.login.LoginException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client {

	Socket clientSocket;

	LogInFrame logInFrame = new LogInFrame();
	Chat chat;
	String login;
	String password;
	InputStream sin;
	OutputStream sout;

	DataInputStream in = new DataInputStream(sin);
	DataOutputStream out = new DataOutputStream(sout);

	public static final int AUTH = 1;

	public Client() throws UnknownHostException, IOException {
		logInFrame.setVisible(true);
		clientSocket = new Socket(InetAddress.getByName("127.0.0.1"), 6680);
		sin = clientSocket.getInputStream();
		sout = clientSocket.getOutputStream();

		in = new DataInputStream(sin);
		out = new DataOutputStream(sout);

	}

	public void sendData() throws LoginException, IOException {
		 out.writeByte(AUTH);

		out.writeUTF(login);
		out.writeUTF(password);
		out.flush();

		String serverAnswer = in.readUTF();
		if (!serverAnswer.equals(login)) {
			throw new LoginException();
		}

	}

	public void sendMessage(String message) {
		if (!message.equals("")) {
			try {
				out.writeUTF(login + ": " + message);
				out.flush();

				String answer = in.readUTF();
				String text = chat.chatPane.getText();
				chat.chatPane.setText(text + answer + "\n");

			} catch (SocketException se) {
				showErrorMessage("Server connection failed");
				chat.chatPane.setEnabled(false);
			} catch (IOException e) {

			}

		}
	}

	public void chat() {
		chat = new Chat();
		MessageListener msgListener = this.new MessageListener();
		chat.msgField.addActionListener(msgListener);
		chat.sendButton.addActionListener(msgListener);
	}

	public static void main(String[] args) {
		try {
			Client client = new Client();
			LogInListener loginListener = client.new LogInListener();
			client.logInFrame.btnLogIn.addActionListener(loginListener);
			client.logInFrame.passwordField.addActionListener(loginListener);
		} catch (UnknownHostException e) {
			showErrorMessage("Cannot connect to server");
		} catch (IOException e) {
			showErrorMessage("An error has occured, please try again");
			System.exit(1);
		}
	}

	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	class LogInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			login = logInFrame.login.getText();
			password = new String(logInFrame.passwordField.getPassword());

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
					showErrorMessage("An error has occured, please try again");
					return;
				}
				logInFrame.setVisible(false);
				chat();
			}
		}

	}

	class MessageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String message = chat.msgField.getText();
			sendMessage(message);
			chat.msgField.setText("");
		}
	}

}
