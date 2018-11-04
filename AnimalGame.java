package Project6;

import java.io.*;

class Animal {
	String question;
	String yesGuess, noGuess;
	Animal noPath = null, yesPath = null;
}

class AnimalGame{
	// root points to the base of our tree
	private Animal root = null;
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public AnimalGame(){
		// initial knowledge to start off
		root = new Animal();
		root.question = "Does it have four legs?"; 
		root.yesGuess = "Cat";
		root.noGuess = "snake";
	}

	// Ask the user for their guess
	private boolean AskGuess(String sGuess) throws Exception{	
		String s;

		System.out.println("Are you thinking of a " + 
			sGuess + "?");
		s = in.readLine();
		if ((s.charAt(0)=='y') || (s.charAt(0)=='Y'))		
			return true;
		return false;
	}	

	// Adds a new node below 'ptr' to the tree of knowledge
	// cYesOrNo indicates if we should add to the Yes or No branch	
	private void AddNewAnimal(Animal ptr, char cYesOrNo) throws Exception{
		String sAnimal, s;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		Animal newAnimal = new Animal();

		if (cYesOrNo == 'y') {
			// Link new node to old yes guess
			newAnimal.yesGuess = ptr.yesGuess;
			newAnimal.noGuess = ptr.yesGuess;
			ptr.yesPath = newAnimal;
		}
		else {
			// Link new node to old no guess
			newAnimal.yesGuess = ptr.noGuess;
			newAnimal.noGuess = ptr.noGuess;
			ptr.noPath = newAnimal;			
		}
		System.out.println("What is the correct answer?");
		sAnimal = in.readLine();
		System.out.println("Enter a question to identify your new animal.");
		newAnimal.question = in.readLine();
		System.out.println("Is the answer 'Y' or 'N'?");
		s = in.readLine();
		if ((s.charAt(0)=='y') || (s.charAt(0)=='Y')) {
			newAnimal.yesGuess = sAnimal;
		}
		else {
			newAnimal.noGuess = sAnimal;
		}
	}

	// Main method to play the game
	public static void main(String[] args) throws Exception{
		AnimalGame g = new AnimalGame();
		Animal curPtr = null;
		String sGuess, sQuestion;
		boolean playgame = true, madeguess = false;
		String s;

		System.out.println("Guess the Animal!\n");
		curPtr = g.root;				
		while (playgame) {
			// Travel down tree until we have a guess
			madeguess = false;
			while (!madeguess) {
				// Ask the question
				System.out.println(curPtr.question);
				s = g.in.readLine();
				if (s.charAt(0)=='y') {
					// Check if we reached bottom of tree
					// if so, guess an animal
					if (curPtr.yesPath==null) {
						if (!g.AskGuess(curPtr.yesGuess)){
							// We were wrong, ask
							// for answer
							g.AddNewAnimal(curPtr,'y');
						}
						madeguess = true;
					}
					else curPtr = curPtr.yesPath;
				}
				else {
					// Check if we reached bottom of tree if so, guess an animal
					if (curPtr.noPath==null) {
						if (!g.AskGuess(curPtr.noGuess)){
							// We were wrong, ask
							// for answer
							g.AddNewAnimal(curPtr,'n');
						}
						madeguess = true;
					}
					else curPtr = curPtr.noPath;
				}
			}
			System.out.println("Play again? (y/n)");
			s = g.in.readLine();
			if (s.charAt(0)=='n') playgame=false;
			curPtr = g.root;
		}
	}
}



