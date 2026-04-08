package problem2;

/**
 * Abstract decorator base class for character equipment.
 * This is the Decorator Pattern - adding responsibilities dynamically.
 */
public abstract class CharacterDecorator extends GameCharacter {
    protected GameCharacter decoratedCharacter;

    public CharacterDecorator(GameCharacter character) {
        this.decoratedCharacter = character;
        this.name = character.getName();
        this.health = character.getHealth();
        this.attack = character.getAttack();
        this.defense = character.getDefense();
        this.characterClass = character.getCharacterClass();
    }

    @Override
    public abstract GameCharacter clone();

    @Override
    public abstract String getDescription();
}
