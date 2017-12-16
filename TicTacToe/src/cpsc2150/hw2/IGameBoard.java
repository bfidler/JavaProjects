package cpsc2150.hw2;

/**
 *	IGameBoard represents a 2-dimensional gameboard that has characters
 *	on it as markers (X, O). No space on the board can have multiple
 *	players, and there can be a clear winner. Board is NUM_ROWS x
    NUM_COLS in size
 *
 *	Initialization ensures: the Board does not have any markers on it
 *	Defines: NUM_ROWS: Z
 *	Defines: NUM_COLS: Z
 *	Constraints: 3 < NUM_ROWS <= MAX_SIZE
 *	3 < NUM_COLS <= MAX_SIZE
 */
public interface IGameBoard {

    int MAX_SIZE = 100;

    /**
     *
     * @param pos is the position to be checked
     * @requires [pos has a row, a col, and a player]
     * @ensures [pos on the board is checked for availability]
     * @return true if the position is empty and on the board, false if not
     */
    boolean checkSpace(BoardPosition pos);

    /**
     *
     * @param lastPos is the position to be placed
     * @requires checkSpace(lastPos) == true
     * @ensures [Marker is placed and another marker is not overwritten]
     */
    void placeMarker(BoardPosition lastPos);

    /**
     *
     * @param lastPos is the position to be checked
     * @requires [the last marker placed was passed]
     * @ensures [the proper part of the board is checked for a win]
     * @return true if the move won the game, false if not
     */
    boolean checkForWinner(BoardPosition lastPos);

}

