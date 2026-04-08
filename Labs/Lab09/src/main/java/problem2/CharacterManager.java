package problem2;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager class that demonstrates creating, cloning, and decorating characters.
 */
public class CharacterManager {
    private List<GameCharacter> characters;

    public CharacterManager() {
        this.characters = new ArrayList<>();
    }

    public void addCharacter(GameCharacter character) {
        characters.add(character);
    }

    public List<GameCharacter> getCharacters() {
        return characters;
    }

    public void displayAllCharacters() {
        System.out.println("\n--- All Characters in Party ---");
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getDescription());
        }
    }

    public GameCharacter cloneAndModify(GameCharacter original, String newName) {
        GameCharacter cloned = original.clone();
        cloned.setName(newName);
        return cloned;
    }
}
