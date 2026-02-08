package chess;

public class Pawn extends ChessPiece {
  public Pawn(int row, int column, Color color) {
    super(row, column, color);

    // White pawns cannot be created in row 0, Black pawns cannot be created in row 7
    if ((color == Color.WHITE && row == 0) || (color == Color.BLACK && row == 7)) {
      throw new IllegalArgumentException("Pawns cannot be created in their royal row");
    }
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

    // Pawn must move in same column for forward movement
    if (this.column != col) {
      return false;
    }

    if (color == Color.WHITE) {
      // White pawns move "up" (increasing row number)
      int rowDiff = row - this.row;

      // Can move one square forward
      if (rowDiff == 1) {
        return true;
      }

      // Can move two squares forward from starting position (row 1)
      if (this.row == 1 && rowDiff == 2) {
        return true;
      }
    } else { // BLACK
      // Black pawns move "down" (decreasing row number)
      int rowDiff = this.row - row;

      // Can move one square forward
      if (rowDiff == 1) {
        return true;
      }

      // Can move two squares forward from starting position (row 6)
      if (this.row == 6 && rowDiff == 2) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (piece == null) {
      return false;
    }

    // Can only kill pieces of opposite color
    if (this.color == piece.getColor()) {
      return false;
    }

    int targetRow = piece.getRow();
    int targetCol = piece.getColumn();

    // Pawn kills diagonally, one square forward
    int colDiff = Math.abs(this.column - targetCol);

    if (colDiff != 1) {
      return false;
    }

    if (color == Color.WHITE) {
      // White pawn kills diagonally upward (one row up)
      return targetRow == this.row + 1;
    } else { // BLACK
      // Black pawn kills diagonally downward (one row down)
      return targetRow == this.row - 1;
    }
  }
}