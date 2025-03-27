//*******************************************
//*************** JAVA 2048 *****************
//*******************************************
//Unique because can choose what size board you want



import java.util.*;

class Main {
  public static boolean gameActive;
  public static int score;
  public static int twentyFortyEightCount;

  public static void main(String[] args) {

    Scanner sus = new Scanner(System.in);
    Random rand = new Random();
    ArrayList<int[]> blankSpaces = new ArrayList<int[]>();
    gameActive = true;
    score = 0;
    boolean botPlay = false;
    String direction = "";
    twentyFortyEightCount = 0;
    boolean numGood = false;
    int rowCountNum = 0;
    int colCountNum = 0;
while (gameActive){
  System.out.println("OKAY ITS TWENTY FORTY EIGHT TIME\n");

  System.out.println(
      "How many rows do you want the board to be? (The standard row amount is 4 but u can go crazy if u want idc)");
  while (!numGood) {
    try {
      String rowCount = sus.nextLine();
      rowCountNum = Integer.parseInt(rowCount);
      if (rowCountNum >= 1 && rowCountNum <= 20) {
        numGood = true;
      } else {
        System.out.println("\nEnter a number between 1 and 20 smh");
      }
    } catch (Exception e) {
      System.out.println("\nEnter a number between 1 and 20 smh");
    }
  }
  numGood = false;
  System.out.println(
      "\nHow many columns do you want the board to be? (The standard column amount is also 4 but u can go crazy if u want idc)");
  while (!numGood) {
    try {
      String colCount = sus.nextLine();
      colCountNum = Integer.parseInt(colCount);
      if (colCountNum >= 1 && colCountNum <= 20) {
        numGood = true;
      } else {
        System.out.println("\nEnter a number between 1 and 20 smh");
      }
    } catch (Exception e) {
      System.out.println("\nEnter a number between 1 and 20 smh");
    }
  }

  // make arrays
  String[][] gameBoard = new String[rowCountNum][colCountNum];
  String[][] gameBoardCopy = new String[gameBoard.length][];
  for (int i = 0; i < gameBoard.length; i++) {
    for (int j = 0; j < gameBoard[i].length; j++) {
      gameBoard[i][j] = " ";
    }
  }

  // make first two starting nums
  for (int i = 0; i < 2; i++) {
    int row = rand.nextInt(gameBoard.length);
    // another way for gameboard[i].length
    int col = rand.nextInt(gameBoard[gameBoard.length - 1].length);
    int randomPicker = rand.nextInt(10);
    int num = 0;
    // 10% chance of a 4, 90% chance of a 2
    if (randomPicker == 0) {
      num = 4;
    } else {
      num = 2;
    }

    // make sure it doesnt pick the same cell twice
    if (gameBoard[row][col].equals(" ")) {
      gameBoard[row][col] = String.valueOf(num);
    } else {
      i--;
    }
    // lol
    if (rowCountNum == 1 && colCountNum == 1) {
      break;
    }
  }

  System.out.println("\nOK LETS GO\n");
  printBoard(gameBoard);

  while (gameActive) {

    if (loseCheck(gameBoard)) {
      break;
    }

    if (botPlay) {
      int randomPicker = rand.nextInt(4);
      if (randomPicker > 0 && randomPicker <= 1) {
        direction = "a";
      } else if (randomPicker > 1 && randomPicker <= 2) {
        direction = "s";
      } else if (randomPicker > 2 && randomPicker <= 3) {
        direction = "d";
      } else {
        direction = "w";
      }
    } else {
      System.out.println("                                   w");
      System.out.println("What Direction?                  a   d");
      System.out.println("                                   s");
      direction = sus.nextLine();
    }

    if (direction.equalsIgnoreCase("w") || direction.equalsIgnoreCase("a") || direction.equalsIgnoreCase("s")
        || direction.equalsIgnoreCase("d") || direction.equalsIgnoreCase("skull")) {

      if (direction.equalsIgnoreCase("skull")) {
        cheatCode(gameBoard);
      }

      // copies 2d arrays somehow idk lol stole this from stack overflow
      for (int i = 0; i < gameBoard.length; i++) {
        gameBoardCopy[i] = gameBoard[i].clone();
      }

      // THE GAME
      compressGrid(gameBoard, direction);
      combineGrid(gameBoard, direction);
      compressGrid(gameBoard, direction);

      // check if the board copy (made before compresses and combines) is the same as
      // the current board state
      if (!Arrays.deepEquals(gameBoardCopy, gameBoard)) {
        // if they are the same, dont make a random cell appear
        makeRandom(gameBoard, rand, blankSpaces);
      }

      printBoard(gameBoard);
      check2048(gameBoard);
      System.out.println("Score: " + score);
    }
      else if (direction.equalsIgnoreCase("exit") || direction.equalsIgnoreCase("lose")){
        gameActive = false;
      }

    else {
      System.out.println("\nsmh u need to type either \"w\", \"a\", \"s\", or \"d\"\n");
    }
  }
  // final remarks
  System.out.println();
  for (int i = 0; i < 5; i++) {
    System.out.println("rip you lost :(");
  }
  System.out.println("\nYour final score was " + score);
  
  System.out.println("\n\nWanna play again? (y/n)");
  String s = sus.next();
  if (!s.equalsIgnoreCase("n")) {
    System.out.println("\n");
    main(args);
  } 
  else {
    System.out.println("\nok bye.");
    gameActive = false;
  }
}
    sus.close();
  }



  
  public static void makeRandom(String[][] gameBoard, Random rand, ArrayList<int[]> blankSpaces) {
    blankSpaces.clear();
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        if (gameBoard[i][j].equals(" ")) {
          // put the indeces of any empty cells into an arraylist
          blankSpaces.add(new int[] { i, j });
        }
      }
    }

    // get a random empty cell from the list
    int randIndex = rand.nextInt(blankSpaces.size());
    int[] blankSpaceArray = blankSpaces.get(randIndex);
    int a = blankSpaceArray[0];
    int b = blankSpaceArray[1];
    int randomPicker = rand.nextInt(10);
    int num = 0;
    // 10% chance of a 4, 90% chance of a 2
    if (randomPicker == 0) {
      num = 4;
    } else {
      num = 2;
    }
    // uses the indeces from the array in the arraylist
    gameBoard[a][b] = String.valueOf(num);

  }

  public static void printBoard(String[][] gameBoard) {
    int curNum = 0;
    int rowLength = 0;
    for (int i = 0; i < gameBoard.length; i++) {
      rowLength = gameBoard[i].length;
      // top print (1 + 11x spaces per column)
      System.out.print("-");
      for (int k = 0; k < 10 * rowLength; k++) {
        System.out.print("-");
      }
      // print the row boxes
      System.out.print("\n|");
      for (int k = 0; k < rowLength; k++) {

        System.out.print("         |");
      }

      // middle print (with num inside)
      System.out.print("\n|");
      for (int j = 0; j < rowLength; j++) {

        // if no num, make the number super big so it hits the final else
        try {
          curNum = Integer.parseInt(gameBoard[i][j]);
        } catch (Exception e) {
          curNum = 20000000;
        }

        // formatting stuff to center it no matter what number
        if (curNum < 10) {
          System.out.print("    " + curNum + "    ");
        } else if (curNum < 100) {
          System.out.print("   " + curNum + "    ");
        } else if (curNum < 1000) {
          System.out.print("   " + curNum + "   ");
        } else if (curNum < 10000) {
          System.out.print("  " + curNum + "   ");
        } else if (curNum < 100000) {
          System.out.print("  " + curNum + "  ");
        } else if (curNum < 1000000) {
          System.out.print(" " + curNum + "  ");
        } else if (curNum < 10000000) {
          System.out.print(curNum + " ");
        } else {
          System.out.print("         ");
        }
        System.out.print("|");

      }

      // print vert lines under nums
      System.out.print("\n|");
      for (int k = 0; k < rowLength; k++) {

        System.out.print("         |");
      }
      System.out.println();

    }
    // final line of dashes
    System.out.print("-");
    for (int k = 0; k < 10 * rowLength; k++) {
      System.out.print("-");
    }
    System.out.println();
  }

  public static void compressGrid(String[][] gameBoard, String direction) {
    if (direction.equalsIgnoreCase("w")) {
      for (int row = 1; row < gameBoard.length; row++) {
        for (int col = 0; col < gameBoard[row].length; col++) {

          if (gameBoard[row][col] != " ") {
            // keep moving it up until it either hits 0 (the top), or theres not an empty
            // space above it
            while (row > 0 && gameBoard[row - 1][col] == " ") {
              gameBoard[row - 1][col] = gameBoard[row][col];
              gameBoard[row][col] = " ";
              row--;
            }
          }

        }
      }
    }
    // down
    else if (direction.equalsIgnoreCase("s")) {
      for (int row = gameBoard.length - 2; row >= 0; row--) {
        for (int col = 0; col < gameBoard[row].length; col++) {

          if (gameBoard[row][col] != " ") {
            while (row < gameBoard.length - 1 && gameBoard[row + 1][col] == " ") {
              gameBoard[row + 1][col] = gameBoard[row][col];
              gameBoard[row][col] = " ";
              row++;
            }
          }

        }
      }
    }
    // left
    else if (direction.equalsIgnoreCase("a")) {
      for (int row = 0; row < gameBoard.length; row++) {
        for (int col = 1; col < gameBoard[row].length; col++) {

          if (gameBoard[row][col] != " ") {
            while (col > 0 && gameBoard[row][col - 1] == " ") {
              gameBoard[row][col - 1] = gameBoard[row][col];
              gameBoard[row][col] = " ";
              col--;
            }
          }

        }
      }
    }
    // right
    else if (direction.equalsIgnoreCase("d")) {
      for (int row = 0; row < gameBoard.length; row++) {
        for (int col = gameBoard[row].length - 2; col >= 0; col--) {

          if (gameBoard[row][col] != " ") {
            while (col < gameBoard[row].length - 1 && gameBoard[row][col + 1] == " ") {
              gameBoard[row][col + 1] = gameBoard[row][col];
              gameBoard[row][col] = " ";
              col++;
            }
          }

        }
      }
    }

  }

  public static void combineGrid(String[][] gameBoard, String direction) {
    if (direction.equalsIgnoreCase("w")) {
      for (int row = 0; row < gameBoard.length - 1; row++) {
        for (int col = 0; col < gameBoard[row].length; col++) {

          // check numbers above and below
          int topNum = 0;
          int botNum = 1;
          try {
            topNum = Integer.parseInt(gameBoard[row][col]);
            botNum = Integer.parseInt(gameBoard[row + 1][col]);
          } catch (Exception e) {

          }
          // if theyre the same (able to be merged) add the nums, delete one of them
          if (topNum == botNum) {
            score += 2 * topNum;
            topNum += botNum;
            gameBoard[row][col] = String.valueOf(topNum);
            gameBoard[row + 1][col] = " ";
          }

        }
      }
    }
    // down
    else if (direction.equalsIgnoreCase("s")) {
      for (int row = gameBoard.length - 1; row > 0; row--) {
        for (int col = 0; col < gameBoard[row].length; col++) {

          int topNum = 0;
          int botNum = 1;
          try {
            topNum = Integer.parseInt(gameBoard[row][col]);
            botNum = Integer.parseInt(gameBoard[row - 1][col]);
          } catch (Exception e) {

          }

          if (topNum == botNum) {
            score += 2 * topNum;
            topNum += botNum;
            gameBoard[row][col] = String.valueOf(topNum);
            gameBoard[row - 1][col] = " ";
          }
        }
      }
    }
    // left
    else if (direction.equalsIgnoreCase("a")) {
      for (int row = 0; row < gameBoard.length; row++) {
        for (int col = 0; col < gameBoard[row].length - 1; col++) {

          int leftNum = 0;
          int rightNum = 1;

          try {
            leftNum = Integer.parseInt(gameBoard[row][col]);
            rightNum = Integer.parseInt(gameBoard[row][col + 1]);
          } catch (Exception e) {

          }

          if (leftNum == rightNum) {
            score += 2 * leftNum;
            leftNum += rightNum;
            gameBoard[row][col] = String.valueOf(leftNum);
            gameBoard[row][col + 1] = " ";
          }

        }
      }
    }
    // right
    else if (direction.equalsIgnoreCase("d")) {
      for (int row = 0; row < gameBoard.length; row++) {
        for (int col = gameBoard[row].length - 1; col > 0; col--) {

          int leftNum = 0;
          int rightNum = 1;
          try {
            leftNum = Integer.parseInt(gameBoard[row][col]);
            rightNum = Integer.parseInt(gameBoard[row][col - 1]);
          } catch (Exception E) {

          }

          if (leftNum == rightNum) {
            score += 2 * leftNum;
            leftNum += rightNum;
            gameBoard[row][col] = String.valueOf(leftNum);
            gameBoard[row][col - 1] = " ";
          }

        }
      }
    }
  }

  public static boolean loseCheck(String[][] gameBoard) {
    boolean validMove = false;
    int emptyCellCount = 0;

    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        if (gameBoard[i][j].equals(" ")) {
          emptyCellCount++;
        }
      }
    }

    for (int i = 0; i < gameBoard.length - 1; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        if (gameBoard[i][j].equals(gameBoard[i + 1][j])) {
          validMove = true;
        }
      }
    }
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < ((gameBoard[i].length) - 1); j++) {
        if (gameBoard[i][j].equals(gameBoard[i][j + 1])) {
          validMove = true;
        }
      }
    }
    if (emptyCellCount == 0 && !validMove) {
      return true;
    }
    return false;
  }

  public static void check2048(String[][] gameBoard) {

    int totalTwentyFortyEights = 0;
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        if (gameBoard[i][j].equals("2048")) {
          totalTwentyFortyEights++;
        }
      }
    }
    if (!(twentyFortyEightCount == totalTwentyFortyEights)) {
      for (int k = 0; k < 5; k++) {
        System.out.println("HOORAY YOU GOT A 2048 SO PRO");
      }
      System.out.println("\nThe game doesnt end here :)\n");
    }

    twentyFortyEightCount = totalTwentyFortyEights;
  }

  public static void cheatCode(String[][] gameBoard) {
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[i].length; j++) {
        gameBoard[i][j] = "2048";
      }
    }
  }

}
