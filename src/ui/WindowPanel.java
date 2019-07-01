package ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import maze.MazeReadingException;

public final class WindowPanel extends JPanel {
	// class handling the various panels
	private final MazePanel mazePanel;
	private final ButtonsPanel buttonsPanel;
	
	public WindowPanel(MazeApp mazeApp) throws MazeReadingException {
		super();		
		setLayout(new BorderLayout());
		add(mazePanel = new MazePanel(mazeApp), BorderLayout.CENTER);
		add(buttonsPanel = new ButtonsPanel(mazeApp), BorderLayout.SOUTH);
	}

	public MazePanel getMazePanel() {
		return mazePanel;
	}

	public void notifyForUpdate(Object o) {
		mazePanel.notifyForUpdate(o);
		buttonsPanel.notifyForUpdate();
	}
}