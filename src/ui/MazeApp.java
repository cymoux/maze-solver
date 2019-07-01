package ui;

import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import maze.MazeReadingException;

public final class MazeApp extends JFrame implements Observer {
	private final WindowPanel windowPanel;
	private MazeAppModel mazeAppModel;
	private String fileName;

	public MazeApp() throws HeadlessException, MazeReadingException {
		//Asks for the access path to the text file used to create the maze
		this(JOptionPane.showInputDialog("Please input the access path to the text file."));
	}

	public MazeApp(String fileName) throws MazeReadingException {
		super("Maze App");
		this.mazeAppModel = new MazeAppModel();
		this.fileName = fileName;
		setJMenuBar(new MazeMenuBar(this));

		windowPanel = new WindowPanel(this);
		setContentPane(windowPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
		mazeAppModel.addObserver(this);
	}

	public void update(Observable obs, Object o) {
		windowPanel.notifyForUpdate(o);
	}

	public WindowPanel getWindowPanel() {
		return windowPanel;
	}

	public MazeAppModel getModel() {
		return mazeAppModel;
	}

	public String getFileName() {
		return fileName;
	}

}