package fruehstueck;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;

public class ClientGUI extends JFrame {

	JPanel contentPane;
	JTextField eingabe;
	JTextField userliste;
	JTextArea nachrichten;

	/**
	 * Create the frame.
	 */
	public ClientGUI(ClientControl control) {
		super("Simple Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton sendButton = new JButton("Send");
		sendButton.setBounds(352, 258, 80, 20);
		contentPane.setLayout(null);
		contentPane.add(sendButton);

		eingabe = new JTextField();
		eingabe.setBounds(12, 257, 328, 21);
		contentPane.add(eingabe);
		eingabe.setColumns(10);

		nachrichten = new JTextArea();
		nachrichten.setBounds(12, 43, 328, 201);
		contentPane.add(nachrichten);

		userliste = new JTextField();
		userliste.setBounds(352, 43, 80, 202);
		contentPane.add(userliste);
		userliste.setColumns(10);

		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(12, 13, 93, 17);
		contentPane.add(btnDisconnect);

	}
}
