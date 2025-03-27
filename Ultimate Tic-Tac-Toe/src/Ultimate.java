import java.util.Random;
import java.util.Scanner;
class Main {
  public static char[][] gameBoard = 
    {  {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
    {'.', '.', '.', '.', '.', '.', '.', '.', '.'},  };

  public static Scanner sus;
  public static char player;
  public static int boardNum;
  public static int insideNum;
  public static char symbol;
  public static int playerPicker;
  public static boolean gameRunning;
  public static int playerNum;
  public static boolean numGood;
  public static Random rand;
  public static boolean botGame;
  public static boolean playBot;
  
  public static void main(String[] args) {
    gameRunning = true;
    playerNum = 1;
    sus = new Scanner(System.in);
    playerPicker = 1;
    symbol = 'X';
    insideNum = 1;
    numGood = false;
    rand = new Random();
    botGame = false;
    playBot = false;
    
Board board = new Board(100);
    Board topLeft = new Board(0);
    Board topMiddle = new Board(1);
    Board topRight = new Board(2);
    Board leftMiddle = new Board(3);
    Board midMiddle = new Board(4);
    Board rightMiddle = new Board(5);
    Board bottomLeft = new Board(6);
    Board bottomMiddle = new Board(7);
    Board bottomRight = new Board(8);
    
System.out.println("\tHello this is ultimate tic tac toe. The objective of the game is to win on the BIG tic tac toe board, but there is a catch. \n\nThree things to note: \n\n\t#1: to fill out a BIG space, you have to win in the corresponding smaller tic tac toe game. \n\n\t#2: you must play in the smaller board that corresponds to the position that your opponent played on their turn. \n\n\t#3: Use the number pad to make moves because each number's POSITION on the number pad corresponds to the POSITION on the board (e.g. 1 == bottom left).\n\nGot it???");
    sus.nextLine();
  System.out.println("GLHF!");

    System.out.println("\nwanna do a bot game? (y or n)");
    String ans = sus.nextLine();
    if (ans.equalsIgnoreCase("y")){
      botGame = true;

      System.out.println("\ndo u wanna play against the bot or watch two bots play against each other? (1 or 2)");
      ans = sus.nextLine();

          if (ans.equalsIgnoreCase("1")){
            playBot = true;
          }
    }

  

    
    board.printFullBoard(gameBoard);
    
    while (boardNum < 1 || boardNum > 9){
  System.out.println("\nPlayer " + playerNum + " (" + symbol + ")" + ": Pick what board you want to make a move in: ");
        if (botGame && !playBot){
          boardNum = rand.nextInt(9) + 1;
        }
      else{
        while (true){
            try{
                boardNum = sus.nextInt();
                break;
                    }
            catch(Exception e){
                    System.out.println("That is not a valid move: try again.");
                    sus.nextLine();
                    }
                }
      }

      if (boardNum < 1 || boardNum > 9){
        System.out.println("\nThat is not a valid move: try again. (Pick a number 1-9)");
      } 
    }
//check first number make sure its good
    
    while (gameRunning){
      
      if (playerPicker % 2 == 1){
        symbol = 'X';
        playerNum = 1;
      }
      else{
        symbol = 'O';
        playerNum = 2;
      }

      pickBoard(board, symbol, playerNum);
      playerInputs(board, symbol, playerNum);
      
       if (board.isValidMove(gameBoard, boardNum, insideNum)){
         board.playerMove(gameBoard, boardNum, insideNum, symbol);

         boardNum = insideNum;
         
         topLeft.checkWin(gameBoard, symbol);
         topMiddle.checkWin(gameBoard, symbol);
         topRight.checkWin(gameBoard, symbol);
         leftMiddle.checkWin(gameBoard, symbol);
         midMiddle.checkWin(gameBoard, symbol);
         rightMiddle.checkWin(gameBoard, symbol);
         bottomLeft.checkWin(gameBoard, symbol);
         bottomMiddle.checkWin(gameBoard, symbol);
         bottomRight.checkWin(gameBoard, symbol);

         board.printFullBoard(gameBoard);
         
         if (board.bigBoardCheck() == 'X' || board.bigBoardCheck() == 'O' || board.isTie(gameBoard)){
           gameRunning = false;
         }
         
         playerPicker++;
         if (botGame && playBot){
           // Thread.sleep(100);
         }
     
       }
      else{
        System.out.println("\nThat is not a valid move: try again.");
      }
      
      
    }
if (board.bigBoardCheck() == 'X' || board.bigBoardCheck() == 'O'){
  for (int i = 0; i < 5; i++){
    System.out.println("\nGG PLAYER " + playerNum + " (" + symbol + ") HAS WON!!!");
  }
}
  else if (board.isTie(gameBoard)){
  for (int i = 0; i < 5; i++){
    System.out.println("It was a tie lmao");
    }
  } 
}


   public static void displayBoardNum(int playernum, char symbol, String region){
     System.out.println("\nPlayer " + playerNum + " (" + symbol + ")" + ": Pick what space in the " + region + " board you want to make a move in: ");

     if (botGame && playernum != 1 || botGame && !playBot){
       insideNum = rand.nextInt(9) + 1;
     }
   else{

    while (true){
        try{
            insideNum = sus.nextInt();
            break;
                }
        catch(Exception e){
                System.out.println("That is not a valid move: try again.");
                sus.nextLine();
                }
            }
     
        }
      
    }
  
  public static void playerInputs(Board board, char symbol, int playerNum){
    
    switch (boardNum){
      case 7:
        displayBoardNum(playerNum, symbol, "↖ TOP LEFT ↖");
        break;
      case 8:
        displayBoardNum(playerNum, symbol, "↑ TOP MIDDLE ↑");
        break;
      case 9:
        displayBoardNum(playerNum, symbol, "↗ TOP RIGHT ↗");
        break;
      case 4:
      displayBoardNum(playerNum, symbol, "← MIDDLE LEFT ←");
        break;
      case 5:
        displayBoardNum(playerNum, symbol, "■ MIDDLE ■");
        break;
      case 6:
       displayBoardNum(playerNum, symbol, "→ MIDDLE RIGHT →");
        break;
      case 1:
        displayBoardNum(playerNum, symbol, "↙ BOTTOM LEFT ↙");
        break;
      case 2:
       displayBoardNum(playerNum, symbol, "↓ BOTTOM MIDDLE ↓");
        break;
      case 3:
        displayBoardNum(playerNum, symbol, "↘ BOTTOM RIGHT ↘");
        break;
    }
  }


  
  public static void pickBoard(Board board, char symbol, int playerNum){
    if(insideNum >= 1 && insideNum <= 9){
      if (board.getBigBoardValue(insideNum - 1) == 'X' || board.getBigBoardValue(insideNum - 1) == 'O'){

            //if the big Char is filled out, player can pick any board to play on

            System.out.println("\nPlayer " + playerNum + " (" + symbol + ")" + ": Pick what board you want to make a move in: ");
        while (true){

          if (botGame && playerNum != 1 || botGame && !playBot){
            boardNum = rand.nextInt(9) + 1;
          }
          else{
            while (true){
                try{
                    boardNum = sus.nextInt();
                    break;
                        }
                catch(Exception e){
                        System.out.println("That is not a valid move: try again.");
                        sus.nextLine();
                        }
                    }
          }

          if (board.getBigBoardValue(boardNum - 1) == 'X' || board.getBigBoardValue(boardNum - 1) == 'O'){
            System.out.println("\nThat board is already filled out. Try again.");
        }
          else break;
        }
      }
    }
  }


  
}