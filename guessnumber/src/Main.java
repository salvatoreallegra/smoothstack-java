import java.util.Random;
import java.util.Scanner;

/*****
 * 
 * @author salal Guess numbers game
 */
public class Main {

	static int numberOfGuesses = 0;
	static int guessedNumber = 0;
	static boolean guessedCorrectly;
	static Random rand = new Random();
	static int correctGuess = rand.nextInt(100);
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (numberOfGuesses < 5) {
			guessedCorrectly = guessingGameEngine();
			numberOfGuesses++;
			if (guessedCorrectly) {

				break;

			}
		}
		if (guessedCorrectly) {
			System.out.println("You are within +- 10 of the correct number, congratulations!");
		} else {
			System.out.println("You are out of guesses, thanks for playing");
			System.exit(0);
		}

	}

	public static boolean guessingGameEngine() {

		System.out.println(correctGuess);
		guessedNumber = sc.nextInt();
		while (guessedNumber < 1 || guessedNumber > 100) {
			System.out.println("Number out of Range, please enter a number between 1 and 100");
			guessedNumber = sc.nextInt();

		}
		if (guessedNumber >= (correctGuess - 10) && guessedNumber <= (correctGuess + 10)) {
			return true;
		} else {
			return false;
		}

	}

}
