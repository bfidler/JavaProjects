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
     * @require playerChar == 'X' || playerChar == 'O'
     * @ensures [GameBoardFast functions that use BoardPosition as a parameter do
     *  not break]
     */
    public BoardPosition(int r, int c, char playerChar) {

        row = r;
        col = c;
        player = playerChar;
    }

    /**
     *@require [row has been initialized]
     *@ensures [integer value of row is returned]
     *@return the row of the coordinate
     */
    public int getRow(){

        return row;
    }

    /**
     *@require [col has been initialized]
     *@ensures [integer value of col is returned]
     *@return the column of the coordinate
     */
    public int getColumn() {

        return col;
    }

    /**
     * @requires [player has been intialized]
     * @ensures [X or O is returned]
     * @return the character of the player (X or O)
     */
    public char getPlayer() {

        return player;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof BoardPosition) {

            BoardPosition pos = (BoardPosition) o;
            return (pos.getPlayer() == this.getPlayer() &&
                    pos.getRow() == this.getRow() &&
                    pos.getColumn() == this.getColumn());
        }
        else
            return false;
    }
}
