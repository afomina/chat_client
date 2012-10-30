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
import java.net.UnknownHostException;

import javax.swing.JTextField;

public class Client {

	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sendData();
			logInFrame.setVisible(false);
			chat();
		}

	}

	Socket clientSocket;

	LogInFrame logInFrame = new LogInFrame();
	String login;
	String password;
	InputStream sin;
	OutputStream sout;

	DataInputStream in = new DataInputStream(sin);
	DataOutputStream out = new DataOutputStream(sout);

	public Client() throws UnknownHostException, IOException {
		logInFrame.setVisible(true);
		clientSocket = new Socket(InetAddress.getByName("127.0.0.1"), 6680);
		sin = clientSocket.getInputStream();
		sout = clientSocket.getOutputStream();

		in = new DataInputStream(sin);
		out = new DataOutputStream(sout);

	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void sendData() {
		login = logInFrame.login.getText();
		password = new String(logInFrame.passwordField.getPassword());
		try {
			out.writeUTF("login: " + login + "\npassword: " + password);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message){
		try {
			out.writeUTF(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void chat() {
		Chat chat=new Chat();
		chat.msgField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = ((JTextField) e.getSource()).getText();
				sendMessage(message);
			}
		});
	}

	public static void main(String[] args) {
		try {
			Client client = new Client();
			client.logInFrame.btnLogIn.addActionListener(client.new Listener());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
