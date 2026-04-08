package problem2;

/**
 * Factory for creating pre-configured character templates.
 * This is the Factory Method Pattern for character archetypes.
 */
public class CharacterFactory {

    public static GameCharacter createCharacter(String role, String name) {
        switch (role.toLowerCase()) {
            case "tank":
                return createTank(name);
            case "dps":
                return createDPS(name);
            case "support":
                return createSupport(name);
            case "assassin":
                return createAssassin(name);
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    private static GameCharacter createTank(String name) {
        return new CharacterBuilder()
            .setCharacterType("warrior")
            .setName(name)
            .setHealth(200)
            .setAttack(12)
            .setDefense(30)
            .setWeaponType("Shield & Mace")
            .build();
    }

    private static GameCharacter createDPS(String name) {
        return new CharacterBuilder()
            .setCharacterType("mage")
            .setName(name)
            .setHealth(70)
            .setAttack(35)
            .setDefense(5)
            .setElementType("Lightning")
            .setMana(150)
            .build();
    }

    private static GameCharacter createSupport(String name) {
        return new CharacterBuilder()
            .setCharacterType("mage")
            .setName(name)
            .setHealth(90)
            .setAttack(15)
            .setDefense(15)
            .setElementType("Holy")
            .setMana(200)
            .build();
    }

    private static GameCharacter createAssassin(String name) {
        return new CharacterBuilder()
            .setCharacterType("archer")
            .setName(name)
            .setHealth(85)
            .setAttack(28)
            .setDefense(10)
            .setPrecision(98)
            .setBowType("Crossbow")
            .build();
    }

    public static GameCharacter createBasicCharacter(String characterClass, String name) {
        return new CharacterBuilder()
            .setCharacterType(characterClass)
            .setName(name)
            .build();
    }
}
