package problem2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * CS5004 Spring 2026 - Lab 9: Design Patterns
 * Problem 2: Game Character Creation System - Unit Tests
 */
public class GameCharacterSystemTest {

    // ========================================================================
    // Warrior Tests
    // ========================================================================

    @Test
    public void testWarriorCreation() {
        Warrior warrior = new Warrior();
        assertNotNull(warrior);
        assertEquals("Warrior", warrior.getCharacterClass());
        assertEquals(150, warrior.getHealth());
        assertEquals(15, warrior.getAttack());
        assertEquals(20, warrior.getDefense());
    }

    @Test
    public void testWarriorParameterizedConstructor() {
        Warrior warrior = new Warrior("Thor", 200, 25, 30, "Hammer");
        assertEquals("Thor", warrior.getName());
        assertEquals(200, warrior.getHealth());
        assertEquals(25, warrior.getAttack());
        assertEquals(30, warrior.getDefense());
        assertEquals("Hammer", warrior.getWeaponType());
    }

    @Test
    public void testWarriorGetDescription() {
        Warrior warrior = new Warrior("Thor", 200, 25, 30, "Hammer");
        String desc = warrior.getDescription();
        assertTrue(desc.contains("Thor"));
        assertTrue(desc.contains("Hammer"));
        assertTrue(desc.contains("200"));
    }

    // ========================================================================
    // Mage Tests
    // ========================================================================

    @Test
    public void testMageCreation() {
        Mage mage = new Mage();
        assertNotNull(mage);
        assertEquals("Mage", mage.getCharacterClass());
        assertEquals(80, mage.getHealth());
        assertEquals(25, mage.getAttack());
        assertEquals(8, mage.getDefense());
        assertEquals(100, mage.getMana());
    }

    @Test
    public void testMageParameterizedConstructor() {
        Mage mage = new Mage("Gandalf", 100, 30, 10, "Ice", 150);
        assertEquals("Gandalf", mage.getName());
        assertEquals(100, mage.getHealth());
        assertEquals(30, mage.getAttack());
        assertEquals(10, mage.getDefense());
        assertEquals("Ice", mage.getElementType());
        assertEquals(150, mage.getMana());
    }

    @Test
    public void testMageGetDescription() {
        Mage mage = new Mage("Gandalf", 100, 30, 10, "Ice", 150);
        String desc = mage.getDescription();
        assertTrue(desc.contains("Gandalf"));
        assertTrue(desc.contains("Ice"));
        assertTrue(desc.contains("150"));
    }

    // ========================================================================
    // Archer Tests
    // ========================================================================

    @Test
    public void testArcherCreation() {
        Archer archer = new Archer();
        assertNotNull(archer);
        assertEquals("Archer", archer.getCharacterClass());
        assertEquals(100, archer.getHealth());
        assertEquals(20, archer.getAttack());
        assertEquals(12, archer.getDefense());
        assertEquals(90, archer.getPrecision());
    }

    @Test
    public void testArcherParameterizedConstructor() {
        Archer archer = new Archer("Legolas", 110, 22, 14, 95, "Elven Bow");
        assertEquals("Legolas", archer.getName());
        assertEquals(110, archer.getHealth());
        assertEquals(22, archer.getAttack());
        assertEquals(14, archer.getDefense());
        assertEquals(95, archer.getPrecision());
        assertEquals("Elven Bow", archer.getBowType());
    }

    @Test
    public void testArcherGetDescription() {
        Archer archer = new Archer("Legolas", 110, 22, 14, 95, "Elven Bow");
        String desc = archer.getDescription();
        assertTrue(desc.contains("Legolas"));
        assertTrue(desc.contains("Elven Bow"));
        assertTrue(desc.contains("95"));
    }

    // ========================================================================
    // Prototype Pattern Tests - Clone
    // ========================================================================

    @Test
    public void testWarriorClone() {
        Warrior original = new Warrior("Thor", 200, 25, 30, "Hammer");
        GameCharacter cloned = original.clone();

        assertNotSame(original, cloned);
        assertTrue(cloned instanceof Warrior);
        assertEquals(original.getName(), cloned.getName());
        assertEquals(original.getHealth(), cloned.getHealth());
        assertEquals(original.getAttack(), cloned.getAttack());
        assertEquals(original.getDefense(), cloned.getDefense());
    }

    @Test
    public void testWarriorCloneIsIndependent() {
        Warrior original = new Warrior("Thor", 200, 25, 30, "Hammer");
        GameCharacter cloned = original.clone();

        cloned.setName("Clone Thor");
        cloned.setHealth(999);

        assertEquals("Thor", original.getName());
        assertEquals(200, original.getHealth());
        assertEquals("Clone Thor", cloned.getName());
        assertEquals(999, cloned.getHealth());
    }

    @Test
    public void testMageClone() {
        Mage original = new Mage("Gandalf", 100, 30, 10, "Ice", 150);
        GameCharacter cloned = original.clone();

        assertNotSame(original, cloned);
        assertTrue(cloned instanceof Mage);
        assertEquals(original.getName(), cloned.getName());
        assertEquals(original.getHealth(), cloned.getHealth());
    }

    @Test
    public void testArcherClone() {
        Archer original = new Archer("Legolas", 110, 22, 14, 95, "Elven Bow");
        GameCharacter cloned = original.clone();

        assertNotSame(original, cloned);
        assertTrue(cloned instanceof Archer);
        assertEquals(original.getName(), cloned.getName());
        assertEquals(original.getHealth(), cloned.getHealth());
    }

    // ========================================================================
    // Builder Pattern Tests - CharacterBuilder
    // ========================================================================

    @Test
    public void testBuilderCreateWarrior() {
        CharacterBuilder builder = new CharacterBuilder();
        GameCharacter character = builder
            .setCharacterType("warrior")
            .setName("Builder Warrior")
            .setHealth(180)
            .setAttack(20)
            .setDefense(25)
            .build();

        assertNotNull(character);
        assertTrue(character instanceof Warrior);
        assertEquals("Builder Warrior", character.getName());
        assertEquals(180, character.getHealth());
        assertEquals(20, character.getAttack());
        assertEquals(25, character.getDefense());
    }

    @Test
    public void testBuilderCreateMage() {
        CharacterBuilder builder = new CharacterBuilder();
        GameCharacter character = builder
            .setCharacterType("mage")
            .setName("Builder Mage")
            .setHealth(75)
            .setAttack(30)
            .setDefense(8)
            .setElementType("Lightning")
            .setMana(200)
            .build();

        assertNotNull(character);
        assertTrue(character instanceof Mage);
        assertEquals("Builder Mage", character.getName());
        assertEquals(75, character.getHealth());
    }

    @Test
    public void testBuilderCreateArcher() {
        CharacterBuilder builder = new CharacterBuilder();
        GameCharacter character = builder
            .setCharacterType("archer")
            .setName("Builder Archer")
            .setHealth(95)
            .setAttack(22)
            .setDefense(12)
            .setPrecision(92)
            .setBowType("Crossbow")
            .build();

        assertNotNull(character);
        assertTrue(character instanceof Archer);
        assertEquals("Builder Archer", character.getName());
    }

    @Test
    public void testBuilderReset() {
        CharacterBuilder builder = new CharacterBuilder();
        builder.setName("First Character")
               .setCharacterType("warrior")
               .setHealth(200);

        builder.reset();

        GameCharacter character = builder
            .setCharacterType("mage")
            .setName("Second Character")
            .build();

        assertTrue(character instanceof Mage);
        assertEquals("Second Character", character.getName());
        assertEquals(100, character.getHealth());
    }

    @Test
    public void testBuilderMethodChaining() {
        CharacterBuilder builder = new CharacterBuilder();
        assertSame(builder, builder.setName("Test"));
        assertSame(builder, builder.setHealth(100));
        assertSame(builder, builder.setAttack(10));
        assertSame(builder, builder.setDefense(10));
        assertSame(builder, builder.setCharacterType("warrior"));
    }

    // ========================================================================
    // Decorator Pattern Tests - WeaponDecorator
    // ========================================================================

    @Test
    public void testWeaponDecoratorCreation() {
        Warrior warrior = new Warrior("Test", 100, 20, 15, "Sword");
        WeaponDecorator decorated = new WeaponDecorator(warrior, "Magic Sword", 10);

        assertNotNull(decorated);
        assertEquals(30, decorated.getAttack());
        assertEquals(15, decorated.getDefense());
    }

    @Test
    public void testWeaponDecoratorGetDescription() {
        Warrior warrior = new Warrior("Test", 100, 20, 15, "Sword");
        WeaponDecorator decorated = new WeaponDecorator(warrior, "Magic Sword", 10);

        String desc = decorated.getDescription();
        assertTrue(desc.contains("Magic Sword"));
        assertTrue(desc.contains("+10 ATK"));
    }

    @Test
    public void testWeaponDecoratorImplementsEquipment() {
        Warrior warrior = new Warrior();
        WeaponDecorator decorated = new WeaponDecorator(warrior, "Sword", 10);

        assertTrue(decorated instanceof Equipment);
        assertEquals(10, decorated.getAttackBonus());
        assertEquals(0, decorated.getDefenseBonus());
    }

    // ========================================================================
    // Decorator Pattern Tests - ArmorDecorator
    // ========================================================================

    @Test
    public void testArmorDecoratorCreation() {
        Warrior warrior = new Warrior("Test", 100, 20, 15, "Sword");
        ArmorDecorator decorated = new ArmorDecorator(warrior, "Steel Plate", 12);

        assertNotNull(decorated);
        assertEquals(20, decorated.getAttack());
        assertEquals(27, decorated.getDefense());
    }

    @Test
    public void testArmorDecoratorGetDescription() {
        Warrior warrior = new Warrior("Test", 100, 20, 15, "Sword");
        ArmorDecorator decorated = new ArmorDecorator(warrior, "Steel Plate", 12);

        String desc = decorated.getDescription();
        assertTrue(desc.contains("Steel Plate"));
        assertTrue(desc.contains("+12 DEF"));
    }

    @Test
    public void testArmorDecoratorImplementsEquipment() {
        Warrior warrior = new Warrior();
        ArmorDecorator decorated = new ArmorDecorator(warrior, "Armor", 15);

        assertTrue(decorated instanceof Equipment);
        assertEquals(0, decorated.getAttackBonus());
        assertEquals(15, decorated.getDefenseBonus());
    }

    // ========================================================================
    // Decorator Pattern Tests - AccessoryDecorator
    // ========================================================================

    @Test
    public void testAccessoryDecoratorCreation() {
        Warrior warrior = new Warrior("Test", 100, 20, 15, "Sword");
        AccessoryDecorator decorated = new AccessoryDecorator(warrior, "Ring of Power", 5, 5);

        assertNotNull(decorated);
        assertEquals(25, decorated.getAttack());
        assertEquals(20, decorated.getDefense());
    }

    @Test
    public void testAccessoryDecoratorImplementsEquipment() {
        Warrior warrior = new Warrior();
        AccessoryDecorator decorated = new AccessoryDecorator(warrior, "Ring", 5, 5);

        assertTrue(decorated instanceof Equipment);
        assertEquals(5, decorated.getAttackBonus());
        assertEquals(5, decorated.getDefenseBonus());
    }

    // ========================================================================
    // Decorator Pattern Tests - Stacking Decorators
    // ========================================================================

    @Test
    public void testStackedDecorators() {
        Archer archer = new Archer("Legolas", 100, 20, 12, 90, "Bow");

        GameCharacter equipped = new WeaponDecorator(archer, "Elven Bow", 15);
        equipped = new ArmorDecorator(equipped, "Mithril Vest", 10);
        equipped = new AccessoryDecorator(equipped, "Ring of Agility", 5, 5);

        assertEquals(40, equipped.getAttack());
        assertEquals(27, equipped.getDefense());
    }

    @Test
    public void testDecoratedCharacterClone() {
        Archer archer = new Archer("Legolas", 100, 20, 12, 90, "Bow");
        GameCharacter equipped = new WeaponDecorator(archer, "Elven Bow", 15);

        GameCharacter cloned = equipped.clone();

        assertNotSame(equipped, cloned);
        assertEquals(equipped.getAttack(), cloned.getAttack());
        assertEquals(equipped.getDefense(), cloned.getDefense());
    }

    // ========================================================================
    // Factory Method Pattern Tests - CharacterFactory
    // ========================================================================

    @Test
    public void testFactoryCreateTank() {
        GameCharacter tank = CharacterFactory.createCharacter("tank", "Tank Test");

        assertNotNull(tank);
        assertTrue(tank instanceof Warrior);
        assertEquals("Tank Test", tank.getName());
        assertEquals(200, tank.getHealth());
        assertEquals(30, tank.getDefense());
    }

    @Test
    public void testFactoryCreateDPS() {
        GameCharacter dps = CharacterFactory.createCharacter("dps", "DPS Test");

        assertNotNull(dps);
        assertTrue(dps instanceof Mage);
        assertEquals("DPS Test", dps.getName());
        assertEquals(35, dps.getAttack());
    }

    @Test
    public void testFactoryCreateSupport() {
        GameCharacter support = CharacterFactory.createCharacter("support", "Support Test");

        assertNotNull(support);
        assertTrue(support instanceof Mage);
        assertEquals("Support Test", support.getName());
    }

    @Test
    public void testFactoryCreateAssassin() {
        GameCharacter assassin = CharacterFactory.createCharacter("assassin", "Assassin Test");

        assertNotNull(assassin);
        assertTrue(assassin instanceof Archer);
        assertEquals("Assassin Test", assassin.getName());
    }

    @Test
    public void testFactoryCaseInsensitive() {
        GameCharacter tank1 = CharacterFactory.createCharacter("TANK", "Test");
        GameCharacter tank2 = CharacterFactory.createCharacter("Tank", "Test");
        GameCharacter tank3 = CharacterFactory.createCharacter("tank", "Test");

        assertTrue(tank1 instanceof Warrior);
        assertTrue(tank2 instanceof Warrior);
        assertTrue(tank3 instanceof Warrior);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryInvalidRole() {
        CharacterFactory.createCharacter("invalid", "Test");
    }

    @Test
    public void testFactoryCreateBasicCharacter() {
        GameCharacter warrior = CharacterFactory.createBasicCharacter("warrior", "Basic Warrior");
        GameCharacter mage = CharacterFactory.createBasicCharacter("mage", "Basic Mage");
        GameCharacter archer = CharacterFactory.createBasicCharacter("archer", "Basic Archer");

        assertTrue(warrior instanceof Warrior);
        assertTrue(mage instanceof Mage);
        assertTrue(archer instanceof Archer);
    }

    // ========================================================================
    // CharacterManager Tests
    // ========================================================================

    @Test
    public void testCharacterManagerCreation() {
        CharacterManager manager = new CharacterManager();
        assertNotNull(manager);
        assertTrue(manager.getCharacters().isEmpty());
    }

    @Test
    public void testCharacterManagerAddCharacter() {
        CharacterManager manager = new CharacterManager();
        Warrior warrior = new Warrior();
        manager.addCharacter(warrior);

        assertEquals(1, manager.getCharacters().size());
        assertSame(warrior, manager.getCharacters().get(0));
    }

    @Test
    public void testCharacterManagerCloneAndModify() {
        CharacterManager manager = new CharacterManager();
        Warrior original = new Warrior("Original", 100, 20, 15, "Sword");

        GameCharacter cloned = manager.cloneAndModify(original, "Cloned");

        assertNotSame(original, cloned);
        assertEquals("Original", original.getName());
        assertEquals("Cloned", cloned.getName());
    }

    // ========================================================================
    // GameCharacter Base Class Tests
    // ========================================================================

    @Test
    public void testGameCharacterSetters() {
        Warrior warrior = new Warrior();

        warrior.setName("New Name");
        warrior.setHealth(999);
        warrior.setAttack(50);
        warrior.setDefense(40);

        assertEquals("New Name", warrior.getName());
        assertEquals(999, warrior.getHealth());
        assertEquals(50, warrior.getAttack());
        assertEquals(40, warrior.getDefense());
    }

    @Test
    public void testGameCharacterImplementsCloneable() {
        Warrior warrior = new Warrior();
        assertTrue(warrior instanceof Cloneable);
    }

    // ========================================================================
    // Integration Tests
    // ========================================================================

    @Test
    public void testFullWorkflow() {
        GameCharacter tank = CharacterFactory.createCharacter("tank", "TestTank");

        GameCharacter tankClone = tank.clone();
        tankClone.setName("TankClone");

        GameCharacter equippedClone = new WeaponDecorator(tankClone, "Legendary Axe", 20);
        equippedClone = new ArmorDecorator(equippedClone, "Dragon Armor", 25);

        CharacterManager manager = new CharacterManager();
        manager.addCharacter(tank);
        manager.addCharacter(equippedClone);

        assertEquals(2, manager.getCharacters().size());
        assertNotEquals(tank.getAttack(), equippedClone.getAttack());
        assertTrue(equippedClone.getDescription().contains("Legendary Axe"));
    }

    @Test
    public void testBuilderWithFactory() {
        CharacterBuilder builder = new CharacterBuilder();
        GameCharacter custom = builder
            .setCharacterType("warrior")
            .setName("Custom Tank")
            .setHealth(250)
            .setAttack(15)
            .setDefense(35)
            .build();

        GameCharacter factoryTank = CharacterFactory.createCharacter("tank", "Factory Tank");

        assertTrue(custom instanceof Warrior);
        assertTrue(factoryTank instanceof Warrior);
        assertEquals(250, custom.getHealth());
        assertEquals(200, factoryTank.getHealth());
    }
}
