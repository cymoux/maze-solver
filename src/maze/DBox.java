package maze;

public final class DBox extends MBox { /* Class of the departure box */
	public DBox(int line, int column)
	{
		super(line, column);
	}
	
	public char getLabel(){
		return 'D';
	}

}