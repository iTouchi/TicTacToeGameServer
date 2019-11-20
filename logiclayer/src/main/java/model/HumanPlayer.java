package model;

public class HumanPlayer extends Player {

    private String name;
    private int id;
    private int wins;
    private int losses;
    private int draw;

    @Override
    public void takeTurn() {
        super.takeTurn();
    }

    public HumanPlayer(String name, int id, int wins, int losses, int draw) {
        this.name = name;
        this.id = id;
        this.wins = wins;
        this.losses = losses;
        this.draw = draw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}
