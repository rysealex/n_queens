import java.io.*;
import java.util.*;

public class N_QueensUser
{
    public static void main(String[] args)
    {
        new N_QueensUser().run();
    } // method main


    /**
     * Runs the program, asks user input for number of queens.
     * Prints out a successful or unsuccessful board.
     */
    public void run()
    {
        final String INPUT_PROMPT =
                "Enter the size of the game board: ";

        final String INPUT_INVALID =
                "Enter a number that is greater than zero! ";

        final String SUCCESS =
                "A solution has been found: ";

        final String FAILURE =
                "There is no solution: ";

        final String FINAL_STATE =
                "The final state is as follows (Q = QUEEN):\n";

        N_Queens n_queens = null;

        Scanner keyboard = new Scanner(System.in);

        int size;

        while (true)
        {
            try
            {
                System.out.print(INPUT_PROMPT);
                size = keyboard.nextInt();
                if (size < 1)
                {
                    System.out.println(INPUT_INVALID);
                    continue;
                }
                break;
            } // try
            catch (NumberFormatException e)
            {
                System.out.println(e);
            } // catch IOException
        } // while
        try
        {
            n_queens = new N_Queens(size);
            if (searchN_Queens(n_queens))
            {
                System.out.println(SUCCESS);
            }
            else
            {
                System.out.println(FAILURE);
            }
            System.out.println(FINAL_STATE + n_queens);
        } // try
        catch (RuntimeException e)
        {
            System.out.println(e);
        } // catch RuntimeException

    } // method run


    /**
     * Performs the n_queens search.
     *
     * @param n_queens - the n_queens board to be searched.
     *
     * @return true - if all queens are placed safely on the board; otherwise false
     */
    public boolean searchN_Queens(N_Queens n_queens)
    {
        BackTrack bt = new BackTrack(n_queens);
        for (int i = 0; i < n_queens.board.length; i++)
        {
            Position start = new Position(0, i);
            n_queens.markAsPossible(start);
            if (n_queens.isGoal(start) || bt.tryToReachGoal(start)) {
                return true;
            }
            n_queens.markAsDeadEnd(start);
        }
        return false;
    } // method searchN_Queens

} // class N_QueensUser