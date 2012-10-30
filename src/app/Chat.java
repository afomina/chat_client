package app;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Chat extends JFrame {
	
	JTextField msgField;
	private JPanel contentPane;

	public Chat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		msgField = new JTextField();
		contentPane.add(msgField, BorderLayout.SOUTH);
		msgField.setColumns(10);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		contentPane.add(editorPane, BorderLayout.CENTER);
		setVisible(true);
		
	}

}
