package dijkstra;

import java.util.Hashtable;

public final class Pi implements PiInterface{
	
	private final Hashtable<VertexInterface, Integer> piTable;

	public Pi()
	{
		piTable = new Hashtable<VertexInterface, Integer>();
	}
	
	public Integer getValue(VertexInterface vertex) 
	{
		return piTable.get(vertex).intValue();
	}	

	public void setValue(VertexInterface vertex, Integer value){ 
		piTable.put(vertex, new Integer(value));
	}
}