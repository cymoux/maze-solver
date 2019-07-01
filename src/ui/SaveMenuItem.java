package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public final class SaveMenuItem extends JMenuItem implements ActionListener {
	private final MazeApp mazeApp;
	
	public SaveMenuItem(MazeApp mazeApp) {
		super("Save");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}	

	@Override
	public void actionPerformed(ActionEvent evt) {
		final MazeAppModel mazeAppModel = mazeApp.getModel();
		if (mazeAppModel.isModified()) //saves the current maze in the text file
		{
			mazeApp.getWindowPanel().getMazePanel().getMaze().saveToTextFile(mazeApp.getFileName());
		}		
	}

}