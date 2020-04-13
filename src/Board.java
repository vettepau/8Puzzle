import java.awt.*;
import java.util.ArrayList;

public class Board {

    private State state;
    private final Dimension size;

    /**
     * construct a board from an N-by-N array of tiles
     * @param tiles
     */
    public Board(int[][] tiles)
    {
        state = new State(tiles);
        size = new Dimension(tiles.length, tiles[0].length);
    }

    public Dimension getSize(){
        return size;
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

    public String get(int i, int j) {
        return state.get(i,j);
    }

    public static class State {
        public static final int UP = 0;
        public static final int RIGHT = 1;
        public static final int LEFT = 2;
        public static final int DOWN = 3;



        private int[][] position;
        private State previous;
        private int numMoves;

        private int row0;
        private int col0;

        public State(int[][] tiles) {
            this.position = tiles;
            previous = null;
            numMoves = 0;

            for (int i = 0; i < position.length; i++) {
                for(int j = 0; j < position[i].length; j++){
                    if(position[i][j] == 0)
                    {
                        row0 = i;
                        col0 = j;
                    }
                }
            }
        }

        private State(State prev, int dir){
            position = new int[prev.position.length][];
            for (int i = 0; i < position.length; i++) {
                position[i] = new int[prev.position[i].length];
                for (int j = 0; j < position[i].length; j++) {
                    position[i][j] = prev.position[i][j];
                }
            }
            switch (dir){
                case UP:
                    position[prev.row0][prev.col0] = position[prev.row0-1][prev.col0];
                    position[prev.row0-1][prev.col0] = 0;
                    row0 = prev.row0 - 1;
                    col0 = prev.col0;
                    break;
                case DOWN:
                    position[prev.row0][prev.col0] = position[prev.row0+1][prev.col0];
                    position[prev.row0+1][prev.col0] = 0;
                    row0 = prev.row0 + 1;
                    col0 = prev.col0;
                    break;
                case LEFT:
                    position[prev.row0][prev.col0] = position[prev.row0][prev.col0-1];
                    position[prev.row0][prev.col0-1] = 0;
                    row0 = prev.row0;
                    col0 = prev.col0 - 1;
                    break;
                case RIGHT:
                    position[prev.row0][prev.col0] = position[prev.row0][prev.col0+1];
                    position[prev.row0][prev.col0+1] = 0;
                    row0 = prev.row0;
                    col0 = prev.col0 + 1;
                    break;
            }


            previous = prev;
            numMoves = prev.numMoves + 1;
        }

        public boolean canMove(int dir){
            switch (dir)
            {
                case UP:
                    return row0 != 0;
                case DOWN:
                    return row0 != position.length - 1;
                case LEFT:
                    return col0 != 0;
                case RIGHT:
                    return col0 != position[row0].length - 1;
                default:
                    return false;
            }
        }

        public State nextState(int dir){
            if(!canMove(dir))
                return null;
            return new State(this, dir);
        }

        public String get(int i, int j){
            int num = position[i][j];
            if(num == 0){
                return " ";
            } else {
                return num + "";
            }
        }

        public String toString()
        {
            String output = "";
            for(int i = 0; i < position.length; i++){
                for(int j = 0; j < position[i].length; j++)
                {
                    output += get(i,j) +  " ";
                }
                output += "\n";
            }
            return output;
        }
    }
}
