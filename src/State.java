public class State {
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

    public String toString()
    {
        String output = "";
        for(int i = 0; i < position.length; i++){
            for(int j = 0; j < position[i].length; j++)
            {
                int num = position[i][j];
                if(num == 0){
                    output += " ";
                } else {
                    output += num;
                }
                output += " ";
            }
            output += "\n";
        }
        return output;
    }
}
