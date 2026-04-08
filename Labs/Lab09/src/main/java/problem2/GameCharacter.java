package problem2;

/**
 * Abstract base class for all game characters.
 * Implements Cloneable for the Prototype Pattern.
 */
public abstract class GameCharacter implements Cloneable {
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected String characterClass;

    public GameCharacter() {
        this.name = "Unknown";
        this.health = 100;
        this.attack = 10;
        this.defense = 10;
    }

    public GameCharacter(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public String getCharacterClass() { return characterClass; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setHealth(int health) { this.health = health; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }

    @Override
    public abstract GameCharacter clone();

    public abstract String getDescription();

    public void displayStats() {
        System.out.println("  Name: " + name);
        System.out.println("  Class: " + characterClass);
        System.out.println("  Health: " + health);
        System.out.println("  Attack: " + attack);
        System.out.println("  Defense: " + defense);
    }
}
