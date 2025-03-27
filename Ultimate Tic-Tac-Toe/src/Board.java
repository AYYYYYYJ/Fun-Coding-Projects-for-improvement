class Board{
    private int boardPassed;
    private static char[] bigBoardValues = {' ', ' ' , ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    public Board(int boardPassed){
      this.boardPassed = boardPassed;
    }
  
  
  
    
    public void playerMove(char[][] board, int boardNum, int insideNum, char symbol){
      // int rowNum = (insideNum - 1) / 3;
      // int columnNum = (insideNum - 1) % 3;
      board[boardNum - 1][insideNum - 1] = symbol;
    }
  
    public boolean isValidMove(char[][] board, int boardNum, int insideNum){
      // int rowNum = (insideNum - 1) / 3;
      // int columnNum = (insideNum - 1) % 3;
      if (boardNum > 9 || boardNum < 1 || insideNum > 9 || insideNum < 1){
        return false;
      }
      // if (boardNum == bigBoardValues[boardNum - 1]){
      //   return false;
      // }
      if (board[boardNum - 1][insideNum - 1] != '.') {
        return false;
        }
      else return true;
    }
  
  
  
    
    public void printFullBoard(char[][]board){
      System.out.println();
  printBoard6to8(board);
      System.out.println("-----------------------------");
  printBoard3to5(board);
      System.out.println("-----------------------------");
  printBoard0to2(board);
    }
    
    public void printBoard0to2(char[][] board){
      int count = 0;
      for (int nextRow = 0; nextRow < 9; nextRow += 3){
        //adds 3 to j to get from index 0,1,2 to 3,4,5
        for (int i = 0; i < 3; i++) {
          //[0][0], [0][1], [0][2], [1][0], [1][1], [1][2], [2][0], [2][1], [2][2]
          //[0][3], [0][4], [0][5], [1][3], [1][4], [1][5], etc
          for (int j = 6; j < 9; j++) {
              System.out.print(" " + board[i][j - nextRow] + " ");
              count++;
  
                if (count % 3 == 0 && count % 9 != 0){
                  System.out.print("|");
                }  
                if (count % 9 == 0){
                  System.out.println();
                }  
              }
            }
      }  
    }
  
    public void printBoard3to5(char[][] board){
      int count = 0;
      for (int nextRow = 0; nextRow < 9; nextRow += 3){
        //adds 3 to j to get from index 0,1,2 to 3,4,5
        for (int i = 3; i < 6; i++) {
         
          for (int j = 6; j < 9; j++) {
              System.out.print(" " + board[i][j - nextRow] + " ");
              count++;
  
                if (count % 3 == 0 && count % 9 != 0){
                  System.out.print("|");
                }  
                if (count % 9 == 0){
                  System.out.println();
                }  
              }
            }
      }  
    }
    
    public void printBoard6to8(char[][] board){
      int count = 0;
      for (int nextRow = 0; nextRow < 9; nextRow += 3){
        //adds 3 to j to get from index 0,1,2 to 3,4,5
        for (int i = 6; i < 9; i++) {
          //[6][6], [6][7], [6][8], [7][6], [7][7], [7][8], [8][6], [8][7], [8][8]
          for (int j = 6; j < 9; j++) {
              System.out.print(" " + board[i][j - nextRow] + " ");
              count++;
  
                if (count % 3 == 0 && count % 9 != 0){
                  System.out.print("|");
                }  
                if (count % 9 == 0){
                  System.out.println();
                }  
              }
            }
      }  
    }
    
  
  
  
  
    
    public void checkWin(char[][] board, char symbol){
      for (int i = 0; i < 9; i += 3){
        //horizontal checks
        if (board[boardPassed][i] == board[boardPassed][i + 1] && board[boardPassed][i] == board[boardPassed][i + 2] && board[boardPassed][i] != '.'){
          if (symbol == 'X'){
            bigChar(board, 'X');
          }
          else if (symbol == 'O'){
            bigChar(board, 'O');
          }
          
          //adds it to the slot in the big board
          bigBoard(symbol);
        }
      } 
  for (int i = 0; i < 3; i++){
    //vertical checks
    if (board[boardPassed][i] == board[boardPassed][i + 3] && board[boardPassed][i] == board[boardPassed][i + 6] && board[boardPassed][i] != '.'){
      if (symbol == 'X'){
        bigChar(board, 'X');
      }
      else if (symbol == 'O'){
        bigChar(board, 'O');
      }
      
      //adds it to the slot in the big board
      bigBoard(symbol);
        }
      }
  
      if (board[boardPassed][0] == board[boardPassed][4] && board[boardPassed][0] == board[boardPassed][8] && board[boardPassed][0] != '.'){
        //diag down right
        if (symbol == 'X'){
          bigChar(board, 'X');
        }
        else if (symbol == 'O'){
          bigChar(board, 'O');
        }
        
        //adds it to the slot in the big board
        bigBoard(symbol);
      }
  
      if (board[boardPassed][2] == board[boardPassed][4] && board[boardPassed][2] == board[boardPassed][6] && board[boardPassed][2] != '.') {
        //diag down left
        if (symbol == 'X'){
          bigChar(board, 'X');
        }
        else if (symbol == 'O'){
          bigChar(board, 'O');
        }
        
        // //adds it to the slot in the big board
        bigBoard(symbol);
      }
  }
  
  
  
  
  
    
    public void bigChar(char[][] board, char symbol){
      if (symbol == 'X'){
      board[boardPassed][0] = '/';
      board[boardPassed][1] = ' ';
      board[boardPassed][2] = '\\';
      board[boardPassed][3] = ' ';
      board[boardPassed][4] = 'X';
      board[boardPassed][5] = ' ';
      board[boardPassed][6] = '\\';
      board[boardPassed][7] = ' ';
      board[boardPassed][8] = '/';
      }
  
      else if (symbol == 'O'){
        board[boardPassed][0] = '\\';
        board[boardPassed][1] = '—';
        board[boardPassed][2] = '/';
        board[boardPassed][3] = '|';
        board[boardPassed][4] = ' ';
        board[boardPassed][5] = '|';
        board[boardPassed][6] = '/';
        board[boardPassed][7] = '—';
        board[boardPassed][8] = '\\';
      }
    }
  
  
  
  
  
    
    public void bigBoard(char symbol){
      //add big chars if person wins to whatever board calls the win method and whatever symbol its currently at
      switch(boardPassed){
        case 0:
          bigBoardValues[0] = symbol;
          break;
        case 1:
          bigBoardValues[1] = symbol;
          break;
        case 2:
          bigBoardValues[2] = symbol;
          break;
        case 3:
          bigBoardValues[3] = symbol;
          break;
        case 4:
          bigBoardValues[4] = symbol;
          break;
        case 5:
          bigBoardValues[5] = symbol;
          break;
        case 6:
          bigBoardValues[6] = symbol;
          break;
        case 7:
          bigBoardValues[7] = symbol;
          break;
        case 8:
          bigBoardValues[8] = symbol;
          break;
      }
    }
  
  
  
  
  
    
    public char bigBoardCheck(){
      char charReturn = ' ';
      for (int i = 0; i < 3; i++){
        //vertical check
        if (bigBoardValues[i] == bigBoardValues[i+3] &&     bigBoardValues[i] == bigBoardValues[i+6] && bigBoardValues[i] != ' '){
          if (bigBoardValues[i] == 'X'){
            charReturn = 'X';
          }
          else{
            charReturn = 'O';
          }
        }
      }
  
      for (int i = 0; i < 9; i += 3){
        //horizontal check
        if (bigBoardValues[i] == bigBoardValues[i+1] &&     bigBoardValues[i] == bigBoardValues[i+2] && bigBoardValues[i] != ' '){
         if (bigBoardValues[i] == 'X'){
           charReturn = 'X';
         }
        else{
          charReturn = 'O';
          }
        }
    }
  
      if (bigBoardValues[0] == bigBoardValues[4] && bigBoardValues[0] == bigBoardValues[8] && bigBoardValues[0] != ' '){
        if (bigBoardValues[0] == 'X'){
          charReturn = 'X';
         }
        else{
          charReturn = 'O';
          }
    }
  
      if (bigBoardValues[2] == bigBoardValues[4] && bigBoardValues[2] == bigBoardValues[6] && bigBoardValues[2] != ' '){
        if (bigBoardValues[2] == 'X'){
          charReturn = 'X';
         }
        else{
          charReturn = 'O';
          }
    }
      return charReturn;
  }
  
  public boolean isTie(char[][] board){
    boolean goBack = true;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
          if (board[i][j] == '.') {
            goBack = false;
          }
      }
    }
    return goBack;
  }
    
    public char getBigBoardValue(int index){
      return bigBoardValues[index];
    }
  
  
    
  }
  