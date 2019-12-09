package client;

import model.*;

import java.util.*;

import static java.lang.Integer.*;

public class App {
    //Other stuff
    static Scanner userInput;

    // Game elements
    static Player playerOne;
    static Player playerTwo;
    static Board board;
    static Session gameSession;
    //    static Tile[] arrayTiles;
    static String[][] stringTiles;
    static ArrayList<Tile> tiles;
    static int startingPlayer;

    // Gameplay stuff
    static Player turn;
    static boolean allTilesOccupied;
    static String winner = null;

    // Algorithm
    static Algorithm algorithm;


    static void getReady() {
        // Determine starting player
        // 0.Player, 1.AI
        Random rand = new Random();
        // Obtain a number between [0 - 1].
        //startingPlayer = rand.nextInt(2);
        startingPlayer = 0;

        if (startingPlayer == 0) {
            playerOne = new HumanPlayer(0, "Jan", 0, 0, 0, "X");
            playerTwo = new HumanPlayer(1, "Ai", 0, 0, 0, "O");
        }

        board = new Board();
        gameSession = new Session(playerOne, playerTwo, board, 1);
        stringTiles = board.getStringTiles();
        tiles = board.getTiles();
        turn = playerOne;
        allTilesOccupied = false;
        algorithm = new Algorithm();
    }

    public static void main(String[] args) {

        getReady();
        //newCode();
        algoritmeTest();

    }

    static void newCode() {
        userInput = new Scanner(System.in);

        System.out.println("Welcome to 2 Player Tic Tac Toe.");
        System.out.println("--------------------------------");
        printBoard();
        System.out.println(playerOne.getName() + "'s will play first. Enter a slot number to place X userInput:");

        if (startingPlayer == 0) {
            System.out.println("Starting player is " + playerOne.getName());
        }

        while (winner == null) {

            while (turn == playerOne) {
                turnHumanPlayer();
            }
            while (turn == playerTwo) {
                turnAiPlayer();
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " has won! Thanks for playing.");
        }

    }

    static void turnAiPlayer() {

        if (turn == playerTwo) {
            System.out.println("The Ai is calculating movement...");

            Move move = algorithm.findBestMove(board.getStringTiles(), playerOne, playerTwo);
            int id = findTile(move.row, move.col);

            System.out.println("...movement is calculated.");

            if (!(tiles.get(id).checkOccupied())) {
                tiles.get(id).setOccupiedBy(playerTwo);
                printBoard();
                System.out.println("Ai has placed his symbol on row: " + move.row + " col: " + move.col);
                winner = checkWinner();
                turn = playerOne;
            }
        }

    }

    static void turnHumanPlayer() {

        // om continue te gebruiken moet je in een WHILE loop zitten

        if (startingPlayer == 0) {
            while (turn == playerOne) {
                int numInput;
                try {
                    numInput = userInput.nextInt();

                    if (!(numInput >= 0 && numInput <= 8)) {
                        System.out.println("Invalid input; re-enter slot number:");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }

                //(arrayTiles[numInput].getId() == numInput) <- deze zat er bij om een of andere redenen
                if (!tiles.get(numInput).checkOccupied()) {
                    if (turn.equals(playerOne)) {
                        tiles.get(numInput).setOccupiedBy(playerOne);
                        printBoard();
                        winner = checkWinner();
                        turn = playerTwo;
                    }
                } else {
                    System.out.println("Slot already taken; re-enter slot number:");
                    continue;
                }
            }
        }


    }


    static int findTile(int row, int col) {
        for (Tile t : tiles) {
            if (t.getRow() == row && t.getCol() == col) {
                return t.getId();
            }
        }
        return -1;
    }

    static void printBoard() {
//        System.out.println("/---|---|---\\");
//        System.out.println("| " + arrayTiles[0] + " | " + arrayTiles[1] + " | " + arrayTiles[2] + " |");
//        System.out.println("|-----------|");
//        System.out.println("| " + arrayTiles[3] + " | " + arrayTiles[4] + " | " + arrayTiles[5] + " |");
//        System.out.println("|-----------|");
//        System.out.println("| " + arrayTiles[6] + " | " + arrayTiles[7] + " | " + arrayTiles[8] + " |");
//        System.out.println("/---|---|---\\");
//        //ARRAYLIST
//        System.out.println("/---|---|---\\");
//        System.out.println("| " + tiles.get(0).getId() + " | " + tiles.get(1).getId() + " | " + tiles.get(2).getId() + " |");
//        System.out.println("|-----------|");
//        System.out.println("| " + tiles.get(3).getId() + " | " + tiles.get(4).getId() + " | " + tiles.get(5).getId() + " |");
//        System.out.println("|-----------|");
//        System.out.println("| " + tiles.get(6).getId() + " | " + tiles.get(7).getId() + " | " + tiles.get(8).getId() + " |");
//        System.out.println("/---|---|---\\");
        //ARRAYLIST
        System.out.println("/---|---|---\\");
        System.out.println("| " + tiles.get(0).getSymbol() + " | " + tiles.get(1).getSymbol() + " | " + tiles.get(2).getSymbol() + " |");
        System.out.println("|-----------|");
        System.out.println("| " + tiles.get(3).getSymbol() + " | " + tiles.get(4).getSymbol() + " | " + tiles.get(5).getSymbol() + " |");
        System.out.println("|-----------|");
        System.out.println("| " + tiles.get(6).getSymbol() + " | " + tiles.get(7).getSymbol() + " | " + tiles.get(8).getSymbol() + " |");
        System.out.println("/---|---|---\\");
        //STRINGTILES
//        System.out.println("/---|---|---\\");
//        System.out.println("| " + stringTiles[0][0] + " | " + stringTiles[0][1] + " | " + stringTiles[0][2]+ " |");
//        System.out.println("|-----------|");
//        System.out.println("| " + stringTiles[1][0] + " | " + stringTiles[1][1] + " | " + stringTiles[1][2] + " |");
//        System.out.println("|-----------|");
//        System.out.println("| " + stringTiles[2][0] + " | " + stringTiles[2][1] + " | " + stringTiles[2][2] + " |");
//        System.out.println("/---|---|---\\");

//        stringTiles = new String[][]{
//                {arrayTiles[0].getSymbol(), arrayTiles[1].getSymbol(), arrayTiles[2].getSymbol()},
//                {arrayTiles[3].getSymbol(), arrayTiles[4].getSymbol(), arrayTiles[5].getSymbol()},
//                {arrayTiles[6].getSymbol(), arrayTiles[7].getSymbol(), arrayTiles[8].getSymbol()}
//        };

        stringTiles = new String[][]{
                {tiles.get(0).getSymbol(), tiles.get(1).getSymbol(), tiles.get(2).getSymbol()},
                {tiles.get(3).getSymbol(), tiles.get(4).getSymbol(), tiles.get(5).getSymbol()},
                {tiles.get(6).getSymbol(), tiles.get(7).getSymbol(), tiles.get(8).getSymbol()}
        };
        board.setStringTiles(stringTiles);

    }

    static void checkTiles() {
        for (int i = 0; i < 9; ) {
            allTilesOccupied = true;
            if (!tiles.get(i).checkOccupied()) {
                allTilesOccupied = false;
                break;
            } else {
                i++;
            }
        }
    }

    static void algoritmeTest() {
        long start = System.currentTimeMillis();

        String[][] b = {
                {"O", "_", "X"},
                {"_", "X", "_"},
                {"_", "_", "_"}
        };

        Move bestMove = algorithm.findBestMove(b, playerOne, playerTwo);

        System.out.println("The Optimal Move is : ");
        System.out.println("Row: " + bestMove.row + " Col: " + bestMove.col);

        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;

        System.out.println("Calculation time in MS = " + timeElapsed);
    }


    static String checkWinner() {

        checkTiles();

        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = tiles.get(0).toString() + tiles.get(1) + tiles.get(2);
                    break;
                case 1:
                    line = tiles.get(3).toString() + tiles.get(4) + tiles.get(5);
                    break;
                case 2:
                    line = tiles.get(6).toString() + tiles.get(7) + tiles.get(8);
                    break;
                case 3:
                    line = tiles.get(0).toString() + tiles.get(3) + tiles.get(6);
                    break;
                case 4:
                    line = tiles.get(1).toString() + tiles.get(4) + tiles.get(7);
                    break;
                case 5:
                    line = tiles.get(2).toString() + tiles.get(5) + tiles.get(8);
                    break;
                case 6:
                    line = tiles.get(0).toString() + tiles.get(4) + tiles.get(8);
                    break;
                case 7:
                    line = tiles.get(2).toString() + tiles.get(4) + tiles.get(6);
                    break;
            }
            if (line.equals(playerOne.getSymbol() + playerOne.getSymbol() + playerOne.getSymbol())) {
                return playerOne.getName();
            } else if (line.equals(playerTwo.getSymbol() + playerTwo.getSymbol() + playerTwo.getSymbol())) {
                return playerTwo.getName();
            } else if (allTilesOccupied) {
                return "draw";
            }
        }

        System.out.println(turn.getName() + "'s turn; enter a slot number to place " + turn.getSymbol() + " userInput:");
        return null;
    }
}