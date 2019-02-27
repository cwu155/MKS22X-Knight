import java.util.*;
public class KnightBoard{

  private int[][] board;
  private int xCor[] = {2, 1, -1, -2, -2, -1, 1, 2};
  private int yCor[] = {1, 2, 2, 1, -1, -2, -2, -1};
  private int nextX, nextY;
  //public static int testing[][] = new int[6][6];

  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    for (int i = 0; i < startingRows; i++) {
      for (int j = 0; j < startingCols; j++) {
        board[i][j] = 0;
      }
    }
  }

  //Not modified yet for testing purposes?
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
    return ((x >= 0 && y >= 0) && (x < board.length && y < board[0].length)
            && (board[x][y] == 0));
  }

  private boolean solveH(int xcor, int ycor, int moveNumber){

    if (xcor < 0 || ycor < 0 || xcor > board.length || ycor > board[0].length) {
        throw new IllegalArgumentException();
    }
    if (moveNumber == (board.length * board[0].length)){
      return true;
    }

      for (int i = 0; i < board.length; i++){
        nextX = xcor + xCor[i];
        nextY = ycor + yCor[i];

        if (inBounds(nextX, nextY, board)){
          board[nextX][nextY] = moveNumber;

          if (solveH(nextX, nextY, moveNumber + 1)){
            return true;
          } else {
            board[nextX][nextY] = 0;
          }
        }
      }
        return false;

    }

  public boolean solve(int startingRow, int startingCol){
    if (!(inBounds(startingRow, startingCol, board))) {
        throw new IllegalArgumentException();
    }

    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[0].length; j++){
        if (board[i][j] != 0){
          throw new IllegalStateException();
        }
      }
    }

    board[startingRow][startingCol] = 1;

    if (solveH(startingRow, startingCol, 1)){
      System.out.println("Yay!");
      return true;
    } else {
      System.out.println("big sad");
      return false;
    }
  }

  public int countH(int row, int col, int moveNumber, int count){
    if (row < 0 || col < 0 || row > board.length || col > board[0].length) {
        throw new IllegalArgumentException();
    }

    if (moveNumber == board.length * board[0].length) {
        return 1;
    }

      for (int i = 0; i < board.length; i++){

        if (moveNumber == board.length * board[0].length) {
          return 1;
        }
        nextX = row + xCor[i];
        nextY = col + yCor[i];

        if (inBounds(nextX, nextY, board)){
          board[nextX][nextY] = moveNumber + 1;
          count += countH(nextX, nextY, 0, moveNumber + 1);
          //board[nextX][nextY] = 0;
      }
    }
      return count;
    }

  public int countSolutions(int startingRow, int startingCol){
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) {
        throw new IllegalArgumentException();
    }

    board[startingRow][startingCol] = 1;
    return countH(startingRow, startingCol, 1, 0);
  }


  public static void main(String[] args) {

    KnightBoard test = new KnightBoard(5,5);
    test.countSolutions(0,0);
    //System.out.println(test.toString());
  }


}
