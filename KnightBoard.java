public class KnightBoard{

  private int[][] board;

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


  public static void main(String[] args) {
    KnightBoard test = new KnightBoard(4,5);
    System.out.println(test.toString());
  }


}
