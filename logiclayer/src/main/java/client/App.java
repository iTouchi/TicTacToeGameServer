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
    static int MAX = 1000;
    static int MIN = -1000;

    static void getReady(){
        playerOne = new HumanPlayer(0, "AI", 0, 0, 0, "X");
        playerTwo = new HumanPlayer(1, "Jan", 0, 0, 0, "O");

        board = new Board();
        gameSession = new Session(playerOne, playerTwo, board, 1);
        arrayTiles = board.getArrayTiles();
        stringTiles = board.getStringTiles();
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

    // Returns a values based on who is winning
    // board[3][3] is the Tic-Tac-Toe board
    static int evaluate(String[][] board, int depth) {

        // Checking for horizontal victory
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == "X")
                    return +10 - depth;
                if (board[row][0] == "O")
                    return -10 +depth;
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

    static void evaluateTest() {
        String[][] b = {
                {"X", "_", "O"},
                {"_", "X", "O"},
                {"_", "_", "X"}
        };

        int value = evaluate(b,0);
        System.out.println("The value of this board is " + value);
    }

    static int algoritmeTest() {
        String[][] b = {
                {"O", "X", "X"},
                {"O", "O", "_"},
                {"X", "_", "_"}
        };

        Move bestMove = findBestMove(b);

        System.out.println("The Optimal Move is : ");
        System.out.println("Row: " + bestMove.row + " Col: " + bestMove.col);
        return 0;
    }

    //Check if there are moves remaining
    static boolean isMoveLeft(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == "_") {
                    return true;
                }
            }
        }
        return false;
    }

    static int minimax(String[][] board, int depth, boolean isMax) {
        int score = evaluate(board, depth);

        // If Maximizer has won the gameSession will return the evaluated score for the Maximizer
        if (score == 10) {
            return score;
        }
        // If Minimizer has won the gameSession will return the evaluated score for the Minimizer
        if (score == -10) {
            return score;
        }

        // If there are no moves left and there is no winner the gameSession will return a tie
        if (!isMoveLeft(board)) { //CHANGED was (isMoveLeft(board)==false)
            return 0;
        }

        // If it's the Maximizer's turn
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == "_") {
                        // Make the move
                        board[i][j] = playerOne.getSymbol();

                        // Call minimax recursively and choose the max value
                        best = max(best, minimax(board, depth + 1, isMax));//CHANGED was !isMax

                        // Undo the move
                        board[i][j] = "_";
                    }
                }
            }
            return best;
        }
        // If it's the Minimizer's turn
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == "_") {
                        // Make the move
                        board[i][j] = playerTwo.getSymbol();

                        // Call minimax recursively and choose the min value
                        best = min(best, minimax(board, depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = "_";
                    }
                }
            }
            return best;
        }
    }

    static Move findBestMove(String[][] board) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function for all empty cells.
        // Return the cell with with the optimal value.

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == "_") {
                    // Makle the move
                    board[i][j] = playerOne.getSymbol();

                    // Compute evaluation function for this move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = "_";

                    // If the value of the current move is more than the best value
                    // update best value.
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        System.out.println("The value of the best move is : " + bestVal);
        return bestMove;
    }

//    static int minimaxNEW(int depth, int nodeIndex, Boolean maximizingPlayer, int values[], int alpha, int beta) {
//
//        if (depth == 3) {
//            return values[nodeIndex];
//        }
//
//        if (maximizingPlayer) {
//            int best = MIN;
//
//            for (int i = 0; i < 2; i++) {
//                int val = minimaxNEW(depth + 1, nodeIndex * 2 + i, false, values, alpha, beta);
//
//                best = max(best, val);
//                alpha = max(alpha, best);
//
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//            return best;
//        } else {
//            int best = MAX;
//            for (int i = 0; i < 2; i++) {
//                int val = minimaxNEW(depth + 1, nodeIndex * 2 + i, true, values, alpha, beta);
//
//                best = min(best, val);
//                beta = min(beta, best);
//
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//            return best;
//        }
//    }
//
//    static void testNEW() {
//        int values[] = {3, 5, 6, 9, 1, 2, 0, -1};
//        System.out.println("The optimal value is : " +
//                minimaxNEW(0, 0, true, values, MIN, MAX));
//    }
}