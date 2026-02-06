/**
 * Represents a Shoe with their details--kind, color, brand, size
 */
public class Shoe {
  private Kind kind;
  private Color color;
  private Brand brand;
  private double size;

  /**
   Creates a new shoe given the four attributes
   * @param kind the shoe's category according to its usage
   * @param color the Shoe's color
   * @param brand the Shoe's brand
   * @param size the Shoe's size (US-sized)
   */
  public Shoe (Kind kind, Color color, Brand brand, double size){
    if (brand == Brand.NIKE && kind == Kind.DRESS) {
      throw new IllegalArgumentException("NIKE does not sell dress shoes");
    }
    if (brand == Brand.UGG && color == Color.BLACK) {
      throw new IllegalArgumentException("UGG does not sell black shoes");
    }
    this.kind = kind;
    this.color = color;
    this.brand = brand;
    this.size = size;
  }

  /**
   * Returns the kind of this shoe.
   * @return the kind
   */
  public Kind getKind() { return this.kind; }

  /**
   * Returns the color of this shoe.
   * @return the color
   */
  public Color getColor() { return this.color; }

  /**
   * Returns the brand of this shoe.
   * @return the brand
   */
  public Brand getBrand() { return this.brand; }

  /**
   * Returns the size of this shoe.
   * @return the size
   */
  public double getSize() { return this.size;}

  /**
   * get a summary of this shoe, converting fields in Enum to correct string
   * @return the summary of the shoe
   */

  public String toString() {

    String kindString;
    switch (kind) {
      case SNEAKER:
        kindString = "sneaker";
        break;
      case BOOT:
        kindString = "boot";
        break;
      case DRESS:
        kindString = "dress shoe";
        break;
      case SLIPPER:
        kindString = "slipper";
        break;
      default:
        kindString = "unknown kind";
    }

    String colorString;
    switch (color) {
      case WHITE:
        colorString = "white";
      break;
      case BLACK:
        colorString = "black";
      break;
      case RED:
      case BLUE:
        colorString = "dramatic";
      break;
    default:
      colorString = "neutral";
  }

    String brandString;
    switch (brand) {
      case NIKE:
        brandString = "Nike";
        break;
      case ADIDAS:
        brandString = "Adidas";
        break;
      case UGG:
        brandString = "UGG";
        break;
      case ASICS:
        brandString = "Asics";
        break;
    default:
      brandString = "unknown brand";
    }

    return  "The "+ kindString +" is of " + brandString
        + " (" + colorString + " color" + ", size " + size + ")";}
}
