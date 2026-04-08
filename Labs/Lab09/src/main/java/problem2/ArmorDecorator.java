package problem2;

/**
 * Armor decorator that adds defense bonus to a character.
 */
public class ArmorDecorator extends CharacterDecorator implements Equipment {
    private String armorName;
    private int defenseBonus;

    public ArmorDecorator(GameCharacter character, String armorName, int defenseBonus) {
        super(character);
        this.armorName = armorName;
        this.defenseBonus = defenseBonus;
        this.defense = decoratedCharacter.getDefense() + defenseBonus;
    }

    @Override
    public int getAttackBonus() {
        return 0;
    }

    @Override
    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public GameCharacter clone() {
        GameCharacter clonedBase = decoratedCharacter.clone();
        return new ArmorDecorator(clonedBase, this.armorName, this.defenseBonus);
    }

    @Override
    public String getDescription() {
        return decoratedCharacter.getDescription() + " + " + armorName + "(+" + defenseBonus + " DEF)";
    }

    public String getArmorName() {
        return armorName;
    }
}
