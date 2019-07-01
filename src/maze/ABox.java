package maze;

public final class ABox extends MBox { /* Class of the arrival box */
	public ABox(int line, int column)
	{
		super(line, column);
	}

	public char getLabel(){
		return 'A';
	}
}