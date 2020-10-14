import java.util.Scanner;

/*****
 * 
 * @author salal
 * Guess numbers game
 */
public class Main {

	static int numberOfGuesses = 0;
	static int guessedNumber = 0;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(numberOfGuesses < 5) {
			guessingGameEngine();
			numberOfGuesses++;			
		}
		System.out.println("You are out of guesses, thanks for playing");

	}
	
	public static void guessingGameEngine() {
		System.out.println("Gueesed");
		guessedNumber = sc.nextInt();
		while(guessedNumber < 1 || guessedNumber  > 100) {
			System.out.println("Number out of Range, please enter a number between 1 and 100");
			guessedNumber = sc.nextInt();
			
			
		}
		
	}

}
