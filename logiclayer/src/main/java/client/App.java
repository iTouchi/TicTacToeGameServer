package client;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    static Scanner in;
    static Player turn;
    static boolean allTilesOccupied;

    static Game game;
    static Player playerOne;
    static Player playerTwo;
    static Board board2;
    static ArrayList<Tile> tiles;

    public static void main(String[] args) {

//        playerOne = new HumanPlayer(0, "Jan", 0, 0, 0, "X");
//        playerTwo = new HumanPlayer(1, "Piet", 0, 0, 0, "O");
//        board2 = new Board();
//        game = new Game(playerOne, playerTwo, board2);
//        tiles = board2.getTiles();
//        allTilesOccupied = false;
//
//        in = new Scanner(System.in);
//        turn = playerOne;
//        String winner = null;
//        //populateEmptyBoard();
//
//        System.out.println("Welcome to 2 Player Tic Tac Toe.");
//        System.out.println("--------------------------------");
//        printBoard();
//        System.out.println(playerOne.getName() + "'s will play first. Enter a slot number to place X in:");
//
//        while (winner == null) {
//            int numInput;
//            try {
//                numInput = in.nextInt();
//                if (!(numInput >= 0 && numInput <= 8)) {
//                    System.out.println("Invalid input; re-enter slot number:");
//                    continue;
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input; re-enter slot number:");
//                continue;
//            }
//
//            if ((tiles.get(numInput).getId() == numInput) && !(tiles.get(numInput).checkOccupied())) {
//                if (turn.equals(playerOne)) {
//                    tiles.get(numInput).setOccupiedBy(playerOne);
//                    turn = playerTwo;
//                } else {
//                    tiles.get(numInput).setOccupiedBy(playerTwo);
//                    turn = playerOne;
//                }
//                printBoard();
//                winner = checkWinner();
//            } else {
//                System.out.println("Slot already taken; re-enter slot number:");
//                continue;
//            }
//        }
//        if (winner.equalsIgnoreCase("draw")) {
//            System.out.println("It's a draw! Thanks for playing.");
//        } else {
//            System.out.println("Congratulations! " + winner + " has won! Thanks for playing.");
//        }

        evaluateTest();

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

        System.out.println(turn.getName() + "'s turn; enter a slot number to place " + turn.getSymbol() + " in:");
        return null;
    }

    static void printBoard() {
        System.out.println("/---|---|---\\");
        System.out.println("| " + tiles.get(0) + " | " + tiles.get(1) + " | " + tiles.get(2) + " |");
        System.out.println("|-----------|");
        System.out.println("| " + tiles.get(3) + " | " + tiles.get(4) + " | " + tiles.get(5) + " |");
        System.out.println("|-----------|");
        System.out.println("| " + tiles.get(6) + " | " + tiles.get(7) + " | " + tiles.get(8) + " |");
        System.out.println("/---|---|---\\");
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

    // Returns a values based on who is winning
    // board[3][3] is the Tic-Tac-Toe board
    static int evaluate(String[][] board) {

        // Checking for horizontal victory
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == "X")
                    return +10;
                if (board[row][0] == "O")
                    return -10;
            }
        }

        // Checking for vertical victory
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == "X")
                    return +10;
                if (board[0][col] == "O")
                    return -10;
            }
        }

        // Checking for diagonal victory
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == "X")
                return +10;
            if (board[0][0] == "O")
                return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == "X")
                return +10;
            if (board[0][2] == "O")
                return -10;
        }
        // Else if none has won return 0 for Draw
        return 0;
    }

    static void evaluateTest(){
        String[][] b = {
                { "X", "_", "O"},
                { "_", "X", "O"},
                { "_", "_", "X"}
        };

        int value = evaluate(b);
        System.out.println("The value of this board is" + value);




    }

}