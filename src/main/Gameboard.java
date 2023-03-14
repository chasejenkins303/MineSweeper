package main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Gameboard {
/**
 * An implementation of a minesweeper gameboard that will run the minesweeper game based on user input
 * Gameboard class will have many methods that allow for the creation of a board that is a 2d array of type "Cell" 
 * The Gameboard will allow a user to guess a point on the board and guess if it is a mine, if they are wrong they lose.
 * 
 * @author chasejenkins
 * 	
 */

	private Cell[][] board;
	private int condition;
	private Random generator;
	private int rowNum;
	private int columnNum;
	private int mineGuessed;
	private int methodNum;
	private boolean lost;
	private boolean playAgainAns;
	private Stack<Cell> stack1;
	/**
	 * Constructor for the Gameboard object that sets the values of instance variables as needed as well as
	 * anything that needs a "new" command.
	 * 
	 */
	public Gameboard() {
		generator = new Random();
		condition = 1;
		board = new Cell[8][8];
	}
/**
 * Method that takes the logic from all of the other methods and uses that to run the full game.
 * Uses while loops to get from each part of the game to another as need and catches exceptions where 
 * it is needed.
 * Uses a scanner to read in input from user and based off of the input received the game will behave in a way
 * as outlined by other methods.
 * 
 * @throws InputMismatchException if the input is not of the expected type
 */
	public void run() throws InputMismatchException {
		mineGuessed = 0;
		methodNum = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Minesweeper!");
			while(condition<2) {
				System.out.print("Would you like to play a game? (y/n): ");
				String input1 = scan.nextLine();
				
				if(input1.equalsIgnoreCase("y")) {
					while(condition<2) {
						makeBoard();
						assignMines(board);
					
					while(methodNum<5) {
						while(methodNum==0) {
						System.out.print("Would you like to peek? (y/n): ");
						String inputPeek=scan.next();
							
						   if(inputPeek.equalsIgnoreCase("y")) {
								peek();
								methodNum++;
						   }else if(inputPeek.equalsIgnoreCase("n")) {
								methodNum++;
							}else {
								System.out.println("Invalid input, please try again.");
								scan.nextLine();
							}
							
						}while(methodNum==1) {
						
							System.out.print("Please enter a row number: ");
							try {
								rowNum = scan.nextInt();
							}catch(InputMismatchException e) {
								System.out.println("Invalid input, please try again.");
								
								scan.nextLine();
								continue;
							}
								if(rowNum<=0||rowNum>=9) {
									System.out.println("Invalid input, please try again.");
									scan.nextLine();
								}else {
									methodNum++;
								}
								
						}while(methodNum==2) {
							System.out.print("Please enter a column number: ");
							try {
								columnNum = scan.nextInt();
							}catch(InputMismatchException ee) {
								System.out.println("Invalid input, please try again.");
								
								scan.nextLine();
								continue;
								
							}
								if(columnNum<=0||columnNum>=9) {
									System.out.println("Invalid input, please try again.");
									scan.nextLine();
									
								}else {
									methodNum++;
								}
							
						}
						
						while(methodNum==3) {
							String rowNumStr = String.valueOf(rowNum);
							String columnNumStr= String.valueOf(columnNum);
							System.out.print("Does row "+ rowNumStr +" and column "+ columnNumStr + " contain a mine? (y/n): ");
							String ansQues = scan.next();
								if(ansQues.equalsIgnoreCase("y")) {
									if(board[rowNum-1][columnNum-1].toString()=="M ") {
										board[rowNum-1][columnNum-1].hasBeenGuessed();
										mineGuessed++;
										if(mineGuessed==10) {
											System.out.println("You win!");
											methodNum=4;
											break;
										}
										getCurrentBoard();
										methodNum=-1;
									}else if(board[rowNum-1][columnNum-1].toString()!="M ") {
										System.out.println("Boom! You lose.");
										methodNum++;
									}
								}else if(ansQues.equalsIgnoreCase("n")) {
									if(board[rowNum-1][columnNum-1].toString()=="M ") {
										System.out.println("Boom! You lose.");
										methodNum++;
									}else if(board[rowNum-1][columnNum-1].toString()!="M ") {
										board[rowNum-1][columnNum-1]= new Cell(makeValues(rowNum-1, columnNum-1));
										board[rowNum-1][columnNum-1].setRow(rowNum-1);
										board[rowNum-1][columnNum-1].setColumn(columnNum-1);
										String exp = board[rowNum-1][columnNum-1].toString();
										if(exp.equalsIgnoreCase("0 ")) {
											expand(rowNum-1, columnNum-1);
											
										}
										getCurrentBoard();
										methodNum=-1;
									}
									
								}else {
									System.out.println("Invalid input, please try again.");
									scan.nextLine();
								}
						 }while(methodNum==4) {
							 	System.out.println("Thank you for playing Minesweeper.");
					    		System.out.print("Would you like to play again? (y/n): ");
					    		String playAgain = scan.next();
					    		if(playAgain.equalsIgnoreCase("y")) {
					    			playAgainAns=true;
					    			methodNum++;
					    		}else if(playAgain.equalsIgnoreCase("n")) {
					    			playAgainAns=false;
					    			methodNum++;
					    		}else {
					    			System.out.println("Invalid input, please try again.");
					    			scan.nextLine();
							}
					    	
					    }
						 if(playAgainAns==true || playAgainAns == false) {
							 methodNum++;
						 }
					}
					  if(playAgainAns==true) {
						  methodNum=0;
						  mineGuessed=0;
						  continue;
					  }else if(playAgainAns==false) {
						  System.out.println("Goodbye!");
						  break;
					  }
					}
				}else if(input1.equalsIgnoreCase("n")) {
					System.out.println("Goodbye!");
					break;
				}else {
					System.out.println("Invalid input, please try again.");
					continue;
				}
				break;
			}

				
}
	
	/**
	 * This method is used to create the starting gameboard where every cell has the string value of a dash
	 * 
	 * @return a 2d array called board that is filled with cells
	 */
	public Cell[][] makeBoard() {
		//makes initial board
		for(int i=0; i<board.length;i++) {
			for(int j=0; j<board[i].length; j++) {
				board[i][j]=new Cell("- ");
			}
		}
		for(int i=0; i<board.length;i++) {
			for(int j=0; j<board[i].length; j++) {
			System.out.print(board[i][j].toString());
				if(j==7) {
					System.out.println();
				}
			}
		}
		System.out.println();
		return board;
	}
	
/**
 * This method takes the original board and randomly assigns ten mines ("M ") to ten distinct array values
 * This is called after the makeboard() method to assure that the board has already been created and is blank 
 * 
 * @param board1 which is a 2d array of cells
 * @return the new board with mines randomly assigned
 */
	public Cell[][] assignMines(Cell[][] board1) {
		int num=0;
		while(num<10) {
			int row=generator.nextInt(8);
			int column=generator.nextInt(8);
			if(board1[row][column].toString()=="M ") {
				continue;
			}else if(board1[row][column].toString()=="- ") {
				board1[row][column]=new Cell("M ");
				num++;
			}
		}
		board=board1;
		return board;
	}
	
	/**
	 * Takes in the row and column number of a cell and then increments a counter that counts how many
	 * mines surround that cell. It checks if there is a cell first to avoid going out of the array bounds 
	 * and then converts the counter into the appropriate string value.
	 * 
	 * @param a is the row number of the cell that it is making values for
	 * @param b is the column number of the cell that it is making values for
	 * @return a String that is a number from 0 to 8 that represents how many mines surround it
	 */
	public String makeValues(int a, int b){
		//checks if there is a cell first
	 int mineCount=0;
	  if(a-1>=0) {
		if(board[a-1][b].toString()=="M ") {
			mineCount++;
		}}
	  if(a+1<board.length) {	
	 	if(board[a+1][b].toString()=="M ") {
			mineCount++;
		}}
	  if(b-1>=0) {
		if(board[a][b-1].toString()=="M ") {
			mineCount++;
		}}
	  if(b+1<board.length) {
		if(board[a][b+1].toString()=="M ") {
			mineCount++;
		}}
	  if(a-1>=0&&b-1>=0) {
		if(board[a-1][b-1].toString()=="M ") {
			mineCount++;
		}}
	  if(a+1<board.length&&b+1<board.length) {
		if(board[a+1][b+1].toString()=="M ") {
			mineCount++;
		}}
	  if(a-1>=0&&b+1<board.length) {
		if(board[a-1][b+1].toString()=="M ") {
			mineCount++;
		}}
	  if(a+1<board.length&&b-1>=0) {
		if(board[a+1][b-1].toString()=="M ") {
			mineCount++;
		}}
		String sMineCount = String.valueOf(mineCount)+" ";
		return sMineCount;
	}
	
	/**
	 * Method that takes no parameters and does not return anything,
	 * it just prints out the board with all of the mines showing.
	 * Only gets called in the run method when the user specifies they want to peek.
	 * 
	 */
	public void peek(){
			for(int i=0; i<board.length;i++) {
				for(int j=0; j<board[i].length; j++) {
				    System.out.print(board[i][j].toString());
						if(j==7) {
							System.out.println();
						}
					}
				}
			System.out.println();
	          }
	/**
	 * Doesn't return anything or have parameters
	 * This method prints out the board after each turn but does not print out the 
	 * mine values if they have not been guessed.
	 * 
	 */
	public void getCurrentBoard() {
		for(int i=0; i<board.length;i++) {
			for(int j=0; j<board[i].length; j++) {
				if(board[i][j].toString()!="M ") {
			    System.out.print(board[i][j].toString());
					if(j==7) {
						System.out.println();
					}
				}else if(board[i][j].toString()=="M "&& board[i][j].getGuess()!=true) {
					System.out.print("- ");
					if(j==7) {
						System.out.println();
					}
				}else if(board[i][j].toString()=="M "&& board[i][j].getGuess()==true) {
					System.out.print(board[i][j].toString());
					if(j==7) {
						System.out.println();
					}
				 }
				}
			   }
		System.out.println();
	}
	
	/**
	 * When a guessed cells value is "0 ", this method is called and it will expand to all 
	 * surrounding values and make those values visible and keeps doing this 
	 * until there are no surrounding zeros.
	 * This method uses a stack that takes in the surrounding values and keeps expanding until the 
	 * stack is empty. Cells are sent to the pushNewCell() method only if they are also "0 "
	 * 
	 * @param a is the int value of the row that the cell is in
	 * @param b is the int value of the column that the cell is in
	 */
		
	public void expand(int a, int b) {
		stack1 = new Stack<Cell>();
		stack1.push(board[a][b]);
		Cell test;
		while(stack1.isEmpty()==false) {
			test = stack1.pop();
			board[a][b].hasBeenExpanded();
			if(test.toString().equalsIgnoreCase("0 ")) {
				pushNewCell(test.getRow(), test.getColumn());
			}
		}
	}
	/**
	 * This method works directly with the expand method and is used to take in a cell that has
	 * the value "0 " and checks if the surrounding values are in bounds, if they are then those values
	 * are pushed onto the stack that is used in expand()
	 * 
	 * @param a is an int that represents the row value of the cell
	 * @param b is an int that represents the column value of the cell
	 */
	private void pushNewCell(int a, int b) {
		
		if(a-1>=0) {
		  if(board[a-1][b].getExpand()==false) {
			board[a-1][b]= new Cell(makeValues(a-1, b));
			board[a-1][b].setRow(a-1);
			board[a-1][b].setColumn(b);
			stack1.push(board[a-1][b]);
			board[a-1][b].hasBeenExpanded();
		  }
			
		}
		if(a+1<board.length) {
			if(board[a+1][b].getExpand()==false) {
			board[a+1][b]= new Cell(makeValues(a+1, b));
			board[a+1][b].setRow(a+1);
			board[a+1][b].setColumn(b);
			stack1.push(board[a+1][b]);
			board[a+1][b].hasBeenExpanded();
			}
		}
		if(b-1>=0) {
			if(board[a][b-1].getExpand()==false) {
			board[a][b-1]= new Cell(makeValues(a, b-1));
			board[a][b-1].setRow(a);
			board[a][b-1].setColumn(b-1);
			stack1.push(board[a][b-1]);
			board[a][b-1].hasBeenExpanded();
			}

		}
		if(b+1<board.length) {
			if(board[a][b+1].getExpand()==false) {
			board[a][b+1]= new Cell(makeValues(a, b+1));
			board[a][b+1].setRow(a);
			board[a][b+1].setColumn(b+1);
			stack1.push(board[a][b+1]);
			board[a][b+1].hasBeenExpanded();
			}
		
		}
		if(a-1>=0&&b-1>=0) {
			if(board[a-1][b-1].getExpand()==false) {
			board[a-1][b-1]= new Cell(makeValues(a-1, b-1));
			board[a-1][b-1].setRow(a-1);
			board[a-1][b-1].setColumn(b-1);
			stack1.push(board[a-1][b-1]);
			board[a-1][b-1].hasBeenExpanded();
			}
		
		}
		if(a+1<board.length&&b+1<board.length) {
			if(board[a+1][b+1].getExpand()==false) {
			board[a+1][b+1]= new Cell(makeValues(a+1, b+1));
			board[a+1][b+1].setRow(a+1);
			board[a+1][b+1].setColumn(b+1);	
			stack1.push(board[a+1][b+1]);
			board[a+1][b+1].hasBeenExpanded();
			}
		}
		if(a-1>=0&&b+1<board.length) {
			if(board[a-1][b+1].getExpand()==false) {
			board[a-1][b+1]= new Cell(makeValues(a-1, b+1));
			board[a-1][b+1].setRow(a-1);
			board[a-1][b+1].setColumn(b+1);
			stack1.push(board[a-1][b+1]);
			board[a-1][b+1].hasBeenExpanded();
			}
		
		}
		if(a+1<board.length&&b-1>=0) {
			if(board[a+1][b-1].getExpand()==false) {
			board[a+1][b-1]= new Cell(makeValues(a+1, b-1));
			board[a+1][b-1].setRow(a+1);
			board[a+1][b-1].setColumn(b-1);
			stack1.push(board[a+1][b-1]);
			board[a+1][b-1].hasBeenExpanded();
			}
			
		}
	
	}
	
	
	
		  
}
	

