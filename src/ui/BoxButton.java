package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import maze.MBox;

public final class BoxButton extends JButton implements ActionListener { 
	private final MazeApp mazeApp;
	private final int line;
	private final int column;
	
	public BoxButton(MazeApp mazeApp, MBox box, int line, int column) {
		this.mazeApp = mazeApp; /* reference to the App */
		this.line = line;		/* line of the MBox */
		this.column = column;	/* column of the MBox */
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) { //allows the user to edit the maze by clicking on the boxes
		final String Label[]={"Arrival", "Departure", "Empty", "Wall"};
		final int boxInt = JOptionPane.showOptionDialog(null, "Set this box as", "Label Choice", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,Label,"Cancel"); 
		if(boxInt == 0)
		{
			if (mazeApp.getWindowPanel().getMazePanel().getMaze().getArrivalBox() == null) {
				this.setBackground(Color.RED);
				mazeApp.getModel().changeModified(line, column);
				mazeApp.getWindowPanel().getMazePanel().getMaze().setMazeText(line, column, 'A');
			}
			else 
				JOptionPane.showMessageDialog(null, "There already is an arrival box!", "The Maze App", JOptionPane.INFORMATION_MESSAGE);

		}
						
		else {
			if (boxInt == 1)
			{
			if (mazeApp.getWindowPanel().getMazePanel().getMaze().getDepartureBox() == null) {
				this.setBackground(Color.GREEN);
				mazeApp.getModel().changeModified(line, column);
				mazeApp.getWindowPanel().getMazePanel().getMaze().setMazeText(line, column, 'D');
				}
			else 
				JOptionPane.showMessageDialog(null, "There already is a departure box!", "The Maze App", JOptionPane.INFORMATION_MESSAGE);
			
			}
			
			else {
				if (boxInt == 2)
				{
				this.setBackground(Color.WHITE);
				mazeApp.getModel().changeModified(line, column);
				mazeApp.getWindowPanel().getMazePanel().getMaze().setMazeText(line, column, 'E');
				}
				else {
					if (boxInt == 3)
					{
						this.setBackground(Color.BLACK);	
						mazeApp.getModel().changeModified(line, column);
						mazeApp.getWindowPanel().getMazePanel().getMaze().setMazeText(line, column, 'W');
					}
				}
			}
		}
	}
	
	public void notifyForUpdate(){
		repaint();
	}
}