package cpsc2150.hw2;

/**
 * @invariant numToWin < numRows && numToWin < numCols
 * @invariant 0 < numRows <= MAX_SIZE
 * @invariant 0 < numCols <= MAX_SIZE
 * Correspondence NUM_ROWS = numRows
 * Correspondence NUM_COLS = numCols
 */
public class GameBoardFast implements IGameBoard{

    //fields
    private char[][] theBoard;
    private int numRows, numCols, numToWin;

    /**
     * @param col is the number of columns
     * @param row is the number of rows
     * @param win is the number in a row to win
     * @requires 3 <= col <= MAX_SIZE && 2 <= row <= MAX_SIZE &&
     *  3 <= win <= row && 2 <= win <= col
     * @ensures [Board is created, board is playable, and board is empty]
     */
    public GameBoardFast(int row, int col, int win) {

        numRows = row;
        numCols = col;
        numToWin = win;

        //creating 8x8 array for the game board
        theBoard = new char[MAX_SIZE][MAX_SIZE];

        //populating the board with empty spaces
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {

                theBoard[i][j] = ' ';
            }
        }

    }

    public boolean checkSpace(BoardPosition pos){

        if (pos.getRow() > -1 && pos.getRow() < numRows)
            if (pos.getColumn() > -1 && pos.getColumn() < numCols)
                return(theBoard[pos.getRow()][pos.getColumn()] == ' ');

        return false;
    }

    public void placeMarker(BoardPosition lastPos){

        theBoard[lastPos.getRow()][lastPos.getColumn()] = lastPos.getPlayer();
    }

    public boolean checkForWinner(BoardPosition lastPos){
        //Three different ways a player can win
        return(checkVerticalWin(lastPos) || checkHorizontalWin(lastPos) ||
                checkDiagonalWin(lastPos));
    }

    /**
     *
     * @param pos is the position to be checked
     * @requires [the last marker placed was passed]
     * @ensures [the proper column of the board is checked for a win]
     * @return true if the move won the game vertically, false if not
     */
    private boolean checkVerticalWin(BoardPosition pos){

        int inLine = 1, row = pos.getRow() - 1;

        //checking above the position
        while (row >= 0 && inLine < numToWin) {

            //Checking for the char of the player
            if (theBoard[row][pos.getColumn()] == pos.getPlayer())
                inLine++;
            //breaking the loop if not in a row
            else
                row = -1;

            row--;
        }

        row = pos.getRow() + 1;

        //checking belows the position
        while (row < numRows && inLine < numToWin) {

            //Checking for the char of the player
            if (theBoard[row][pos.getColumn()] == pos.getPlayer())
                inLine++;
            //breaking the loop if not in a row
            else
                row = numRows;

            row++;
        }

        return(inLine == numToWin);
    }

    /**
     *
     * @param pos is the position to be checked
     * @requires [the last marker placed was passed]
     * @ensures [the proper row of the board is checked for a win]
     * @return true if the move won the game horizontally, false if not
     */
    private boolean checkHorizontalWin(BoardPosition pos){

        int inLine = 1, col = pos.getColumn() - 1;

        //checking to the left of the position
        while (col >= 0 && inLine < numToWin) {

            //checking for the char of the player
            if (theBoard[pos.getRow()][col] == pos.getPlayer())
                inLine++;
            //breaking the loop if not in a row
            else
                col = -1;

            col--;
        }

        col = pos.getColumn() + 1;

        //checking to the right of the position
        while (col < numCols && inLine < numToWin) {

            //checking for the char of the player
            if (theBoard[pos.getRow()][col] == pos.getPlayer())
                inLine++;
            //breaking the loop if not in a row
            else
                col = numCols;

            col++;
        }

        return(inLine == numToWin);
    }

    /**
     *
     * @param pos is the position to be checked
     * @requires [the last marker placed was passed]
     * @ensures [the proper diagonals of the board is checked for a win]
     * @return true if the move won the game diagonally, false if not
     */
    private boolean checkDiagonalWin(BoardPosition pos){

        //counts for either diagonal
        int inLineA = 1, inLineB = 1;
        //setting start position for top left
        int row = pos.getRow() - 1;
        int col = pos.getColumn() -1;

        //checking top left
        while (col >= 0 && row >= 0 && inLineA < numToWin) {

            //Checking to see if position is in line and incrementing inLineA
            if (theBoard[row][col] == pos.getPlayer())
                inLineA++;
            //Breaking the loop if not
            else
                col = -1;

            col--;
            row--;
        }

        //setting starting position to check bottom right
        row = pos.getRow() + 1;
        col = pos.getColumn() + 1;

        //checking bottom right
        while (col < numCols && row < numRows && inLineA < numToWin) {

            //Checking to see if position is in line and incrementing inLineA
            if (theBoard[row][col] == pos.getPlayer())
                inLineA++;
            //Breaking the loop if not
            else
                col = numCols;

            col++;
            row++;
        }

        //setting starting position to check top right
        row = pos.getRow() - 1;
        col = pos.getColumn() + 1;

        //checking top right
        while (col < numCols && row >= 0 && inLineA < numToWin &&
                inLineB < numToWin) {

            //Checking to see if position is in line and incrementing inLineB
            if (theBoard[row][col] == pos.getPlayer())
                inLineB++;
            //Breaking the loop if not
            else
                col = numCols;

            col++;
            row--;
        }

        //setting starting position to check bottom left
        row = pos.getRow() + 1;
        col = pos.getColumn() - 1;

        //bottom left
        while (col >= 0 && row < numRows && inLineA < numToWin &&
                inLineB < numToWin) {

            //Checking to see if position is in line and incrementing inLineB
            if (theBoard[row][col] == pos.getPlayer())
                inLineB++;
            //Breaking the loop if not
            else
                col = -1;

            col--;
            row++;
        }

        return(inLineA == numToWin || inLineB == numToWin);
    }

    /**
     * @requires [board has been created and has rows and cols]
     * @ensures [board is converted to string properly]
     * @return the String of the board, displayed in rows in columns
     */
    @Override
    public String toString(){

        //Creating a string to return
        String boardString = "   ";

        //labeling the columns
        for(int i = 0; i < numCols; i++) {
            if (i < 10)
                boardString = boardString + " " + i + "|";
            else
                boardString = boardString + i + "|";
        }

        //next line
        boardString = boardString + '\n';

        //Double loop to access each board space
        for(int i = 0; i < numRows; i++){
            //adding the row label to the string
            if (i < 10)
                boardString = boardString + " " + i;
            else
                boardString = boardString + i;

            for(int j = 0; j < numCols; j++){
                //preceding each board space with a bar
                boardString = boardString +'|' + " " + theBoard[i][j];
            }
            //adding final bar to the string and going to the next line of the
            //board
            boardString = boardString + ("|\n");
        }

        return boardString;
    }
}
