package dijkstra;
import java.util.ArrayList;

public final class Dijkstra {
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) {
		return dijkstra(g, r, new ASet(), new Pi(), new Previous());
	}

	private static PreviousInterface dijkstra(GraphInterface g, VertexInterface r, ASetInterface a, PiInterface pi, PreviousInterface previous) {
		final ArrayList<VertexInterface> allVertices = g.getAllVertices();	
		for (VertexInterface x : allVertices) {
			pi.setValue(x, Integer.MAX_VALUE); /* Integer.MAX_VALUE is supposed to be infinity */
		}
		a.add(r);
		VertexInterface pivot = r;
		pi.setValue(r, 0);
		final int numberOfVertices = g.getSize();
		
		for(int vertexIndex = 1 ; vertexIndex < numberOfVertices ; vertexIndex++) {
			final ArrayList<VertexInterface> successors = g.getSuccessors(pivot);			
			for (VertexInterface y : successors) { 
				if(!a.contains(y))
				{
					if(pi.getValue(pivot) + g.getWeight(y, pivot) < pi.getValue(y)) {
						pi.setValue(y, pi.getValue(pivot) + g.getWeight(y, pivot));
						previous.setValue(y, pivot);
					}
				}
			}				
			VertexInterface minimumVertex = null; /* minimumVertex is the vertex not in A such as pi.getPi(y) is minimal and equals to minimumPi */
			Integer minimumPi = Integer.MAX_VALUE;							
			for(VertexInterface z : allVertices) {
				if(!a.contains(z)) {
					if (pi.getValue(z) < minimumPi){
						minimumVertex = z;
						minimumPi = pi.getValue(minimumVertex);
					}
				}
			}	
			if (minimumVertex == null) {
				return previous;
			}
			pivot = minimumVertex;
			a.add(pivot); // Add the new pivot in a and remove it from notA
		}
	return previous;
	}
}
