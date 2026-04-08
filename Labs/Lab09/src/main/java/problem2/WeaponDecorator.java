package problem2;

/**
 * Weapon decorator that adds attack bonus to a character.
 */
public class WeaponDecorator extends CharacterDecorator implements Equipment {
    private String weaponName;
    private int attackBonus;

    public WeaponDecorator(GameCharacter character, String weaponName, int attackBonus) {
        super(character);
        this.weaponName = weaponName;
        this.attackBonus = attackBonus;
        this.attack = decoratedCharacter.getAttack() + attackBonus;
    }

    @Override
    public int getAttackBonus() {
        return attackBonus;
    }

    @Override
    public int getDefenseBonus() {
        return 0;
    }

    @Override
    public GameCharacter clone() {
        GameCharacter clonedBase = decoratedCharacter.clone();
        return new WeaponDecorator(clonedBase, this.weaponName, this.attackBonus);
    }

    @Override
    public String getDescription() {
        return decoratedCharacter.getDescription() + " + " + weaponName + "(+" + attackBonus + " ATK)";
    }

    public String getWeaponName() {
        return weaponName;
    }
}
