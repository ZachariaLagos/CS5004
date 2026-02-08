package chess;

public class Bishop extends ChessPiece {
  public Bishop(int row, int column, Color color) {
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

    // Bishop moves diagonally: absolute difference in rows equals absolute difference in columns
    return Math.abs(this.row - row) == Math.abs(this.column - col);
  }
}