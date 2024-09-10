package tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private int[] lastMove;
    private char lastMovePlayer;

    TicTacToe(){
        //Initializing board with empty cells
        board = new char[3][3];
        for (char[] rows : board) {
            Arrays.fill(rows, ' ');
        }

        //Setting first player to 'X'
        currentPlayer = 'X';
    }

    private boolean isWinningMove(){
        //If there isn't any move yet return false
        if(lastMove == null) return false;

        int x = lastMove[0];
        int y = lastMove[1];

        //Check possible winning cases for current move

        //Horizontal win
        if(board[x][0] == lastMovePlayer && board[x][1] == lastMovePlayer && board[x][2] == lastMovePlayer){
            return true;
        }

        //Vertical win
        if(board[0][y] == lastMovePlayer && board[1][y] == lastMovePlayer && board[2][y] == lastMovePlayer){
            return true;
        }

        //Diagonal win
        if(board[0][0] == lastMovePlayer && board[1][1] == lastMovePlayer && board[2][2] == lastMovePlayer){
            return true;
        }

        //Anti-diagonal win
        if(board[1][2] == lastMovePlayer && board[2][1] == lastMovePlayer && board[3][0] == lastMovePlayer){
            return true;
        }

        return false;
    }

    private boolean isBoardFull(){
        //Checking if the board is full
        for(char[] row : board){
            for(char cell : row){
                if(cell == ' ') return false;
            }
        }

        return true;
    }

    private void printBoard(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                System.out.print(" " + board[i][j] + " ");
                if(j < board[i].length-1){
                    System.out.print("|");
                }
            }

            System.out.println();

            if(i < board.length-1){
                System.out.println("-----------");
            }
        }
    }

    public void play(){
        //Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        //Play game until there's a winning move or the board is full
        while(!isWinningMove() && !isBoardFull()){
            //Declare input variables
            int rowInput = 0;
            int columnInput = 0;

            //Print board and indicate current player
            System.out.println("Current player: " + currentPlayer);
            printBoard();
            System.out.println();

            //Prompt current player to introduce move
            boolean inputException = false;
            do {
                inputException = false;
                try{
                    System.out.println("Enter your move, enter row number (1-3)");
                    rowInput = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter your move, enter column number (1-3)");
                    columnInput = Integer.parseInt(scanner.nextLine());

                    if(rowInput < 1 || rowInput > 3 || columnInput < 1 || columnInput > 3){
                        throw(new Exception("Input out of bounds of board"));
                    }

                    if(board[rowInput-1][columnInput-1] != ' '){
                        throw(new Exception("Position in board already played"));
                    }
                }catch (Exception e){
                    inputException = true;
                    System.out.println("Input error: " + e.getMessage());
                    System.out.println("Try again!");
                }
            } while(inputException);

            //Register move in board
            board[rowInput-1][columnInput-1] = currentPlayer;
            lastMove = new int[]{rowInput-1, columnInput-1};
            lastMovePlayer = currentPlayer;

            //change current player
            currentPlayer = (currentPlayer ==  'X') ? 'O' : 'X';

            //Print turn separator
            System.out.println("-------------------------------------------------------");
        }

        if(isWinningMove()){
            printBoard();
            System.out.println("Player X won!");
        }

        if(isBoardFull() && !isWinningMove()){
            printBoard();
            System.out.println("Draw!");
        }
    }
}
