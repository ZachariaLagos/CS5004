package problem2;

/**
 * Accessory decorator that adds both attack and defense bonuses.
 */
public class AccessoryDecorator extends CharacterDecorator implements Equipment {
    private String accessoryName;
    private int attackBonus;
    private int defenseBonus;

    public AccessoryDecorator(GameCharacter character, String accessoryName, int attackBonus, int defenseBonus) {
        super(character);
        this.accessoryName = accessoryName;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.attack = decoratedCharacter.getAttack() + attackBonus;
        this.defense = decoratedCharacter.getDefense() + defenseBonus;
    }

    @Override
    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public GameCharacter clone() {
        GameCharacter clonedBase = decoratedCharacter.clone();
        return new AccessoryDecorator(clonedBase, this.accessoryName, this.attackBonus, this.defenseBonus);
    }

    @Override
    public String getDescription() {
        return decoratedCharacter.getDescription() + " + " + accessoryName + 
               "(+" + attackBonus + " ATK, +" + defenseBonus + " DEF)";
    }
}
