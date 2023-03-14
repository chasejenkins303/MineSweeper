package main;



public class Cell {

/**
 * A class that defines an object "cell" that will be used to make the gameboard.
 * 
 * @author chasejenkins
 * 
 */
	
	private String display;
	private boolean guessed;
	private boolean expanded;
	private int row;
	private int column;
	/**
	 * Creates cell object and sets value of instance variables as needed
	 * @param display is the string value of the cell object
	 */
	public Cell(String display) {
		this.display=display;
		guessed=false;
		expanded=false;
	}
	/**
	 * Used to get the String value of the cell
	 * @return the String value of the cell
	 */
	public String toString() {
		return display;
		
	}
	/**
	 * sets the boolean variable guessed equal to true, meaning this cell has been guessed by the user.
	 * 
	 */
	public void hasBeenGuessed() {
		guessed=true;
	}
	/**
	 * returns whether the cell has been guessed using true or false.
	 * 
	 * @return the value of boolean guessed
	 */
	public boolean getGuess() {
		return guessed;
	}
	/**
	 * sets the boolean variable expanded equal to true indicating it has been expanded on already
	 * 
	 */
	public void hasBeenExpanded() {
		expanded = true;
	}
	/**
	 * returns whether the cell has been expanded on or not using true or false.
	 * 
	 * @return the value of boolean expanded
	 */
	public boolean getExpand() {
		return expanded;
	}
	/**
	 * sets the value of "row" for a cell to the variable that is in the parameter
	 * 
	 * @param row1 takes in the value of the row the cell is in.
	 */
	public void setRow(int row1) {
		row=row1;
	}
	/**
	 * Used to determine what row the cell is located in.
	 * 
	 * @return the row variable 
	 */
	public int getRow() {
		return row;
	}
	/**
	 * sets the value of "column" to the the variable that is in the parameter
	 * 
	 * @param column1 takes in the value of what column the cell is in
	 */
	public void setColumn(int column1) {
		column=column1;
	}
	/**
	 * Used to determine what column the cell is located in
	 * 
	 * @return the column variable
	 */
	public int getColumn() {
		return column;
	}

}
