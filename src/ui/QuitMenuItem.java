package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public final class QuitMenuItem extends JMenuItem implements ActionListener {
	private final MazeApp mazeApp;
	public QuitMenuItem(MazeApp mazeApp) {
		super("Quit");
		this.mazeApp = mazeApp;
		addActionListener(this);
		}
	
	public void actionPerformed(ActionEvent evt){
		final MazeAppModel mazeAppModel = mazeApp.getModel();
		if (mazeAppModel.isModified()) {
			// asks whether to save the current maze
			final int response = JOptionPane.showConfirmDialog(null, "Current maze not saved. Would you like to save it?", "Quit App", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			switch (response) {
				case JOptionPane.CANCEL_OPTION:
					return ;
				case JOptionPane.OK_OPTION:
					mazeApp.getWindowPanel().getMazePanel().getMaze().saveToTextFile(mazeApp.getFileName());
					break;
				case JOptionPane.NO_OPTION:
					break;
			}	
		}
		System.exit(0);
	}
}