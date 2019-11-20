package model;

public class Game {

    private Player playerOne;
    private Player playerTwo;
    private Board board;

    public Game(Player playerOne, Player playerTwo, Board board) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
    }

    public void StartGame(){
        // do something
    }
}
