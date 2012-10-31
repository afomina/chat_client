package app;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class Chat extends JFrame {

	JTextField msgField;
	JEditorPane chatPane;
	JButton sendButton;
	static final Color borderColor = new Color(51, 153, 204);
	private JPanel contentPane;

	public Chat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 424, 0 };
		gbl_contentPane.rowHeights = new int[] { 139, 50, 28, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		chatPane = new JEditorPane();
		chatPane.setFocusCycleRoot(false);
		chatPane.setEditable(false);
		chatPane.setBorder(new LineBorder(borderColor));
		GridBagConstraints gbc_chatPane = new GridBagConstraints();
		gbc_chatPane.fill = GridBagConstraints.BOTH;
		gbc_chatPane.insets = new Insets(0, 0, 5, 0);
		gbc_chatPane.gridx = 0;
		gbc_chatPane.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(chatPane);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, gbc_chatPane);

		msgField = new JTextField();
		msgField.setFocusCycleRoot(true);
		GridBagConstraints gbc_msgField = new GridBagConstraints();
		gbc_msgField.fill = GridBagConstraints.BOTH;
		gbc_msgField.insets = new Insets(0, 0, 5, 0);
		gbc_msgField.gridx = 0;
		gbc_msgField.gridy = 1;
		contentPane.add(msgField, gbc_msgField);
		msgField.setColumns(10);
		msgField.setBorder(new LineBorder(borderColor));

		sendButton = new JButton(
				"\u041E\u0442\u043F\u0440\u0430\u0432\u0438\u0442\u044C");

		GridBagConstraints gbc_sendButton = new GridBagConstraints();
		gbc_sendButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_sendButton.gridx = 0;
		gbc_sendButton.gridy = 2;
		contentPane.add(sendButton, gbc_sendButton);

		setVisible(true);

	}
}
