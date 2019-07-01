package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface {
	public VertexInterface getValue(VertexInterface vertex);
	public void setValue(VertexInterface vertex, VertexInterface value); /* Assigns value as the previous vertex of vertex*/  
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);
}
