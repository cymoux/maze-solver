package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public final class HelpMenuItem extends JMenuItem implements ActionListener{ 
	public HelpMenuItem() {
		super("Help");
		addActionListener(this);
	}

	public final void actionPerformed(ActionEvent evt) {
		// Clicking on "Help" displays the message written below
		JOptionPane.showMessageDialog(null, "The maze is composed of 4 types of boxes : the departure box, the arrival box, the wall boxes and the empty boxes.\n Clicking on the 'Solve' button displays the shortest path (using only the empty boxes) from the departure box to the arrival box, if such a path exists. \n It is possible to reconfigure the maze by clicking directly on the boxes and choosing their new type. You then need to push 'Solve' again. Enjoy!", "The Maze App", JOptionPane.INFORMATION_MESSAGE);
	}
}