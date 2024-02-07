import java.io.*;

//CS211(C), 6/15/2020
//Marcus Jundt
//Final Project: Test client for the game of Assassin

public class AssassinTest {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("names.txt");
		
		new AssassinGame(file);
		
		// Test the methods for printing the "ring" and "graveyard" by typing SCORE for the input

	}

}
