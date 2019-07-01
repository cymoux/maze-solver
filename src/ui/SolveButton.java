package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import maze.MazeReadingException;

public final class SolveButton extends JButton implements ActionListener{
	private final MazeApp mazeApp;
	
	public SolveButton(MazeApp mazeApp) {
		super("Solve");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final MazePanel mazePanel = mazeApp.getWindowPanel().getMazePanel();
		try {
			mazePanel.showPath(); //when clicked, the button asks for the path to be displayed
		} catch (MazeReadingException e1) {
			e1.printStackTrace();
		}
	}

	public void notifyForUpdate() {
		repaint();
	}

}