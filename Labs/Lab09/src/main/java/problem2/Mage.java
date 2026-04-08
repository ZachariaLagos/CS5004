package problem2;

/**
 * Mage character - high attack, low defense.
 */
public class Mage extends GameCharacter {
    private String elementType;
    private int mana;

    public Mage() {
        super();
        this.characterClass = "Mage";
        this.health = 80;
        this.attack = 25;
        this.defense = 8;
        this.elementType = "Fire";
        this.mana = 100;
    }

    public Mage(String name, int health, int attack, int defense, String elementType, int mana) {
        super(name, health, attack, defense);
        this.characterClass = "Mage";
        this.elementType = elementType;
        this.mana = mana;
    }

    public String getElementType() { return elementType; }
    public void setElementType(String elementType) { this.elementType = elementType; }
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }

    @Override
    public GameCharacter clone() {
        return new Mage(this.name, this.health, this.attack, this.defense, this.elementType, this.mana);
    }

    @Override
    public String getDescription() {
        return "Mage '" + name + "' channeling " + elementType + " magic" +
               " [HP:" + health + " ATK:" + attack + " DEF:" + defense + " MP:" + mana + "]";
    }
}
