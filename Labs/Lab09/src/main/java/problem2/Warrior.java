package problem2;

/**
 * Warrior character - high health and defense.
 */
public class Warrior extends GameCharacter {
    private String weaponType;

    public Warrior() {
        super();
        this.characterClass = "Warrior";
        this.health = 150;
        this.attack = 15;
        this.defense = 20;
        this.weaponType = "Sword";
    }

    public Warrior(String name, int health, int attack, int defense, String weaponType) {
        super(name, health, attack, defense);
        this.characterClass = "Warrior";
        this.weaponType = weaponType;
    }

    public String getWeaponType() { return weaponType; }
    public void setWeaponType(String weaponType) { this.weaponType = weaponType; }

    @Override
    public GameCharacter clone() {
        return new Warrior(this.name, this.health, this.attack, this.defense, this.weaponType);
    }

    @Override
    public String getDescription() {
        return "Warrior '" + name + "' wielding a " + weaponType + 
               " [HP:" + health + " ATK:" + attack + " DEF:" + defense + "]";
    }
}
