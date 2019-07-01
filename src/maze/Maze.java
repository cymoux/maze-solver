package maze;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

public final class Maze implements GraphInterface{
	private ArrayList<ArrayList<MBox>> boxes = new ArrayList<ArrayList<MBox>>();
	private final int length;
	private final int width;
	private ABox arrivalBox;
	private DBox departureBox;
	private ArrayList<String> mazeText = new ArrayList<String>();

	public Maze(String fileName) throws MazeReadingException { /* First initialization of the Maze, based on the reading of a file called fileName */
		ArrayList<String> mazeText = initFromTextFile(fileName); /* Builds a list of all the lines of the file called fileName */
		this.length = mazeText.size()-1;  /* Calculates the length of the maze */
		this.width = mazeText.get(0).length(); /* Calculates the width of the maze, which is supposed to be a rectangle */
		int departureOccurency = 0; /* Counts the number of departure boxes */
		int arrivalOccurency = 0; /* Counts the number of arrival boxes */

		for(int lineNumber = 0 ; lineNumber < length; lineNumber++) { 
			if(width != mazeText.get(lineNumber).length())
				throw new MazeReadingException(fileName, lineNumber, "The maze is meant to be a rectangle.");	
			boxes.add(new ArrayList<MBox>());

			for(int columnNumber = 0 ; columnNumber < width ; columnNumber++) {
				char currentChar = mazeText.get(lineNumber).charAt(columnNumber);
				switch (currentChar) { /* currentChar is the character read at the line lineNumber and the column columnNumber */
				case 'A' :
					arrivalOccurency++;
					if(arrivalOccurency > 1) /* there is more than one arrival box */
						throw new MazeReadingException(fileName, lineNumber, "There is more than one arrival box.");
					this.arrivalBox = new ABox(lineNumber, columnNumber);
					boxes.get(lineNumber).add(arrivalBox);
					break;
				case 'D' :
					departureOccurency++;
					if(departureOccurency > 1) /* there is more than one departure box */
						throw new MazeReadingException(fileName, lineNumber, "There is more than one departure box.");
					this.departureBox = new DBox(lineNumber, columnNumber);
					boxes.get(lineNumber).add(departureBox);
					break;
				case 'E' :
					boxes.get(lineNumber).add(new EBox(lineNumber, columnNumber));
					break;	
				case 'W' :
					boxes.get(lineNumber).add(new WBox(lineNumber, columnNumber));
					break;
				default:
					throw new MazeReadingException(fileName, lineNumber, "There is a box with an unknown type.");
				}
			}
		}
		if(arrivalOccurency != 1)
			throw new MazeReadingException(fileName, length, "There is no arrival box.");
		if(departureOccurency != 1)
			throw new MazeReadingException(fileName, length, "There is no departure box.");
	}

	public Maze(ArrayList<String> mazeText){ /* Last initialization of the maze, based on the edition of the user interface; no exception can be done */
		this.mazeText = mazeText;
		boxes = new ArrayList<ArrayList<MBox>>();
		this.length = mazeText.size()-1;
		this.width = mazeText.get(0).length();

		for(int lineNumber = 0 ; lineNumber < length ; lineNumber++) {
			boxes.add(new ArrayList<MBox>());				
			for(int columnNumber = 0 ; columnNumber < width ; columnNumber++) {
				char currentChar = mazeText.get(lineNumber).charAt(columnNumber);
				switch (currentChar) {
				case 'A' :
					this.arrivalBox = new ABox(lineNumber, columnNumber);
					boxes.get(lineNumber).add(arrivalBox);

					break;
				case 'D' :
					this.departureBox = new DBox(lineNumber, columnNumber);
					boxes.get(lineNumber).add(departureBox);
					break;
				case 'E' :
					boxes.get(lineNumber).add(new EBox(lineNumber, columnNumber));
					break;
				case 'W' :
					boxes.get(lineNumber).add(new WBox(lineNumber, columnNumber));
					break;
				}
			}
		}
	}

	public ArrayList<String> getMazeText(){
		return mazeText;
	}

	public void setMazeText(int line, int column, char c) { /* Modifies a character in mazeText, in a specified line and column. */
		String newLine = mazeText.get(line).substring(0, column) + c + mazeText.get(line).substring(column+1);
		mazeText.set(line, newLine);

	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public DBox getDepartureBox() {
		return departureBox;
	}

	public void setDepartureBox(DBox departureBox) {
		this.departureBox = departureBox;
	}	

	public ABox getArrivalBox() {
		return arrivalBox;
	}

	public void setArrivalBox(ABox arrivalBox){
		this.arrivalBox = arrivalBox;
	}

	public MBox getMBox(int line, int column){ /* returns the MBox of a line and a column chosen. */
		return boxes.get(line).get(column);
	}

	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex){ 
		/* method of GraphInterface, necessary to get Dijkstra's algorithm working */
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
		MBox box = (MBox) vertex;
		int line = box.getLine();
		int column = box.getColumn();
		if (line > 0) {
			MBox successor = boxes.get(line-1).get(column);
			if(successor.getLabel() != 'W')
				successors.add(successor);
		}
		if (line < length - 1) {
			MBox successor = boxes.get(line+1).get(column);
			if(successor.getLabel() != 'W')
				successors.add(successor);
		}
		if (column > 0) {
			MBox successor = boxes.get(line).get(column-1);
			if(successor.getLabel() != 'W')
				successors.add(successor);
		}
		if (column < width - 1) {
			MBox successor = boxes.get(line).get(column+1);
			if(successor.getLabel() != 'W')
				successors.add(successor);
		}
		return successors;
	}

	public int getSize() { /* method of GraphInterface */
		return length*width; /* the maze is a rectangle*/
	}

	public int getWeight(VertexInterface src, VertexInterface dst){ /* method of GraphInterface */
		return 1;
	}

	public ArrayList<VertexInterface> getAllVertices(){ /* method of GraphInterface */
		ArrayList<VertexInterface> Vertices = new ArrayList<VertexInterface>();

		for(int i = 0; i < boxes.size(); i++){
			for(int j = 0; j < boxes.get(i).size(); j++){
				Vertices.add(boxes.get(i).get(j));
			}
		}
		return Vertices;
	}

	private final ArrayList<String> initFromTextFile(String fileName) throws MazeReadingException{ 
		/* initializes the maze from a text file */
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String currentLine;
			do {
				currentLine = br.readLine();
				mazeText.add(currentLine);
			} while (currentLine != null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return mazeText;
	}

	public final void saveToTextFile(String fileName) { /* saves the current maze in a text file called fileName */
		try {
			PrintWriter pw = new PrintWriter(fileName);

			for(int lineNumber = 0 ; lineNumber < length; lineNumber++) {
				for (int columnNumber = 0 ; columnNumber < width; columnNumber++) {
					pw.print(boxes.get(lineNumber).get(columnNumber).getLabel());
				}
				pw.print("\n");

			}
			pw.close();  

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}