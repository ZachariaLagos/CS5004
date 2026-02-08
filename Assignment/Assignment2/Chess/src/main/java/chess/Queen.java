package chess;

public class Queen extends ChessPiece {
  public Queen(int row, int column, Color color) {
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

    // Queen moves horizontally, vertically, or diagonally
    boolean horizontal = this.row == row;
    boolean vertical = this.column == col;
    boolean diagonal = Math.abs(this.row - row) == Math.abs(this.column - col);

    return horizontal || vertical || diagonal;
  }
}