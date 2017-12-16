package cpsc2150.hw2;
import java.util.ArrayList;
import java.util.List;

/**
 * @invariant numToWin < numRows && numToWin < numCols
 * @invariant 0 < numRows <= MAX_SIZE
 * @invariant 0 < numCols <= MAX_SIZE
 * Correspondence NUM_ROWS = numRows
 * Correspondence NUM_COLS = numCols
 */
public class GameBoardMem implements IGameBoard{

    //fields
    private List<BoardPosition> xList, oList;
    private int numRows, numCols, numToWin;

    /**
     * @param col is the number of columns
     * @param row is the number of rows
     * @param win is the number in a row to win
     * @requires 3 <= col <= MAX_SIZE && 2 <= row <= MAX_SIZE &&
     *  3 <= win <= row && 2 <= win <= col
     * @ensures [Board is created, board is playable, and board is empty]
     */
    public GameBoardMem(int row, int col, int win) {

        numRows = row;
        numCols = col;
        numToWin = win;

        xList = new ArrayList<>();
        oList = new ArrayList<>();
    }

    public boolean checkSpace(BoardPosition pos){

        List<BoardPosition>tempList = new ArrayList<>();
        tempList.addAll(oList);
        tempList.addAll(xList);

        BoardPosition posX = new BoardPosition(pos.getRow(), pos.getColumn(),
                'X');
        BoardPosition posO = new BoardPosition(pos.getRow(),pos.getColumn(),
                'O');

        if (pos.getPlayer() == 'X' || pos.getPlayer() == 'O')
            if (pos.getRow() > -1 && pos.getRow() < numRows)
                if (pos.getColumn() > -1 && pos.getColumn() < numCols)
                    return (!tempList.contains(posX) &&
                            !tempList.contains(posO));

        return false;
    }

    public void placeMarker(BoardPosition lastPos){

        if (lastPos.getPlayer() == 'X')
            xList.add(lastPos);

        if (lastPos.getPlayer() == 'O')
            oList.add(lastPos);
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

        List<BoardPosition> tempList;
        int inLine = 1, row = pos.getRow() - 1;

        //setting our list to xList or oList
        if (pos.getPlayer() == 'X')
            tempList = xList;
        else
            tempList = oList;

        //checking above the position
        while (row >= 0 && inLine < numToWin) {

            //creating a temporary board position to check for
            BoardPosition tempPos = new BoardPosition(row, pos.getColumn(),
                    pos.getPlayer());

            //Checking if next position is in list
            if (tempList.contains(tempPos))
                inLine++;
            //breaking the loop if not in a row
            else
                row = -1;

            row--;
        }

        row = pos.getRow() + 1;

        //checking belows the position
        while (row < numRows && inLine < numToWin) {

            //creating a temporary board position to check for
            BoardPosition tempPos = new BoardPosition(row, pos.getColumn(),
                    pos.getPlayer());

            //Checking if next position is in list
            if (tempList.contains(tempPos))
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

        List<BoardPosition> tempList;
        int inLine = 1, col = pos.getColumn() - 1;

        //setting our list to xList or oList
        if (pos.getPlayer() == 'X')
            tempList = xList;
        else
            tempList = oList;

        //checking to the left of the position
        while (col >= 0 && inLine < numToWin) {

            //creating a temporary board position to check for
            BoardPosition tempPos = new BoardPosition(pos.getRow(), col,
                    pos.getPlayer());

            //Checking if next position is in list
            if (tempList.contains(tempPos))
                inLine++;
            //breaking the loop if not in a row
            else
                col = -1;

            col--;
        }

        col = pos.getColumn() + 1;

        //checking to the right of the position
        while (col < numCols && inLine < numToWin) {

            //creating a temporary board position to check for
            BoardPosition tempPos = new BoardPosition(pos.getRow(), col,
                    pos.getPlayer());

            //Checking if next position is in list
            if (tempList.contains(tempPos))
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

        List<BoardPosition> tempList;
        //counts for either diagonal
        int inLineA = 1, inLineB = 1;
        //setting start position for top left
        int row = pos.getRow() - 1;
        int col = pos.getColumn() -1;

        //setting our list to xList or oList
        if (pos.getPlayer() == 'X')
            tempList = xList;
        else
            tempList = oList;

        //checking top left
        while (col >= 0 && row >= 0 && inLineA < numToWin) {

            //creating a temporary board position to check for
            BoardPosition tempPos = new BoardPosition(row, col,
                    pos.getPlayer());

            //Checking to see if position is in line and incrementing inLineA
            if (tempList.contains(tempPos))
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

            //creating a temporary board position to check for
            BoardPosition tempPos = new BoardPosition(row, col,
                    pos.getPlayer());

            //Checking to see if position is in line and incrementing inLineA
            if (tempList.contains(tempPos))
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

            //creating a temporary board position to check for
            BoardPosition tempPos = new BoardPosition(row, col,
                    pos.getPlayer());

            //Checking to see if position is in line and incrementing inLineB
            if (tempList.contains(tempPos))
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

            //creating a temporary board position to check for
            BoardPosition tempPos = new BoardPosition(row, col,
                    pos.getPlayer());

            //Checking to see if position is in line and incrementing inLineB
            if (tempList.contains(tempPos))
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

        //Creating a temp list with both x and o lists
        List <BoardPosition> tempList = new ArrayList<>();
        tempList.addAll(xList);
        tempList.addAll(oList);
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
                boardString = boardString +'|' + " ";

                BoardPosition tempPosX = new BoardPosition(i, j, 'X');
                BoardPosition tempPosO = new BoardPosition(i, j, 'O');

                //adding an X, O, or blank space depending on the marker
                if (tempList.contains(tempPosX))
                    boardString = boardString + tempPosX.getPlayer();
                else if (tempList.contains(tempPosO))
                    boardString = boardString + tempPosO.getPlayer();
                else
                    boardString = boardString + ' ';
            }
            //adding final bar to the string and going to the next line of the
            //board
            boardString = boardString + ("|\n");
        }

        return boardString;
    }
}
