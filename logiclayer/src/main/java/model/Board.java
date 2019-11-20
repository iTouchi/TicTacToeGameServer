package model;

import java.util.ArrayList;

public class Board {

    private ArrayList<Tile> tiles;

    public Board (){
        tiles = new ArrayList<>();
        createTiles();
    }

    public void createTiles(){

        Tile t0 = new Tile(0,0,0);
        Tile t1 = new Tile(1,0,1);
        Tile t2 = new Tile(2,0,2);
        Tile t3 = new Tile(3,1,0);
        Tile t4 = new Tile(4,1,1);
        Tile t5 = new Tile(5,1,2);
        Tile t6 = new Tile(6,2,0);
        Tile t7 = new Tile(7,2,1);
        Tile t8 = new Tile(8,2,2);


        tiles.add(t0);
        tiles.add(t1);
        tiles.add(t2);
        tiles.add(t3);
        tiles.add(t4);
        tiles.add(t5);
        tiles.add(t6);
        tiles.add(t7);
        tiles.add(t8);
    }

    public void takeOneTurn(Player player, Tile tile){
        tile.setOccupiedBy(player);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
}
