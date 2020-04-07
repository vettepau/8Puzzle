import java.util.ArrayList;

public class Board {

    private State state;

    /**
     * construct a board from an N-by-N array of tiles
     * @param tiles
     */
    public Board(int[][] tiles)
    {
        state = new State(tiles);
    }

    /**
     *
     * @return number of blocks out of place
     */
    public int hamming()
    {
        return 0;
    }

    /**
     *
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan()
    {
        return 0;
    }

    /**
     *
     * @param other
     * @return does this board equal other
     */
    public boolean equals(Board other)
    {
        return false;
    }

    /**
     *
     * @return an ArrayList of all neighboring board positions
     */
    public ArrayList<Board> neighbors()
    {
        return null;
    }


    public boolean move(int direction)
    {
        State newState = state.nextState(direction);
        if(newState == null)
            return false;
        state = newState;
        return true;
    }

    /**
     *
     * @return a string representation of the board
     */
    public String toString()
    {
        return state.toString();
    }

    /**
     * test client
     * @param args
     */
    public static void main(String[] args)
    {
        int[][] testBoard = {
                {1,2,3},
                {4,0,6},
                {7,8,5}
        };
        Board board = new Board(testBoard);
        System.out.println(board);
        board.move(State.DOWN);
        System.out.println(board);
        board.move(State.DOWN);
        System.out.println(board);
        board.move(State.LEFT);
        System.out.println(board);
        board.move(State.RIGHT);
        System.out.println(board);
    }
}
