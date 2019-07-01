package maze;

public final class EBox extends MBox { /* Class of the empty boxes */
	public EBox(int line, int column)
	{
		super(line, column);
	}

	public char getLabel(){
		return 'E';
	}

}