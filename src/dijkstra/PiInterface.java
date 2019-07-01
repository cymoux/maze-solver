package dijkstra;

public interface PiInterface {
	public Integer getValue(VertexInterface vertex); // returns the current minimal weight from the initial vertex r to the vertex x.
	public void setValue(VertexInterface vertex, Integer weight); // sets a new weight value for vertex.
}