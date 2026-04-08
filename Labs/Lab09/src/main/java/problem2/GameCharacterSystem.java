package problem2;

/**
 * CS5004 Spring 2026 - Lab 9: Design Patterns
 * Problem 2: Game Character Creation System
 * 
 * Main class demonstrating all design patterns:
 * - Builder Pattern: CharacterBuilder
 * - Prototype Pattern: Cloneable characters
 * - Decorator Pattern: Equipment decorators
 * - Factory Method Pattern: CharacterFactory
 */
public class GameCharacterSystem {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║    CS5004 Lab 9 - Problem 2: Game Character Creation System  ║");
        System.out.println("║                    Design Patterns Demo                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        CharacterManager manager = new CharacterManager();

        // ====================================================================
        // DEMONSTRATE FACTORY METHOD PATTERN
        // ====================================================================
        System.out.println("=== FACTORY METHOD PATTERN: CharacterFactory ===");
        System.out.println("Creating characters with pre-configured templates...\n");

        GameCharacter tank = CharacterFactory.createCharacter("tank", "Thorin");
        GameCharacter dps = CharacterFactory.createCharacter("dps", "Merlin");
        GameCharacter support = CharacterFactory.createCharacter("support", "Elena");
        GameCharacter assassin = CharacterFactory.createCharacter("assassin", "Shadow");

        System.out.println("Tank Template:");
        tank.displayStats();
        System.out.println("\nDPS Template:");
        dps.displayStats();
        System.out.println("\nSupport Template:");
        support.displayStats();
        System.out.println("\nAssassin Template:");
        assassin.displayStats();

        manager.addCharacter(tank);
        manager.addCharacter(dps);
        manager.addCharacter(support);
        manager.addCharacter(assassin);

        // ====================================================================
        // DEMONSTRATE BUILDER PATTERN
        // ====================================================================
        System.out.println("\n\n=== BUILDER PATTERN: CharacterBuilder ===");
        System.out.println("Building custom character step-by-step...\n");

        CharacterBuilder builder = new CharacterBuilder();
        
        GameCharacter customWarrior = builder
            .setCharacterType("warrior")
            .setName("Ragnar the Bold")
            .setHealth(180)
            .setAttack(22)
            .setDefense(25)
            .setWeaponType("Battle Axe")
            .build();

        System.out.println("Custom Built Warrior:");
        customWarrior.displayStats();
        System.out.println("  Description: " + customWarrior.getDescription());

        GameCharacter customMage = builder.reset()
            .setCharacterType("mage")
            .setName("Gandora the Wise")
            .setHealth(75)
            .setAttack(30)
            .setDefense(10)
            .setElementType("Arcane")
            .setMana(180)
            .build();

        System.out.println("\nCustom Built Mage:");
        customMage.displayStats();
        System.out.println("  Description: " + customMage.getDescription());

        manager.addCharacter(customWarrior);
        manager.addCharacter(customMage);

        // ====================================================================
        // DEMONSTRATE PROTOTYPE PATTERN (Cloning)
        // ====================================================================
        System.out.println("\n\n=== PROTOTYPE PATTERN: Character Cloning ===");
        System.out.println("Cloning existing characters and modifying them...\n");

        GameCharacter tankClone = manager.cloneAndModify(tank, "Thorin Jr.");
        tankClone.setHealth(tankClone.getHealth() + 20);
        
        System.out.println("Original Tank:");
        System.out.println("  " + tank.getDescription());
        System.out.println("\nCloned and Modified Tank:");
        System.out.println("  " + tankClone.getDescription());
        System.out.println("  (Health increased by 20)");

        GameCharacter mageClone = dps.clone();
        mageClone.setName("Merlin's Apprentice");
        mageClone.setAttack(dps.getAttack() - 10);

        System.out.println("\nOriginal DPS Mage:");
        System.out.println("  " + dps.getDescription());
        System.out.println("\nCloned Apprentice Mage:");
        System.out.println("  " + mageClone.getDescription());
        System.out.println("  (Attack reduced - still learning!)");

        System.out.println("\nVerifying clones are separate objects:");
        System.out.println("  tank == tankClone: " + (tank == tankClone) + " (should be false)");
        System.out.println("  tank.getName(): " + tank.getName());
        System.out.println("  tankClone.getName(): " + tankClone.getName());

        manager.addCharacter(tankClone);
        manager.addCharacter(mageClone);

        // ====================================================================
        // DEMONSTRATE DECORATOR PATTERN
        // ====================================================================
        System.out.println("\n\n=== DECORATOR PATTERN: Equipment Decorators ===");
        System.out.println("Adding equipment to enhance character abilities...\n");

        GameCharacter archer = CharacterFactory.createBasicCharacter("archer", "Legolas");
        System.out.println("Base Character:");
        System.out.println("  " + archer.getDescription());
        System.out.println("  Attack: " + archer.getAttack() + ", Defense: " + archer.getDefense());

        GameCharacter archerWithWeapon = new WeaponDecorator(archer, "Elven Bow", 15);
        System.out.println("\nAfter adding Weapon (Elven Bow +15 ATK):");
        System.out.println("  " + archerWithWeapon.getDescription());
        System.out.println("  Attack: " + archerWithWeapon.getAttack() + ", Defense: " + archerWithWeapon.getDefense());

        GameCharacter archerFullyEquipped = new ArmorDecorator(archerWithWeapon, "Mithril Vest", 12);
        System.out.println("\nAfter adding Armor (Mithril Vest +12 DEF):");
        System.out.println("  " + archerFullyEquipped.getDescription());
        System.out.println("  Attack: " + archerFullyEquipped.getAttack() + ", Defense: " + archerFullyEquipped.getDefense());

        GameCharacter archerLegendary = new AccessoryDecorator(archerFullyEquipped, "Ring of Power", 5, 5);
        System.out.println("\nAfter adding Accessory (Ring of Power +5 ATK, +5 DEF):");
        System.out.println("  " + archerLegendary.getDescription());
        System.out.println("  Attack: " + archerLegendary.getAttack() + ", Defense: " + archerLegendary.getDefense());

        System.out.println("\n--- Decorating the Tank ---");
        GameCharacter equippedTank = new WeaponDecorator(tank, "Legendary Hammer", 10);
        equippedTank = new ArmorDecorator(equippedTank, "Dragon Scale Armor", 20);
        equippedTank = new AccessoryDecorator(equippedTank, "Amulet of Vitality", 0, 10);
        
        System.out.println("Fully Equipped Tank:");
        System.out.println("  " + equippedTank.getDescription());
        System.out.println("  Final Stats - Attack: " + equippedTank.getAttack() + ", Defense: " + equippedTank.getDefense());

        manager.addCharacter(archerLegendary);
        manager.addCharacter(equippedTank);

        // ====================================================================
        // DEMONSTRATE CLONING DECORATED CHARACTERS
        // ====================================================================
        System.out.println("\n\n=== CLONING DECORATED CHARACTERS ===");
        System.out.println("Demonstrating that decorated characters can also be cloned...\n");

        GameCharacter clonedLegendaryArcher = archerLegendary.clone();
        clonedLegendaryArcher.setName("Legolas Clone");

        System.out.println("Original Legendary Archer:");
        System.out.println("  " + archerLegendary.getDescription());
        System.out.println("\nCloned Legendary Archer:");
        System.out.println("  " + clonedLegendaryArcher.getDescription());

        // ====================================================================
        // FINAL SUMMARY
        // ====================================================================
        System.out.println("\n\n=== ALL CHARACTERS IN PARTY ===");
        manager.displayAllCharacters();

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    PATTERNS DEMONSTRATED                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. BUILDER: CharacterBuilder - step-by-step construction     ║");
        System.out.println("║  2. PROTOTYPE: clone() - character duplication                ║");
        System.out.println("║  3. DECORATOR: WeaponDecorator, ArmorDecorator - stat mods    ║");
        System.out.println("║  4. FACTORY METHOD: CharacterFactory - role templates         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
