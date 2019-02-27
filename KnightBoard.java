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

  private boolean solveH(int xcor, int ycor, int moveNumber) throws IllegalStateException, IllegalArgumentException{

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
          board[nextX][nextY] = -1;
        }
      }
    }
        return false;

  }

  public boolean solve(int startingRow, int startingCol) throws IllegalStateException, IllegalArgumentException{
    if (solveH(startingRow, startingCol, 1)){
      System.out.println("Yay!");
      return true;
    } else {
      System.out.println("Yeah as always");
      return false;
    }
  }


  public static void main(String[] args) {
    KnightBoard test = new KnightBoard(5,5);
    test.solve(2,2);
    System.out.println(test.toString());
  }


}
