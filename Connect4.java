import java.util.Scanner;

public class Connect4 {
    public static void main(String args[]) {
       String[][] board = { {".", ".", ".", ".", ".", ".", "."},
                             {".", ".", ".", ".", ".", ".", "."},
                             {".", ".", ".", ".", ".", ".", "."},
                             {".", ".", ".", ".", ".", ".", "."},
                             {".", ".", ".", ".", ".", ".", "."},
                             {".", ".", ".", ".", ".", ".", "."}, }; // makes board
        boolean yellow = false; // yellow turn
        boolean red = true; // red turn (starts the game)
        theGame(board, yellow, red);//start
        //OX (Game pieces)
    }

    public static void theGame(String[][] board, boolean yellow, boolean red) {
        int column1 = 6;//make each column have a height of 0 (the arrays go bottom up with row 1 at top, row 6 at bottom)
        int column2 = 6;
        int column3 = 6;
        int column4 = 6;
        int column5 = 6;
        int column6 = 6;
        int column7 = 6;
boolean columnGood; // initialize a boolean to check for non ints

        Scanner sus = new Scanner(System.in);
        printBoard(board);

        while (red || yellow) {
            if (red) {
                columnGood = false;
                int column = 0;//column number chosen
                while (!columnGood){
                    try {
                        System.out.println("\nPick a column (Solid)");
                        column = sus.nextInt();
                        columnGood = true;
                    }

                    catch (Exception bad){
                        System.out.println("Put an actual number from 1-7 in bruh");
                        sus.nextLine();
                        printBoard(board);
                    }
                }
              
                    if (column == 1) {
                        //column from scanner
                        if (column1 != 0) {//checks for if row not full
                            column1--;//every time piece added, raises row # by 1 (deletes 1 since they're inverse)
                            board[column1][column - 1] = "X";
                            red = false;//swap turns
                            yellow = true;
                        }
                        //columnnum shows what row we're in -- every time executed (also cant go below 0)
                        else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                        //if full, print full
                    } else if (column == 2) {
                        if (column2 != 0) {
                            column2--;
                            board[column2][column - 1] = "X";
                            red = false;
                            yellow = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 3) {
                        if (column3 != 0) {
                            column3--;
                            board[column3][column - 1] = "X";
                            red = false;
                            yellow = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 4) {
                        if (column4 != 0) {
                            column4--;
                            board[column4][column - 1] = "X";
                            red = false;
                            yellow = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 5) {
                        if (column5 != 0) {
                            column5--;
                            board[column5][column - 1] = "X";
                            red = false;
                            yellow = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 6) {
                        if (column6 != 0) {
                            column6--;
                            board[column6][column - 1] = "X";
                            red = false;
                            yellow = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 7) {
                        if (column7 != 0) {
                            column7--;
                            board[column7][column - 1] = "X";
                            red = false;
                            yellow = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    }
                     else {
                        System.out.println("Put an actual number from 1-7 in bruh");
                    }

                    printBoard(board);
                    Win(board);
            }
            if (yellow) {
columnGood = false;
                int column = 0;
                while (!columnGood){
                    try {
                        System.out.println("\nPick a column (Clear)");
                        column = sus.nextInt();
                        columnGood = true;
                    }

                    catch (Exception bad){
                        System.out.println("Put an actual number from 1-7 in bruh");
                        sus.nextLine();
                        printBoard(board);
                    }
                }


                    if (column == 1) {
                        if (column1 != 0) {
                            column1--;
                            board[column1][column - 1] = "O";
                            yellow = false;
                            red = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 2) {
                        if (column2 != 0) {
                            column2--;
                            board[column2][column - 1] = "O";
                            yellow = false;
                            red = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 3) {
                        if (column3 != 0) {
                            column3--;
                            board[column3][column - 1] = "O";
                            yellow = false;
                            red = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 4) {
                        if (column4 != 0) {
                            column4--;
                            board[column4][column - 1] = "O";
                            yellow = false;
                            red = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 5) {
                        if (column5 != 0) {
                            column5--;
                            board[column5][column - 1] = "O";
                            yellow = false;
                            red = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 6) {
                        if (column6 != 0) {
                            column6--;
                            board[column6][column - 1] = "O";
                            yellow = false;
                            red = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    } else if (column == 7) {
                        if (column7 != 0) {
                            column7--;
                            board[column7][column - 1] = "O";
                            yellow = false;
                            red = true;
                        } else {
                            System.out.println("Please put a different row. This one's full.");
                        }
                    }
                     else {
                        System.out.println("Put an actual number from 1-7 in bruh");
                    }
                    printBoard(board);
                    Win(board);
            }
            }
        }
    public static void Win(String [][] board) {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 3; j++) {
                if ((!board[i][j].equals(".") && board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(board[i + 2][j + 2]) && board[i][j].equals(board[i + 3][j + 3])))//diagonal down right
                {
                    if (board[i][j].equals("X")){
                     solidWin();
                    }

                    else if (board[i][j].equals("O")){
                       clearWin();
                    }
                }
            }
        }


        for (int i = 0; i <= 2; i++) {
            for (int j = 3; j <= 6; j++) {
                if ((!board[i][j].equals(".") && board[i][j].equals(board[i + 1][j - 1]) && board[i][j].equals(board[i + 2][j - 2]) && board[i][j].equals(board[i + 3][j - 3])))//diagonal down left
                {
                    if (board[i][j].equals("X")){
                        solidWin();
                    }

                    else if (board[i][j].equals("O")){
                        clearWin();
                    }
                    }
                }
            }


        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 6; j++) {
                if ((!board[i][j].equals(".") && board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 2][j]) && board[i][j].equals(board[i + 3][j])))//down
                {
                    if (board[i][j].equals("X")){
                        solidWin();
                    }

                    else if (board[i][j].equals("O")){
                        clearWin();
                    }
                }
            }
            }

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 3; j++) {
                if ((!board[i][j].equals(".") && board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i][j + 2]) && board[i][j].equals(board[i][j + 3]))) //right
                {
                    if (board[i][j].equals("X")){
                        solidWin();
                    }

                    else if (board[i][j].equals("O")){
                        clearWin();
                    }
                }
            }
        }

        }
    public static void printBoard(String [][] board){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void solidWin(){
        System.out.println("SOLID CIRCLE WINS");
        System.out.println("SOLID CIRCLE WINS");
        System.out.println("SOLID CIRCLE WINS");
        System.exit(0);
    }

    public static void clearWin(){
        System.out.println("CLEAR CIRCLE WINS");
        System.out.println("CLEAR CIRCLE WINS");
        System.out.println("CLEAR CIRCLE WINS");
        System.exit(0);
    }

}

