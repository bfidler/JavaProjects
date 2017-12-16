package cpsc2150.hw2;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class TestGameBoardFast extends TestCase {

    private GameBoardMem board;
    private int BOARD_LW = 8;

    public String printBoardArr(char[][] arr) {
        //Creating a string to return
        String boardString = "   ";

        //labeling the columns
        for(int i = 0; i < BOARD_LW; i++) {
            if (i < 10)
                boardString = boardString + " " + i + "|";
            else
                boardString = boardString + i + "|";
        }

        //next line
        boardString = boardString + '\n';

        //Double loop to access each board space
        for(int i = 0; i < BOARD_LW; i++){
            //adding the row label to the string
            if (i < 10)
                boardString = boardString + " " + i;
            else
                boardString = boardString + i;

            for(int j = 0; j < BOARD_LW; j++){
                //preceding each board space with a bar
                boardString = boardString +'|' + " " + arr[i][j];
            }
            //adding final bar to the string and going to the next line of the
            //board
            boardString = boardString + ("|\n");
        }

        return boardString;
    }

    @Before
    public void setUp() {
        board = new GameBoardMem(8, 8, 5);
    }

    @After
    public void tearDown() { board = null;}

    @Test
    public void test_checkSpace_low_Row() {

        //Creating board position
        BoardPosition pos = new BoardPosition(0, -1, ' ');

        assertEquals("Testing Checkspace with a row that is too small.",
                false, board.checkSpace(pos));
    }

    @Test
    public void test_checkSpace_high_Row() {

        //Creating board position
        BoardPosition pos = new BoardPosition(8, 0, ' ');

        assertEquals("Testing Checkspace with a row that is too large.",
                false, board.checkSpace(pos));
    }

    @Test
    public void test_checkSpace_low_Col() {

        //Creating board position
        BoardPosition pos = new BoardPosition(0, -1, ' ');

        assertEquals("Testing Checkspace with row in bounds, but column too small.",
                false, board.checkSpace(pos));
    }

    @Test
    public void test_checkSpace_high_Col() {

        //Creating board position
        BoardPosition pos = new BoardPosition(0, 8, ' ');

        assertEquals("Testing Checkspace with row in bounds but column too large.",
                false, board.checkSpace(pos));
    }


    @Test
    public void test_checkSpace_hasX() {

        //Creating board position and placing on the board
        BoardPosition pos = new BoardPosition(0, 0, 'O');
        board.placeMarker(pos);

        assertEquals("Testing Checkspace with column and row in bounds, but" +
                " board already has a marker (X) there.", false,
                board.checkSpace(pos));
    }

    @Test
    public void test_checkSpace_isEmpty() {

        //Creating a board position but not placing it on the board
        BoardPosition pos = new BoardPosition(0, 0, 'X');

        assertTrue("Testing Checkspace with column and row in bound, and no" +
                "marker on board.", board.checkSpace(pos));
    }

    @Test
    public void test_placeMarker_0_0() {

        //Creating psuedo-board to check with real board
        char[][] boardArr = new char[BOARD_LW][BOARD_LW];
        for (int i = 0; i < BOARD_LW; i++)
            for (int j = 0; j < BOARD_LW; j++)
                boardArr[i][j] = ' ';
        //placing marker on fake board
        boardArr[0][0] = 'X';

        //placing marker on real board
        BoardPosition pos = new BoardPosition(0, 0, 'X');
        board.placeMarker(pos);

        assertEquals("Checking to see if 0,0 is placed correctly on the board",
                printBoardArr(boardArr), board.toString());
    }

    @Test
    public void test_placeMarker_0_7() {

        //Creating psuedo-board to check with real board
        char[][] boardArr = new char[BOARD_LW][BOARD_LW];
        for (int i = 0; i < BOARD_LW; i++)
            for (int j = 0; j < BOARD_LW; j++)
                boardArr[i][j] = ' ';
        //placing marker on fake board
        boardArr[0][7] = 'X';

        //placing marker on real board
        BoardPosition pos = new BoardPosition(0, 7, 'X');
        board.placeMarker(pos);

        assertEquals("Checking to see if 0,7 is placed correctly on the board",
                printBoardArr(boardArr), board.toString());
    }

    @Test
    public void test_placeMarker_7_0() {

        //Creating psuedo-board to check with real board
        char[][] boardArr = new char[BOARD_LW][BOARD_LW];
        for (int i = 0; i < BOARD_LW; i++)
            for (int j = 0; j < BOARD_LW; j++)
                boardArr[i][j] = ' ';
        //placing marker on fake board
        boardArr[7][0] = 'X';

        //placing marker on real board
        BoardPosition pos = new BoardPosition(7, 0, 'X');
        board.placeMarker(pos);

        assertEquals("Checking to see if 7,0 is placed correctly on the board",
                printBoardArr(boardArr), board.toString());
    }

    @Test
    public void test_placeMarker_7_7() {

        //Creating psuedo-board to check with real board
        char[][] boardArr = new char[BOARD_LW][BOARD_LW];
        for (int i = 0; i < BOARD_LW; i++)
            for (int j = 0; j < BOARD_LW; j++)
                boardArr[i][j] = ' ';
        //placing marker on fake board
        boardArr[7][7] = 'X';

        //placing marker on real board
        BoardPosition pos = new BoardPosition(7, 7, 'X');
        board.placeMarker(pos);

        assertEquals("Checking to see if 7,7 is placed correctly on the board",
                printBoardArr(boardArr), board.toString());
    }

    @Test
    public void test_placeMarker_3_2() {

        //Creating psuedo-board to check with real board
        char[][] boardArr = new char[BOARD_LW][BOARD_LW];
        for (int i = 0; i < BOARD_LW; i++)
            for (int j = 0; j < BOARD_LW; j++)
                boardArr[i][j] = ' ';
        //placing marker on fake board
        boardArr[3][2] = 'X';

        //placing marker on real board
        BoardPosition pos = new BoardPosition(3, 2, 'X');
        board.placeMarker(pos);

        assertEquals("Checking to see if 3,2 is placed correctly on the board",
                printBoardArr(boardArr), board.toString());
    }

    @Test
    public void test_placeMarker_2_6() {

        //Creating psuedo-board to check with real board
        char[][] boardArr = new char[BOARD_LW][BOARD_LW];
        for (int i = 0; i < BOARD_LW; i++)
            for (int j = 0; j < BOARD_LW; j++)
                boardArr[i][j] = ' ';
        //placing marker on fake board
        boardArr[2][6] = 'X';

        //placing marker on real board
        BoardPosition pos = new BoardPosition(2, 6, 'X');
        board.placeMarker(pos);

        assertEquals("Checking to see if 2,6 is placed correctly on the board",
                printBoardArr(boardArr), board.toString());
    }

    @Test
    public void test_checkForWinner_TopLeft_Horz_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(0, 0, 'X');
        BoardPosition pos2 = new BoardPosition(0, 1, 'X');
        BoardPosition pos3 = new BoardPosition(0, 2, 'X');
        BoardPosition pos4 = new BoardPosition(0, 3, 'X');
        BoardPosition pos5 = new BoardPosition(0, 4, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Top Left of board horizontally for a win with" +
                "5 pieces (placing 0,4 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_TopLeft_Horz_6() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(0, 0, 'X');
        BoardPosition pos2 = new BoardPosition(0, 1, 'X');
        BoardPosition pos3 = new BoardPosition(0, 2, 'X');
        BoardPosition pos4 = new BoardPosition(0, 3, 'X');
        BoardPosition pos5 = new BoardPosition(0, 4, 'X');
        BoardPosition pos6 = new BoardPosition(0, 5, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos5);
        board.placeMarker(pos6);
        //Winning Move
        board.placeMarker(pos4);

        assertTrue("Checking Top Left of board horizontally for a win with" +
                "6 pieces (placing 0,3 last)", board.checkForWinner(pos4));
    }

    @Test
    public void test_checkForWinner_TopLeft_Diag_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(0, 0, 'X');
        BoardPosition pos2 = new BoardPosition(1, 1, 'X');
        BoardPosition pos3 = new BoardPosition(2, 2, 'X');
        BoardPosition pos4 = new BoardPosition(3, 3, 'X');
        BoardPosition pos5 = new BoardPosition(4, 4, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Top Left of board diagonally for a win with" +
                "5 pieces (placing 4,4 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_TopLeft_Diag_6() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(0, 0, 'X');
        BoardPosition pos2 = new BoardPosition(1, 1, 'X');
        BoardPosition pos3 = new BoardPosition(2, 2, 'X');
        BoardPosition pos4 = new BoardPosition(3, 3, 'X');
        BoardPosition pos5 = new BoardPosition(4, 4, 'X');
        BoardPosition pos6 = new BoardPosition(5, 5, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos5);
        board.placeMarker(pos6);
        //Winning Move
        board.placeMarker(pos4);

        assertTrue("Checking Top Left of board diagonally for a win with" +
                "6 pieces (placing 3,3 last)", board.checkForWinner(pos4));
    }

    @Test
    public void test_checkForWinner_TopLeft_Vert_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(0, 0, 'X');
        BoardPosition pos2 = new BoardPosition(1, 0, 'X');
        BoardPosition pos3 = new BoardPosition(2, 0, 'X');
        BoardPosition pos4 = new BoardPosition(3, 0, 'X');
        BoardPosition pos5 = new BoardPosition(4, 0, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Top Left of board vertically for a win with" +
                "5 pieces (placing 4,0 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_TopLeft_Vert_6() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(0, 0, 'X');
        BoardPosition pos2 = new BoardPosition(1, 0, 'X');
        BoardPosition pos3 = new BoardPosition(2, 0, 'X');
        BoardPosition pos4 = new BoardPosition(3, 0, 'X');
        BoardPosition pos5 = new BoardPosition(4, 0, 'X');
        BoardPosition pos6 = new BoardPosition(5, 0, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos5);
        board.placeMarker(pos6);
        //Winning Move
        board.placeMarker(pos4);

        assertTrue("Checking Top Left of board vertically for a win with" +
                "6 pieces (placing 3,0 last)", board.checkForWinner(pos4));
    }

    @Test
    public void test_checkForWinner_BotLeft_Vert_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(3, 0, 'X');
        BoardPosition pos2 = new BoardPosition(4, 0, 'X');
        BoardPosition pos3 = new BoardPosition(5, 0, 'X');
        BoardPosition pos4 = new BoardPosition(6, 0, 'X');
        BoardPosition pos5 = new BoardPosition(7, 0, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Bottom Left of board vertically for a win with" +
                "5 pieces (placing 7,0 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_BotLeft_Horz_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(7, 0, 'X');
        BoardPosition pos2 = new BoardPosition(7, 1, 'X');
        BoardPosition pos3 = new BoardPosition(7, 2, 'X');
        BoardPosition pos4 = new BoardPosition(7, 3, 'X');
        BoardPosition pos5 = new BoardPosition(7, 4, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Bottom Left of board horizontally for a win with" +
                "5 pieces (placing 7,4 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_BotRight_Vert_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(3, 7, 'X');
        BoardPosition pos2 = new BoardPosition(4, 7, 'X');
        BoardPosition pos3 = new BoardPosition(5, 7, 'X');
        BoardPosition pos4 = new BoardPosition(6, 7, 'X');
        BoardPosition pos5 = new BoardPosition(7, 7, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Bottom Right of board vertically for a win with" +
                "5 pieces (placing 7,7 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_BotRight_Horz_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(7, 3, 'X');
        BoardPosition pos2 = new BoardPosition(7, 4, 'X');
        BoardPosition pos3 = new BoardPosition(7, 5, 'X');
        BoardPosition pos4 = new BoardPosition(7, 6, 'X');
        BoardPosition pos5 = new BoardPosition(7, 7, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Bottom Right of board horizontally for a win with" +
                "5 pieces (placing 7,7 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_BotRight_Diag_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(3, 3, 'X');
        BoardPosition pos2 = new BoardPosition(4, 4, 'X');
        BoardPosition pos3 = new BoardPosition(5, 5, 'X');
        BoardPosition pos4 = new BoardPosition(6, 6, 'X');
        BoardPosition pos5 = new BoardPosition(7, 7, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Bottom Right of board diagonally for a win with" +
                "5 pieces (placing 7,7 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_TopRight_Vert_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(0, 7, 'X');
        BoardPosition pos2 = new BoardPosition(1, 7, 'X');
        BoardPosition pos3 = new BoardPosition(2, 7, 'X');
        BoardPosition pos4 = new BoardPosition(3, 7, 'X');
        BoardPosition pos5 = new BoardPosition(4, 7, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Top Right of board vertically for a win with" +
                "5 pieces (placing 4,7 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_TopRight_Horz_5() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(0, 3, 'X');
        BoardPosition pos2 = new BoardPosition(0, 4, 'X');
        BoardPosition pos3 = new BoardPosition(0, 5, 'X');
        BoardPosition pos4 = new BoardPosition(0, 6, 'X');
        BoardPosition pos5 = new BoardPosition(0, 7, 'X');
        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);
        //Winning Move
        board.placeMarker(pos5);

        assertTrue("Checking Top Right of board horizontally for a win with" +
                "5 pieces (placing 0,7 last)", board.checkForWinner(pos5));
    }

    @Test
    public void test_checkForWinner_Middle_Horz_4() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(2, 3, 'X');
        BoardPosition pos2 = new BoardPosition(2, 4, 'X');
        BoardPosition pos3 = new BoardPosition(2, 5, 'X');
        BoardPosition pos4 = new BoardPosition(2, 6, 'X');

        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);

        assertEquals("Checking Middle of board horizontally for a win with" +
                "4 pieces (placing 2,7 last)", false, board.checkForWinner(pos4));
    }

    @Test
    public void test_checkForWinner_Middle_Vert_4() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(2, 3, 'X');
        BoardPosition pos2 = new BoardPosition(3, 3, 'X');
        BoardPosition pos3 = new BoardPosition(4, 3, 'X');
        BoardPosition pos4 = new BoardPosition(5, 3, 'X');

        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);

        assertEquals("Checking Middle of board vertically for a win with" +
                "4 pieces (placing 5,7 last)", false, board.checkForWinner(pos4));
    }

    @Test
    public void test_checkForWinner_Middle_Diag_4() {
        //Creating markers
        BoardPosition pos1 = new BoardPosition(2, 2, 'X');
        BoardPosition pos2 = new BoardPosition(3, 3, 'X');
        BoardPosition pos3 = new BoardPosition(4, 4, 'X');
        BoardPosition pos4 = new BoardPosition(5, 5, 'X');

        //Placing markers
        board.placeMarker(pos1);
        board.placeMarker(pos2);
        board.placeMarker(pos3);
        board.placeMarker(pos4);

        assertEquals("Checking Middle of board vertically for a win with" +
                "4 pieces (placing 5,5 last)", false, board.checkForWinner(pos4));
    }
}
