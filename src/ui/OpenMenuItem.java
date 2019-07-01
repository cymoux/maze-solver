package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import maze.MazeReadingException;

public final class OpenMenuItem extends JMenuItem implements ActionListener {
	
	public OpenMenuItem() {
		super("Open");
		addActionListener(this);
	}
	
	public final void actionPerformed(ActionEvent evt) {
		//Asks for the access path to the text file used to create the maze
		 final String fileName = JOptionPane.showInputDialog("Please input the access path to the text file.");
		 try {
			 new MazeApp(fileName);
		} catch (MazeReadingException e) {
			e.printStackTrace();
		} 
	}
}