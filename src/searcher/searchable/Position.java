package searcher.searchable;

public class Position {

    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        Position p = (Position)obj;

        return (p.getCol() == col && p.getRow() == row);
    }

    @Override
    public String toString() {
        return row+","+col;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
