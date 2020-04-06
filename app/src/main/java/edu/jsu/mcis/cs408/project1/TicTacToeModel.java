package edu.jsu.mcis.cs408.project1;

import android.widget.Button;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TicTacToeModel {

    public static final int DEFAULT_SIZE = 3;

    private Mark[][] grid;      /* the game grid */
    private boolean xTurn;      /* is TRUE if X is the current player */
    private int size;           /* the size (width and height) of the game grid */

    private TicTacToeController controller;

    protected PropertyChangeSupport propertyChangeSupport;

    public TicTacToeModel(TicTacToeController controller, int size) {

        this.size = size;
        //this.controller = controller;
        propertyChangeSupport = new PropertyChangeSupport(this);

        resetModel(size);

    }

    public void resetModel(int size) {

        //
        // This method resets the Model to its default state.  It should (re)initialize the size of
        // the grid, (re)set X as the current player, and create a new grid array of Mark objects,
        // initially filled with empty marks.
        //

        this.size = size;
        this.xTurn = true;

        /* Create grid (width x width) as a 2D Mark array */

        // INSERT YOUR CODE BELOW
        grid = new Mark[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = Mark.EMPTY;
            }
        }

        //

    }

    public boolean setMark(TicTacToeSquare square) {

        //
        // This method accepts the target square as a TicTacToeSquare argument, and adds the
        // current player's mark to this square.  First, it should use "isValidSquare()" to check if
        // the specified square is within range, and then it should use "isSquareMarked()" to see if
        // this square is already occupied!  If the specified location is valid, make a mark for the
        // current player, then use "firePropertyChange()" to fire the corresponding property change
        // event, which will inform the Controller that a change of state has taken place which
        // requires a change to the View.  Finally, toggle "xTurn" (from TRUE to FALSE, or vice-
        // versa) to switch to the other player.  Return TRUE if the mark was successfully added to
        // the grid; otherwise, return FALSE.
        //

        int row = square.getRow();
        int col = square.getCol();


        // INSERT YOUR CODE BELOW

        isValidSquare(row, col);
        if (isValidSquare(row, col) == true) {
            isSquareMarked(row, col);
        }


        if (isSquareMarked(row, col) == false) {
            if (xTurn) {
                grid[row][col] = Mark.X;
                xTurn = !xTurn;
                return true;
            } else {
                grid[row][col] = Mark.O;
            }

        } else {
            return false;
        }
        return false;
    }


    private boolean isValidSquare(int row, int col) {

        // This method should return TRUE if the specified location is within bounds of the grid

        if ((row >= getSize() || col >= getSize()) || (row < 0 || col < 0)){

            return false;
        }

        else {
            return true;
        }

    }


    private boolean isSquareMarked(int row, int col) {

        // This method should return TRUE if the square at the specified location is already marked

        if (grid[row][col] != Mark.EMPTY) {
            return true;
        }
        else {
            return false;
        }
    }


    public Mark getMark(int row, int col) {

        // This method should return the Mark from the square at the specified location

        return grid[row][col];

    }

    public Result getResult() {

        //
        // This method should return a Result value indicating the current state of the game.  It
        // should use "isMarkWin()" to see if X or O is the winner, and "isTie()" to see if the game
        // is a TIE.  If neither condition applies, return a default value of NONE.
        //

        if (isTie()){
            return Result.TIE;
        }
        else {
            if (!xTurn) {
                if (isMarkWin(Mark.X)){
                    return Result.X;
                }
                else {
                    return Result.NONE;
                }

            } else {
                if (isMarkWin(Mark.O)){
                    return Result.O;
                }
                else {
                    return Result.NONE;
                }
            }
        }


    }


    private boolean isMarkWin(Mark mark) {

        //
        // This method should check the squares of the grid to see if the specified Mark is the
        // winner.  (Hint: this method must check for complete rows, columns, and diagonals, using
        // an algorithm which will work for all possible grid sizes!)
        //

        /*
       switch(grid[row][col]) {
           //horizontal
           case grid[0][0] == grid[0][1] && grid[0][0] == grid[0][2]:
               return true;
           case (grid[1][0] == grid[1][1] && grid[1][0] == grid[1][2]):
               return true;
           case grid[2][0] == grid[2][1] && grid[2][0] == grid[2][2]:
               return true;

           //vertical
           case (grid[0][0] == grid[1][0] && grid[0][0] == grid[2][0]):
               return true;
           case (grid[0][1] == grid[1][1] && grid[0][1] == grid[2][1]):
               return true;
           case (grid[0][2] == grid[1][2] && grid[0][2] == grid[2][2]):
               return true;

           //diagonal
           case (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]):
               return true;
           case (grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) :
               return true;
            break;
       }

*/


        return false;
    }

    private boolean isTie() {

        //
        // This method should check the squares of the grid to see if the game is a tie.
        //
        boolean isEmpty = false;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (grid[i][j] == Mark.EMPTY) {
                    isEmpty = true;
                }
            }

        }
       if (isEmpty) {
           return false;
       }
       else {
           return true;
       }
    }

    public boolean isXTurn() {

        // Getter for "xTurn"
        return xTurn;

    }

    public int getSize() {

        // Getter for "size"
        return size;

    }

    // Property Change Methods (adds/removes a PropertyChangeListener, or fires a property change)

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /* ENUM TYPE DEFINITIONS */

    // Mark (represents X, O, or an empty square)

    public enum Mark {

        X("X"),
        O("O"),
        EMPTY("-");

        private String message;

        private Mark(String msg) {
            message = msg;
        }

        @Override
        public String toString() {
            return message;
        }

    };

    // Result (represents the game state: X wins, O wins, a TIE, or NONE if the game is not over)

    public enum Result {

        X("X"),
        O("O"),
        TIE("TIE"),
        NONE("NONE");

        private String message;

        private Result(String msg) {
            message = msg;
        }

        @Override
        public String toString() {
            return message;
        }

    };

}