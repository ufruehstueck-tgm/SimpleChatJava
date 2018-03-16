package fruehstueck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ClientControl implements ActionListener {
	private ClientGUI gui;
	private Client c;

	public ClientControl() {
		gui = new ClientGUI(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		c.sendMessageToServer();
	}

	public class MessagesFromServerListener implements Runnable {

		@Override
		public void run() {
			String message;

			try {
				while ((message = c.reader.readLine()) != null) {
					if (message.equals("exit")) {
						message = "The Server closed the connection. Bye.";
						c.appendTextMessages(message);
						gui.nachrichten.setCaretPosition(gui.nachrichten.getText().length());
						break;
					} else {
						c.appendTextMessages(message);
						gui.nachrichten.setCaretPosition(gui.nachrichten.getText().length());
					}
				}
			} catch (IOException e) {
				c.appendTextMessages("Nachricht konnte nicht empfangen werden!");
				e.printStackTrace();
			}
		}

	}

	public class SendPressEnterListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				c.sendMessageToServer();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

	}

}
