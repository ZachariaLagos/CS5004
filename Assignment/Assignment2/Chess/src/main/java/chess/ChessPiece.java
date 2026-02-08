package chess;

public abstract class ChessPiece implements ChessPieceContract {
  protected int row;
  protected int column;
  protected Color color;

  public ChessPiece(int row, int column, Color color) {
    if (row < 0 || row > 7 || column < 0 || column > 7) {
      throw new IllegalArgumentException("Position out of bounds");
    }
    this.row = row;
    this.column = column;
    this.color = color;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getColumn() {
    return column;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public abstract boolean canMove(int row, int col);

  @Override
  public boolean canKill(ChessPiece piece) {
    if (piece == null) {
      return false;
    }
    // Can only kill pieces of opposite color
    if (this.color == piece.getColor()) {
      return false;
    }
    // Can kill if can move to that position
    return canMove(piece.getRow(), piece.getColumn());
  }

  protected boolean isValidPosition(int row, int col) {
    return row >= 0 && row <= 7 && col >= 0 && col <= 7;
  }
}