package maze;

public final class WBox extends MBox { /* Class of the wall boxes */
	
	public WBox(int line, int column) {
		super(line, column);
	}
	public char getLabel(){
		return 'W';
	}
}