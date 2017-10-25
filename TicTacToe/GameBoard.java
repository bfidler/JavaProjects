package cpsc2150.hw2;

public class GameBoard {

    //fields
    private char[][] theBoard;
    private static final int BOARD_LW = 8;

    /**
     * @ensures [Board is created and board is empty]
     */
    public GameBoard() {

        //creating 8x8 array for the game board
        theBoard = new char[BOARD_LW][BOARD_LW];

        //populating the board with empty spaces
        for(int i = 0; i < BOARD_LW; i++) {
            for(int j = 0; j < BOARD_LW; j++) {

                theBoard[i][j] = ' ';
            }
        }

    }

    /**
     *
     * @param pos
     * @requires [pos has a row and a column]
     * @ensures [pos on the board is available]
     * @return true if the position is empty and on the board, false if not
     */
    public boolean checkSpace(BoardPosition pos){

        if (pos.getRow() > -1 && pos.getRow() < BOARD_LW)
            if (pos.getColumn() > -1 && pos.getColumn() < BOARD_LW)
                return(theBoard[pos.getRow()][pos.getColumn()] == ' ');

        return false;
    }

    /**
     *
     * @param pos
     * @requires (pos.checkSpace == true) &&
     *  (0 <= pos.getRow() < BOARD_LW) &&
     *  (0 <= pos.getCol() < BOARD_LW)
     * @ensures [Another marker is not overwritten]
     */
    public void placeMarker(BoardPosition pos){

        theBoard[pos.getRow()][pos.getColumn()] = pos.getPlayer();
    }

    /**
     *
     * @param pos
     * @requires [the last marker placed was passed]
     * @ensures [the proper part of the board is checked for a win]
     * @return true if the move won the game, false if not
     */
    public boolean checkForWinner(BoardPosition pos){
        //Three different ways a player can win
        return(checkVerticalWin(pos) || checkHorizontalWin(pos) ||
                checkDiagonalWin(pos));
    }

    /**
     *
     * @param pos
     * @requires [the last marker placed was passed]
     * @ensures [the proper column of the board is checked for a win]
     * @return true if the move won the game vertically, false if not
     */
    private boolean checkVerticalWin(BoardPosition pos){
        //inline counts the number of Xs or Os in row
        int inLine = 0, row = 0;

        /*loop from the first row of the column to the last row or until
          a row of five has been counted*/
        while(row < BOARD_LW  && inLine != 5){
            /*Checks to see if the position contains the correct character.
              If it does inLine goes up one. If not, the inLine count is reset
              because the X/0S are not in a row from that point.*/
            if(theBoard[row][pos.getColumn()] == pos.getPlayer())
                inLine++;
            else
                inLine = 0;

            //increment
            row++;
        }

        //true if 5 in a row!
        return(inLine == 5);
    }

    /**
     *
     * @param pos
     * @requires [the last marker placed was passed]
     * @ensures [the proper row of the board is checked for a win]
     * @return true if the move won the game horizontally, false if not
     */
    private boolean checkHorizontalWin(BoardPosition pos){
        //inLine counts the number of X/Os in a row
        int inLine = 0, col = 0;

        /*loop from the first column to the last column or until
          five in a row have been counted*/
        while(col < BOARD_LW && inLine != 5){
            /*Checks to see if the position contains the correct character.
              If it does inLine goes up one. If not, the inLine count is reset
              because the X/0S are not in a row from that point.*/
            if(theBoard[pos.getRow()][col] == pos.getPlayer())
                inLine++;
            else
                inLine = 0;

            //increment
            col++;
        }

        //true if 5 in row!
        return(inLine == 5);
    }

    /**
     *
     * @param pos
     * @requires [the last marker placed was passed]
     * @ensures [the proper diagonals of the board is checked for a win]
     * @return true if the move won the game diagonally, false if not
     */
    private boolean checkDiagonalWin(BoardPosition pos){
        //inline counts the number of Xs and Os
        int inLine = 0;
        int row = pos.getRow(), col = pos.getColumn();

        /*This starts the row and column at the top left corner of the
          diagonal. If the pos given is on a farther row down than column, it
          starts checking against the left side of the board. If the column is
          farther down than the row then it starts checking from the top.*/
        if(row > col) {
            row = row - col;
            col = 0;
        }
        else {
            col = col - row;
            row = 0;
        }

        /*This loops until the right side or bottom of the board has been
          reached or until 5 in a row. Checks topLeft to bottomRight. */
        while(row < BOARD_LW && col < BOARD_LW && inLine != 5) {
            /*Checks to see if the position contains the correct character.
              If it does inLine goes up one. If not, the inLine count is reset
              because the X/0S are not in a row from that point.*/
            if(theBoard[row][col] == pos.getPlayer())
                inLine++;
            else
                inLine = 0;

            //increments
            row++;
            col++;
        }

        //if the first diagonal doesn't find 5 in a row, the other is checked
        if(inLine != 5) {
            //Resetting inLine
            inLine = 0;

            //Setting the row and column to the original position
            row = pos.getRow();
            col = pos.getColumn();

            /*This increments the row and column down and to the left until
              the bottom left corner of the diagonal has been reached (either the
              left side or bottom of the board) */
            while(row < BOARD_LW - 1 && col > 0){
                row++;
                col--;
            }

            /*This loops until the top or right of the board has been reached or
              until five in a row are found*/
            while (row > -1 && col < BOARD_LW && inLine != 5) {
                /*Checks to see if the position contains the correct character.
                  If it does inLine goes up one. If not, the inLine count is
                  reset because the X/0S are not in a row from that point.*/
                if (theBoard[row][col] == pos.getPlayer())
                    inLine++;
                else
                    inLine = 0;

                //icrements
                row--;
                col++;
            }
        }

        //true if 5 in a row!
        return(inLine == 5);
    }

    /**
     *
     * @return the String of the board, displayed in rows in columns
     */
    @Override
    public String toString(){

        //Creating a string to return
        String boardString = "  ";

        //labeling the columns
        for(int i = 0; i < BOARD_LW; i++)
            boardString = boardString + i + " ";

        //next line
        boardString = boardString + '\n';

        //Double loop to access each board space
        for(int i = 0; i < BOARD_LW; i++){
            //adding the row label to the string
            boardString = boardString + i;

            for(int j = 0; j < BOARD_LW; j++){
                //preceding each board space with a bar
                boardString = boardString +'|' + theBoard[i][j];
            }
            //adding final bar to the string and going to the next line of the
            //board
            boardString = boardString + ("|\n");
        }

        return boardString;
    }

}
