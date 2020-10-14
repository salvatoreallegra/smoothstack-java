package game;

import java.util.Scanner;

import model.Pile;
import model.Player;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String playerOneName = "";
		String playerTwoName = "";

		int initialStack = 0;
		int maxEligibleRemoval = 0;
		int removedChips = 0;

		boolean player1Turn = true;
		boolean player2Turn = false;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter player ones name ");
		playerOneName = sc.nextLine();

		System.out.println("Enter player twos name ");
		playerTwoName = sc.nextLine();

		while (playerOneName.equalsIgnoreCase(playerTwoName)) {

			System.out.println("Name can't be the same");

			System.out.println("Enter player ones name ");
			playerOneName = sc.nextLine();

			System.out.println("Enter player twos name ");
			playerTwoName = sc.nextLine();

		}
		Pile chipPile = new Pile();

		System.out.println("Enter initial chip stack ");

		initialStack = sc.nextInt();

		while (initialStack < 3 || (initialStack % 2 == 0)) {

			System.out.println("Can't be < 3 or Even, enter initial chips stack again");
			initialStack = sc.nextInt();

		}
		chipPile.setChipStack(initialStack);

		System.out.println(chipPile.getChipStack());

		boolean gameOver = false;

		Player player1 = new Player(playerOneName);
		Player player2 = new Player(playerTwoName);

		// game loop
		while (!gameOver) {
			
			int eligibleRemoval = 0;
			
			System.out.println(player1.getName() + " Has " + player1.getChips() + " chips");
			System.out.println(player2.getName() + " Has " + player2.getChips() + " chips");

			if (player1Turn) {
				System.out.println("It's your turn " + player1.getName());
				maxEligibleRemoval = (initialStack - 1) / 2;
				System.out.println("Remove Chips from the pile...between this amount...1 and " + maxEligibleRemoval);
				removedChips = sc.nextInt();
				initialStack -= removedChips;
				System.out.println(initialStack);
				player2Turn = true;
			}
			else {
				
			}

			System.out.println("Game over");
			gameOver = true;

		}
	}

}
