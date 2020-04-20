import java.awt.*;
import java.util.ArrayList;

public class Board {

    private Dimension size;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;



    private final int[][] position;
    private Board previous;
    private int numMoves;

    private int row0;
    private int col0;

    /**
     * construct a board from an N-by-N array of tiles
     * @param tiles
     */
    public Board(int[][] tiles) {
        this.position = tiles;
        previous = null;
        numMoves = 0;


        size = new Dimension(tiles.length, tiles[0].length);

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

    private Board(Board prev, int dir){
        position = new int[prev.position.length][];
        for (int i = 0; i < position.length; i++) {
            position[i] = new int[prev.position[i].length];
            System.arraycopy(prev.position[i], 0, position[i], 0, position[i].length);
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
        size = prev.size;
    }



    public Dimension getSize(){
        return size;
    }

    /**
     * heuristic for board completion
     * @return number of blocks out of place (not counting blank)
     */
    public int hamming()
    {
        return 0;
    }

    /**
     * heuristic for board completion
     * @return sum of Manhattan distances between blocks and goal (not counting blank)
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
        if(position.length != other.position.length || position[0].length != other.position[0].length)
            return false;

        for(int i = 0; i < position.length; i++) {
            for(int j = 0; j < position[i].length; j++) {
                if(position[i][j] != other.position[i][j])
                    return false;
            }
        }

        return true;
    }

    /**
     *
     * @return an ArrayList of all neighboring board positions
     */
    public ArrayList<Board> neighbors()
    {
        ArrayList<Board> neighbors = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            if(canMove(i))
            {
                Board s = new Board(this,i);
                if(!s.equals(previous))
                    neighbors.add(s);
            }
        }
        return neighbors;
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
        board = board.next(Board.DOWN);
        System.out.println(board);
        board = board.next(Board.DOWN);
        System.out.println(board);
        board = board.next(Board.LEFT);
        System.out.println(board);
        board = board.next(Board.RIGHT);
        System.out.println(board);
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

        public Board next(int dir){
            if(!canMove(dir))
                return this;
            return new Board(this, dir);
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
