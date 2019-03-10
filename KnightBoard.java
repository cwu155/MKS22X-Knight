import java.util.*;
public class KnightBoard{

  private int[][] board;
  private int xCor[] = {2, 1, -1, -2, -2, -1, 1, 2};
  private int yCor[] = {1, 2, 2, 1, -1, -2, -2, -1};
  private int nextX, nextY;


  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    for (int i = 0; i < startingRows; i++) {
      for (int j = 0; j < startingCols; j++) {
        board[i][j] = 0;
      }
    }
  }

  public String toString(){
    String result = "";
      for (int i = 0; i < board.length; i++){
        for (int j = 0; j < board[i].length; j++){

          if (j == board[i].length - 1){
            result += board[i][j] + "\n";
            } else {
            result += board[i][j] + " ";
            }
          }
        }
      return result;
    }

  private boolean inBounds(int x, int y, int[][] board){
    // return ((x >= 0 && y >= 0) && (x < board.length && y < board[0].length)
    //         && (board[x][y] == 0));
    return (x >= 0 && x < board.length && y >= 0 &&
                y < board.length && board[x][y] == 0);
  }

  public boolean solve(int startingRow,int startingCol){
    // if(startingRow < 0||startingCol < 0||startingRow >= board.length||startingCol >= board[0].length){
    //   throw new IllegalArgumentException();
    // }
    // for(int i = 0;i < board.length; i++){
    //   for(int j = 0;j < board.length;j++){
    //     if(board[i][j] != 0){
    //       throw new IllegalStateException();
    //     }
    //   }
    // }
    // return solveH(startingRow, startingCol, 1);


    board[startingRow][startingCol] = 1;

        if (!solveH(startingRow, startingCol ,1)) {
            System.out.println(board);
            return false;
        } else {
          System.out.println(board);
          return true;
        }
  }

  private boolean solveH(int xcor, int ycor, int moveNumber){

    if (moveNumber == (board.length * board[0].length)){
      return true;
    }

    for (int i = 0; i < 8; i++) {
            nextX = xcor + xCor[i];
            nextY = ycor + yCor[i];

            if (nextX < board.length && nextY < board.length
                && nextX >= 0 && nextY >= 0 && board[nextX][nextY] == 0){

                board[nextX][nextY] = moveNumber;
                //debug
                //System.out.println("Movenumber: " + moveNumber);

                if (solveH(nextX, nextY, moveNumber + 1))
                  return true;
                else

                //debug
                //System.out.println("help");
                  board[xcor + xCor[i]][ycor + yCor[i]] = 0;
              }
            }
      return false;
  }

  private int countH(int row, int col, int moveNumber, int count){

    if(moveNumber == board.length * board[0].length){
      return count + 1;
    }

    for (int i = 0; i < 8; i++){
      nextX = row + xCor[i];
      nextY = col + yCor[i];

      if (nextX < board.length && nextY < board.length &&
         nextX >= 0 && nextY >= 0 && board[nextX][nextY] == 0){

           board[row][col] = moveNumber;

           count = countH(row + xCor[i], col + yCor[i], moveNumber + 1, count);
           board[row + xCor[i]][col + yCor[i]] = 0;
      }
    }

    board[row][col] = 0;
    return count;
  }

  public int countSolutions(int startingRow, int startingCol){
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) {
        throw new IllegalArgumentException();
    }

    return countH(startingRow, startingCol, 1, 0);
  }


  public static void main(String[] args) {


    KnightBoard test = new KnightBoard(5,5);
    System.out.println(test.countSolutions(2,2));
    System.out.println(test);
  }


}
