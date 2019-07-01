package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import dijkstra.Dijkstra;
import dijkstra.Previous;
import dijkstra.VertexInterface;
import maze.ABox;
import maze.DBox;
import maze.MBox;
import maze.Maze;
import maze.MazeReadingException;

public final class MazePanel extends JPanel {
	private final MazeApp mazeApp;
	private Maze maze;
	private final ArrayList<ArrayList<BoxButton>> boxesButtons = new ArrayList<ArrayList<BoxButton>>();
	
	public MazePanel(MazeApp mazeApp) throws MazeReadingException {
		//creates a grid of boxes and colors them according to the text file
		super();
		this.mazeApp = mazeApp;
		String fileName = mazeApp.getFileName();
		this.setBackground(Color.WHITE);
		setPreferredSize(new Dimension(512, 512));
		this.maze = new Maze(fileName);
		int length = maze.getLength();
		int width = maze.getWidth();
	    setLayout(new GridLayout(length, width));		
		for(int line = 0; line < length ; line++){
			boxesButtons.add(new ArrayList<BoxButton>());
			for(int column = 0; column < width; column++){
				BoxButton bb = new BoxButton(mazeApp, maze.getMBox(line, column), line, column);
				boxesButtons.get(line).add(bb);
				switch (maze.getMBox(line, column).getLabel()) //gives the color according to the label letter
				{
				case 'A':
					bb.setBackground(Color.RED);
					break;
				case 'D':
					bb.setBackground(Color.GREEN);
					break;
				case 'W':
					bb.setBackground(Color.BLACK);
					break;
				case 'E':
					bb.setBackground(Color.WHITE);
					break;
				}
				add(bb);
			}
		}
	}

	
	public Maze getMaze() {
		return maze;
	}
	
	public MazeApp getMazeApp() {
		return mazeApp;
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}
	
	
	public void showPath() throws MazeReadingException {
		//Calculates the shortest path using Dijkstra's algorithm
		maze = new Maze(maze.getMazeText());
		if (mazeApp.getModel().isModified()) {
			// erases the former path so that it doesn't interfere with the new one
			int length = maze.getLength();
			int width = maze.getWidth();			
			for(int line = 0; line < length; line++) {
				for(int column = 0; column < width; column++) {
					if (maze.getMBox(line, column).getLabel() == 'E')
						boxesButtons.get(line).get(column).setBackground(Color.WHITE);
				}
			}
		}
		DBox departureBox = maze.getDepartureBox();
		Previous p = (Previous) Dijkstra.dijkstra(maze, departureBox);
		ABox arrivalBox = maze.getArrivalBox();
		ArrayList<VertexInterface> path = p.getShortestPathTo(arrivalBox);		
		for(VertexInterface vertex : path) {
			MBox currentBox = (MBox) vertex;
			int line = currentBox.getLine();
			int column = currentBox.getColumn();
			if (vertex != departureBox && vertex != arrivalBox)
				boxesButtons.get(line).get(column).setBackground(Color.YELLOW);
		}
	}
	
	public void notifyForUpdate(Object o) {
		ArrayList<Integer> coordinates = (ArrayList<Integer>) o;
		boxesButtons.get(coordinates.get(0)).get(coordinates.get(1)).notifyForUpdate();
	}
}