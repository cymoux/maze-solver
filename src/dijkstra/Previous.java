package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

public final class Previous implements PreviousInterface{
	
	private final Hashtable<VertexInterface, VertexInterface> previousTable; 

	public Previous(){
		previousTable = new Hashtable<VertexInterface, VertexInterface>(); 
	}

	public VertexInterface getValue(VertexInterface vertex){
		return previousTable.get(vertex);
	}

	public void setValue(VertexInterface vertex, VertexInterface previous){
		previousTable.put(vertex, previous);
	}

	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex){
		ArrayList<VertexInterface> shortestPath = new ArrayList<VertexInterface>();
		while (vertex != null){
			shortestPath.add(vertex);
			vertex = getValue(vertex);
		}
		return shortestPath;
	}
}