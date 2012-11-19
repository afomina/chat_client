package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	LogInFrame logInFrame = new LogInFrame();
	Chat chat;
	volatile String login;
	volatile String password;
	volatile DataInputStream in;
	volatile DataOutputStream out;
	Thread sendThread;

	public Client() {
		LogInListener loginListener = new LogInListener();

		logInFrame.btnLogIn.addActionListener(loginListener);
		logInFrame.passwordField.addActionListener(loginListener);
	}

	public void showGUI() {
		logInFrame.setVisible(true);
	}

	public void connect() throws IOException {
		InetAddress inetAddress = InetAddress.getByName(ADDRESS);

		Socket connectSocket = new Socket(inetAddress, CONNECT_PORT);

		DataOutputStream connectOut = new DataOutputStream(
				connectSocket.getOutputStream());

		connectOut.writeInt(AUTH);

		Socket messageSocket = new Socket(inetAddress, PORT);

		in = new DataInputStream(messageSocket.getInputStream());
		out = new DataOutputStream(messageSocket.getOutputStream());
	}

	public void login(String login, String password) throws LoginException,
			IOException {
		connect();

		this.login = login;
		this.password = password;

		if (login.equals("")) {
			showErrorMessage("Please enter your login");
		} else if (password.equals("")) {
			showErrorMessage("Please enter your password");
		} else {
			sendAuthData();
			logInFrame.setVisible(false);
			chat();
		}
	}

	synchronized void sendAuthData() throws LoginException, IOException {
		out.writeUTF(login);
		out.flush();
		out.writeUTF(password);
		out.flush();
		String serverAnswer = in.readUTF();
		if (!serverAnswer.equals(login)) {
			throw new LoginException();
		}
	}

	synchronized void sendMessage(final String message) {
		if (!message.equals("")) {
			try {
				out.writeUTF(login + ": " + message);
				out.flush();
			} catch (SocketException e) {
				showErrorMessage("Connection failed");
			} catch (IOException e) {
				showErrorMessage("Cannot connect to server");
			}
			chat.msgField.setText("");
		}
	}

	void chat() {
		chat = new Chat();

		Thread chatThread = new Thread(new ChatMessageListener());

		chatThread.setDaemon(true);
		chatThread.start();

		SendButtonListener msgListener = this.new SendButtonListener();

		sendThread = new Thread(msgListener);

		chat.msgField.addKeyListener(msgListener);
		chat.sendButton.addActionListener(msgListener);
	}

	void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	class LogInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String login = logInFrame.login.getText();
			String pass = new String(logInFrame.passwordField.getPassword());

			try {
				login(login, pass);
			} catch (LoginException e) {
				showErrorMessage("Login or password uncorrect");
			} catch (IOException e) {
				showErrorMessage("Cannot connect to server");
			}
		}
	}

	class SendButtonListener implements KeyListener, ActionListener, Runnable {
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

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				actionPerformed(null);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
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
					return;
				}
			}
		}
	}
}
