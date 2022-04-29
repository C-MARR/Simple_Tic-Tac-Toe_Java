package tictactoe;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int ROWS = 3;
        final int COLUMNS = 3;
        final char[] xCheck = {'X', 'X', 'X'};
        final char[] oCheck = {'O', 'O', 'O'};
        boolean xWin = false;
        boolean oWin = false;
        boolean xTurn = true;
        char[][] board = new char[ROWS][COLUMNS];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }

        //Game Loop
        while (!xWin && !oWin) {
            printBoard(board);
            while (true) {
                System.out.print("Enter the coordinates: ");
                String newInput = scanner.nextLine();
                if (Pattern.matches("[123] [123]", newInput)) {
                    int row = Integer.parseInt(newInput.substring(0, 1)) - 1;
                    int column = Integer.parseInt(newInput.substring(2)) - 1;
                    if (board[row][column] == ' ' || board[row][column] == '_') {
                        board[row][column] = playerMoves(xTurn);
                        xWin = checkForWin(board, xCheck);
                        oWin = checkForWin(board, oCheck);
                        xTurn = !xTurn;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else if (Pattern.matches("[0-9] [0-9]", newInput)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    System.out.println("You should enter numbers!");
                }
            }
            if (checkForDraw(board)) {
                break;
            }
        }
        printBoard(board);
        if (xWin) {
            System.out.println("X wins");
        } else if (oWin) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static char playerMoves(boolean turn) {
        if (turn) {
            return 'X';
        } else {
            return 'O';
        }

    }

    public static boolean checkForWin(char[][] board, char[] check) {
                //Check Rows
        return Arrays.equals(board[0], check) || Arrays.equals(board[1], check) || Arrays.equals(board[2], check) ||
                //Check Columns
                Arrays.equals(new char[]{board[0][0], board[1][0], board[2][0]}, check) ||
                Arrays.equals(new char[]{board[0][1], board[1][1], board[2][1]}, check) ||
                Arrays.equals(new char[]{board[0][2], board[1][2], board[2][2]}, check) ||
                //Check Diagonals
                Arrays.equals(new char[]{board[0][0], board[1][1], board[2][2]}, check) ||
                Arrays.equals(new char[]{board[0][2], board[1][1], board[2][0]}, check);
    }

    public static boolean checkForDraw(char[][] board) {
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i <= 2; i++) {
            for (char piece : board[i]) {
                if (piece == 'X') {
                    xCount++;
                } else if (piece == 'O') {
                    oCount++;
                }
            }
        }
        return xCount + oCount == 9;
    }
}
