package ui;

import javax.swing.JMenuBar;

public final class MazeMenuBar extends JMenuBar {
	/* Menu bar at the top, with four buttons (Open, Save, Quit, Help) */
	public MazeMenuBar(MazeApp mazeApp) {
		super();
		add(new OpenMenuItem());
		add(new SaveMenuItem(mazeApp));
		add(new QuitMenuItem(mazeApp));
		add(new HelpMenuItem());
	}
}
