package chess;

public class Rook extends ChessPiece {
  public Rook(int row, int column, Color color) {
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

    // Rook moves horizontally or vertically
    return this.row == row || this.column == col;
  }
}