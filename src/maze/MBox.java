package maze;

import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface { /* Super-class of all the boxes */
	private final int line;
	private final int column;
	
	public MBox(int line, int column){
		this.line=line;
		this.column=column;
	}
	
	public final int getLine() {
		return line;
	}
	
	public final int getColumn() {
		return column;
	}
	
	public abstract char getLabel();
}