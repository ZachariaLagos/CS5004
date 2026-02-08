package chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessPieceTest {

  @BeforeEach
  void setUp() {
    // Setup code can go here if needed
  }

  // ========== Getter Tests ==========

  @Test
  void getRow() {
    Bishop bishop = new Bishop(3, 5, Color.BLACK);
    assertEquals(3, bishop.getRow());

    Queen queen = new Queen(7, 2, Color.WHITE);
    assertEquals(7, queen.getRow());
  }

  @Test
  void getColumn() {
    Bishop bishop = new Bishop(3, 5, Color.BLACK);
    assertEquals(5, bishop.getColumn());

    Rook rook = new Rook(4, 6, Color.WHITE);
    assertEquals(6, rook.getColumn());
  }

  @Test
  void getColor() {
    Bishop bishop = new Bishop(3, 5, Color.BLACK);
    assertEquals(Color.BLACK, bishop.getColor());

    Knight knight = new Knight(2, 3, Color.WHITE);
    assertEquals(Color.WHITE, knight.getColor());
  }

  // ========== Bishop Tests ==========

  @Test
  void testBishopMoveDiagonal() {
    Bishop bishop = new Bishop(3, 3, Color.WHITE);
    assertTrue(bishop.canMove(5, 5));
    assertTrue(bishop.canMove(1, 1));
    assertTrue(bishop.canMove(0, 6));
    assertTrue(bishop.canMove(6, 0));
  }

  @Test
  void testBishopCannotMoveNotDiagonal() {
    Bishop bishop = new Bishop(3, 3, Color.WHITE);
    assertFalse(bishop.canMove(3, 5));
    assertFalse(bishop.canMove(5, 3));
    assertFalse(bishop.canMove(3, 3));
  }

  @Test
  void testBishopKillDifferentColor() {
    Bishop whiteBishop = new Bishop(3, 3, Color.WHITE);
    Bishop blackBishop = new Bishop(5, 5, Color.BLACK);
    assertTrue(whiteBishop.canKill(blackBishop));
  }

  @Test
  void testBishopCannotKillSameColor() {
    Bishop whiteBishop1 = new Bishop(3, 3, Color.WHITE);
    Bishop whiteBishop2 = new Bishop(5, 5, Color.WHITE);
    assertFalse(whiteBishop1.canKill(whiteBishop2));
  }

  // ========== Knight Tests ==========

  @Test
  void testKnightMoveLShape() {
    Knight knight = new Knight(4, 4, Color.WHITE);
    assertTrue(knight.canMove(6, 5));
    assertTrue(knight.canMove(6, 3));
    assertTrue(knight.canMove(2, 5));
    assertTrue(knight.canMove(2, 3));
    assertTrue(knight.canMove(5, 6));
    assertTrue(knight.canMove(3, 6));
    assertTrue(knight.canMove(5, 2));
    assertTrue(knight.canMove(3, 2));
  }

  @Test
  void testKnightCannotMoveNotLShape() {
    Knight knight = new Knight(4, 4, Color.WHITE);
    assertFalse(knight.canMove(5, 5));
    assertFalse(knight.canMove(4, 6));
    assertFalse(knight.canMove(4, 4));
  }

  @Test
  void testKnightKillDifferentColor() {
    Knight whiteKnight = new Knight(4, 4, Color.WHITE);
    Pawn blackPawn = new Pawn(6, 5, Color.BLACK);
    assertTrue(whiteKnight.canKill(blackPawn));
  }

  // ========== Queen Tests ==========

  @Test
  void testQueenMoveAllDirections() {
    Queen queen = new Queen(4, 4, Color.WHITE);
    // Horizontal
    assertTrue(queen.canMove(4, 7));
    assertTrue(queen.canMove(4, 0));
    // Vertical
    assertTrue(queen.canMove(7, 4));
    assertTrue(queen.canMove(0, 4));
    // Diagonal
    assertTrue(queen.canMove(7, 7));
    assertTrue(queen.canMove(1, 1));
  }

  @Test
  void testQueenCannotMoveInvalid() {
    Queen queen = new Queen(4, 4, Color.WHITE);
    assertFalse(queen.canMove(5, 6));
    assertFalse(queen.canMove(4, 4));
  }

  @Test
  void testQueenKillDifferentColor() {
    Queen whiteQueen = new Queen(4, 4, Color.WHITE);
    Rook blackRook = new Rook(4, 7, Color.BLACK);
    assertTrue(whiteQueen.canKill(blackRook));
  }

  @Test
  void testQueenCannotKillSameColor() {
    Queen whiteQueen = new Queen(4, 4, Color.WHITE);
    Rook whiteRook = new Rook(4, 7, Color.WHITE);
    assertFalse(whiteQueen.canKill(whiteRook));
  }

  // ========== King Tests ==========

  @Test
  void testKingMoveOneSquareAllDirections() {
    King king = new King(4, 4, Color.WHITE);
    assertTrue(king.canMove(5, 4));
    assertTrue(king.canMove(3, 4));
    assertTrue(king.canMove(4, 5));
    assertTrue(king.canMove(4, 3));
    assertTrue(king.canMove(5, 5));
    assertTrue(king.canMove(3, 3));
    assertTrue(king.canMove(5, 3));
    assertTrue(king.canMove(3, 5));
  }

  @Test
  void testKingCannotMoveTwoSquares() {
    King king = new King(4, 4, Color.WHITE);
    assertFalse(king.canMove(6, 4));
    assertFalse(king.canMove(4, 6));
    assertFalse(king.canMove(4, 4));
  }

  @Test
  void testKingKillDifferentColor() {
    King whiteKing = new King(4, 4, Color.WHITE);
    Pawn blackPawn = new Pawn(5, 4, Color.BLACK);
    assertTrue(whiteKing.canKill(blackPawn));
  }

  // ========== Rook Tests ==========

  @Test
  void testRookMoveHorizontalAndVertical() {
    Rook rook = new Rook(4, 4, Color.WHITE);
    assertTrue(rook.canMove(4, 0));
    assertTrue(rook.canMove(4, 7));
    assertTrue(rook.canMove(0, 4));
    assertTrue(rook.canMove(7, 4));
  }

  @Test
  void testRookCannotMoveDiagonal() {
    Rook rook = new Rook(4, 4, Color.WHITE);
    assertFalse(rook.canMove(5, 5));
    assertFalse(rook.canMove(3, 3));
    assertFalse(rook.canMove(4, 4));
  }

  @Test
  void testRookKillDifferentColor() {
    Rook whiteRook = new Rook(4, 4, Color.WHITE);
    Knight blackKnight = new Knight(4, 7, Color.BLACK);
    assertTrue(whiteRook.canKill(blackKnight));
  }

  // ========== Pawn Tests ==========

  @Test
  void testWhitePawnMoveForwardOne() {
    Pawn pawn = new Pawn(3, 3, Color.WHITE);
    assertTrue(pawn.canMove(4, 3));
  }

  @Test
  void testWhitePawnMoveTwoFromStartPosition() {
    Pawn pawn = new Pawn(1, 3, Color.WHITE);
    assertTrue(pawn.canMove(2, 3));
    assertTrue(pawn.canMove(3, 3));
  }

  @Test
  void testWhitePawnCannotMoveTwoAfterStart() {
    Pawn pawn = new Pawn(3, 3, Color.WHITE);
    assertTrue(pawn.canMove(4, 3));
    assertFalse(pawn.canMove(5, 3));
  }

  @Test
  void testBlackPawnMoveForwardOne() {
    Pawn pawn = new Pawn(4, 3, Color.BLACK);
    assertTrue(pawn.canMove(3, 3));
  }

  @Test
  void testBlackPawnMoveTwoFromStartPosition() {
    Pawn pawn = new Pawn(6, 3, Color.BLACK);
    assertTrue(pawn.canMove(5, 3));
    assertTrue(pawn.canMove(4, 3));
  }

  @Test
  void testBlackPawnCannotMoveTwoAfterStart() {
    Pawn pawn = new Pawn(4, 3, Color.BLACK);
    assertTrue(pawn.canMove(3, 3));
    assertFalse(pawn.canMove(2, 3));
  }

  @Test
  void testPawnCannotMoveBackward() {
    Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
    assertFalse(whitePawn.canMove(2, 3));

    Pawn blackPawn = new Pawn(4, 3, Color.BLACK);
    assertFalse(blackPawn.canMove(5, 3));
  }

  @Test
  void testPawnCannotMoveSideways() {
    Pawn pawn = new Pawn(3, 3, Color.WHITE);
    assertFalse(pawn.canMove(3, 4));
    assertFalse(pawn.canMove(3, 2));
  }

  @Test
  void testWhitePawnKillDiagonal() {
    Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
    Pawn blackPawn1 = new Pawn(4, 4, Color.BLACK);
    Pawn blackPawn2 = new Pawn(4, 2, Color.BLACK);

    assertTrue(whitePawn.canKill(blackPawn1));
    assertTrue(whitePawn.canKill(blackPawn2));
  }

  @Test
  void testBlackPawnKillDiagonal() {
    Pawn blackPawn = new Pawn(4, 3, Color.BLACK);
    Pawn whitePawn1 = new Pawn(3, 4, Color.WHITE);
    Pawn whitePawn2 = new Pawn(3, 2, Color.WHITE);

    assertTrue(blackPawn.canKill(whitePawn1));
    assertTrue(blackPawn.canKill(whitePawn2));
  }

  @Test
  void testPawnCannotKillStraight() {
    Pawn whitePawn = new Pawn(3, 3, Color.WHITE);
    Pawn blackPawn = new Pawn(4, 3, Color.BLACK);

    assertFalse(whitePawn.canKill(blackPawn));
  }

  @Test
  void testPawnCannotKillSameColor() {
    Pawn whitePawn1 = new Pawn(3, 3, Color.WHITE);
    Pawn whitePawn2 = new Pawn(4, 4, Color.WHITE);

    assertFalse(whitePawn1.canKill(whitePawn2));
  }

  // ========== canMove() General Tests ==========

  @Test
  void canMove() {
    // Test pieces can move to valid positions
    Bishop bishop = new Bishop(0, 0, Color.WHITE);
    assertTrue(bishop.canMove(7, 7));

    Knight knight = new Knight(0, 0, Color.WHITE);
    assertTrue(knight.canMove(2, 1));

    Queen queen = new Queen(3, 3, Color.BLACK);
    assertTrue(queen.canMove(3, 7));
    assertTrue(queen.canMove(7, 3));
    assertTrue(queen.canMove(6, 6));
  }

  // ========== canKill() General Tests ==========

  @Test
  void canKill() {
    // Test pieces can kill different color
    Queen whiteQueen = new Queen(4, 4, Color.WHITE);
    Bishop blackBishop = new Bishop(4, 7, Color.BLACK);
    assertTrue(whiteQueen.canKill(blackBishop));

    // Test pieces cannot kill same color
    Queen whiteQueen2 = new Queen(4, 0, Color.WHITE);
    assertFalse(whiteQueen.canKill(whiteQueen2));

    // Test null
    assertFalse(whiteQueen.canKill(null));
  }

  // ========== isValidPosition() Tests ==========

  @Test
  void isValidPosition() {
    // Valid positions do not throw error
    assertDoesNotThrow(() -> new Bishop(0, 0, Color.WHITE));
    assertDoesNotThrow(() -> new Bishop(7, 7, Color.BLACK));
    assertDoesNotThrow(() -> new Knight(3, 4, Color.WHITE));
  }

  @Test
  void testInvalidPositionNegativeRow() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Bishop(-1, 3, Color.WHITE);
    });
  }

  @Test
  void testInvalidPositionRowTooLarge() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Bishop(8, 3, Color.WHITE);
    });
  }

  @Test
  void testInvalidPositionNegativeColumn() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Knight(3, -1, Color.WHITE);
    });
  }

  @Test
  void testInvalidPositionColumnTooLarge() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Knight(3, 8, Color.WHITE);
    });
  }

  @Test
  void testWhitePawnCannotCreateInRow0() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Pawn(0, 3, Color.WHITE);
    });
  }

  @Test
  void testBlackPawnCannotCreateInRow7() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Pawn(7, 3, Color.BLACK);
    });
  }

  // ========== Edge Case Tests ==========

  @Test
  void testPieceCannotMoveToSamePosition() {
    Bishop bishop = new Bishop(3, 3, Color.WHITE);
    assertFalse(bishop.canMove(3, 3));

    Knight knight = new Knight(4, 4, Color.BLACK);
    assertFalse(knight.canMove(4, 4));

    Queen queen = new Queen(5, 5, Color.WHITE);
    assertFalse(queen.canMove(5, 5));
  }

  @Test
  void testPieceCannotMoveOutOfBoard() {
    Bishop bishop = new Bishop(0, 0, Color.WHITE);
    assertFalse(bishop.canMove(-1, -1));
    assertFalse(bishop.canMove(8, 8));

    Rook rook = new Rook(7, 7, Color.BLACK);
    assertFalse(rook.canMove(8, 7));
    assertFalse(rook.canMove(7, 8));
  }
}