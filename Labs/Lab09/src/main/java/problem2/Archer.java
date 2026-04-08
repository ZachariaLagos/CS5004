package problem2;

/**
 * Archer character - balanced stats with high precision.
 */
public class Archer extends GameCharacter {
    private int precision;
    private String bowType;

    public Archer() {
        super();
        this.characterClass = "Archer";
        this.health = 100;
        this.attack = 20;
        this.defense = 12;
        this.precision = 90;
        this.bowType = "Longbow";
    }

    public Archer(String name, int health, int attack, int defense, int precision, String bowType) {
        super(name, health, attack, defense);
        this.characterClass = "Archer";
        this.precision = precision;
        this.bowType = bowType;
    }

    public int getPrecision() { return precision; }
    public void setPrecision(int precision) { this.precision = precision; }
    public String getBowType() { return bowType; }
    public void setBowType(String bowType) { this.bowType = bowType; }

    @Override
    public GameCharacter clone() {
        return new Archer(this.name, this.health, this.attack, this.defense, this.precision, this.bowType);
    }

    @Override
    public String getDescription() {
        return "Archer '" + name + "' with " + bowType +
               " [HP:" + health + " ATK:" + attack + " DEF:" + defense + " PREC:" + precision + "%]";
    }
}
