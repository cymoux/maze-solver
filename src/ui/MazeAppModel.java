package ui;

import java.util.ArrayList;
import java.util.Observable;

public final class MazeAppModel extends Observable {
	
	private boolean modified; 
	
	public MazeAppModel() {
		super();
		modified = false;
	}
	
	public boolean isModified() {
		// indicates whether the maze has been edited by the user
		return modified;
		}
	
	public final void changeModified(int line, int column) {
		// indicates the box that has been changed to mazeApp 
		modified=true;
		ArrayList<Integer> coordinates = new ArrayList<Integer>(); 
		coordinates.add(line);
		coordinates.add(column);
		setChanged();
		notifyObservers(coordinates);
	}	
}