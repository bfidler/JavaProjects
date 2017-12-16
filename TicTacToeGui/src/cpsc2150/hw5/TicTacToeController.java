package cpsc2150.hw5;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and one of the IGameBoard implementations from Homework 4
 * You can choose which IGameBoard implementation to use
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class besides the package name
 */
public class TicTacToeController {
    //our current game that is being played
    private IGameBoard curGame;
    //to track who's turn it is
    private char curPlayer;
    //The screen that provides our view
    private TicTacToeView screen;
    // no other class variables are needed or should be declared


    /**
     *
     * @param model the GameBoard which is being used to run the game
     * @param view the display that allows the user to interact with the game
     * @requires [model and view have been initialized and are empty/clear]
     * @ensures curGame = model && screen = view && curPlayer = 'X'
     */
    TicTacToeController(IGameBoard model, TicTacToeView view)
    {
        this.curGame = model;
        this.screen = view;
        //first message in view says player x is first
        curPlayer = 'X';
    }

    //Add the code to respond to a user clicking on a button to try to claim a space
    //User will click on the button at row, col
    //User may click on a space that is not available, they do not get that space
    //We no longer have a main function that controls the flow of the game. Users will click buttons, anf
    //Will handle the events here
    // Make sure to make any changes to the screen needed through publicly available functions
    // When a player wins, display a message. You do not need to reset the game or close the window
    // The players can close the window and restart the game themselves
    // remember your javadoc comments and contracts

    /**
     *
     * @param row is the row of the button press
     * @param col is the column of the button press
     * @requires [Game has not been won already]
     * @ensures [Current player makes a valid move and it is placed on the board
     *  whilst being checked for a win]
     */
    public void processButtonClick(int row, int col)
    {
        BoardPosition test = new BoardPosition(row,col,curPlayer);

        if (curGame.checkSpace(test)) {

            //placing marker on model and view
            curGame.placeMarker(test);
            screen.setMarker(row, col, curPlayer);

            //prints winning message if move won the game
            if(curGame.checkForWinner(test)) {
                screen.setMessage("Congratulations, Player " + curPlayer +
                        "! You have won!");
            }
            //swaps user turn if not
            else {
                if (curPlayer == 'X')
                    curPlayer = 'O';
                else
                    curPlayer = 'X';

                screen.setMessage("It is " + curPlayer + "'s turn.");
            }
        }
        //Prints error if check space fails
        else {
            screen.setMessage("Position is already occupied. Choose another.");
        }
    }
}
