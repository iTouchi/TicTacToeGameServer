package model;

public class Tile {

    private int id;
    private int row;
    private int col;
    private Player occupiedBy = null;

    public Tile(int id, int row, int col) {
        this.id = id;
        this.row = row;
        this.col = col;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setOccupiedBy(Player player) {
        this.occupiedBy = player;
    }

    public boolean checkOccupied() {
        if (occupiedBy != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {

        if (occupiedBy == null) {
            return Integer.toString(this.id);
        } else {
            return occupiedBy.getSymbol();
        }

    }
}
