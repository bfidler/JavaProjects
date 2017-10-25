package cpsc2150.hw2;
public class BoardPosition {

    //fields
    private int row;
    private int col;
    private char player;

    //Constructor
    /**
     *
     * @param r row of position
     * @param c column of position
     * @param playerChar character marker
     * @requires (0 <= r <= 7) and (0 <= c <= 7) and
     *  ((playerChar == 'X') or (playerChar == 'O'))
     * @ensures [GameBoard functions that use BoardPosition as a parameter do
     *  not break]
     */
    public BoardPosition(int r, int c, char playerChar) {

        row = r;
        col = c;
        player = playerChar;
    }

    /**
     *
     *@return the row of the coordinate
     */
    public int getRow(){

        return row;
    }

    /**
     *
     *@return the column of the coordinate
     */
    public int getColumn() {

        return col;
    }

    /**
     *
     * @return the character of the player (X or O)
     */
    public char getPlayer() {

        return player;
    }
}
