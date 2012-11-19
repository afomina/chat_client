package app;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;

@SuppressWarnings("serial")
public class Chat extends JFrame {

	static final Color borderColor = new Color(51, 153, 204);
	static final int X1 = 100;
	static final int X2 = 455;
	static final int Y1 = 100;
	static final int Y2 = 260;
	JEditorPane chatPane;
	JButton sendButton;
	JEditorPane msgField;
	private JLayeredPane contentPane;
	private JScrollPane scrollPane_2;

	public Chat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(X1, Y1, X2, Y2);
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

		sendButton = new JButton(
				"\u041E\u0442\u043F\u0440\u0430\u0432\u0438\u0442\u044C");

		scrollPane_2 = new JScrollPane((Component) null);
		scrollPane_2
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(335, Short.MAX_VALUE)
							.addComponent(sendButton)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sendButton))
		);

		msgField = new JEditorPane();
		msgField.setFocusCycleRoot(false);
		msgField.setBorder(new LineBorder(borderColor));
		scrollPane_2.setViewportView(msgField);
		contentPane.setLayout(gl_contentPane);

		setVisible(true);
	}

	void showMessage(String message) {
		String text = chatPane.getText();
		chatPane.setText(text + message + "\n");
	}
}
