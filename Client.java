package fruehstueck;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Client {
	private ClientGUI gui;
	private ClientControl cc;
	private PrintWriter writer;
	BufferedReader reader;
	Socket client;
	
	
     public static void main(String[] args) {
             Client client = new Client();
     }
     
     public Client () {
    	cc = new ClientControl();
    	gui = new ClientGUI(cc);
    	
    	connectToServer();
     }
    
     public boolean connectToServer() {
             try {
                     client = new Socket(Values.server, 5050);
                     reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     writer = new PrintWriter(client.getOutputStream());
                     appendTextMessages("Netzwerkverbindung hergestellt");
                     
                     String nachricht= JOptionPane.showInputDialog("What is your Username? (leave empty for anonymus chating)");
                     if(nachricht.equals("")) {
                    	 writer.println("none");
                    	 writer.flush();
                    	 gui.userliste.setText(reader.readLine());
                     }else {
                    	 writer.println(nachricht+":");
                    	 gui.userliste.setText(nachricht);
                     }
                     
                     return true;
             } catch(Exception e) {
                     appendTextMessages("Netzwerkverbindung konnte nicht hergestellt werden");
                     e.printStackTrace();
                    
                     return false;
             }
     }
    
     public void sendMessageToServer() {
    	 	 if(gui.eingabe.getText().startsWith("/")) {
    	 		if (gui.eingabe.getText().equals("/ls")||gui.eingabe.getText().equals("/list")) {
                    writer.println("ls");
                }else if (gui.eingabe.getText().equals("/exit")||gui.eingabe.getText().equals("/close")) {
                    writer.println("exit");
                }
    	 	 }else {
    	 		 writer.println(gui.userliste.getText() + ": " + gui.userliste.getText());
    	 	 }
             writer.flush();
            
             gui.eingabe.setText("");
             gui.eingabe.requestFocus();
     }
    
     public void appendTextMessages(String message) {
            gui.nachrichten.append(message + "\n");
     }
}


