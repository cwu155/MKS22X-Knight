import java.util.*;
public class KnightBoard{

  public static int[][] board;
  public static int[][] moves;
  public static int xCor[] = {2, 1, -1, -2, -2, -1, 1, 2};
  public static int yCor[] = {1, 2, 2, 1, -1, -2, -2, -1};
  public static int nextX, nextY, addX, addY;
  public static ArrayList<Cell> sortedMoves = new ArrayList<Cell>();

  public class Cell implements Comparable<Cell>{
    public int xcor, ycor, outcomes;

    public Cell (int x, int y, int n){
      xcor = x;
      ycor = y;
      outcomes = n;
    }

    public int getX(){
      return xcor;
    }

    public int getY(){
      return ycor;
    }

    public int getOutcomes(){
      return outcomes;
    }

    public int compareTo(Cell other){
      if (this.getOutcomes() >= other.getOutcomes()){ return 1; }
      if (this.getOutcomes() <= other.getOutcomes()){ return -1; }
      return 0;
    }
  }

  public static int assignValue(int xcor, int ycor, int rows, int cols){
    int value = 0;
    if (xcor == 0 || xcor == rows-1){ value += 1; }
    if (ycor == 0 || ycor == cols-1){ value += 1; }
    if (xcor-1 <= 0 || rows-1-xcor <= 1){ value += 1; }
    if (ycor-1 <= 0 || cols-1-ycor <= 1){ value += 1; }
    return value;
  }

  public void initMoves(int startingRows, int startingCols){
    moves = new int[startingRows][startingCols];

    for (int i = 0; i < startingRows; i++) {
      for (int j = 0; j < startingCols; j++) {
        if (assignValue(i, j, startingRows, startingCols) == 4){moves[i][j] = 2;}
        if (assignValue(i, j, startingRows, startingCols) == 3){moves[i][j] = 3;}
        if (assignValue(i, j, startingRows, startingCols) == 2){moves[i][j] = 4;}
        if (assignValue(i, j, startingRows, startingCols) == 1){moves[i][j] = 6;}
        if (assignValue(i, j, startingRows, startingCols) == 0){moves[i][j] = 8;}
      }
    }
  }

  public KnightBoard(int startingRows,int startingCols){
    initMoves(startingRows, startingCols);
    board = new int[startingRows][startingCols];
      for (int i = 0; i < board.length; i++){
        for (int j = 0; j < board[0].length; j++){
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

  public boolean solve(int startingRow,int startingCol){

    board[startingRow][startingCol] = 1;

        if (!solveH(startingRow, startingCol ,1)) {
            System.out.println(board);
            return false;
        } else {
          System.out.println(board);
          return true;
        }
  }

  public void sortMoves(int xcor, int ycor, List<Cell> sortedMoves){
    for (int k = 0; k < 8; k++){
      addX = xcor + xCor[k];
      addY = ycor + yCor[k];
      if (addX < board.length && addY < board[0].length
          && addX >= 0 && addY >= 0 && board[addX][addY] == 0)
      sortedMoves.add(new Cell(addX, addY, moves[addX][addY]));
    }

    Collections.sort(sortedMoves);
  }



  private boolean solveH(int xcor, int ycor, int moveNumber){

    if (moveNumber == (moves.length * moves[0].length)){
      return true;
    }

    sortMoves(xcor, ycor, sortedMoves);

    for (int i = 0; i < sortedMoves.size(); i++) {
            nextX = sortedMoves.get(i).getX();
            nextY = sortedMoves.get(i).getY();

            //debug purposes??
            //System.out.println(sortedMoves.get(i).getX());
            //System.out.println(sortedMoves.get(i).getY());

            if (nextX < board.length && nextY < board[0].length
                && nextX >= 0 && nextY >= 0 && board[nextX][nextY] == 0){ //validates if in boundaries

                board[nextX][nextY] = moveNumber;

                //System.out.println("Movenumber: " + moveNumber);

                if (solveH(nextX, nextY, moveNumber + 1)){
                  return true;
                } else {
                  //System.out.println("help");
                  board[sortedMoves.get(i).getX()][sortedMoves.get(i).getY()] = 0;
                }
              }
          }
      return false;
  }

  public int countSolutions(int startingRow, int startingCol){
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) {
        throw new IllegalArgumentException();
    }

    return countH(startingRow, startingCol, 1, 0);
  }


  private int countH(int row, int col, int moveNumber, int count){

    if(moveNumber == board.length * board[0].length){
      return count + 1;
    }

    for (int i = 0; i < 8; i++){
      nextX = row + xCor[i];
      nextY = col + yCor[i];

      if (nextX < board.length && nextY < board[0].length &&
         nextX >= 0 && nextY >= 0 && board[nextX][nextY] == 0){

           board[row][col] = moveNumber;

           count = countH(row + xCor[i], col + yCor[i], moveNumber + 1, count);
           board[row + xCor[i]][col + yCor[i]] = 0;
      }
    }
    board[row][col] = 0;
    return count;
  }

  public static void main(String[] args) {

    KnightBoard test = new KnightBoard(5,4);
    System.out.println(test.countSolutions(0,0));
    System.out.println(test);
  }

}
