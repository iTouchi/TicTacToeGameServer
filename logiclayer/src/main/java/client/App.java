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
    static Tile[] arrayTiles;
    static String[][] stringTiles;

    // Gameplay stuff
    static Player turn;
    static boolean allTilesOccupied;

    // Algorithm
    static Algorithm algorithm;


    static void getReady(){
        playerOne = new HumanPlayer(0, "AI", 0, 0, 0, "X");
        playerTwo = new HumanPlayer(1, "Jan", 0, 0, 0, "O");

        board = new Board();
        gameSession = new Session(playerOne, playerTwo, board, 1);
        arrayTiles = board.getArrayTiles();
        stringTiles = board.getStringTiles();

        algorithm = new Algorithm();
    }

    public static void main(String[] args) {

        getReady();

        allTilesOccupied = false;
        userInput = new Scanner(System.in);
        turn = playerOne; // make this random

        String winner = null;

        System.out.println("Welcome to 2 Player Tic Tac Toe.");
        System.out.println("--------------------------------");
        printBoard();
        System.out.println(playerOne.getName() + "'s will play first. Enter a slot number to place X userInput:");

        while (winner == null) {
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

            if ((arrayTiles[numInput].getId() == numInput) && !(arrayTiles[numInput].checkOccupied())) {
                if (turn.equals(playerOne)) {
                    arrayTiles[numInput].setOccupiedBy(playerOne);
                    turn = playerTwo;
                } else {
                    arrayTiles[numInput].setOccupiedBy(playerTwo);
                    turn = playerOne;
                }
                printBoard();
                winner = checkWinner();
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
                continue;
            }
        }
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " has won! Thanks for playing.");
        }

        //evaluateTest();
        algoritmeTest();
        //testNEW();

    }

    static String checkWinner() {

        checkTiles();

        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = arrayTiles[0].toString() + arrayTiles[1] + arrayTiles[2];
                    break;
                case 1:
                    line = arrayTiles[3].toString() + arrayTiles[4] + arrayTiles[5];
                    break;
                case 2:
                    line = arrayTiles[6].toString() + arrayTiles[7] + arrayTiles[8];
                    break;
                case 3:
                    line = arrayTiles[0].toString() + arrayTiles[3] + arrayTiles[6];
                    break;
                case 4:
                    line = arrayTiles[1].toString() + arrayTiles[4] + arrayTiles[7];
                    break;
                case 5:
                    line = arrayTiles[2].toString() + arrayTiles[5] + arrayTiles[8];
                    break;
                case 6:
                    line = arrayTiles[0].toString() + arrayTiles[4] + arrayTiles[8];
                    break;
                case 7:
                    line = arrayTiles[2].toString() + arrayTiles[4] + arrayTiles[6];
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

    static void printBoard() {
        System.out.println("/---|---|---\\");
        System.out.println("| " + arrayTiles[0] + " | " + arrayTiles[1] + " | " + arrayTiles[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + arrayTiles[3] + " | " + arrayTiles[4] + " | " + arrayTiles[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + arrayTiles[6] + " | " + arrayTiles[7] + " | " + arrayTiles[8] + " |");
        System.out.println("/---|---|---\\");

        stringTiles = new String[][] {
                {arrayTiles[0].getSymbol(), arrayTiles[1].getSymbol(), arrayTiles[2].getSymbol()},
                {arrayTiles[3].getSymbol(), arrayTiles[4].getSymbol(), arrayTiles[5].getSymbol()},
                {arrayTiles[6].getSymbol(), arrayTiles[7].getSymbol(), arrayTiles[8].getSymbol()}
        };

    }

    static void checkTiles() {
        for (int i = 0; i < 9; ) {
            allTilesOccupied = true;
            if (!arrayTiles[i].checkOccupied()) {
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
                {"X", "O", "X"},
                {"O", "X", "O"},
                {"_", "_", "_"}
        };

        Move bestMove = algorithm.findBestMove(b, playerOne, playerTwo);

        System.out.println("The Optimal Move is : ");
        System.out.println("Row: " + bestMove.row + " Col: " + bestMove.col);

        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;

        System.out.println("Calculation time in MS = " + timeElapsed);
    }

}