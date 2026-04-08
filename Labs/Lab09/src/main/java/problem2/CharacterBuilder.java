package problem2;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for creating characters step by step.
 * This is the Builder Pattern - separating construction from representation.
 */
public class CharacterBuilder {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private String characterType;
    private List<Equipment> equipment;

    private String weaponType;
    private String elementType;
    private int mana;
    private int precision;
    private String bowType;

    public CharacterBuilder() {
        this.equipment = new ArrayList<>();
        this.health = 100;
        this.attack = 10;
        this.defense = 10;
        this.mana = 100;
        this.precision = 85;
    }

    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CharacterBuilder setHealth(int health) {
        this.health = health;
        return this;
    }

    public CharacterBuilder setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public CharacterBuilder setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public CharacterBuilder setCharacterType(String type) {
        this.characterType = type;
        return this;
    }

    public CharacterBuilder setWeaponType(String weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public CharacterBuilder setElementType(String elementType) {
        this.elementType = elementType;
        return this;
    }

    public CharacterBuilder setMana(int mana) {
        this.mana = mana;
        return this;
    }

    public CharacterBuilder setPrecision(int precision) {
        this.precision = precision;
        return this;
    }

    public CharacterBuilder setBowType(String bowType) {
        this.bowType = bowType;
        return this;
    }

    public CharacterBuilder addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
        return this;
    }

    public GameCharacter build() {
        GameCharacter character;

        switch (characterType != null ? characterType.toLowerCase() : "warrior") {
            case "mage":
                character = new Mage(
                    name != null ? name : "Unknown Mage",
                    health, attack, defense,
                    elementType != null ? elementType : "Fire",
                    mana
                );
                break;
            case "archer":
                character = new Archer(
                    name != null ? name : "Unknown Archer",
                    health, attack, defense,
                    precision,
                    bowType != null ? bowType : "Longbow"
                );
                break;
            case "warrior":
            default:
                character = new Warrior(
                    name != null ? name : "Unknown Warrior",
                    health, attack, defense,
                    weaponType != null ? weaponType : "Sword"
                );
                break;
        }

        for (Equipment equip : equipment) {
            character.setAttack(character.getAttack() + equip.getAttackBonus());
            character.setDefense(character.getDefense() + equip.getDefenseBonus());
        }

        return character;
    }

    public CharacterBuilder reset() {
        this.name = null;
        this.health = 100;
        this.attack = 10;
        this.defense = 10;
        this.characterType = null;
        this.equipment.clear();
        this.weaponType = null;
        this.elementType = null;
        this.mana = 100;
        this.precision = 85;
        this.bowType = null;
        return this;
    }
}
