package chess;

public class Knight extends ChessPiece {
  public Knight(int row, int column, Color color) {
    super(row, column, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (!isValidPosition(row, col)) {
      return false;
    }

    // Cannot move to same position
    if (this.row == row && this.column == col) {
      return false;
    }

    int rowDiff = Math.abs(this.row - row);
    int colDiff = Math.abs(this.column - col);

    // Knight moves in L shape: (2,1) or (1,2)
    return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
  }
}