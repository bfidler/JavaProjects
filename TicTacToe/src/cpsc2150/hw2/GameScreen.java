package cpsc2150.hw2;
import java.util.*;
import java.lang.*;

public class GameScreen {

    public static void main(String[] args) {

        /*The boolean is for multiple games.
          Scanner for input.*/
        char keepPlaying;
        Scanner input = new Scanner(System.in);
        //Max and Min number of rows/columns
        final int MAX_SIZE = 100;
        final int MIN_SIZE = 3;


        /*This loops runs the first game, then runs until the user
          no longer wants to keep playing*/
        do {

            //Creating board variables
            char player1, player2, implement;
            int rows, cols, numToWin, boardSize;
            int piecesOnBoard = 0, playerTurn = 1;
            boolean gameWin, gameCat = false;
            IGameBoard ticTacToe;

            //Setting the various board variables
            rows = getNumRows(MIN_SIZE, MAX_SIZE);
            cols = getNumCols(MIN_SIZE, MAX_SIZE);
            numToWin = getNumToWin(rows, cols);
            boardSize = rows * cols;

            //Getting implementation
            do {
                //Asks for a fast or memory efficient implementation
                System.out.println("Enter F for a (F)ast Implementation or " +
                    "M for a (M)emory Efficient Implementation");
                implement = input.nextLine().charAt(0);

                //automatically converts the input to uppercase
                implement = Character.toUpperCase(implement);

                //Prints an error message if not an f or m
                if (implement != 'F' && implement != 'M')
                    System.out.println("Error...");

            } while (implement != 'F' && implement != 'M');

            //Setting implementation
            if (implement == 'F')
                ticTacToe = new GameBoardFast(rows, cols, numToWin);
            else
                ticTacToe = new GameBoardMem(rows, cols, numToWin);

            //Getting player chars
            do {
                //Asks for the symbol for the 1st player, and reads it in
                System.out.println("What character is Player 1: 'X' or 'O'?");
                player1 = input.nextLine().charAt(0);

                //automatically converts the input to uppercase
                player1 = Character.toUpperCase(player1);

                //Prints an error message if p1 does not enter an X or O
                if (player1 != 'X' && player1 != 'O')
                    System.out.println("Error invalid input. Please enter 'X'" +
                            "or 'O'");

            } while (player1 != 'X' && player1 != 'O');

            //the character of p2 is set to the char p1 is not (x or o)
            if (player1 == 'X')
                player2 = 'O';
            else
                player2 = 'X';

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
                piecesOnBoard++;
                gameWin = ticTacToe.checkForWinner(lastMove);

                //If game isn't won...
                if (!gameWin) {

                    //The turn of the player is alternated
                    playerTurn = (playerTurn == 1) ? 2 : 1;
                    //gameCat becomes true if the board reaches capacity
                    gameCat = (piecesOnBoard == boardSize);
                }

            } while (!gameCat && !gameWin);

            //Congratulation message for winner or tie message
            if (gameWin)
                System.out.println("Congrats Player " + playerTurn +
                        ", you have won!");
            else
                System.out.println("No Player got " + numToWin +
                        " in a row - Cat (tie)");


            //loops until the user gives a proper response
            do {
                //Asking the user if he wants to keep playing
                System.out.println("Would you like to play again(y or n)?");
                keepPlaying = input.nextLine().charAt(0);

                //converting response to uppercase
                keepPlaying = Character.toUpperCase(keepPlaying);

                //Error message if user does not enter proper input
                if (keepPlaying != 'N' && keepPlaying != 'Y')
                    System.out.println("Error, Invalid Input. Please enter " +
                        "a Y or N");

            } while(keepPlaying != 'Y' && keepPlaying != 'N');

        } while(keepPlaying == 'Y');
    }

    /**
     *
     * @param player is the character that the current player will put on the
     *               board
     * @param tttBoard is the board that player is being placed upon
     * @requires [player has an X or O; tttBoard has at least one position
     *  available]
     * @ensures [a valid position is returned]
     * @return the move of the player
     */
    private static BoardPosition getMove(char player, IGameBoard tttBoard) {

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
                System.out.println("That position is unavailable. Please " +
                    "enter a valid location.");

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
     * @requires [function call on integer]
     * @ensures [row is an integer]
     * @return row, which is the row input from the user
     */
    private static int getRowInput() {

        int row = 0;
        boolean hasRow = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the row of your move.");

        //Runs until a row is entered by the user
        do {
            //Checking to see if user entered an integer
            if(input.hasNextInt()) {
                row = input.nextInt();
                hasRow = true;
            }
            //Printing error and clearing scanner if not an integer
            else {
                System.out.println("Error, please enter an integer value " +
                        " for the row.");
                input.next();
            }
        } while (!hasRow);

        return row;
    }

    /**
     * @requires [function call on an integer]
     * @ensures [col is an integer]
     * @return col, which is the column input from the user
     */
    private static int getColInput() {

        int col = 0;
        boolean hasCol = false;
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the column of your move.");

        //Runs until a column is entered by the user
        do {
            //Checking to see if user entered an integer
            if(input.hasNextInt()) {
                col = input.nextInt();
                hasCol = true;
            }
            //Printing error and clearing buffer if not
            else {
                System.out.println("Error, please enter an integer value " +
                        " for the column.");
                input.next();
            }
        } while (!hasCol);

        return col;
    }

    /**
     *
     * @param maxSize is the largest number of rows allowed
     * @param minSize is the smallest number of rows allowed
     * @requires minSize >= 0 && minSize <= maxSize
     * @ensures minSize <= rows <= maxSize
     * @return the number of rows for a board
     */
    private static int getNumRows(int minSize, int maxSize) {

        int rows = -1;
        Scanner input = new Scanner(System.in);

        //Runs until number of rows is entered by a user
        do {
            System.out.println("How many rows should be on the board?");

            //Checking for integer input
            if (input.hasNextInt()) {

                rows = input.nextInt();
                //Checking if rows is between min and max
                if (!(rows >= minSize && rows <= maxSize))
                    System.out.println("Error, can only have between " +
                        minSize + " and " + maxSize + " rows.");
            }
            //Printing error and clearing buffer if not an integer
            else {
                System.out.println("Error, not an integer");
                input.next();
            }
        } while (!(rows >= minSize && rows <= maxSize));

        return rows;
    }

    /**
     *
     * @param maxSize is the largest number of columns allowed
     * @param minSize is the smallest number of columns allowed
     * @requires minSize >= 0 && minSize <= maxSize
     * @ensures minSize <= cols <= maxSize
     * @return the number of columns for a board
     */
    private static int getNumCols(int minSize, int maxSize){

        int cols = -1;
        Scanner input = new Scanner(System.in);

        //Runs until number of columns is entered by user
        do {
            System.out.println("How many columns should be on the board?");

            //checking for integer input
            if (input.hasNextInt()) {

                cols = input.nextInt();
                //Checking if cols is between min an max
                if (!(cols >= minSize && cols <= maxSize))
                    System.out.println("Error, can only have between " +
                            minSize + " and " + maxSize + " columns.");
            }
            //Printing error and clearing buffer if not an integer
            else {
                System.out.println("Error, not an integer");
                input.next();
            }
        } while (!(cols >= minSize && cols <= maxSize));

        return cols;
    }

    /**
     *
     * @param rows is the number of rows on a board
     * @param cols is the number of columns on a board
     * @requires rows >= 2 && cols >= 2
     * @ensures num > 1 && num <= rows && num <= cols
     * @return (the number in a row a player needs to win)
     */
    private static int getNumToWin(int rows, int cols) {

        int num = -1;
        Scanner input = new Scanner(System.in);

        //Runs until user has entered the number to win
        do {
            System.out.println("How many in a row to win?");

            //Checking for integer input
            if (input.hasNextInt()) {

                num = input.nextInt();
                /*Checking to make sure num is more than 1 but less than
                    rows and cols*/
                if (!(num > 1 && num <= rows && num <= cols))
                    System.out.println("Number in a row must be less" +
                            " than rows and columns and greater than 1.");
            }
            //printing error and clearing buffer if not integer
            else {
                System.out.println("Error, please enter an integer");
                input.next();
            }
        } while (!(num > 1 && num <= rows && num <= cols));

        return num;
    }
}

