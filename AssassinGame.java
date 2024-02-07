import java.io.*;
import java.util.*;

// CS211(C), 6/15/2020
// Marcus Jundt
// Final Project: Program that models a game of Assassin

public class AssassinGame {
	
	private String name;
	private LinkedList<String> ring;
	private LinkedList<String> grave;
	
	@SuppressWarnings("resource")
	public AssassinGame(File file) throws FileNotFoundException { 
		
		// Creates LinkedLists
		Scanner sc = new Scanner(file); 
		ring = new LinkedList<String>();
		grave = new LinkedList<String>();
	    while (sc.hasNextLine()) {
	    	ring.add(sc.nextLine()); 
	    } 
	    shuffle();

	    // The following runs the game:
	    
	    // Intro to the game
	    System.out.println("-----------------------------------------------");
	    System.out.println("================={ ASSASSIN }==================");
	    System.out.println("-----------------------------------------------");
	    System.out.println("               by Marcus Jundt\n");
	    printRing();
	    System.out.println("\n-----------------------------------------------");
	    
	    // Game start
	    System.out.println("Enter 'SCORE' (all caps) for updated standings.");
	    Scanner inp = new Scanner(System.in);
	    while (ring.size() > 1) {	
	    	System.out.println("Who to kill?");
	    	name = inp.next();
	    	if (name.equals("SCORE")) {
	    		System.out.println("\n-----------------------------------------------------------");
	    		printGrave();
	    		printRing();
	    		System.out.println("------------------------------------------------------------\n");
	    	} else {
	    		kill();
	    	}	    		    	
	    }    
	    System.out.println("-----------------------------------------------");
	    System.out.println("             "+ring.remove()+" is the winner.");	 
	    System.out.println("-----------------------------------------------\n");
	    
	    // Game end screen, play again option
	    System.out.println("================={ GAME OVER }=================");
	    System.out.println("           Play again? Yes or no.");
	    String response = inp.next();
	    while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no"))) {    	
	    	System.out.println("Invalid, yes or no?");
	    	response = inp.next();	
	    }	  
    	if (response.equalsIgnoreCase("yes")) {
	    	System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");  // clears terminal when restarting game             
	    	new AssassinGame(file);
	    } else if (response.equalsIgnoreCase("no")) {
	    	inp.close();		    
	    }
	}
	
	// https://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time
	// Shuffle the ring
	private void shuffle() {	
		for (int j = 0; j < 2; j++) {
			for (int i=0; i < ring.size(); i++) {
				double num = Math.random();
				if (num < 0.55) {
					ring.add(ring.remove(i));
				}
			}
		}
			
	}
	
	// Removes name from Ring list, adds to graveyard list
	private void kill() {
		String cur = "";
		String old = ring.get(ring.size()-1);
		
		Iterator<String> itr = ring.iterator();
		while (itr.hasNext()) {
			cur = itr.next();
			
			if (name.equalsIgnoreCase(cur)) {
				itr.remove();
				grave.add(cur);
				
				double num = Math.random();
				double num2 = Math.random();
				if (num < 0.25 && num2 > 0.75) { // Chance for alternate sayings
					System.out.println(old+" was murdered by "+cur+".\n");
				} else if (num < 0.5 && num2 > 0.5) {
					System.out.println(old+" slaughtered "+cur+".\n");
				} else if (num < 0.75 && num2 > 0.25) {
					System.out.println(old+" showed no mercy to "+cur+".\n");
				} else {
					System.out.println(old+" has killed "+cur+".\n");
				}		
				if (ring.size() == 2) {
		    		System.out.println("{  2  REMAINING  }\n");
		    	}
				return;
			}		
			old = cur;
		}
		System.out.println(name.substring(0, 1).toUpperCase() + name.substring(1)+" is not in the ring.\n");
	}
	
	// Prints alive people
	public void printRing() {
		Iterator<String> itr = ring.iterator();
		if (ring.size() == 0) {
			return;
		} else if (ring.size() == 2) {			
			System.out.println(itr.next()+" and "+itr.next()+" remain.");
		} else {
			for (int i = 1; i < ring.size(); i++) {			
				System.out.print(itr.next()+", ");						
			}		
			System.out.println("and "+itr.next()+" remain.");
		}				
	}
	
	// Prints dead people
	public void printGrave() {			
		Iterator<String> itr = grave.iterator();		
		if (grave.size() == 0) {
			return;
		} else if (grave.size() == 1) {
			System.out.println(itr.next()+" has been sent to the graveyard.");
		} else if (grave.size() == 2) {
			System.out.println(itr.next()+" and "+itr.next()+" have been sent to the graveyard.");
		} else {
			for (int i = 1; i < grave.size(); i++) {			
				System.out.print(itr.next()+", ");						
			}
			System.out.println("and "+itr.next()+" have been sent to the graveyard.");
		}				
	}		
}

