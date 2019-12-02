package model;

public class Board {

    //private ArrayList<Tile> arrayTiles;
    private String[][] stringTiles;
    private Tile[] arrayTiles;

    public Board() {
        //arrayTiles = new ArrayList<>();
        stringTiles = new String[][]{
                {"_", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };
        arrayTiles = createArrayTiles();
    }

    public Tile[] createArrayTiles() {
        Tile[] ts = new Tile[9];

        for (int i = 0; i < 9; i++) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    ts[i] = new Tile(i, row, col);
                }
            }
        }
        return ts;
    }

//        Tile t0 = new Tile(0,0,0);
//        Tile t1 = new Tile(1,0,1);
//        Tile t2 = new Tile(2,0,2);
//        Tile t3 = new Tile(3,1,0);
//        Tile t4 = new Tile(4,1,1);
//        Tile t5 = new Tile(5,1,2);
//        Tile t6 = new Tile(6,2,0);
//        Tile t7 = new Tile(7,2,1);
//        Tile t8 = new Tile(8,2,2);

//
//        arrayTiles.add(t0);
//        arrayTiles.add(t1);
//        arrayTiles.add(t2);
//        arrayTiles.add(t3);
//        arrayTiles.add(t4);
//        arrayTiles.add(t5);
//        arrayTiles.add(t6);
//        arrayTiles.add(t7);
//        arrayTiles.add(t8);

    public void takeOneTurn(Player player, Tile tile) {
        tile.setOccupiedBy(player);
    }

    public Tile[] getArrayTiles() {
        return arrayTiles;
    }

    public String[][] getStringTiles() {
        return stringTiles;
    }

}
