package chess;

public class King extends ChessPiece {
  public King(int row, int column, Color color) {
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

    // King can move one square in any direction
    return rowDiff <= 1 && colDiff <= 1;
  }
}