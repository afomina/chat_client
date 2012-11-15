package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.security.auth.login.LoginException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client {
	public static final int PORT = 6680;
	static final int CONNECT_PORT = 6681;
	static final String ADDRESS = "172.18.68.151";
	static final int AUTH = 1;
	InetAddress inetAddress;
	Socket messageSocket;
	Socket connectSocket;
	LogInFrame logInFrame = new LogInFrame();
	Chat chat;
	volatile String login;
	volatile String password;
	volatile DataInputStream in;
	volatile DataOutputStream out;
	volatile DataInputStream authIn;
	volatile DataOutputStream authOut;

	Thread sendThread;

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
			inetAddress = InetAddress.getByName(ADDRESS);

			connectSocket = new Socket(inetAddress, CONNECT_PORT);
			authIn = new DataInputStream(connectSocket.getInputStream());
			authOut = new DataOutputStream(connectSocket.getOutputStream());

			messageSocket = new Socket(inetAddress, PORT);
			in = new DataInputStream(messageSocket.getInputStream());
			out = new DataOutputStream(messageSocket.getOutputStream());

			DataOutputStream connectOut = new DataOutputStream(
					connectSocket.getOutputStream());
			connectOut.writeInt(AUTH);
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
			sendData();
			logInFrame.setVisible(false);
			chat();
		}
	}

	void sendData() {
		try {
			out.writeUTF(login);
			out.flush();
			out.writeUTF(password);
			out.flush();
			String serverAnswer = in.readUTF();
			if (!serverAnswer.equals(login)) {
				throw new LoginException();
			}
		} catch (LoginException e1) {
			showErrorMessage("Login or password uncorrect");
		} catch (IOException e1) {
			showErrorMessage("Cannot connect to server");
		}
	}

	void chat() {
		chat = new Chat();

		Thread chatThread = new Thread(new ChatMessageListener());
		chatThread.setDaemon(true);
		chatThread.start();

		SendButtonListener msgListener = this.new SendButtonListener();
		sendThread = new Thread(msgListener);

		chat.msgField.addActionListener(msgListener);
		chat.sendButton.addActionListener(msgListener);
	}

	synchronized void sendMessage(final String message) {
		if (!message.equals("")) {
			try {
				out.writeUTF(login + ": " + message);
				out.flush();
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

	class LogInListener implements ActionListener, Runnable {

		@Override
		public void actionPerformed(ActionEvent e) {
			new Thread(this).start();
		}

		@Override
		public void run() {
			String login = logInFrame.login.getText();
			String pass = new String(logInFrame.passwordField.getPassword());
			login(login, pass);
		}
	}

	class SendButtonListener implements ActionListener, Runnable {
		String message = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			message = chat.msgField.getText();

			sendThread.run();
		}

		@Override
		public void run() {
			sendMessage(message);
			Thread.yield();
		}
	}

	class ChatMessageListener implements Runnable {
		public void run() {
			while (true) {
				try {
					String message = in.readUTF();
					chat.showMessage(message);
				} catch (IOException e) {
					showErrorMessage("Connection failed");
				}
			}
		}
	}
}
