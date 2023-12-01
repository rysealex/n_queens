import org.junit.*;
import static org.junit.Assert.*;

import org.junit.rules.ExpectedException;
import org.junit.runner.Result;
import static org.junit.runner.JUnitCore.runClasses;

import java.sql.PseudoColumnUsage;
import java.util.*;
import java.io.*;

public class N_QueensTester {

    public static void main(String[] args)
    {
        Result result = runClasses(N_QueensTester.class);
        System.out.println("Tests run = " + result.getRunCount() +
                           "\nTests failed = " + result.getFailures());
    } // method main

    protected N_Queens n_queens;

    @Before
    public void createBoard()
    {
        n_queens = new N_Queens(4);
    } // method createBoard

    @Test
    public void constructorTest1()
    {
        n_queens = new N_Queens(1);

        int expected = 1;

        assertEquals(expected, n_queens.board.length);
    } // constructorTest1

    @Test
    public void constructorTest2()
    {
        n_queens = new N_Queens(5);

        int expected = 5;

        assertEquals(expected, n_queens.board.length);
    } // constructorTest2

    @Test
    public void getBoardTest1()
    {
        byte[][] expected = new byte[n_queens.board.length][n_queens.board[0].length];
        assertEquals(expected, n_queens.board);
    } // getBoardTest1

    @Test
    public void getBoardTest2()
    {
        n_queens.board = new byte[5][5];
        byte[][] expected = new byte[n_queens.board.length][n_queens.board[0].length];
        assertEquals(expected, n_queens.board);
    } // getBoardTest2

    @Test
    public void isOKTest1()
    {
        Position pos = new Position(0, 0);
        assertEquals(true, n_queens.isOK(pos));
    } // isOkTest1

    @Test
    public void isOkTest2()
    {
        Position pos = new Position(3, 3);
        assertEquals(true, n_queens.isOK(pos));
    } // isOkTest2

    @Test
    public void isOkTest3()
    {
        Position pos = new Position(0, 2);
        Iterator<Position> itr = n_queens.iterator(pos);
        Position next = itr.next();
        n_queens.markAsPossible(next);
        assertEquals(false, n_queens.isOK(next));
    } // isOkTest3

    @Test
    public void markAsPossibleTest1()
    {
        Position pos = new Position(0, 2);

        byte expected = n_queens.QUEEN;

        n_queens.markAsPossible(pos);
        assertEquals(expected, n_queens.getBoard()[0][2]);
    } // markAsPossibleTest1

    @Test
    public void markAsPossibleTest2()
    {
        Position pos = new Position(3,2);

        byte expected = n_queens.QUEEN;

        n_queens.markAsPossible(pos);
        assertEquals(expected, n_queens.getBoard()[3][2]);
    } // markAsPossibleTest2

    @Test
    public void isGoalTest1()
    {
        Position pos = new Position(0, 0);
        assertEquals(false, n_queens.isGoal(pos));
    } // isGoalTest1

    @Test
    public void isGoalTest2()
    {
        Position pos = new Position(3,3);
        assertEquals(false, n_queens.isGoal(pos));
    } // isGoalTest2

    @Test
    public void isGoalTest3()
    {
        Position pos = new Position(3, 2);
        n_queens.queenCount = n_queens.board.length;
        assertEquals(true, n_queens.isGoal(pos));
    } // isGoalTest3

    @Test
    public void markAsDeadEndTest1()
    {
        Position pos = new Position(0, 0);

        byte expected = n_queens.DEAD_END;

        n_queens.markAsDeadEnd(pos);
        assertEquals(expected, n_queens.getBoard()[0][0]);
    } // markAsDeadEndTest1

    @Test
    public void markAsDeadEndTest2()
    {
        Position pos = new Position(2, 1);

        byte expected = n_queens.DEAD_END;

        n_queens.markAsDeadEnd(pos);
        assertEquals(expected, n_queens.getBoard()[2][1]);
    } // markAsDeadEndTest2

    @Test
    public void hasNextTest1()
    {
        Position pos = new Position(0, 0);
        Iterator<Position> itr = n_queens.iterator(pos);
        assertEquals(true, itr.hasNext());
    } // hasNextTest1

    @Test
    public void hasNextTest2()
    {
        Position pos = new Position(1, 2);
        Iterator<Position> itr = n_queens.iterator(pos);
        assertEquals(true, itr.hasNext());
    } // hasNextTest2

    @Test
    public void hasNextTest3()
    {
        Position pos = new Position(3,3);
        Iterator<Position> itr = n_queens.iterator(pos);
        assertEquals(false, itr.hasNext());
    } // hasNextTest3

    @Test
    public void nextTest1()
    {
        Position pos = new Position(0, 3);
        Iterator<Position> itr = n_queens.iterator(pos);
        Position next = itr.next();
        assertEquals(0, next.getRow());
    } // nextTest1

    @Test
    public void nextTest2()
    {
        Position pos = new Position(2, 2);
        Iterator<Position> itr = n_queens.iterator(pos);
        Position next = itr.next();
        assertEquals(3, next.getColumn());
    } // nextTest2

    @Test
    public void toStringTest1()
    {
        n_queens = new N_Queens(0);

        String expected = "";

        assertEquals(expected, n_queens.toString());
    } // toStringTest1

    @Test
    public void toStringTest2()
    {
        n_queens = new N_Queens(2);

        String expected = n_queens.toString();

        assertEquals(expected, n_queens.toString());
    } // toStringTest2

    @Test
    public void iteratorTest1()
    {
        Position pos = new Position(0, 0);
        Iterator<Position> itr = n_queens.iterator(pos);
    } // iteratorTest1

    @Test
    public void iteratorTest2()
    {
        Position pos = new Position(2, 3);
        Iterator<Position> itr = n_queens.iterator(pos);
    } // iteratorTest2

    @Test
    public void N_QueensIteratorTest1()
    {
        Position pos = new Position(1, 1);
        assertEquals(1, pos.getRow());
    } // N_QueensIteratorTest1

    @Test
    public void N_QueensIteratorTest2()
    {
        Position pos = new Position(2, 3);
        assertEquals(3, pos.getColumn());
    } // N_QueensIteratorTest2

    @Test(expected = UnsupportedOperationException.class)
    public void removeTest1()
    {
        Position pos = new Position(0, 0);
        Iterator<Position> itr = n_queens.iterator(pos);
        itr.remove();
    } // removeTest1

    @Rule
    public ExpectedException exceptionRemoveTest2 = ExpectedException.none();

    @Test
    public void removeTest2()
    {
        Position pos = new Position(3, 3);
        Iterator<Position> itr = n_queens.iterator(pos);
        exceptionRemoveTest2.expect(UnsupportedOperationException.class);
        itr.remove();
    } // removeTest2

} // class N_QueensTester