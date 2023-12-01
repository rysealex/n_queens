import java.awt.*;
import java.util.*;

public class N_Queens implements Application
{
    public static final byte DEAD_END = 0;
    public static final byte QUEEN = (byte) 'Q';

    public int numQueens;
    public int queenCount;
    public byte[][] board;


    /**
     * Initializes this N_Queens object from user input.
     *
     * @param numQueens - the number of queens.
     */
    public N_Queens(int numQueens)
    {
        this.numQueens = numQueens;
        this.queenCount = 0;
        this.board =  new byte[numQueens][numQueens];
    } // constructor


    /**
     * Returns a 2-D array that holds a copy of the board configuration.
     *
     * @return - a 2-D array that holds a copy of the board configuration.
     */
    public byte[][] getBoard()
    {
        byte[][] boardCopy = new byte[board.length][board[0].length];

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    } // method getBoard


    /**
     * Determines if a given position is legal and doesn't interfere with other queens.
     * Checks the same column, main diagonal and ant-diagonal for other queens.
     *
     * @param pos - the given position.
     *
     * @return true if pos is a legal position and doesn't interfere with other queens.
     */
    public boolean isOK(Position pos)
    {
        int row = pos.getRow();
        int col = pos.getColumn();

        // check for queens in the same column
        for (int r = 0; r < board.length; r++)
        {
            if (board[r][col] == QUEEN)
            {
                return false;
            }
        }
        // check for queens in the same main diagonal (top left - bottom right)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
        {
            if (board[i][j] == QUEEN)
            {
                return false;
            }
        }
        // check for queens in the same anti-diagonal (top right - bottom left)
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++)
        {
            if (board[i][j] == QUEEN)
            {
                return false;
            }
        }
        return true;
    } // method isOk


    /**
     * Indicates that a given position is a possible position for a queen.
     *
     * @param pos the position that has been marked as possibly being a position
     *            for a queen.
     */
    public void markAsPossible(Position pos)
    {
        board[pos.getRow()][pos.getColumn()] = QUEEN;
        queenCount++;
    } // method markAsPossible


    /**
     * Indicates whether a given position is a goal position, successfully placed all queens.
     *
     * @param pos the position that may or may not be safe for all queens to be placed on the board.
     *
     * @return true if pos is a goal position; false otherwise.
     */
    public boolean isGoal(Position pos)
    {
        return queenCount == numQueens;
    } // method isGoal


    /**
     * Indicates that a given position is not a possible position for a queen.
     *
     * @param pos the position that has been marked as not being a possible position
     *            for a queen.
     */
    public void markAsDeadEnd(Position pos)
    {
        board[pos.getRow()][pos.getColumn()] = DEAD_END;
        queenCount--;
    } // method markAsDeadEnd


    /**
     * Converts this Application object into a String object.
     *
     * @return the String representation of this Application object.
     */
    public String toString()
    {
        String result = "";

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                result += (char) board[row][col] + " ";
            }
            result += "\n";
        }
        return result;
    } // method toString


    /**
     * Produces an Iterator object that starts at a given position.
     *
     * @param pos the position the Iterator object starts at.
     *
     * @return an Iterator object that accesses the positions directly
     *               available from pos.
     */
    public Iterator<Position> iterator(Position pos)
    {
        return new N_QueensIterator(pos);
    } // method iterator


    protected class N_QueensIterator implements Iterator<Position>
    {
        protected int row, column;


        /**
         * Initializes this N_QueensIterator object to start at a given position.
         *
         * @param pos the position the Iterator object starts at.
         */
        public N_QueensIterator(Position pos)
        {
            row = pos.getRow();
            column = pos.getColumn();
        } // constructor


        /**
         * Determines if this N_QueensIterator object can advance to another
         * position.
         *
         * @return true if this N_QueensIterator object can advance; false otherwise.
         */
        public boolean hasNext()
        {
            return board[row][column] == QUEEN && row < board.length - 1
                    || column < board.length - 1;
        } // method hasNext


        /**
         * Advances this N_QueensIterator object to the next position.
         *
         * @return the position advanced to.
         */
        public Position next()
        {
            if (board[row][column] == QUEEN)
            {
                row++;
                column = 0;
            }
            else
            {
                column++;
            }
            return new Position(row, column);
        } // method next


        /**
         * Throws new UnsupportedOperationException.
         */
        public void remove()
        {
            // removal is illegal for an N_QueensIterator object
            throw new UnsupportedOperationException();
        } // method remove

    } // class N_QueensIterator

} // class N_Queens
