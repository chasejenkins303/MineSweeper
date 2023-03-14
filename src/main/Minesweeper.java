package main;

public class Minesweeper {
/**
 * main method whose only function is to create an instance of Gameboard and then use the .run() method 
 * on that board to play the minesweeper game.
 * @param args
 */
	public static void main(String[] args) {
		Gameboard board = new Gameboard();
		board.run();
	}

}
