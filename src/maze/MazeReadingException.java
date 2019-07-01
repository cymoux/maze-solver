package maze;

public final class MazeReadingException extends Exception{ //exception that can be thrown by reading the text file 
	public MazeReadingException(String fileName, int lineNumber, String reason){
		System.out.println("Le fichier " + fileName + " fourni n'est pas correct. Il y a un problème à la ligne " + lineNumber + ". " + reason + " ");
	}
}