package dijkstra;


public interface ASetInterface {
	public void add(VertexInterface r); // Assigns a vertex r in the set A
	public boolean contains(VertexInterface r); // Checks if a vertex r is in the set A
}