package model;

public class Tile {

    private int Id;
    private int row;
    private int col;
    private Player occupiedBy;

    public Tile(int id, int row, int col) {
        Id = id;
        this.row = row;
        this.col = col;
    }

    public void setOccupiedBy(Player player) {
        this.occupiedBy = player;
    }
}
