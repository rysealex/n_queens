import java.util.*;

public interface Application
{
    /**
     * Determines if a given position is legal and doesn't interfere with other queens.
     * Checks the same column, main diagonal and ant-diagonal for other queens.
     *
     * @param pos - the given position.
     *
     * @return true if pos is a legal position and doesn't interfere with other queens.
     */
    boolean isOK (Position pos);


    /**
     * Indicates that a given position is possible position for a queen.
     *
     * @param pos the position that has been marked as possibly being a position
     *            for a queen.
     */
    void markAsPossible (Position pos);


    /**
     * Indicates whether a given position is a goal position, successfully placed all queens.
     *
     * @param pos the position that may or may not be safe for all queens to be placed on the board.
     *
     * @return true if pos is a goal position; false otherwise.
     */
    boolean isGoal (Position pos);


    /**
     * Indicates that a given position is not a possible position for a queen.
     *
     * @param pos the position that has been marked as not being a possible position
     *            for a queen.
     */
    void markAsDeadEnd (Position pos);


    /**
     * Converts this Application object into a String object.
     *
     * @return the String representation of this Application object.
     */
    String toString();


    /**
     * Produces an Iterator object that starts at a given position.
     *
     * @param pos the position the Iterator object starts at.
     *
     * @return an Iterator object that accesses the positions directly
     *               available from pos.
     */
    Iterator<Position> iterator (Position pos);

} // interface Application