package app;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class Chat extends JFrame {

	JTextField msgField;
	JEditorPane chatPane;
	JButton sendButton;
	static final Color borderColor = new Color(51, 153, 204);
	static final int X1 = 100;
	static final int X2 = 450;
	static final int Y1 = 100;
	static final int Y2 = 260;
	private JLayeredPane contentPane;

	public Chat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 260);
		setMinimumSize(getSize());
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		chatPane = new JEditorPane();
		chatPane.setFocusCycleRoot(false);
		chatPane.setEditable(false);
		chatPane.setBorder(new LineBorder(borderColor));
		JScrollPane scrollPane = new JScrollPane(chatPane);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		msgField = new JTextField();
		msgField.setFocusCycleRoot(true);
		msgField.setColumns(10);
		msgField.setBorder(new LineBorder(borderColor));

		sendButton = new JButton(
				"\u041E\u0442\u043F\u0440\u0430\u0432\u0438\u0442\u044C");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(msgField, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(335)
					.addComponent(sendButton))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(msgField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(sendButton))
		);
		contentPane.setLayout(gl_contentPane);

		setVisible(true);
	}
}
