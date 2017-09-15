package TicTacToe;
import java.util.*;
import java.lang.*;
import TicTacToe.BoardPosition;
import TicTacToe.GameBoard;

public class GameScreen {

    //for reading input in each main/getMove functions

    public static void main(String[] args) {

        /*The chars are the Xs and Os.
          The boolean is for multiple games.
          Scanner for input.*/
        char player1, player2;
        char keepPlaying = 'Y';
        Scanner input = new Scanner(System.in);

        //This loop runs until player1 has picked an x or an o
        do {
            //Asks for the symbol for the 1st player, and reads it in
            System.out.println("What character is Player 1: 'X' or 'O'?");
            player1 = input.nextLine().charAt(0);

            //automatically converts the input to uppercase
            player1 = Character.toUpperCase(player1);

            //Prints an error message if player 1 does not enter an X or O
            if (player1 != 'X' && player1 != 'O')
                System.out.println("Error invalid input. Please enter 'X'" +
                        "or 'O'");

        } while (player1 != 'X' && player1 != 'O');

        //the character of player2 is set to the char player1 is not (x or o)
        if (player1 == 'X')
            player2 = 'O';
        else
            player2 = 'X';


        /*This loops runs the first game, then runs until the user
          no longer wants to keep playing*/
        do {
            /*setting/resetting board pieces and player turn
              setting/resetting gameWin and Tie vars
              Creating a new GameBoard.*/
            int boardPieces = 0, playerTurn = 1;
            boolean gameWin = false, gameCat = false;
            GameBoard ticTacToe = new GameBoard();

            //printing players and the game board
            System.out.println("Player 1 is " + player1);
            System.out.println("Player 2 is " + player2 + '\n');
            System.out.println(ticTacToe.toString());

            //This loop runs until the game has been won or tied(cat)
            do {
                //The turn of the current player stated
                System.out.println("Player " + playerTurn + ", it is your turn.");
                /*GetMove is called. The board and the character of the current
                  players turn is passed. A position is successfully made then passed
                  to lastMove. The board is also reprinted*/
                BoardPosition lastMove = getMove(playerTurn == 1 ?
                        player1 : player2, ticTacToe);

                //the number of pieces is updated and we check for a win
                boardPieces++;
                gameWin = ticTacToe.checkForWinner(lastMove);

                //If game isn't won...
                if (!gameWin) {

                    //The turn of the player is alternated
                    playerTurn = (playerTurn == 1) ? 2 : 1;
                    //gameCat becomes true if the board reaches capacity
                    gameCat = (boardPieces == GameBoard.MAX_BOARD_SPACES);
                }

            } while (!gameCat && !gameWin);

            //Congratulation message for winner or tie message
            if (gameWin)
                System.out.println("Congrats Player " + playerTurn +
                        ", you have won!");
            else
                System.out.println("No Player got five in a row - Cat (tie)");


            //loops until the user gives a proper response
            do {
                //Asking the user if he wants to keep playing
                System.out.println("Would you like to play again(y or n)?");
                keepPlaying = input.nextLine().charAt(0);

                //converting response to uppercase
                keepPlaying = Character.toUpperCase(keepPlaying);

                //Error message if user does not enter proper input
                if (keepPlaying != 'N' && keepPlaying != 'Y')
                    System.out.println("Error, Invalid Input. Please enter" +
                        "a Y or N");

            } while(keepPlaying != 'Y' && keepPlaying != 'N');

        } while(keepPlaying == 'Y');
    }

    /*This function asks the user for board position, then places a piece
      on the board (after ensuring it's an available move). It returns the
     position that was used afterwords. */
    private static BoardPosition getMove(char player, GameBoard tttBoard) {

        BoardPosition move;

        //This loop runs until the user gives a position that is available
        do {
            /*A position is created using the getRow/Col inputs and the
            current player*/
            BoardPosition moveTry = new BoardPosition(getRowInput(),
                    getColInput(), player);

            /*If the position isn't available on the board,
              an error message is printed*/
            if(!tttBoard.checkSpace(moveTry))
                System.out.println("That space is already taken. Please " +
                    "enter another location.");

            /*moveTry is copied to the position created outside the loop,
              so it can be used in the while statement and returned(if the
              while statement is false. */
            move = moveTry;

        }while(!tttBoard.checkSpace(move));

        //The position is placed on the board, and the board is printed.
        tttBoard.placeMarker(move);
        System.out.println(tttBoard.toString());

        return move;
    }

    /**
     * @ensures (0 <=row <= 7)
     * @return row, which is the row input from the user
     */
    private static int getRowInput() {

        int row = -1;
        char inputRow;
        Scanner input = new Scanner(System.in);

        //Runs until the user has given correct input
        do {
            System.out.println("Please enter the row of your move" +
                    "(0 - 7)");

            //Tries to read an integer from the user
            inputRow = input.nextLine().charAt(0);

            //checking to see if char is withing range before parsing
            if (inputRow < '0' || inputRow > '7')
                System.out.println("Error, invalid input.");
            else
                row = Character.getNumericValue(inputRow);


        } while (row == -1);

        return row;
    }

    /**
     * @ensures (0 <= col <= 7)
     * @return col, which is the column input from the user
     */
    private static int getColInput() {

        int col = -1;
        char inputCol;
        Scanner input = new Scanner(System.in);

        //Runs until the user has given correct input
        do {
            System.out.println("Please enter the column of your move" +
                    "(0 - 7)");

            //Tries to read an integer from the user
            inputCol = input.nextLine().charAt(0);

            //checking to see if char is withing range before parsing
            if (inputCol < '0' || inputCol > '7')
                System.out.println("Error, invalid input.");
            else
                col = Character.getNumericValue(inputCol);


        } while (col == -1);

        return col;
    }

}
