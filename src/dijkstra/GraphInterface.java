package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex); /* returns the ArrayList of all the successors of vertex */
	public int getWeight(VertexInterface x, VertexInterface y); /* returns the weight vertex x and vertex y */
	public int getSize(); /*returns the size of the graph, i.e. the total number of vertices */
	public ArrayList<VertexInterface> getAllVertices(); /*returns the ArrayList of all the vertices*/
}